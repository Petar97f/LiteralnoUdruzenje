package com.lu.backend.model;

import java.util.List;

import org.camunda.bpm.engine.form.FormField;

public class FormFieldsDTO {
	String taskId;
	String processName;
	List<FormField> formFields;
	String processInstanceId;

	public FormFieldsDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public FormFieldsDTO(String taskId, String processName, List<FormField> formFields, String processInstanceId) {
		super();
		this.taskId = taskId;
		this.processName = processName;
		this.formFields = formFields;
		this.processInstanceId = processInstanceId;
	}


	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public List<FormField> getFormFields() {
		return formFields;
	}
	public void setFormFields(List<FormField> formFields) {
		this.formFields = formFields;
	}
	public String getProcessInstanceId() {
		return processInstanceId;
	}
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	
	
}
