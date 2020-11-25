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
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import com.Service.camundaDto.FieldDto;
import com.Service.camundaDto.FormFieldsDto;
import com.Service.camundaDto.TaskDto;
import com.Service.contracts.RentAcarContract;
import com.Service.dto.OfferDto;
import com.Service.dto.OrderDto;
import com.Service.repositories.AppuserRepository;
import com.Service.services.Web3Service;

import model.Appuser;

@RequestMapping(value = "api/camunda")
@RestController
public class CamundaController {
	
	@Autowired
	TaskService taskService;
	
	@Autowired
	FormService formService;
	
	@GetMapping(path = "/get/{taskId}", produces = "application/json")
    public @ResponseBody FormFieldsDto getTaskForm(@PathVariable String taskId) {

		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		TaskFormData tfd = formService.getTaskFormData(task.getId());
		List<FormField> properties = tfd.getFormFields();
		
        return new FormFieldsDto(task.getId(),task.getProcessInstanceId(), properties);
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