package com.example.testTask.controllers;

import com.example.testTask.dtos.CommentsDto;
import com.example.testTask.dtos.UserSmallDto;
import com.example.testTask.dtos.UserTaskDto;
import com.example.testTask.mappers.CommentMapper;
import com.example.testTask.mappers.UserTaskMapper;
import com.example.testTask.models.taskModels.Comment;
import com.example.testTask.models.taskModels.UserTask;
import com.example.testTask.services.CommentService;
import com.example.testTask.services.UserService;
import com.example.testTask.services.UserTaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping()
@RequiredArgsConstructor
public class TaskController {


    @Autowired
    private final UserTaskService userTaskService;
    @Autowired
    private final CommentService commentService;
    @Autowired
    private final UserTaskMapper userTaskMapper;
    @Autowired
    private final CommentMapper commentMapper;


    @PostMapping("/userAddNewTasks/{userId}")
    public UserTask createUserTasks(@PathVariable("userId") @Min(0) Long userId, @RequestBody UserTask userTask) {
        userTaskService.setUserCreator(userTask, userId);
        return userTaskService.save(userTask);
    }

    @PostMapping("/userUpdateTasks/{taskId}/{userId}")
    public UserTask updateUserTasks(@PathVariable("userId") @Min(0) Long userId, @PathVariable("taskId") @Min(0) Long taskId, @RequestBody UserTask userTask) {
        return userTaskService.findById(taskId)
                .filter(task -> task.getUserCreator().getId().equals(userId))
                .map(task -> {
                    task.setDescription(userTask.getDescription());
                    task.setHeading(userTask.getHeading());
                    task.setPrioritie(userTask.getPrioritie());
                    task.setStatus(userTask.getStatus());
                    return userTaskService.save(task);
                })
                .orElse(null);
    }

    @PostMapping("/userUpdateExecuter/{taskId}/{userId}")
    public UserTask updateUserTaskExecuter(@PathVariable("taskId") @Min(0) Long taskId, @PathVariable("userId") Long userId) {
        return userTaskService.updateUserExecutor(userId, taskId);
    }

    @DeleteMapping("/userDeleteTask/{taskId}")
    public void deleteUserTask(@PathVariable("taskId") @Min(0) Long taskId) {
        userTaskService.deleteById(taskId);
    }

    @PostMapping("/addComments/{taskId}")
    public Comment addNewComments(@PathVariable("taskId") @Min(0) Long taskId,  @RequestBody() Comment comment) {
        UserTask userTask = userTaskService.findById(taskId).get();
        comment.setTasksId(userTask);
        comment.setUserCreator(userTask.getUserCreator());
        return commentService.save(comment);
    }

    @GetMapping("/GetAllTask")
    public List<UserTaskDto> getAllTask(@RequestParam("offset") Integer offset,
                                        @RequestParam("limit") Integer limit) {
        List<UserTaskDto> userTaskDtoList = userTaskService.findAllPage(PageRequest.of(offset, limit)).stream()
                .map(userTask -> {
                    List<CommentsDto> commentsDtos = commentService.findAllByTasksId(userTask).stream()
                            .map(el -> commentMapper.toDTO(el))
                            .collect(Collectors.toList());
                    UserSmallDto userSmallDto = userTaskMapper.toDTO(userTask);
                    return new UserTaskDto(commentsDtos, userSmallDto);
                })
                .collect(Collectors.toList());
        return userTaskDtoList;
    }

    @GetMapping("/GetUserTask/{userId}")
    public List<UserTaskDto> getAllUserTask(@PathVariable("userId") Long userId, @RequestParam("offset") Integer offset,
                                            @RequestParam("limit") Integer limit) {
        List<UserTaskDto> userTaskDtoList = userTaskService.findAllByUserCreator(userId, PageRequest.of(offset, limit))
                .stream()
                .map(userTask -> {
                    List<CommentsDto> commentsDtos = commentService.findAllByTasksId(userTask).stream()
                            .map(el -> commentMapper.toDTO(el))
                            .collect(Collectors.toList());
                    UserSmallDto userSmallDto = userTaskMapper.toDTO(userTask);
                    return new UserTaskDto(commentsDtos, userSmallDto);
                })
                .collect(Collectors.toList());
        return userTaskDtoList;
    }

    @GetMapping("/GetAllTaskByEmailCreator")
    public List<UserTaskDto> getAllTaskCreator(@RequestParam("email") @Pattern(regexp = "^[a-zA-Z0-9]+$") String email,
                                               @RequestParam("offset") Integer offset,
                                               @RequestParam("limit") Integer limit) {
        List<UserTaskDto> userTaskDtoList = userTaskService.findAllByUserNameCreator(email, PageRequest.of(offset, limit))
                .stream()
                .map(userTask -> {
                    List<CommentsDto> commentsDtos = commentService.findAllByTasksId(userTask).stream()
                            .map(el -> commentMapper.toDTO(el))
                            .collect(Collectors.toList());
                    UserSmallDto userSmallDto = userTaskMapper.toDTO(userTask);
                    return new UserTaskDto(commentsDtos, userSmallDto);
                })
                .collect(Collectors.toList());
        return userTaskDtoList;
    }

    @GetMapping("/GetAllTaskByEmailExecuter")
    public List<UserTaskDto> getAllTaskExecuter(@RequestParam("email") @Pattern(regexp = "^[a-zA-Z0-9]+$") String email,
                                                @RequestParam("offset") Integer offset,
                                                @RequestParam("limit") Integer limit) {
        List<UserTaskDto> userTaskDtoList = userTaskService.findAllByUserNameExecuter(email, PageRequest.of(offset, limit))
                .stream()
                .map(userTask -> {
                    List<CommentsDto> commentsDtos = commentService.findAllByTasksId(userTask).stream()
                            .map(el -> commentMapper.toDTO(el))
                            .collect(Collectors.toList());
                    UserSmallDto userSmallDto = userTaskMapper.toDTO(userTask);
                    return new UserTaskDto(commentsDtos, userSmallDto);
                })
                .collect(Collectors.toList());
        return userTaskDtoList;
    }

}
