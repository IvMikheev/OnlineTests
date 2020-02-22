package com.testing.controller;

import com.testing.domain.User;
import com.testing.domain.UserAnswer;
import com.testing.service.QuestionAndAnswerService;
import com.testing.service.UserAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserAnswerController {
    private QuestionAndAnswerService questionAndAnswerService;
    private UserAnswerService userAnswerService;

    @Autowired
    public void setUserAnswerService(UserAnswerService userAnswerService) {
        this.userAnswerService = userAnswerService;
    }

    @Autowired
    public void setQuestionAndAnswerService(QuestionAndAnswerService questionAndAnswerService) {
        this.questionAndAnswerService = questionAndAnswerService;
    }

    @GetMapping("/test")
    public String test(Model model) {
        model.addAttribute("entrySet", questionAndAnswerService.findFiveRandomQuestions().entrySet());
        return "test";
    }

    @PostMapping("/test")
    public String test(@AuthenticationPrincipal User user,
                        @RequestParam(value = "question") String[] questions,
                        @RequestParam(value = "answer") String[] answers) {

        List<UserAnswer> userAnswers = new ArrayList<>();

        for (int i = 0; i < questions.length; i++) {
            boolean correct = false;

            if (answers[i].equals(questionAndAnswerService.findCorrectAnswerByQuestion(questions[i]))) {
                correct = true;
            }

            userAnswers.add(new UserAnswer(user.getUsername(), questions[i], answers[i], correct));
        }

        userAnswerService.save(userAnswers);

        return "redirect:/result";
    }

    @GetMapping("/result")
    public String result(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("title", "Test result");
        model.addAttribute("h2", "Test result");
        model.addAttribute("userAnswers", userAnswerService.findLastResultByUsername(user.getUsername()));
        return "result";
    }

    @GetMapping("/allAnswers")
    public String allAnswers(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("title", "All answers");
        model.addAttribute("h2", "All answers");
        model.addAttribute("userAnswers", userAnswerService.findAllResultsByUsername(user.getUsername()));
        return "result";
    }
}
