package com.example.demo.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Notes;


public interface NotesRepository extends JpaRepository<Notes, Long>{
	//@Query(value="select * from Notes n where n.thread_id=?1 order by created_on desc",nativeQuery = true)
	@Query("select n from Notes n where n.threadTable.id=?1 order by n.createdOn desc")
	Page<Notes> findFirst10ByThreadId(Long threadTblid,Pageable pageable);
	 @Query("select n from Notes n where n.id=?1 and n.threadTable.id=?2")
	 Optional<Notes> findByNotesandThreadId(Long Id,Long threadTblid);
	/*List<Notes> findTop10ByThreadId(Long threadTblid); */
	// @Query("select n from Notes n where n.threadTable.id=?1 AND n.createdOn <?2 order by n.createdOn desc")
	 @Query(value="select * from Notes where thread_id=?1 and created_on<?2 order by created_on desc",nativeQuery=true)
	 Page<Notes> getDetails(Long threadId,LocalDateTime createdOn,Pageable pageable);
}
