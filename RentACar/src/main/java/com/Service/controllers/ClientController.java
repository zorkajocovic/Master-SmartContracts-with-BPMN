package com.Service.controllers;

import java.util.HashMap;
import java.util.List;

import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.form.FormField;
import org.camunda.bpm.engine.form.TaskFormData;
import org.camunda.bpm.engine.runtime.Execution;
import org.camunda.bpm.engine.runtime.ProcessInstance;
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

import com.Service.camundaDto.FormFieldsDto;
import com.Service.dto.OrderDto;

@RequestMapping(value = "api/client")
@RestController
public class ClientController {

	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	TaskService taskService;
	
	@Autowired
	FormService formService;
	
	@GetMapping(path = "/startClientProcess", produces = "application/json")
    public @ResponseBody FormFieldsDto startClientProcess() {
		
		ProcessInstance client = runtimeService.startProcessInstanceByKey("Client_ProcessId");
		Task task = taskService.createTaskQuery().processInstanceId(client.getId()).list().get(0);
		TaskFormData tfd = formService.getTaskFormData(task.getId());
		List<FormField> properties = tfd.getFormFields();
		
        return new FormFieldsDto(task.getId(),task.getProcessInstanceId(), properties);
    }
	
	@PostMapping(path = "/orderCar/{taskId}", produces = "application/json")
    public @ResponseBody ResponseEntity<?> post(@RequestBody OrderDto order, @PathVariable String taskId) {

		HashMap<String, Object> map = new HashMap<>();
		map.put("customerName", order.getCustomerName());
		map.put("customerSurname", order.getCustomerSurname());
		map.put("model", order.getModel());
		map.put("type", order.getType());
		
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		String clientPIid = task.getProcessInstanceId();
		runtimeService.setVariable(clientPIid, "customerName", order.getCustomerName());
		runtimeService.setVariable(clientPIid, "customerSurame", order.getCustomerSurname());
		runtimeService.setVariable(clientPIid, "type", order.getType());
		runtimeService.setVariable(clientPIid, "model", order.getModel());
		runtimeService.setVariable(clientPIid, "dealerCompany", "Dealer company");

		formService.submitTaskForm(taskId, map);
		return new ResponseEntity<>(HttpStatus.OK);
    }
	
	@PostMapping(path = "/decide/{taskId}", produces = "application/json")
    public @ResponseBody ResponseEntity<?> acceptedOffer(@RequestBody boolean accepted, @PathVariable String taskId) {
		
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		runtimeService.setVariable(task.getProcessInstanceId(), "agree", accepted);
		HashMap<String, Object> map = new HashMap<>();
		map.put("agree", accepted);
		formService.submitTaskForm(taskId, map);
		
        return new ResponseEntity<>(HttpStatus.OK);
    }
	
	@PostMapping(path = "/readDocuments/{taskId}", produces = "application/json")
    public @ResponseBody ResponseEntity<?> readDocuments(@RequestBody boolean read, @PathVariable String taskId) {

		HashMap<String, Object> map = new HashMap<>();
		map.put("readDocuments", read);
		formService.submitTaskForm(taskId, map);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PostMapping(path = "/readReceipt/{taskId}", produces = "application/json")
    public @ResponseBody ResponseEntity<?> readReceipt(@RequestBody boolean read, @PathVariable String taskId) {

		HashMap<String, Object> map = new HashMap<>();
		map.put("readReceipt", read);
		formService.submitTaskForm(taskId, map);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PostMapping(path = "/pay/{taskId}", produces = "application/json")
    public @ResponseBody ResponseEntity<?> pay(@RequestBody Long price, @PathVariable String taskId) {

		HashMap<String, Object> map = new HashMap<>();
		map.put("price", price);

		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		runtimeService.setVariable(task.getProcessInstanceId(), "pay", true);
		formService.submitTaskForm(taskId, map);	
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PostMapping(path = "/pickup/{taskId}", produces = "application/json")
    public @ResponseBody ResponseEntity<?> pickup(@RequestBody boolean pickedUp, @PathVariable String taskId) {

		HashMap<String, Object> map = new HashMap<>();
		map.put("collectedCar", pickedUp);
		formService.submitTaskForm(taskId, map);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
