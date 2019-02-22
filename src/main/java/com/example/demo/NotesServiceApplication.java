package com.example.demo;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.Repository.NotesRepository;
import com.example.demo.Repository.ThreadRepository;

@SpringBootApplication
public class NotesServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotesServiceApplication.class, args);
	}
/*
	@Bean
	ApplicationRunner init(ThreadRepository repo,NotesRepository repoNotes) {
		return arg -> {
			repoNotes.findByThreadId(651l).forEach((Notes)->{System.out.println(Notes.getId());});;
			
			//repo.save(threadTable);
		};
	}*/

}