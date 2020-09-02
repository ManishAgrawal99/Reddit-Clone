package com.manish.reddit.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.manish.reddit.dto.CommentsDto;
import com.manish.reddit.exception.PostNotFoundException;
import com.manish.reddit.exception.UsernameNotFoundException;
import com.manish.reddit.model.Comment;
import com.manish.reddit.model.Post;
import com.manish.reddit.model.User;
import com.manish.reddit.repository.CommentRepository;
import com.manish.reddit.repository.PostRepository;
import com.manish.reddit.repository.UserRepository;


@Service
public class CommentsService {

	private final PostRepository postRepository;
	private final UserRepository userRepository;
	private final AuthService authService;
	private final CommentRepository commentRepository;



	public CommentsService(PostRepository postRepository, UserRepository userRepository, AuthService authService,
			CommentRepository commentRepository) {
		super();
		this.postRepository = postRepository;
		this.userRepository = userRepository;
		this.authService = authService;
		this.commentRepository = commentRepository;
	}







	@Transactional
	public Comment save(CommentsDto commentsDto) {
		
		
		Post post = postRepository.findById(commentsDto.getPostId())
				      .orElseThrow(()-> new PostNotFoundException("Cannot find post with ID: "+commentsDto.getPostId()));
		
		Comment comment = new Comment();
		
		comment.setPost(commentsDto.getPostId());
		comment.setText(commentsDto.getText());
		comment.setCreatedDate(Instant.now());
		comment.setUser(authService.getCurrentUser());
		
		Comment savedComment = commentRepository.save(comment);

		List<Comment> comments;
		
		if (post.getComments() == null) {
			comments = new ArrayList<Comment>();
			
		} 
		else {
			comments = post.getComments();
		}
		
		comments.add(savedComment);
		post.setComments(comments);
		
		postRepository.save(post);
		
		return savedComment;
	}
	
	public List<Comment> getAllComments(){
		
		return commentRepository.findAll();
	}








	public List<Comment> getAllCommentsForPost(String postId) {

		Post post = postRepository.findById(postId).orElseThrow(()-> new PostNotFoundException("Cannot find Post with ID: "+postId));
		
		return commentRepository.findByPostId(postId);
	}
	
	
	
	public List<Comment> getAllCommentsForUser(String email) {
		
		User user = userRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("Cannot find user with ID: "+ email));
		
		return commentRepository.findAllByUser(user);
	}
}
