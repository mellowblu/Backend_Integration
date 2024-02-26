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


import com.backend.integration.Entity.QuizTaken;

import com.backend.integration.Service.QuizTakenService;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin("http://localhost:5173")
public class QuizTakenController {
     @Autowired
    private QuizTakenService quizTakenService;

    @GetMapping("/quiztakens")
    public List<QuizTaken> getAllQuizTakens() {
        return quizTakenService.getAllQuizTakens();
    }

    @GetMapping("/quiztaken/{quiz_taken_id}")
    public ResponseEntity<QuizTaken> getQuizTakenById(@PathVariable Long quiz_taken_id) {
        Optional<QuizTaken> quizTaken = quizTakenService.getQuizTakenById(quiz_taken_id);
        return quizTaken.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/quiztaken")
    public ResponseEntity<QuizTaken> createQuizTaken(@RequestBody QuizTaken quizTaken) {
        QuizTaken createdQuizTaken = quizTakenService.saverOrUpdateQuizTaken(quizTaken);
        return new ResponseEntity<>(createdQuizTaken, HttpStatus.CREATED);
    }

    @PutMapping("/quiztaken/{quiz_taken_id}")
    public ResponseEntity<QuizTaken> updateQuizTaken(@PathVariable Long quiz_taken_id, @RequestBody QuizTaken quizTakenDetails) {
        Optional<QuizTaken> quizTaken = quizTakenService.getQuizTakenById(quiz_taken_id);
        if (quizTaken.isPresent()) {
            QuizTaken updatedQuizTaken = quizTakenService.saverOrUpdateQuizTaken(quizTakenDetails);
            return new ResponseEntity<>(updatedQuizTaken, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/quiztaken/{quiz_taken_id}")
    public ResponseEntity<Void> deleteQuizTaken(@PathVariable Long quiz_taken_id) {
        quizTakenService.deleteQuizTaken(quiz_taken_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
