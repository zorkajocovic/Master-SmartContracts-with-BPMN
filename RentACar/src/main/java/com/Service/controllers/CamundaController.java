package com.Service.controllers;

import java.util.HashMap;
import java.util.List;

import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.form.FormField;
import org.camunda.bpm.engine.form.TaskFormData;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.Service.camundaDto.FieldDto;
import com.Service.camundaDto.FormFieldsDto;
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
	
	@GetMapping(path = "/startClientProcess", produces = "application/json")
    public @ResponseBody FormFieldsDto startClientProcess() {
		
		ProcessInstance pi = runtimeService.startProcessInstanceByKey("Client_ProcessId");
		Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).list().get(0);
		TaskFormData tfd = formService.getTaskFormData(task.getId());
		List<FormField> properties = tfd.getFormFields();
		
        return new FormFieldsDto(task.getId(), pi.getId(), properties);
    }
	
	@GetMapping(path = "/startDealerProcess", produces = "application/json")
    public @ResponseBody FormFieldsDto startDealerProcess() {
		
		ProcessInstance pi = runtimeService.startProcessInstanceByKey("Dealer_ProcessId");
		Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).list().get(0);
		TaskFormData tfd = formService.getTaskFormData(task.getId());
		List<FormField> properties = tfd.getFormFields();
		
        return new FormFieldsDto(task.getId(), pi.getId(), properties);
    }
	
	@PostMapping(path = "/orderCar/{taskId}", produces = "application/json")
    public @ResponseBody ResponseEntity<Object> post(@RequestBody OrderDto order, @PathVariable String taskId) {

		HashMap<String, Object> map = new HashMap<>();
		map.put("customerName", order.getCustomerName());
		map.put("customerSurname", order.getCustomerSurname());
		map.put("model", order.getModel());
		map.put("type", order.getType());
		
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		String processInstanceId = task.getProcessInstanceId();
		runtimeService.setVariable(processInstanceId, "customerName", order.getCustomerName());
		runtimeService.setVariable(processInstanceId, "customerSurname", order.getCustomerSurname());
		runtimeService.setVariable(processInstanceId, "type", order.getType());
		runtimeService.setVariable(processInstanceId, "model", order.getModel());

		formService.submitTaskForm(taskId, map);
		ProcessInstance pi = runtimeService.startProcessInstanceByKey("Dealer_ProcessId");

        return new ResponseEntity<>(HttpStatus.OK);
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