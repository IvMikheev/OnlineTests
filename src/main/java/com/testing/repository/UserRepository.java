package com.testing.repository;

import com.testing.domain.User;

import java.util.List;

public interface UserRepository {

    void save(User user);

    List<User> findByName(String username);
}
