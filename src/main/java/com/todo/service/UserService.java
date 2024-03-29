package com.todo.service;

import com.todo.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
  public User findByID(Long id);

  public User findByAuthToken(String authToken);

  public User register(String login);
}
