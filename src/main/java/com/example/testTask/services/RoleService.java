package com.example.testTask.services;

import com.example.testTask.models.userModels.ERole;
import com.example.testTask.models.userModels.Role;
import com.example.testTask.repositorys.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;
    public Optional<Role> findByName(ERole name){
        return roleRepository.findByName(name);
    }
}
