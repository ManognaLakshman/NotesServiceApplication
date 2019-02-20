package com.example.demo.controllers;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Repository.NotesRepository;
import com.example.demo.Repository.ThreadRepository;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Notes;
import com.example.demo.model.ThreadTable;


@CrossOrigin
@RestController
public class NotesController {

	@Autowired
	ThreadRepository threadrepository;
	@Autowired
	NotesRepository notesrepository;
	
	@GetMapping(value="/notes/{threadId}")
	 public List<Notes> getAllNotes(@PathVariable(value="threadId") Long threadId) {
		
		if(!threadrepository.existsById(threadId)){
			throw  new ResourceNotFoundException("threadId " + threadId + "not found");
		}
		return notesrepository.findByThreadId(threadId);
	} 
	
	@GetMapping(value="/threads/{threadId}/notes/{notesId}")
	public Notes getNotes(@PathVariable (value = "threadId") Long threadId,
			@PathVariable (value = "notesId") Long notesId) {

		if(!threadrepository.existsById(threadId)){
			throw  new ResourceNotFoundException("threadId " + threadId + "not found");
		}
		return notesrepository.findByNotesandThreadId(notesId,threadId).map(notes->notes).orElseThrow(() -> new ResourceNotFoundException("NotesId " + notesId + "not found"));

	}
	
	@PostMapping(value="/threads/new")
	public ThreadTable AddNotes(@Valid @RequestBody Notes notes) {
		if(!org.springframework.util.StringUtils.hasText(notes.getMessage())) {
			throw new IllegalArgumentException("Message should not be empty");
		}
		ThreadTable threadTable=new ThreadTable();
		threadTable.setCreatedBy("Manogna");
		threadTable.setCreatedOn(LocalDateTime.now());
		threadTable.addToNotes(notes);
		return threadrepository.save(threadTable);
    }

	
	@PostMapping(value="/notes/new/{threadId}")
	public Notes addNotesToThread(@PathVariable(value="threadId") Long threadId,@Valid @RequestBody Notes notesRequest) {

		if(!threadrepository.existsById(threadId)){
			throw  new ResourceNotFoundException("threadId " + threadId + "not found");
		}
		ThreadTable threadTable=threadrepository.findByID(threadId);
		notesRequest.setThreadTable(threadTable);
		return notesrepository.save(notesRequest);
	}
	
	@PutMapping(value="/notes/{notesId}/{threadId}")
	public Notes updateNotes(@PathVariable (value = "notesId") Long notesId,@PathVariable (value = "threadId") Long threadId,@Valid @RequestBody Notes notesReq)
	{
		if(!threadrepository.existsById(threadId)){
			
			throw  new ResourceNotFoundException("threadId " + threadId + "not found");
		}
		return notesrepository.findByNotesandThreadId(notesId, threadId).map(notes->{notes.setUpdatedBy("Om");
		notes.setUpdatedOn(LocalDateTime.now());
		notes.setMessage(notesReq.getMessage());
		return notesrepository.save(notes);}).orElseThrow(() -> new ResourceNotFoundException("NotesId " + notesId + "not found"));
		
	}
	
	

}
