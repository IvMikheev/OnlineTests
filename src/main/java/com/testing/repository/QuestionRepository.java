package com.testing.repository;

import com.testing.domain.Question;

import java.util.List;

public interface QuestionRepository {

    void save(Question question);

    List<String> findFiveRandomQuestions();

    List<String> findQuestionByText(String text);
}
