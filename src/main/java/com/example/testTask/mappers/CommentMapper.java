package com.example.testTask.mappers;

import com.example.testTask.dtos.CommentsDto;
import com.example.testTask.models.taskModels.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    CommentsDto toDTO(Comment comment);
}
