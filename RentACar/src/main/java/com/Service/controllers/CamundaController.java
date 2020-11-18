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
import org.camunda.bpm.engine.runtime.Execution;
import org.camunda.bpm.engine.runtime.ExecutionQuery;
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
	private String dealerIdProcessInstance = "";
	private String blockchainIdProcessInstance = "";
	
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
		String clientPIid = task.getProcessInstanceId();
		runtimeService.setVariable(clientPIid, "customerName", order.getCustomerName());
		runtimeService.setVariable(clientPIid, "customerSurame", order.getCustomerSurname());
		runtimeService.setVariable(clientPIid, "type", order.getType());
		runtimeService.setVariable(clientPIid, "model", order.getModel());
		runtimeService.setVariable(clientPIid, "dealerCompany", "Dealer company");

		formService.submitTaskForm(taskId, map);

		ProcessInstance dealerPI = runtimeService.startProcessInstanceByKey("Dealer_ProcessId");
		dealerIdProcessInstance = dealerPI.getProcessInstanceId();

		runtimeService.setVariable(dealerPI.getProcessInstanceId(), "customerName", order.getCustomerName());
		runtimeService.setVariable(dealerPI.getProcessInstanceId(), "customerSurame", order.getCustomerSurname());
		runtimeService.setVariable(dealerPI.getProcessInstanceId(), "type", order.getType());
		runtimeService.setVariable(dealerPI.getProcessInstanceId(), "model", order.getModel());
		runtimeService.setVariable(dealerPI.getProcessInstanceId(), "dealerCompany", "Dealer company");
		
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
			Execution execution = runtimeService.createExecutionQuery()
					  .processInstanceId(dealerIdProcessInstance)
					  .activityId("acceptedContract_id")
					  .singleResult();

			runtimeService.signal(execution.getId());
			String activeUser = SecurityContextHolder.getContext().getAuthentication().getName();
			runtimeService.setVariable(dealerIdProcessInstance, "fullname", activeUser);
		}
        return new ResponseEntity<>(HttpStatus.OK);
    }
	
	@PostMapping(path = "/sign/{taskId}", produces = "application/json")
    public @ResponseBody ResponseEntity<?> sign(@RequestBody String fullName, @PathVariable String taskId) {

		HashMap<String, Object> map = new HashMap<>();
		map.put("fullname", fullName);
		formService.submitTaskForm(taskId, map);
		ProcessInstance blockchainPI = runtimeService.startProcessInstanceByKey("Blockchain_ProcessId");
		blockchainIdProcessInstance = blockchainPI.getProcessInstanceId();
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PostMapping(path = "/sendDocuments/{taskId}", produces = "application/json")
    public @ResponseBody ResponseEntity<?> sendDocuments(@RequestBody boolean sent, @PathVariable String taskId) {

		HashMap<String, Object> map = new HashMap<>();
		map.put("sendDocuments", sent);
		formService.submitTaskForm(taskId, map);
		
		if(sent) {
			Execution execution = runtimeService.createExecutionQuery()
					  .processInstanceId(clientIdProcessInstance)
					  .activityId("receiveDocuments_id")
					  .singleResult();
			runtimeService.signal(execution.getId());
		}
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PostMapping(path = "/readDocuments/{taskId}", produces = "application/json")
    public @ResponseBody ResponseEntity<?> readDocuments(@RequestBody boolean read, @PathVariable String taskId) {

		HashMap<String, Object> map = new HashMap<>();
		map.put("readDocuments", read);
		formService.submitTaskForm(taskId, map);
		
		if(read) {
			Execution clientExecution = runtimeService.createExecutionQuery()
					  .processInstanceId(blockchainIdProcessInstance)
					  .activityId("clientReadDocuments_id")
					  .singleResult();
			runtimeService.signal(clientExecution.getId());
			
			Execution dealerExecution = runtimeService.createExecutionQuery()
					  .processInstanceId(dealerIdProcessInstance)
					  .activityId("readDocs_id")
					  .singleResult();
			runtimeService.signal(dealerExecution.getId());
		}
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PostMapping(path = "/readReceipt/{taskId}", produces = "application/json")
    public @ResponseBody ResponseEntity<?> readReceipt(@RequestBody boolean read, @PathVariable String taskId) {

		formService.submitTaskForm(taskId, new HashMap<>());
		
		if(read) {
			Execution blockchainExecution = runtimeService.createExecutionQuery()
					  .processInstanceId(blockchainIdProcessInstance)
					  .activityId("clientReadReceipt_id")
					  .singleResult();
			runtimeService.signal(blockchainExecution.getId());
		}
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PostMapping(path = "/pickup/{taskId}", produces = "application/json")
    public @ResponseBody ResponseEntity<?> pickup(@RequestBody boolean pickedUp, @PathVariable String taskId) {

		formService.submitTaskForm(taskId, new HashMap<>());

		if(pickedUp) {
			Execution blockchainExecution = runtimeService.createExecutionQuery()
					  .processInstanceId(blockchainIdProcessInstance)
					  .activityId("deliveryConfirmation_id")
					  .singleResult();
			runtimeService.signal(blockchainExecution.getId());
		}

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PostMapping(path = "/sendReceipt/{taskId}", produces = "application/json")
    public @ResponseBody ResponseEntity<?> sendReceipt(@RequestBody boolean sent, @PathVariable String taskId) {

		HashMap<String, Object> map = new HashMap<>();
		map.put("sendReceipt", sent);
		formService.submitTaskForm(taskId, map);
		
		if(sent) {
			Execution execution = runtimeService.createExecutionQuery()
					  .processInstanceId(clientIdProcessInstance)
					  .activityId("receiveReceipt_id")
					  .singleResult();
			runtimeService.signal(execution.getId());
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PostMapping(path = "/handOverCar/{taskId}", produces = "application/json")
    public @ResponseBody ResponseEntity<?> handOverCar(@RequestBody boolean sent, @PathVariable String taskId) {

		HashMap<String, Object> map = new HashMap<>();
		map.put("sendCar", sent);
		formService.submitTaskForm(taskId, map);
		
		if(sent) {
			Execution execution = runtimeService.createExecutionQuery()
					  .processInstanceId(clientIdProcessInstance)
					  .activityId("readyCar_id")
					  .singleResult();
			runtimeService.signal(execution.getId());
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PostMapping(path = "/pay/{taskId}", produces = "application/json")
    public @ResponseBody ResponseEntity<?> pay(@RequestBody Long price, @PathVariable String taskId) {

		HashMap<String, Object> map = new HashMap<>();
		map.put("price", price);
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		runtimeService.setVariable(task.getProcessInstanceId(), "pay", true);
		formService.submitTaskForm(taskId, map);

		Execution blockchainExecution = runtimeService.createExecutionQuery()
				  .processInstanceId(blockchainIdProcessInstance)
				  .activityId("clientPayed_id")
				  .singleResult();
		runtimeService.signal(blockchainExecution.getId());
		
		Execution dealerExecution = runtimeService.createExecutionQuery()
				  .processInstanceId(dealerIdProcessInstance)
				  .activityId("clientPayedRent_id")
				  .singleResult();
		runtimeService.signal(dealerExecution.getId());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
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

		Execution execution = runtimeService.createExecutionQuery()
				  .processInstanceId(clientIdProcessInstance)
				  .activityId("receiveOffer_Id")
				  .singleResult();

		runtimeService.signal(execution.getId());
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
	
}