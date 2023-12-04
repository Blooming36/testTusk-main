package com.example.testTask.dtos;

import com.example.testTask.models.taskModels.Prioritie;
import com.example.testTask.models.taskModels.Status;
import lombok.Data;

@Data
public class UserSmallDto {
    private Status status;
    private String heading;
    private String description;
    private Prioritie prioritie;
    private UserDto userExecutor;
    private UserDto userCreator;
}
