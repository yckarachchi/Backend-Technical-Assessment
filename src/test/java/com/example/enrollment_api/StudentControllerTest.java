package com.example.enrollment_api;

import com.example.enrollment_api.model.Student;
import com.example.enrollment_api.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class StudentControllerTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    void testCreateStudent() {
        Student student = new Student();
        student.setFirstName("John");
        student.setLastName("Doe");
        student.setEmail("john.doe@test.com");
        student.setAge(22);
        
        Student saved = studentRepository.save(student);
        assertNotNull(saved.getId()); // Test 1: Ensure student is saved and ID is generated
    }

    @Test
    void testSoftDeleteStudent() {
        Student student = new Student();
        student.setFirstName("Jane");
        student.setLastName("Doe");
        student.setEmail("jane.doe@test.com");
        Student saved = studentRepository.save(student);
        
        saved.setIsActive(false);
        Student deleted = studentRepository.save(saved);
        
        assertTrue(!deleted.getIsActive()); // Test 2: Ensure soft delete works
    }
}

