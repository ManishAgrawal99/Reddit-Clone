package com.manish.reddit.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.manish.reddit.dto.VoteDto;
import com.manish.reddit.exception.PostNotFoundException;
import com.manish.reddit.exception.SpringRedditException;
import com.manish.reddit.model.Post;
import com.manish.reddit.model.Vote;
import com.manish.reddit.model.VoteType;
import com.manish.reddit.repository.PostRepository;
import com.manish.reddit.repository.VoteRepository;

@Service
public class VoteService {

	private final VoteRepository voteRepository;
	private final PostRepository postRepository;
	private final AuthService authService;

	public VoteService(VoteRepository voteRepository, PostRepository postRepository, AuthService authService) {
		super();
		this.voteRepository = voteRepository;
		this.postRepository = postRepository;
		this.authService = authService;
	}

	
	@Transactional
	public void vote(VoteDto voteDto) {
		Post post = postRepository.findById(voteDto.getPostId())
								  .orElseThrow(()-> new PostNotFoundException("Cannot Find the post with ID: "+voteDto.getPostId()));
		
		Optional<Vote> voteByPostAndUser = voteRepository.findTopByPostIdAndUserEmailOrderByVoteIdDesc(voteDto.getPostId(), authService.getCurrentUser().getEmail());
		
		if(voteByPostAndUser.isPresent() && voteByPostAndUser.get().getVoteType().equals(voteDto.getVoteType())) {
			throw new SpringRedditException("You have already casted a vote");
		}
		
		if(VoteType.UPVOTE.equals(voteDto.getVoteType())) {
			post.setVoteCount(post.getVoteCount()+1);
		}
		else {
			post.setVoteCount(post.getVoteCount()-1);
		}
		
		Vote vote = new Vote();
		vote.setPostId(voteDto.getPostId());
		vote.setUserEmail(authService.getCurrentUser().getEmail());
		vote.setVoteType(voteDto.getVoteType());
		
		voteRepository.save(vote);
		postRepository.save(post);
	}
}
