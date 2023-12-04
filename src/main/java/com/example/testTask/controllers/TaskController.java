package com.example.testTask.controllers;

import com.example.testTask.dtos.CommentsDto;
import com.example.testTask.dtos.UserSmallDto;
import com.example.testTask.dtos.UserTaskDto;
import com.example.testTask.mappers.CommentMapper;
import com.example.testTask.mappers.UserTaskMapper;
import com.example.testTask.models.taskModels.Comment;
import com.example.testTask.models.taskModels.UserTask;
import com.example.testTask.repositorys.UserRepository;
import com.example.testTask.repositorys.UserTaskRepository;
import com.example.testTask.services.CommentService;
import com.example.testTask.services.UserService;
import com.example.testTask.services.UserTaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping()
@RequiredArgsConstructor
public class TaskController {

    @Autowired
    private final UserService userService;
    @Autowired
    private final UserTaskService userTaskService;
    @Autowired
    private final CommentService commentService;
    @Autowired
    private final UserTaskMapper userTaskMapper;
    @Autowired
    private final CommentMapper commentMapper;


    @PostMapping("/userAddNewTasks/{userId}")
    public UserTask createUserTasks(@PathVariable("userId") @NotNull @Positive Long userId, @Valid @RequestBody UserTask userTask){
        userTaskService.setUserCreator(userTask,userId);
        return userTaskService.save(userTask);
    }

    @PostMapping("/userUpdateTasks/{taskId}")
    public UserTask updateUserTasks(@PathVariable("taskId") @NotNull @Positive Long taskId,@Valid @RequestBody UserTask userTask){
        return userTaskService.findById(taskId)
                .map(userTaskInitial -> {
                    userTaskInitial.setDescription(userTask.getDescription());
                    userTaskInitial.setHeading(userTask.getHeading());
                    userTaskInitial.setPrioritie(userTask.getPrioritie());
                    userTaskInitial.setStatus(userTask.getStatus());
                    return userTaskService.save(userTaskInitial);
                } ).get();
    }

    @PostMapping("/userUpdateExecuter/{taskId}/{userId}")
    public UserTask updateUserTaskExecuter(@PathVariable("taskId") @NotNull @Positive Long taskId,@PathVariable("userId") @NotNull @Positive Long userId){
        return userTaskService.updateUserExecutor(userId,taskId);
    }

    @DeleteMapping("/userDeleteTask/{taskId}")
    public void  deleteUserTask(@PathVariable("taskId") @NotNull @Positive Long taskId){
         userTaskService.deleteById(taskId);
    }

    @PostMapping("/addComments/{taskId}")
    public Comment addNewComments(@PathVariable("taskId") @NotNull @Positive Long taskId,@Valid @RequestBody() Comment comment){
        UserTask userTask = userTaskService.findById(taskId).get();
        comment.setTasksId(userTask);
        comment.setUserCreator(userTask.getUserCreator());
        return commentService.save(comment);
    }

    @GetMapping("/GetAllTask")
    public List<UserTaskDto> getAllTask(@RequestParam("offset") Integer offset,
                                    @RequestParam("limit") Integer limit){
        List<UserTaskDto> userTaskDtoList = userTaskService.findAllPage(PageRequest.of(offset,limit)).stream()
                .map(userTask -> {
                    List<CommentsDto> commentsDtos = commentService.findAllByTasksId(userTask).stream()
                            .map(el->commentMapper.toDTO(el))
                            .collect(Collectors.toList());
                    UserSmallDto userSmallDto = userTaskMapper.toDTO(userTask);
                    return new UserTaskDto(commentsDtos,userSmallDto);
                })
                .collect(Collectors.toList());
        return userTaskDtoList;
    }

    @GetMapping("/GetUserTask/{userId}")
    public List<UserTaskDto> getAllUserTask(@PathVariable("userId") @NotNull @Positive Long userId, @RequestParam("offset") Integer offset,
                                            @RequestParam("limit") Integer limit){
        List<UserTaskDto> userTaskDtoList = userTaskService.findAllByUserCreator(userId,PageRequest.of(offset,limit))
                .stream()
                .map(userTask -> {
                    List<CommentsDto> commentsDtos = commentService.findAllByTasksId(userTask).stream()
                            .map(el->commentMapper.toDTO(el))
                            .collect(Collectors.toList());
                    UserSmallDto userSmallDto = userTaskMapper.toDTO(userTask);
                    return new UserTaskDto(commentsDtos,userSmallDto);
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
