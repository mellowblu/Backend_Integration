package com.backend.integration.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.integration.Entity.Enrollment;
import com.backend.integration.Entity.FinalScore;
import com.backend.integration.Entity.QuizTaken;
import com.backend.integration.Repo.EnrollmentRepository;
import com.backend.integration.Repo.FinalScoreRepository;
import com.backend.integration.Repo.QuizTakenRepository;

@Service
public class FinalScoreService {

    @Autowired
    private FinalScoreRepository finalScoreRepository;

    // public List<FinalScore> findAllByEnrollmentIdFromQuizTaken(Long enrollmentId) {
    //     return finalScoreRepository.findAllByEnrollmentIdFromQuizTaken(enrollmentId);
    // }

    @Autowired
    private QuizTakenRepository quizTakenRepository;
    
    @Autowired
    private EnrollmentRepository enrollmentRepository;  // Add the EnrollmentRepository

   

    public void calculateAndSaveAverageScore(Long enrollmentId) {
        List<QuizTaken> quizTakenList = quizTakenRepository.findByEnrollmentId(enrollmentId);

        if (!quizTakenList.isEmpty()) {
            // Calculate the average score
            double averageScore = quizTakenList.stream().mapToDouble(QuizTaken::getScore).average().orElse(0.0);

            // Save the average score to FinalScore entity
            FinalScore finalScore = new FinalScore();
            finalScore.setFinal_score((int) averageScore);
            finalScore.setEnd_date(LocalDate.now());

            // Set remarks based on average score
            if (averageScore >= 80) {
                finalScore.setRemarks("PASSED");
            } else {
                finalScore.setRemarks("FAILED");
            }

            // Set the Enrollment for this FinalScore
            Enrollment enrollment = enrollmentRepository.findById(enrollmentId).orElse(null);
            finalScore.setEnrollment(enrollment);

            finalScoreRepository.save(finalScore);
        }
    }

    // You can add more methods as needed

    public List<FinalScore> getAllFinalScores() {
        return finalScoreRepository.findAll();
    }

    public Optional<FinalScore> getFinalScoreById(Long fscore_id) {
        return finalScoreRepository.findById(fscore_id);
    }

    public FinalScore saveOrUpdateFinalScore(FinalScore finalScore) {
        return finalScoreRepository.save(finalScore);
    }

    public void deleteFinalScore(Long fscore_id) {
        finalScoreRepository.deleteById(fscore_id);
    }

}
