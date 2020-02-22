package com.testing.service;

import com.testing.domain.UserAnswer;
import com.testing.repository.UserAnswerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAnswerServiceImpl implements UserAnswerService {
    private final UserAnswerRepository userAnswerRepository;

    public UserAnswerServiceImpl(UserAnswerRepository userAnswerRepository) {
        this.userAnswerRepository = userAnswerRepository;
    }

    public List<UserAnswer> findLastResultByUsername(String username) {
        return userAnswerRepository.findLastResultByUsername(username);
    }

    public List<UserAnswer> findAllResultsByUsername(String username) {
        return userAnswerRepository.findAllResultsByUsername(username);
    }

    public void save(List<UserAnswer> userAnswers) {
        userAnswerRepository.save(userAnswers);
    }
}
