package com.manish.reddit.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manish.reddit.dto.VoteDto;
import com.manish.reddit.service.VoteService;

@RestController
@RequestMapping("/api/votes/")
public class VoteController {

	private final VoteService voteService;
	
	
	public VoteController(VoteService voteService) {
		super();
		this.voteService = voteService;
	}





	@PostMapping
	public ResponseEntity<Object> vote(@RequestBody VoteDto voteDto) {
		
		voteService.vote(voteDto);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
