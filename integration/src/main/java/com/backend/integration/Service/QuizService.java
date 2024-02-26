package com.backend.integration.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.integration.Entity.Quiz;
import com.backend.integration.Repo.QuizRepository;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    public Optional<Quiz> getQuizById(Long quiz_id) {
        return quizRepository.findById(quiz_id);
    }

    public Quiz saverOrUpdateQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    public void deleteQuiz(Long quiz_id) {
        quizRepository.deleteById(quiz_id);
    }
}
