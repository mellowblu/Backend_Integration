package com.backend.integration.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.integration.Entity.Question;
import com.backend.integration.Repo.QuestionRepository;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public List<Question> getQuestionsByQuizId(Long quizId) {
        return questionRepository.findByQuizId(quizId);
    }

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public Optional<Question> getQuestionById(Long question_id) {
        return questionRepository.findById(question_id);
    }

    public Question saveOrUpdateQuestion(Question question) {
        return questionRepository.save(question);
    }

    public void deleteQuestion(Long question_id) {
        questionRepository.deleteById(question_id);
    }

}
