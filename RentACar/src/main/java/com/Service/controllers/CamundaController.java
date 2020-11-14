package com.Service.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.form.FormField;
import org.camunda.bpm.engine.form.TaskFormData;
import org.camunda.bpm.engine.runtime.ActivityInstance;
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

import com.Service.camundaDto.FieldDto;
import com.Service.camundaDto.FormFieldsDto;
import com.Service.camundaDto.TaskDto;
import com.Service.dto.OfferDto;
import com.Service.dto.OrderDto;
import com.Service.repositories.AppuserRepository;

import model.Appuser;

@RequestMapping(value = "api/camunda")
@RestController
public class CamundaController {
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	TaskService taskService;
	
	@Autowired
	FormService formService;
	
	private String clientIdProcessInstance = "";
	
	@GetMapping(path = "/startClientProcess", produces = "application/json")
    public @ResponseBody FormFieldsDto startClientProcess() {
		
		ProcessInstance client = runtimeService.startProcessInstanceByKey("Client_ProcessId");
		clientIdProcessInstance = client.getProcessInstanceId();
		Task task = taskService.createTaskQuery().processInstanceId(client.getId()).list().get(0);
		TaskFormData tfd = formService.getTaskFormData(task.getId());
		List<FormField> properties = tfd.getFormFields();
		
        return new FormFieldsDto(task.getId(),task.getProcessInstanceId(), properties);
    }
	
	@GetMapping(path = "/get/{taskId}", produces = "application/json")
    public @ResponseBody FormFieldsDto getTaskForm(@PathVariable String taskId) {

		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
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
		String processInstanceId = task.getProcessInstanceId();
		runtimeService.setVariable(processInstanceId, "customerName", order.getCustomerName());
		runtimeService.setVariable(processInstanceId, "customerSurame", order.getCustomerSurname());
		runtimeService.setVariable(processInstanceId, "type", order.getType());
		runtimeService.setVariable(processInstanceId, "model", order.getModel());
		runtimeService.setVariable(processInstanceId, "dealerCompany", "Dealer company");

		formService.submitTaskForm(taskId, map);
		
		ProcessInstance pi = runtimeService.startProcessInstanceByKey("Dealer_ProcessId");
		
		runtimeService.setVariable(pi.getProcessInstanceId(), "customerName", order.getCustomerName());
		runtimeService.setVariable(pi.getProcessInstanceId(), "customerSurame", order.getCustomerSurname());
		runtimeService.setVariable(pi.getProcessInstanceId(), "type", order.getType());
		runtimeService.setVariable(pi.getProcessInstanceId(), "model", order.getModel());
		runtimeService.setVariable(pi.getProcessInstanceId(), "dealerCompany", "Dealer company");

        return new ResponseEntity<>(HttpStatus.OK);
    }
	
	@PostMapping(path = "/decide/{taskId}", produces = "application/json")
    public @ResponseBody ResponseEntity<?> acceptedOffer(@RequestBody boolean accepted, @PathVariable String taskId) {
		
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		runtimeService.setVariable(task.getProcessInstanceId(), "agree", accepted);

		HashMap<String, Object> map = new HashMap<>();
		map.put("agree", accepted);
		formService.submitTaskForm(taskId, map);
		if(accepted) {
			ProcessInstance pi = runtimeService.startProcessInstanceByKey("Blockchain_ProcessId");
		}
        return new ResponseEntity<>(HttpStatus.OK);
    }
	
	@PostMapping(path = "/getSendOfferForm/{taskId}", produces = "application/json")
    public @ResponseBody ResponseEntity<?> sendOffer(@RequestBody OfferDto offer, @PathVariable String taskId) {

		runtimeService.setVariable(clientIdProcessInstance, "price", offer.getPrice());
		runtimeService.setVariable(clientIdProcessInstance, "contractText", offer.getText());
		runtimeService.setVariable(clientIdProcessInstance, "date", new Date().toLocaleString());
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("price", offer.getPrice());
		map.put("contractText", offer.getText());
		formService.submitTaskForm(taskId, map);
		
        return new ResponseEntity<>(HttpStatus.OK);
    }
	
	@GetMapping(path = "/getTasksForUser", produces = "application/json")
    public @ResponseBody ResponseEntity<List<TaskDto>> getTaskFormForUser() {
		
		String activeUser = SecurityContextHolder.getContext().getAuthentication().getName();
		List<Task> tasks = taskService.createTaskQuery().taskAssignee(activeUser).active().list();
		
		List<TaskDto> dtos = new ArrayList<TaskDto>();
		for (Task task : tasks) {
			TaskDto t = new TaskDto(task.getId(), task.getName(), task.getAssignee());
			dtos.add(t);
		}
        return new ResponseEntity<List<TaskDto>>(dtos,  HttpStatus.OK);
    }
	
	private HashMap<String, Object> mapListToDto(List<FieldDto> list)
	{
		HashMap<String, Object> map = new HashMap<String, Object>();
		for(FieldDto temp : list) {
			map.put(temp.getFieldId(), temp.getFieldValue());
		}
		return map;
	}
}