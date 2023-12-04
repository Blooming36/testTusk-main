package com.example.testTask.services;

import com.example.testTask.models.taskModels.UserTask;
import com.example.testTask.models.userModels.User;
import com.example.testTask.repositorys.UserRepository;
import com.example.testTask.repositorys.UserTaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserTaskService {
    private final UserTaskRepository userTaskRepository;
    private final UserService userService;
    private final UserRepository userRepository;

    public UserTask save(UserTask userTask) {
        return userTaskRepository.save(userTask);
    }

    public void setUserCreator(UserTask userTask, Long id) {
        userTask.setUserCreator(userService.findById(id).get());
    }

    public Optional<UserTask> findById(Long id) {
        return userTaskRepository.findById(id);
    }

    public Page<UserTask> findAllByUserCreator(Long id, PageRequest pageRequest) {
        User user = userService.findById(id).get();
        return userTaskRepository.findAllByUserCreator(pageRequest, user);
    }

    public UserTask updateUserExecutor(Long idUser, Long idTask) {
        User user = userService.findById(idUser).get();
        UserTask userTask = findById(idTask).get();
        userTask.setUserExecutor(user);
        return save(userTask);
    }

    public List<UserTask> findAllPage(PageRequest pageRequest) {
        return userTaskRepository.findAll(pageRequest).getContent();
    }

    public void deleteById(Long id) {
        userTaskRepository.deleteById(id);
    }

    public Page<UserTask> findAllByUserNameCreator(String email, Pageable pageable) {
        User user = userRepository.findByEmail(email).get();
        return userTaskRepository.findAllByUserCreator(pageable, user);
    }

    public Page<UserTask> findAllByUserNameExecuter(String email, Pageable pageable) {
        User user = userRepository.findByEmail(email).get();
        return userTaskRepository.findAllByUserExecutor(pageable, user);
    }
}
