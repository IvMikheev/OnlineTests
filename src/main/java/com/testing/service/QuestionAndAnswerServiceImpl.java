package com.testing.service;

import com.testing.domain.Answer;
import com.testing.domain.Question;
import com.testing.repository.AnswerRepository;
import com.testing.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QuestionAndAnswerServiceImpl implements QuestionAndAnswerService {
    private QuestionRepository questionRepository;
    private AnswerRepository answerRepository;

    @Autowired
    public void setQuestionRepository(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Autowired
    public void setAnswerRepository(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public Map<String, List<Answer>> findFiveRandomQuestions() {
        Map<String, List<Answer>> questionsAndAnswers = new HashMap<>();

        for (String question : questionRepository.findFiveRandomQuestions()) {
            questionsAndAnswers.put(question, answerRepository.findByQuestion(question));
        }

        return questionsAndAnswers;
    }

    public String findQuestionByText(String text) {
        List<String> questions = questionRepository.findQuestionByText(text);

        if (questions.isEmpty()) {
            return "";
        } else {
            return questions.get(0);
        }
    }

    public String findCorrectAnswerByQuestion(String question) {
        List<Answer> answers = answerRepository.findCorrectAnswerByQuestion(question);

        if (answers.isEmpty()) {
            return "";
        } else {
            return answers.get(0).getText();
        }
    }

    public void addQuestionAndAnswer(Question question, List<Answer> answers) {
        questionRepository.save(question);
        answerRepository.save(answers);
    }
}
