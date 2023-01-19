package com.sda.service.impl;

import com.sda.model.Comment;
import com.sda.repository.CommentRepository;
import com.sda.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> getCommentsByEventId(long eventId) {
        return commentRepository.findByEventId(eventId);
    }

    public void addComment(Comment comment) {
        commentRepository.save(comment);
    }
}
