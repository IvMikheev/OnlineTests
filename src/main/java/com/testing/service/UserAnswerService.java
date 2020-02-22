package com.testing.service;

import com.testing.domain.UserAnswer;

import java.util.List;

public interface UserAnswerService {

    List<UserAnswer> findLastResultByUsername(String username);

    List<UserAnswer> findAllResultsByUsername(String username);

    void save(List<UserAnswer> userAnswers);
}
