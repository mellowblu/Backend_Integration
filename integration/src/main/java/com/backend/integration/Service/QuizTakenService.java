package com.backend.integration.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.integration.Entity.Quiz;
import com.backend.integration.Entity.QuizTaken;
import com.backend.integration.Repo.QuizRepository;
import com.backend.integration.Repo.QuizTakenRepository;

@Service
public class QuizTakenService {

     @Autowired
    private QuizTakenRepository quizTakenRepository;

    public List<QuizTaken> getAllQuizTakens() {
        return quizTakenRepository.findAll();
    }

    public Optional<QuizTaken> getQuizTakenById(Long quiz_taken_id) {
        return quizTakenRepository.findById(quiz_taken_id);
    }

    public QuizTaken saverOrUpdateQuizTaken(QuizTaken quizTaken) {
        return quizTakenRepository.save(quizTaken);
    }

    public void deleteQuizTaken(Long quiz_taken_id) {
        quizTakenRepository.deleteById(quiz_taken_id);
    }
}
