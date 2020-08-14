package com.manish.reddit.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.manish.reddit.model.Comment;
import com.manish.reddit.model.Post;
import com.manish.reddit.model.User;

public interface CommentRepository extends MongoRepository<Comment, Long> {
	
	List<Comment> findByPost(Post post);

    List<Comment> findAllByUser(User user);
}
