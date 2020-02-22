package com.testing.controller;

import com.testing.domain.Answer;
import com.testing.domain.Question;
import com.testing.service.QuestionAndAnswerService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class QuestionAndAnswerController {
    private final QuestionAndAnswerService questionAndAnswerService;

    public QuestionAndAnswerController(QuestionAndAnswerService questionAndAnswerService) {
        this.questionAndAnswerService = questionAndAnswerService;
    }

    @GetMapping("/question")
    public String question() {
        return "question";
    }

    @PostMapping("/question")
    public String addQuestion(@RequestParam(value = "text") String text,
                              @RequestParam(value = "answer1") String answer1,
                              @RequestParam(value = "correct1", required = false) boolean correct1,
                              @RequestParam(value = "answer2") String answer2,
                              @RequestParam(value = "correct2", required = false) boolean correct2,
                              @RequestParam(value = "answer3") String answer3,
                              @RequestParam(value = "correct3", required = false) boolean correct3,
                              @RequestParam(value = "answer4") String answer4,
                              @RequestParam(value = "correct4", required = false) boolean correct4,
                              Model model) {

        if (text.isEmpty()) {
            model.addAttribute("message", "Enter the question!");
            return "/question";
        }

        if (!questionAndAnswerService.findQuestionByText(text).isEmpty()) {
            model.addAttribute("message", "Question exists!");
            return "/question";
        }

        if (answer1.isEmpty() | answer2.isEmpty() | answer3.isEmpty() | answer4.isEmpty()) {
            model.addAttribute("answer", "Fill in all the answers!");
            return "/question";
        }

        if (!correct1 & !correct2 & !correct3 & !correct4) {
            model.addAttribute("correct", "Choose the correct answer!");
            return "/question";
        }

        List<Answer> answers = new ArrayList<>();
        answers.add(new Answer(answer1, text, correct1));
        answers.add(new Answer(answer2, text, correct2));
        answers.add(new Answer(answer3, text, correct3));
        answers.add(new Answer(answer4, text, correct4));

        questionAndAnswerService.addQuestionAndAnswer(new Question(text), answers);

        return "question";
    }
}
