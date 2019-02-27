package com.example.demo.service;

import java.time.LocalDateTime;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.demo.Repository.NotesRepository;
import com.example.demo.Repository.ThreadRepository;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Notes;
import com.example.demo.model.ThreadTable;

@Service
public class NotesService {
	@Autowired
	static ThreadRepository threadrepository;
	@Autowired
	static NotesRepository notesrepository;
//	DozerBeanMapper dozerBeanMapper=new DozerBeanMapper();

	public static Page<Notes> getAllNotes(Long threadId,Pageable pageable){
		
		if(!threadrepository.existsById(threadId)){
			throw new ResourceNotFoundException("threadId " + threadId + " not found");
		}
		return notesrepository.findByThreadId(threadId,pageable);
	} 
	
	public static Page<Notes> getNextNotes(Long threadId,CharSequence timestamp,Pageable pageable) {
		if(!threadrepository.existsById(threadId)){
			throw new ResourceNotFoundException("threadId " + threadId + " not found");
		}
		LocalDateTime createddateTime = LocalDateTime.parse(timestamp);
		return notesrepository.getDetails(threadId,createddateTime,pageable);
	}
	public static Notes getNotes(Long threadId,Long notesId) {
		if(!threadrepository.existsById(threadId)){
			throw new ResourceNotFoundException("threadId " + threadId + " not found");
		}
		return (notesrepository.findByNotesandThreadId(notesId,threadId).map(notes->notes)).orElseThrow(() -> 
		new ResourceNotFoundException("NotesId " + notesId + "not found"));
	}	
	public ThreadTable AddNotes(Notes notes) {
		if(!org.springframework.util.StringUtils.hasText(notes.getMessage())) {
			throw new IllegalArgumentException("Message should not be empty");
		}
		ThreadTable threadTable=new ThreadTable();
		threadTable.setCreatedBy("Manogna");
		threadTable.setCreatedOn(LocalDateTime.now());
		threadTable.addToNotes(notes);
		return threadrepository.save(threadTable);
    }

	public Notes addNotesToThread(Long threadId,Notes notesRequest) {

		if(!threadrepository.existsById(threadId)){
			throw new ResourceNotFoundException("threadId " + threadId + "not found");
		}
		ThreadTable threadTable=threadrepository.findByID(threadId);
		notesRequest.setThreadTable(threadTable);
		notesRequest.setCreatedBy("Om");
		notesRequest.setCreatedOn(LocalDateTime.now());
		return notesrepository.save(notesRequest);
	}

	public Notes updateNotes(Long notesId,Long threadId,Notes notesReq)
	{
		if(!threadrepository.existsById(threadId)){
			throw new ResourceNotFoundException("threadId " + threadId + "not found");
		}
		return notesrepository.findByNotesandThreadId(notesId, threadId).map(notes->{
		notes.setUpdatedBy("Om");
		notes.setUpdatedOn(LocalDateTime.now());
		notes.setMessage(notesReq.getMessage());
		return notesrepository.save(notes);}).orElseThrow(() -> new ResourceNotFoundException("NotesId " + notesId +
				"not found"));
		
	}
}
