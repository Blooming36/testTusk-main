package com.example.testTask.repositorys;

import com.example.testTask.models.userModels.ERole;
import com.example.testTask.models.userModels.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
