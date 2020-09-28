package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.SubredditDto;
import com.example.demo.exception.SpringRedditException;
import com.example.demo.model.Subreddit;
import com.example.demo.repository.SubredditRepository;

@Service
public class SubredditService {
	private final SubredditRepository subredditRepository;
	
	public SubredditService(SubredditRepository subredditRepository) {
		super();
		this.subredditRepository = subredditRepository;
	}


	@Transactional
	public SubredditDto save(SubredditDto dto) {
		
		Subreddit subreddit = mapSubredditDto(dto);
		Subreddit save = subredditRepository.save(subreddit);
		dto.setId(save.getId());
		return dto;
	}

	
	@Transactional(readOnly = true)
	public List<Subreddit> getAll() {
//		return subredditRepository.findAll()
//						   .stream()
//						   .map(this::mapToDto)
//						   .collect(Collectors.toList());
		return subredditRepository.findAll();
	}
	
	
	public Subreddit getSubreddit(String id) {
		
		return subredditRepository.findById(id)
								  .orElseThrow(()-> new SpringRedditException("No subreddit found with the ID: "+id));
	
	}
	
	private SubredditDto mapToDto(Subreddit subreddit) {
		SubredditDto dto = new SubredditDto();
		dto.setSubredditName(subreddit.getName());
		dto.setDescription(subreddit.getDescription());
		dto.setNumberOfPosts(subreddit.getPosts().size());
		return dto;
	}


	private Subreddit mapSubredditDto(SubredditDto dto) {
		Subreddit subreddit = new Subreddit();
		subreddit.setName(dto.getSubredditName());
		subreddit.setDescription(dto.getDescription());
		return subreddit;
	}
}
