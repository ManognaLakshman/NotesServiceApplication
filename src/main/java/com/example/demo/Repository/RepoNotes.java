package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Notes;


public interface RepoNotes extends JpaRepository<Notes, Long>{

}
