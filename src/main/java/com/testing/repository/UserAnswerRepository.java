package com.testing.repository;

import com.testing.domain.UserAnswer;

import java.util.List;

public interface UserAnswerRepository {

    List<UserAnswer> findLastResultByUsername(String username);

    List<UserAnswer> findAllResultsByUsername(String username);

    void save(List<UserAnswer> userAnswers);

}
