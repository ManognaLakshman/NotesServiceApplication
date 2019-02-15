package com.example.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Repository.RepoThread;
import com.example.demo.model.Notes;

@Controller
public class ControllerClass {

	@Autowired
	private RepoThread repoThread;
	private Notes notes;
	
	@RequestMapping("/getnotesdetails")
	public void GetNotesDetails() {	
		
	}
	
	@RequestMapping("/getnotes")
	public void GetNotes() {
		
	}
	
	@RequestMapping("/addnotes")
	public void AddNotes() {
		
	}
	
	@RequestMapping("/append")
	public void appendThread() {
		
	}

}
