package com.backend.integration.Repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.integration.Entity.Certification;
import com.backend.integration.Entity.FinalScore;

public interface CertificationRepository extends JpaRepository<Certification, Long>{
     // Method to retrieve all certifications
    List<Certification> findAll();

    // Method to retrieve a certification by its ID
    Optional<Certification> findById(Long certificateID);

    // Custom query to retrieve certifications based on user ID
    // @Query("SELECT cr FROM Certification cr WHERE cr.quizTaken.users.userID = :user_ID")
    // List<Certification> findByUserId(@Param("user_ID") Long user_ID);

    // Custom query to retrieve certifications based on user ID
  @Query("SELECT cr FROM Certification cr WHERE cr.finalScore.enrollment.user_id.user_id = :user_id")
  List<Certification> findByUserId(@Param("user_id") Long user_id);

  // boolean existsByFinalScore_FscoreId(Long fscore_id);
  // boolean existsByFinalScoreFscoreId(Long fscore_id);

}
