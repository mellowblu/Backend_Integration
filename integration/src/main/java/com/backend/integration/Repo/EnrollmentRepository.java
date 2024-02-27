package com.backend.integration.Repo;




import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.integration.Entity.Enrollment;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
     
 
}
