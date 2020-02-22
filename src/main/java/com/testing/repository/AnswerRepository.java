package com.testing.repository;

import com.testing.domain.Answer;

import java.util.List;

public interface AnswerRepository {

    void save(List<Answer> answers);

    List<Answer> findByQuestion(String question);

    List<Answer> findCorrectAnswerByQuestion(String question);
}
