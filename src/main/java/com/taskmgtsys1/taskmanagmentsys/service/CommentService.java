package com.taskmgtsys1.taskmanagmentsys.service;

import com.taskmgtsys1.taskmanagmentsys.model.Comment;
import com.taskmgtsys1.taskmanagmentsys.repisotory.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    // Create or Save a Comment
    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }

    // Get All Comments
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    // Get Comment by ID
    public Optional<Comment> getCommentById(Long id) {
        return commentRepository.findById(id);
    }

    // Get Comments by Task ID
    public List<Comment> getCommentsByTaskId(Long taskId) {
        return commentRepository.findByTaskId(taskId);
    }

    // Get Comments by User ID
    public List<Comment> getCommentsByUserId(Long userId) {
        return commentRepository.findByUserId(userId);
    }

    // Update a Comment
    public Comment updateComment(Long id, Comment updatedComment) {
        return commentRepository.findById(id).map(comment -> {
            comment.setCommentText(updatedComment.getCommentText());
            comment.setTimestamp(updatedComment.getTimestamp());
            return commentRepository.save(comment);
        }).orElseThrow(() -> new RuntimeException("Comment not found"));
    }

    // Delete a Comment
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}
