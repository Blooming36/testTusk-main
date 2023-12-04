package com.example.testTask.dtos;

import com.example.testTask.models.taskModels.Prioritie;
import com.example.testTask.models.taskModels.Status;
import com.example.testTask.models.userModels.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserTaskDto {
    private List<CommentsDto> commentsDto;
    private UserSmallDto userSmallDto;
}
