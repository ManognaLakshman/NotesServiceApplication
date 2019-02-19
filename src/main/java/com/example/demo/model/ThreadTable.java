package com.example.demo.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="thread")
public class ThreadTable implements Serializable  {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="THREAD_SEQ_ID")
	@SequenceGenerator(name="THREAD_SEQ_ID",sequenceName="THREAD_SEQ_ID",allocationSize=1)
	private Long id;
	private String createdBy;
	@Column(nullable=false)
	private LocalDateTime createdOn;
	
	@OneToMany(mappedBy = "threadTable",cascade = CascadeType.ALL,orphanRemoval = true)
	private Set<Notes> notes=new HashSet<>();
	
	public Set<Notes> getNotes() {
		return notes;
	}

	protected void setNotes(Set<Notes> notes) {
		this.notes = notes;
	}
	public void addToNotes(Notes note) {
		this.notes.add(note);
		note.setThreadTable(this);
	}
	public void removeNote(Notes note) {
		this.notes.remove(note);
		note.setThreadTable(null);
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
}
