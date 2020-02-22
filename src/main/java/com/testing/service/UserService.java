package com.testing.service;

import com.testing.domain.User;

public interface UserService {

    void save(User user);

    User findByName(String username);

}

