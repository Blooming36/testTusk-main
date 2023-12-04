package com.example.testTask.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserTaskDto {
    private List<CommentsDto> commentsDto;
    private UserSmallDto userSmallDto;
}
