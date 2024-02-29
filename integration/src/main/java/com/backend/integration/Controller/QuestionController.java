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

import com.backend.integration.Entity.Question;
import com.backend.integration.Service.QuestionService;


@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin("http://localhost:5173")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/questions")
    public List<Question> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("/questionbyquiz/{quizId}")
    public List<Question> getQuestionsByQuizId(@PathVariable Long quizId) {
        return questionService.getQuestionsByQuizId(quizId);
    }

    @GetMapping("/question/{question_id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable Long question_id) {
        Optional<Question> question = questionService.getQuestionById(question_id);
        return question.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/question")
    public ResponseEntity<Question> createQuestion(@RequestBody Question question) {
        Question createdQuestion = questionService.saveOrUpdateQuestion(question);
        return new ResponseEntity<>(createdQuestion, HttpStatus.CREATED);
    }

    @PutMapping("/question/{question_id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable Long question_id, @RequestBody Question questionDetails) {
        Optional<Question> question = questionService.getQuestionById(question_id);
        if (question.isPresent()) {
            Question updatedQuestion = questionService.saveOrUpdateQuestion(questionDetails);
            return new ResponseEntity<>(updatedQuestion, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/question/{question_id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long question_id) {
        questionService.deleteQuestion(question_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
