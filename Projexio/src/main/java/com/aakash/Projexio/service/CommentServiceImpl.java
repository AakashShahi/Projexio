package com.aakash.Projexio.service;

import com.aakash.Projexio.model.Comment;
import com.aakash.Projexio.model.Issue;
import com.aakash.Projexio.model.User;
import com.aakash.Projexio.repository.CommentRepository;
import com.aakash.Projexio.repository.IssueRepository;
import com.aakash.Projexio.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private IssueRepository issueRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Comment createComment(Long issueId, Long userId, String content) throws Exception {
        Optional<Issue> issueOptional = issueRepository.findById(issueId);
        Optional<User> userOptional = userRepository.findById(userId);

        if(issueOptional.isEmpty()){
            throw new Exception("Issue not found "+issueId);
        }
        if(userOptional.isEmpty()){
            throw new Exception("User not found "+userId);
        }
        Issue issue = issueOptional.get();
        User user = userOptional.get();

        Comment comment= new Comment();

        comment.setIssue(issue);
        comment.setUser(user);
        comment.setCreatedDateTime(LocalDateTime.now());
        comment.setContent(content);

        Comment savedComment = commentRepository.save(comment);

        issue.getComments().add(savedComment);


        return savedComment;
    }

    @Override
    public void deleteComment(Long commentId, Long userId) throws Exception {
        Optional<Comment> commentOptional = commentRepository.findById(commentId);
        Optional<User> userOptional = userRepository.findById(userId);

        if(commentOptional.isEmpty()){
            throw new Exception("Comment not found "+commentId);
        }
        if(userOptional.isEmpty()){
            throw new Exception("User not found "+userId);
        }

        Comment comment = commentOptional.get();
        User user = userOptional.get();

        if(comment.getUser().equals(user)){
            commentRepository.delete(comment);
        }
        else {
            throw new Exception("User not authorized to delete comment");
        }
    }

    @Override
    public List<Comment> findCommentByIssueId(Long issueId) throws Exception {

        return commentRepository.findByIssueId(issueId);
    }
}
