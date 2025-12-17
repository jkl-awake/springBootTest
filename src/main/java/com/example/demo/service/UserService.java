package com.example.demo.service;

import com.example.demo.dto.UserCreateRequest;
import com.example.demo.dto.UserResponse;
import com.example.demo.dto.UserUpdateRequest;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public UserResponse create(UserCreateRequest req) {
        User user = new User(null, req.getName(), req.getEmail());
        User saved = repository.save(user);
        return toResponse(saved);
    }

    public List<UserResponse> list() {
        return repository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public UserResponse get(Long id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        return toResponse(user);
    }

    public UserResponse update(Long id, UserUpdateRequest req) {
        User existing = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        existing.setName(req.getName());
        existing.setEmail(req.getEmail());
        User saved = repository.save(existing);
        return toResponse(saved);
    }

    public void delete(Long id) {
        boolean removed = repository.deleteById(id);
        if (!removed) {
            throw new UserNotFoundException(id);
        }
    }

    private UserResponse toResponse(User user) {
        UserResponse resp = new UserResponse();
        resp.setId(user.getId());
        resp.setName(user.getName());
        resp.setEmail(user.getEmail());
        return resp;
    }
}
