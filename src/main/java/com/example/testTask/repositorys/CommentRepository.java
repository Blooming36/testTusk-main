package com.example.testTask.repositorys;

import com.example.testTask.models.taskModels.Comment;
import com.example.testTask.models.taskModels.UserTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByTasksId(UserTask userTask);
}
