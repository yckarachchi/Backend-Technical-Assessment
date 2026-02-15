package com.example.enrollment_api.repository;

import com.example.enrollment_api.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.UUID;

public interface StudentRepository extends JpaRepository<Student, UUID> {
    
    // Supports pagination and searching by name/email, and filtering by active status 
    @Query("SELECT s FROM Student s WHERE s.isActive = :isActive AND " +
           "(:search IS NULL OR LOWER(s.firstName) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(s.lastName) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(s.email) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<Student> findFilteredStudents(@Param("search") String search, 
                                       @Param("isActive") Boolean isActive, 
                                       Pageable pageable);
}
