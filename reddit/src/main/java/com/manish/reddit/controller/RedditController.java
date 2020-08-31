package com.manish.reddit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manish.reddit.dto.SubredditDto;
import com.manish.reddit.model.Subreddit;
import com.manish.reddit.service.SubredditService;

@RestController
@RequestMapping("/api/subreddit")
public class RedditController {

	private final SubredditService subredditService;
	
	@Autowired
	public RedditController(SubredditService subredditService) {
		super();
		this.subredditService = subredditService;
	}

	@PostMapping
	public ResponseEntity<SubredditDto> createSubreddit(@RequestBody SubredditDto subredditDto) {
		
		return ResponseEntity.status(HttpStatus.CREATED)
					  .body(subredditService.save(subredditDto));
		
	}
	
	@GetMapping
	@Transactional
	public ResponseEntity<List<Subreddit>> getAllSubreddits() {
		
		return ResponseEntity.status(HttpStatus.OK)
							 .body(subredditService.getAll());
	}
}
