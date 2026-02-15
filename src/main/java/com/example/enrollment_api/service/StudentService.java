package com.example.enrollment_api.service;

import com.example.enrollment_api.model.Course;
import com.example.enrollment_api.model.Student;
import com.example.enrollment_api.repository.CourseRepository;
import com.example.enrollment_api.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.Set;
import java.util.UUID;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    
    @Autowired
    private CourseRepository courseRepository;

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Page<Student> getStudents(String search, Boolean isActive, int page, int size) {
        return studentRepository.findFilteredStudents(search, isActive, PageRequest.of(page, size));
    }

    public Student getStudentById(UUID id) {
        return studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
    }

    public Student updateStudent(UUID id, Student updatedStudent) {
        Student existing = getStudentById(id);
        existing.setFirstName(updatedStudent.getFirstName());
        existing.setLastName(updatedStudent.getLastName());
        existing.setAge(updatedStudent.getAge());
        return studentRepository.save(existing);
    }

    // Soft Delete Implementation 
    public void softDeleteStudent(UUID id) {
        Student student = getStudentById(id);
        student.setIsActive(false); 
        studentRepository.save(student);
    }

    // Enrollment Implementation [cite: 54]
    public Student enrollStudentInCourse(UUID studentId, Long courseId) {
        Student student = getStudentById(studentId);
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));
        student.enrollCourse(course);
        return studentRepository.save(student);
    }
    
    // Get Student's Courses [cite: 55]
    public Set<Course> getStudentCourses(UUID studentId) {
        return getStudentById(studentId).getCourses();
    }
}
