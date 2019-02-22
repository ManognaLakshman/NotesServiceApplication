package com.example.demo.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.datetime.joda.LocalDateTimeParser;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;

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
	
	//Fetches all the notes associated with ThreadID
	@GetMapping(value="/notes/{threadId}")
	 public Page<Notes> getAllNotes(@PathVariable(value="threadId") Long threadId,@PageableDefault(page = 0, size = 10) Pageable pageable) {
		if(!threadrepository.existsById(threadId)){
			throw new ResourceNotFoundException("threadId " + threadId + " not found");
		}
		return notesrepository.findFirst10ByThreadId(threadId,pageable);
	} 
	
	//Fetches note associated with the threadid and records that were previously created before that last timestamp 
	@GetMapping(value="/notesView/{threadId}/{timeStamp}")
	public Page<Notes> getNextNotes(@PathVariable(value="threadId")Long threadId,@PathVariable(value="timeStamp") CharSequence timestamp,@PageableDefault(page = 0, size = 10) Pageable pageable) {
		LocalDateTime createddateTime = LocalDateTime.parse(timestamp);
		return notesrepository.getDetails(threadId, createddateTime,pageable);
	}
	
	//Fetches a specific node associated with threadID and NoteID
	@GetMapping(value="/threads/{threadId}/notes/{notesId}")
	public Notes getNotes(@PathVariable (value = "threadId") Long threadId,
			@PathVariable (value = "notesId") Long notesId) {
		if(!threadrepository.existsById(threadId)){
			throw new ResourceNotFoundException("threadId " + threadId + " not found");
		}
		return ( notesrepository.findByNotesandThreadId(notesId,threadId)).map(notes->notes).orElseThrow(() -> new ResourceNotFoundException("NotesId " + notesId + "not found"));

	}
	
	//creates new threadId and NotesId,stores the records in the db 
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

	//creates new NotesId and saves a new record in the notes table
	@PostMapping(value="/notes/new/{threadId}")
	public Notes addNotesToThread(@PathVariable(value="threadId") Long threadId,@Valid @RequestBody Notes notesRequest) {

		if(!threadrepository.existsById(threadId)){
			throw new ResourceNotFoundException("threadId " + threadId + "not found");
		}
		ThreadTable threadTable=threadrepository.findByID(threadId);
		notesRequest.setThreadTable(threadTable);
		notesRequest.setCreatedBy("Om");
		notesRequest.setCreatedOn(LocalDateTime.now());
		return notesrepository.save(notesRequest);
	}
	
	//Updates the Notes record	
	@PutMapping(value="/notes/{noteId}/{threadId}")
	public Notes updateNotes(@PathVariable (value = "noteId") Long notesId,@PathVariable (value = "threadId") Long threadId,@Valid @RequestBody Notes notesReq)
	{
		if(!threadrepository.existsById(threadId)){
			
			throw new ResourceNotFoundException("threadId " + threadId + "not found");
		}
		return notesrepository.findByNotesandThreadId(notesId, threadId).map(notes->{
		notes.setUpdatedBy("Om");
		notes.setUpdatedOn(LocalDateTime.now());
		notes.setMessage(notesReq.getMessage());
		return notesrepository.save(notes);}).orElseThrow(() -> new ResourceNotFoundException("NotesId " + notesId + "not found"));
		
	}
	
}
