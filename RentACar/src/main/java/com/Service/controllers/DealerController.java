package com.Service.controllers;

import java.util.Date;
import java.util.HashMap;

import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.runtime.Execution;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.Service.dto.OfferDto;

@RequestMapping(value = "api/dealer")
@RestController
public class DealerController {

	@Autowired
    RuntimeService runtimeService;
	
	@Autowired
	TaskService taskService;
	
	@Autowired
	FormService formService;
	
	@PostMapping(path = "/submitSendOfferForm/{taskId}", produces = "application/json")
    public @ResponseBody ResponseEntity<?> sendOffer(@RequestBody OfferDto offer, @PathVariable String taskId) {

		HashMap<String, Object> map = new HashMap<>();
		map.put("price", offer.getPrice());
		map.put("contractText", offer.getText());
		map.put("deposit", offer.getDeposit());
		map.put("date", new Date().toLocaleString());
		
		String activeUser = SecurityContextHolder.getContext().getAuthentication().getName();
		map.put("fullname", activeUser);

		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		runtimeService.setVariables(task.getProcessInstanceId(), map);
		formService.submitTaskForm(taskId, map);
	
        return new ResponseEntity<>(HttpStatus.OK);
    }
	
	@PostMapping(path = "/calcDamagePrice/{taskId}", produces = "application/json")
    public @ResponseBody ResponseEntity<?> calcDamagePrice(@RequestBody Long damagePrice, @PathVariable String taskId) {

		HashMap<String, Object> map = new HashMap<>();
		map.put("damagePrice", damagePrice);

		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		Long deposit = (Long) runtimeService.getVariable(task.getProcessInstanceId(), "deposit");
		Long basePrice = (Long) runtimeService.getVariable(task.getProcessInstanceId(), "price");

		Long totalPrice;
		//vec je placen depozit, dakle ostaje samo osnovica sa stetom
		if(damagePrice > deposit) 
			totalPrice = basePrice + damagePrice - deposit;
		else 
			totalPrice = basePrice - damagePrice;
		
		runtimeService.setVariable(task.getProcessInstanceId(), "totalPrice", totalPrice);
		formService.submitTaskForm(taskId, map);	
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PostMapping(path = "/sendReceipt/{taskId}", produces = "application/json")
    public @ResponseBody ResponseEntity<?> sendReceipt(@RequestBody boolean sent, @PathVariable String taskId) {

		HashMap<String, Object> map = new HashMap<>();
		map.put("sendReceipt", sent);
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		runtimeService.setVariable(task.getProcessInstanceId(), "sendReceipt", sent);
		
		formService.submitTaskForm(taskId, map);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping(path = "/confirmPayment/{taskId}", produces = "application/json")
    public @ResponseBody ResponseEntity<?> confirmPayment(@PathVariable String taskId) {

		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		String date = (String) runtimeService.getVariable(task.getProcessInstanceId(), "payedDate");
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("clientPayed", date);
		
		formService.submitTaskForm(taskId, map);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PostMapping(path = "/sendCar/{taskId}", produces = "application/json")
    public @ResponseBody ResponseEntity<?> sendCar(@RequestBody boolean sent, @PathVariable String taskId) {

		HashMap<String, Object> map = new HashMap<>();
		map.put("sendCar", sent);
		formService.submitTaskForm(taskId, map);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}	
	
	@PostMapping(path = "/readyCar/{taskId}", produces = "application/json")
    public @ResponseBody ResponseEntity<?> readyCar(@RequestBody boolean ready, @PathVariable String taskId) {

		HashMap<String, Object> map = new HashMap<>();
		map.put("readyCar", ready);
		formService.submitTaskForm(taskId, map);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PostMapping(path = "/carCheck/{taskId}", produces = "application/json")
    public @ResponseBody ResponseEntity<?> carCheck(@RequestBody boolean damaged, @PathVariable String taskId) {

		HashMap<String, Object> map = new HashMap<>();
		map.put("hasDamage", damaged);
		formService.submitTaskForm(taskId, map);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}	
}
