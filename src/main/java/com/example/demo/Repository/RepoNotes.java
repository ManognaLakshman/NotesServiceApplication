package com.example.demo.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Notes;


public interface RepoNotes extends JpaRepository<Notes, Long>{
@Query(value="select * from Notes n where n.thread_id=?1",nativeQuery = true)	
 List<Notes> findByThreadId(Long threadTblid);
 @Query("select n from Notes n where n.id=?1 and n.threadTable.id=?2")
 Optional<Notes> findByNotesandThreadId(Long Id,Long threadTblid);
 
}
