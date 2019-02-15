package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.ThreadTable;

public interface RepoThread extends JpaRepository<ThreadTable, Long>{
	
	
	@Query("select u from ThreadTable u where u.id=?1")
	ThreadTable findByID(Long ID);
	

}
