package com.example.testTask.services;

import com.example.testTask.models.taskModels.Comment;
import com.example.testTask.models.taskModels.UserTask;
import com.example.testTask.repositorys.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserTaskService userTaskService;

    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    public List<Comment> findAllByTasksId(UserTask userTask) {
        return commentRepository.findAllByTasksId(userTask);
    }
}
