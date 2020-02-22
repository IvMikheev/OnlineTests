package com.testing.service;

import com.testing.domain.Answer;
import com.testing.domain.Question;

import java.util.List;
import java.util.Map;

public interface QuestionAndAnswerService {

    Map<String, List<Answer>> findFiveRandomQuestions();

    String findQuestionByText(String text);

    String findCorrectAnswerByQuestion(String question);

    void addQuestionAndAnswer(Question question, List<Answer> answers);
}
