package com.backend.integration.Repo;




import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.integration.Entity.Enrollment;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
     
    @Query("SELECT e FROM Enrollment e WHERE e.user_id.user_id = :userId")
    List<Enrollment> findEnrollmentsByUserId(@Param("userId") int userId);
}
