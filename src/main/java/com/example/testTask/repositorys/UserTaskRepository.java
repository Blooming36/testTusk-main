package com.example.testTask.repositorys;

import com.example.testTask.models.taskModels.UserTask;
import com.example.testTask.models.userModels.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserTaskRepository extends JpaRepository<UserTask, Long> {
    Optional<UserTask> findById(Long id);

    Page<UserTask> findAllByUserCreator(Pageable pageable, User user);

    Page<UserTask> findAllByUserExecutor(Pageable pageable, User user);

    void deleteById(Long id);
}
