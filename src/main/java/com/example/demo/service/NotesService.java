package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.demo.DTO.NotesDTO;
import com.example.demo.DTO.ThreadDTO;
import com.example.demo.Repository.NotesRepository;
import com.example.demo.Repository.ThreadRepository;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Notes;
import com.example.demo.model.ThreadTable;

@Service
public class NotesService {
	@Autowired
	private ThreadRepository threadrepository;
	@Autowired
	private NotesRepository notesrepository;
	@Autowired
	private ModelMapper mapper;
	
	public  Page<NotesDTO> getAllNotes(Long threadId,Pageable pageable){
		
		if(!threadrepository.existsById(threadId)){
			throw new ResourceNotFoundException("threadId " + threadId + " not found");
		}
		Page<Notes>  notesPageable=notesrepository.findByThreadId(threadId,pageable);
		return notesPageable.map(notes -> this.mapper.map(notes, NotesDTO.class));
		} 
	
	public  Page<NotesDTO> getNextNotes(Long threadId,CharSequence timestamp,Pageable pageable) {
		if(!threadrepository.existsById(threadId)){
			throw new ResourceNotFoundException("threadId " + threadId + " not found");
		}
		LocalDateTime createddateTime = LocalDateTime.parse(timestamp);
		Page<Notes> nextNotesPageable=notesrepository.getDetails(threadId, createddateTime, pageable);
		return nextNotesPageable.map(notes-> this.mapper.map(notes, NotesDTO.class));
	}
	
	public  NotesDTO getNote(Long notesId ,Long threadId) {
		if(!threadrepository.existsById(threadId)){
			throw new ResourceNotFoundException("threadId " + threadId + " not found");
		}
		Optional<Notes> getNotePageable=notesrepository.findByNotesandThreadId(notesId,threadId);
		return getNotePageable.map(notes-> this.mapper.map(notes, NotesDTO.class)).orElseThrow(() -> 
		new ResourceNotFoundException("NotesId " + notesId + "not found"));
	}	
	public ThreadDTO AddNotes(Notes notes) {
		if(!org.springframework.util.StringUtils.hasText(notes.getMessage())) {
			throw new IllegalArgumentException("Message should not be empty");
		}
		ThreadTable threadTable=new ThreadTable();
		threadTable.setCreatedBy("Manogna");
		threadTable.setCreatedOn(LocalDateTime.now());
		notes.setCreatedBy("Manogna");
		notes.setCreatedOn(LocalDateTime.now());
		threadTable.addToNotes(notes);
		ThreadTable postThread=threadrepository.save(threadTable);
		return mapper.map(postThread, ThreadDTO.class);
	
    }

	public NotesDTO addNotesToThread(Long threadId,Notes notesRequest) {

		if(!threadrepository.existsById(threadId)){
			throw new ResourceNotFoundException("threadId " + threadId + "not found");
		}
		ThreadTable threadTable=threadrepository.findByID(threadId);
		notesRequest.setThreadTable(threadTable);
		notesRequest.setUpdatedBy("Om");
		notesRequest.setUpdatedOn(LocalDateTime.now());
		Notes postNote=notesrepository.save(notesRequest);
		return mapper.map(postNote, NotesDTO.class);
	}

	public NotesDTO updateNotes(Long notesId,Long threadId,Notes notesReq)
	{
		if(!threadrepository.existsById(threadId)){
			throw new ResourceNotFoundException("threadId " + threadId + "not found");
		}
		Notes notes =notesrepository.findByNotesandThreadId(notesId, threadId).get();
		notes.setUpdatedBy("Om");
		notes.setUpdatedOn(LocalDateTime.now());
		notes.setMessage(notesReq.getMessage());
		Notes note=notesrepository.save(notesReq);
		return mapper.map(note, NotesDTO.class);
		
		
		/*return notesrepository.save(notes);}).orElseThrow(() -> new ResourceNotFoundException("NotesId " + notesId +
				"not found"));*/
		
	}

}
