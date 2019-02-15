package com.example.demo;

import java.time.Instant;
import java.util.HashSet;

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

	@SuppressWarnings("serial")
	@Bean
	ApplicationRunner init(RepoThread repo) {
		return arg -> {
			ThreadTable threadTable=new ThreadTable();
			threadTable.setCreated_by("Manogna");
			threadTable.setCreated_on(Instant.now().toString());
			Notes notes=new Notes();
			notes.setMessage("qeqweqweqw sadfs sdaf sdf");
			notes.setCreated_by("Manogna");
			threadTable.addToNotes(notes);
			repo.save(threadTable);
		};
	}

}

