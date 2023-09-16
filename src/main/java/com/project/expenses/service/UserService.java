package com.project.expenses.service;

import com.project.expenses.domain.User;
import com.project.expenses.dto.UserDTO;
import com.project.expenses.repository.JpaUserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final JpaUserRepository repository;

    public UserService(JpaUserRepository repository) {
        this.repository = repository;
    }

    public Long save(UserDTO userDTO) {
        User saved = repository.save(userDTO.toEntity());
        return saved.getId();
    }

    public User findById(Long id) {
        return repository.findById(id).orElse(null);
    }

}
