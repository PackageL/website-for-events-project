package com.sda.service;

import com.sda.model.Comment;
import com.sda.model.Event;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    List<Comment> getCommentsByEventId(long eventId);
    void addComment(Comment comment);
}
