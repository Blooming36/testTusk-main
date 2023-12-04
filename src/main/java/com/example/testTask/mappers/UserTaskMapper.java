package com.example.testTask.mappers;

import com.example.testTask.dtos.UserSmallDto;
import com.example.testTask.models.taskModels.UserTask;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserTaskMapper {
    @Mapping(source = "userExecutor", target = "userExecutor")
    @Mapping(source = "userCreator", target = "userCreator")
    UserSmallDto toDTO(UserTask userTask);
}
