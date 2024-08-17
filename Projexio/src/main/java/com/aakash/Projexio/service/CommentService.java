package com.aakash.Projexio.service;

import com.aakash.Projexio.model.Comment;

import java.util.List;

public interface CommentService {
    Comment createComment(Long issueId, Long userId, String content)throws Exception;
    void deleteComment(Long commentId,Long userId)throws Exception;
    List<Comment> findCommentByIssueId(Long issueId)throws Exception;
}
