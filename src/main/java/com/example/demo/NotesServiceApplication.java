package com.example.demo;

import java.time.LocalDateTime;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.Repository.RepoNotes;
import com.example.demo.Repository.RepoThread;
import com.example.demo.model.Notes;
import com.example.demo.model.ThreadTable;

@SpringBootApplication
public class NotesServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotesServiceApplication.class, args);
	}

	@Bean
	ApplicationRunner init(RepoThread repo,RepoNotes repoNotes) {
		return arg -> {
			repoNotes.findByThreadId(651l).forEach((Notes)->{System.out.println(Notes.getId());});;
			
			//repo.save(threadTable);
		};
	}

}