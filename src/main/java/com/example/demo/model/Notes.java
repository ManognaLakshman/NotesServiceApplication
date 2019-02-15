package com.example.demo.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="Notes")
public class Notes {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="NOTES_SEQ_ID")
	@SequenceGenerator(name="NOTES_SEQ_ID",sequenceName="NOTES_SEQ_ID",allocationSize=1)
	private Long id ;
	private String createdBy;
	@Column(nullable=false)
	private LocalDateTime createdOn;
	private String updatedBy;
	private LocalDateTime updatedOn;
	private String message;
	
	@ManyToOne
	@JoinColumn(name="THREAD_ID")
	private ThreadTable threadTable;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public ThreadTable getThreadTable() {
		return threadTable;
	}
	public void setThreadTable(ThreadTable threadTable) {
		this.threadTable = threadTable;
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
}
