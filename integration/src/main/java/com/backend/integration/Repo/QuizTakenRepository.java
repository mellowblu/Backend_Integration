package com.backend.integration.Repo;






import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.integration.Entity.QuizTaken;

public interface QuizTakenRepository extends JpaRepository<QuizTaken, Long> {

     @Query("SELECT qt FROM QuizTaken qt WHERE qt.enrollment.enrollmentId = :enrollmentId")
    List<QuizTaken> findByEnrollmentId(@Param("enrollmentId") Long enrollmentId);

    @Query("SELECT AVG(qt.score) FROM QuizTaken qt WHERE qt.enrollment.enrollmentId = :enrollmentId")
    Double findAverageScoreByEnrollmentId(@Param("enrollmentId") Long enrollmentId);
}
