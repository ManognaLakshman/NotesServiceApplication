package com.example.demo.DTO;

import java.time.LocalDateTime;

import com.example.demo.model.ThreadTable;
import com.fasterxml.jackson.annotation.JsonBackReference;

public class NotesDTO {
	private Long id ;
	private String createdBy;
	private LocalDateTime createdOn;
	private String updatedBy;
	private LocalDateTime updatedOn;
	private String message;
	@JsonBackReference
	private ThreadTable threadTable;
	
	public NotesDTO(Long id, String createdBy, LocalDateTime createdOn, String updatedBy, LocalDateTime updatedOn,
			String message, ThreadTable threadTable) {
		super();
		this.id = id;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
		this.updatedBy = updatedBy;
		this.updatedOn = updatedOn;
		this.message = message;
		this.threadTable = threadTable;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public LocalDateTime getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public LocalDateTime getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(LocalDateTime updatedOn) {
		this.updatedOn = updatedOn;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public ThreadTable getThreadTable() {
		return threadTable;
	}
	public void setThreadTable(ThreadTable threadTable) {
		this.threadTable = threadTable;
	}
	
}
