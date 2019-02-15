package com.example.demo;

import java.time.LocalDateTime;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.Repository.RepoThread;
import com.example.demo.model.Notes;
import com.example.demo.model.ThreadTable;

@SpringBootApplication
public class NotesServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotesServiceApplication.class, args);
	}

	@Bean
	ApplicationRunner init(RepoThread repo) {
		return arg -> {
			ThreadTable threadTable=new ThreadTable();
			threadTable.setCreatedBy("Manogna");
			threadTable.setCreatedOn(LocalDateTime.now());
			Notes notes=new Notes();
			notes.setMessage("qeqweqweqw sadfs sdaf sdf");
			notes.setCreatedBy("Manogna");
			notes.setCreatedOn(LocalDateTime.now());
			threadTable.addToNotes(notes);
			repo.save(threadTable);
		};
	}

}

