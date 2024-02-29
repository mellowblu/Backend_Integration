package com.backend.integration.Repo;


import org.springframework.data.jpa.repository.JpaRepository;


import com.backend.integration.Entity.FinalScore;

public interface FinalScoreRepository extends JpaRepository<FinalScore, Long>{

    // // Retrieve all final scores with a specific enrollmentId from QuizTaken
    // @Query("SELECT fs FROM FinalScore fs JOIN fs.quizTaken qt WHERE qt.enrollment = :enrollmentId")
    // List<FinalScore> findAllByEnrollmentIdFromQuizTaken(@Param("enrollnebt") Long enrollment);
}
