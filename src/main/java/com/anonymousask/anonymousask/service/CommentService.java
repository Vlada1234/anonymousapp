package com.anonymousask.anonymousask.service;

import com.anonymousask.anonymousask.model.Comment;

public interface CommentService {
    Comment save(Comment comment);

    void delete(Comment comment);
}
