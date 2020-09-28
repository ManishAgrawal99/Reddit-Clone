package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.SubredditDto;
import com.example.demo.model.Subreddit;
import com.example.demo.service.SubredditService;


@RestController
@RequestMapping("/v1/subreddit")
public class SubredditController {

private final SubredditService subredditService;
	
	@Autowired
	public SubredditController(SubredditService subredditService) {
		super();
		this.subredditService = subredditService;
	}

	@PostMapping
	public ResponseEntity<SubredditDto> createSubreddit(@RequestBody SubredditDto subredditDto) {
		
		return ResponseEntity.status(HttpStatus.CREATED)
					  .body(subredditService.save(subredditDto));
		
	}
	
	@GetMapping
	public ResponseEntity<List<Subreddit>> getAllSubreddits() {
		
		return ResponseEntity.status(HttpStatus.OK)
							 .body(subredditService.getAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Subreddit> getSubreddit(@PathVariable String id){
		
		return ResponseEntity.status(HttpStatus.OK)
							 .body(subredditService.getSubreddit(id));
	}
}
