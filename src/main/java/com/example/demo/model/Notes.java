package com.example.demo.model;

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
	@SequenceGenerator(name="NOTES_SEQ_ID",sequenceName="NOTES_SEQ_ID")
	private Long id ;
	private String created_by;
	private String Created_on;
	private String Updated_by;
	private String Updated_on;
	private String message;
	
	@ManyToOne
	@JoinColumn(name="THREAD_ID",referencedColumnName="ID")
	private ThreadTable threadTable;
	
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public String getCreated_on() {
		return Created_on;
	}
	public void setCreated_on(String created_on) {
		Created_on = created_on;
	}
	public String getUpdated_by() {
		return Updated_by;
	}
	public void setUpdated_by(String updated_by) {
		Updated_by = updated_by;
	}
	public String getUpdated_on() {
		return Updated_on;
	}
	public void setUpdated_on(String updated_on) {
		Updated_on = updated_on;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "Notes [id=" + id + ", created_by=" + created_by + ", Created_on="
				+ Created_on + ", Updated_by=" + Updated_by + ", Updated_on=" + Updated_on + ", message=" + message
				+ "]";
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
	

}
