package com.backend.integration.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.integration.Entity.Quiz;
import com.backend.integration.Service.QuizService;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin("http://localhost:5173")
public class QuizController {
     @Autowired
    private QuizService quizService;

    @GetMapping("/quizzes")
    public List<Quiz> getAllQuizzes() {
        return quizService.getAllQuizzes();
    }

    @GetMapping("/quiz/{quiz_id}")
    public ResponseEntity<Quiz> getQuizById(@PathVariable Long quiz_id) {
        Optional<Quiz> quiz = quizService.getQuizById(quiz_id);
        return quiz.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/quiz")
    public ResponseEntity<Quiz> createQuiz(@RequestBody Quiz quiz) {
        Quiz createdQuiz = quizService.saverOrUpdateQuiz(quiz);
        return new ResponseEntity<>(createdQuiz, HttpStatus.CREATED);
    }

    @PutMapping("/quiz/{quiz_id}")
    public ResponseEntity<Quiz> updateQuiz(@PathVariable Long quiz_id, @RequestBody Quiz quizDetails) {
        Optional<Quiz> quiz = quizService.getQuizById(quiz_id);
        if (quiz.isPresent()) {
            Quiz updatedQuiz = quizService.saverOrUpdateQuiz(quizDetails);
            return new ResponseEntity<>(updatedQuiz, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/quiz/{quiz_id}")
    public ResponseEntity<Void> deleteQuiz(@PathVariable Long quiz_id) {
        quizService.deleteQuiz(quiz_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
