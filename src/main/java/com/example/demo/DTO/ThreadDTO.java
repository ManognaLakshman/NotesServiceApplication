package com.example.demo.DTO;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import com.example.demo.model.Notes;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
public class ThreadDTO {
	private Long id;
	private String createdBy;
	private LocalDateTime createdOn;
	@JsonBackReference 
	private Set<Notes> notes=new HashSet<>();
	
	public ThreadDTO(Long id, String createdBy, LocalDateTime createdOn, Set<Notes> notes) {
		super();
		this.id = id;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
		this.notes = notes;
	}
	public ThreadDTO() {
		// TODO Auto-generated constructor stub
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
	public Set<Notes> getNotes() {
		return notes;
	}
	public void setNotes(Set<Notes> notes) {
		this.notes = notes;
	}

}
