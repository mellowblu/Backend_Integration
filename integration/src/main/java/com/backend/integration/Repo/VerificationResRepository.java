package com.backend.integration.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.integration.Dto.Response.VerificationResponse;
import com.backend.integration.Entity.Certification;

public interface VerificationResRepository extends JpaRepository<Certification, Long> {

    @Query("SELECT new com.backend.integration.Dto.Response.VerificationResponse(cr.serial_no, cr.finalScore.enrollment.course.course_title, cr.finalScore.enrollment.user_id.firstName, cr.finalScore.enrollment.user_id.lastName) FROM Certification cr WHERE cr.serial_no = :serial_no")
    List<VerificationResponse> findBySerialNumberWithDetails(
        @Param("serial_no") String serial_no
    );
}
