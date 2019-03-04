package com.example.demo.controllers;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.NotesDTO;
import com.example.demo.DTO.ThreadDTO;
import com.example.demo.model.Notes;
import com.example.demo.service.NotesService;


@CrossOrigin
@RestController
public class NotesController {

	@Autowired
	private NotesService notesService;
	
	
	//Fetches all the notes associated with ThreadID
	@GetMapping(value="/notes/{threadId}")
	 public Page<NotesDTO> getAllNotes(@PathVariable(value="threadId") Long threadId,@PageableDefault(page = 0, size = 10)
	 Pageable pageable) {
		return notesService.getAllNotes(threadId,pageable);
	} 
	
	//Fetches note associated with the threadId and records that were previously created before that last timestamp 
	@GetMapping(value="/notes/{threadId}/{timeStamp}")
	public Page<NotesDTO> getNextNotes(@PathVariable(value="threadId")Long threadId,@PathVariable(value="timeStamp") CharSequence
			timestamp,@PageableDefault(page = 0, size = 10) Pageable pageable) {
		return notesService.getNextNotes(threadId,timestamp,pageable);
	}
	
	//Fetches a specific node associated with threadID and NoteID
	@GetMapping(value="/threads/{threadId}/notes/{notesId}")
	public NotesDTO getNotes(@PathVariable (value = "threadId") Long threadId,@PathVariable (value = "notesId") Long notesId) {
		return notesService.getNote(notesId,threadId);
	}
	
	//creates new threadId and NotesId,stores the records in the db 
	@PostMapping(value="/threads")
	public ThreadDTO AddNotes(@Valid @RequestBody Notes notes) {
		return notesService.AddNotes(notes);
    }

	//creates new NotesId and saves a new record in the notes table
	@PostMapping(value="/notes/{threadId}")
	public NotesDTO addNotesToThread(@PathVariable(value="threadId") Long threadId,@Valid @RequestBody Notes notesRequest) {
		return notesService.addNotesToThread(threadId, notesRequest);
	}
	
	//Updates the Notes record	
	@PutMapping(value="/notes/{noteId}/thread/{threadId}")
	public NotesDTO updateNotes(@PathVariable (value = "noteId") Long notesId,@PathVariable (value = "threadId") Long threadId,
			@Valid @RequestBody Notes notesReq) {
		return notesService.updateNotes(notesId,threadId,notesReq);
	}
	
}
