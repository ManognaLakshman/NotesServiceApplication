package com.example.demo.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="thread")
public class ThreadTable {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="THREAD_SEQ_ID")
	@SequenceGenerator(name="THREAD_SEQ_ID",sequenceName="THREAD_SEQ_ID")
	private Long ID;
	private String created_by;
	private String created_on;
	@OneToMany(mappedBy="threadTable",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private Set<Notes> notes;

	public String getCreated_on() {
		return created_on;
	}

	public void setCreated_on(String created_on) {
		this.created_on = created_on;
	}



	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	@Override
	public String toString() {
		return "Thread [ID=" + ID + ", created_by=" + created_by + ", created_on=" + created_on + "]";
	}

	public Set<Notes> getNotes() {
		return notes;
	}

	protected void setNotes(Set<Notes> notes) {
		this.notes = notes;
	}
public void addToNotes(Notes note) {
	note.setThreadTable(this);
	this.notes.add(note);
}
	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}
	
}
