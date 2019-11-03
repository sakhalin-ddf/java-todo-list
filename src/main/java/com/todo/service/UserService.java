package com.todo.service;

import com.todo.model.User;
import com.todo.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.UUID;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User findByID(int id) {
        User user = repository.findById(id);

        if (user == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "User not found");
        }

        return user;
    }

    public User findByAuthToken(String authToken) {
        User user = repository.findByAuthToken(authToken);

        if (user == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "User not found");
        }

        return user;
    }

    public User register(String login) {
        User user = new User(login, UUID.randomUUID().toString());

        this.repository.save(user);

        return user;
    }
}
