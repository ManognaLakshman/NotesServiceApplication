package com.example.demo.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Repository.RepoNotes;
import com.example.demo.Repository.RepoThread;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Notes;


@CrossOrigin
@RestController
public class ControllerClass {

	@Autowired
	RepoThread threadrepository;
	@Autowired
	RepoNotes notesRepo;
	@GetMapping(value="/notes/{threadId}")
	@Transactional(readOnly = true)
	 public List<Notes> getAllNotes(@PathVariable(value="threadId") Long threadId) {
		return notesRepo.findByThreadId(threadId);
	} 
	
	@GetMapping(value="/threads/{threadId}/notes/{notesId}")
	public Notes getNotes(@PathVariable (value = "threadId") Long threadId,
			@PathVariable (value = "notesId") Long notesId) {
		return notesRepo.findByNotesandThreadId(notesId,threadId).map(notes->notes).orElseThrow(() -> new ResourceNotFoundException("NotesId " + notesId + "not found"));

	}
	
	@RequestMapping(value="/addnotes/{id}",method=RequestMethod.POST)
	public void AddNotes(@RequestParam("id") Long id) {
		
	}
	
	@RequestMapping(value="/append/{id}",method=RequestMethod.POST)
	public void appendThread(@RequestParam("id") Long id) {
		
	}

}
