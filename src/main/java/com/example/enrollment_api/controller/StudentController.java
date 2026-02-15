package com.example.enrollment_api.controller;


import com.example.enrollment_api.model.Course;
import com.example.enrollment_api.model.Student;
import com.example.enrollment_api.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        return ResponseEntity.ok(studentService.createStudent(student));
    }

    @GetMapping
    public ResponseEntity<Page<Student>> getStudents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "true") Boolean is_active) {
        return ResponseEntity.ok(studentService.getStudents(search, is_active, page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable UUID id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable UUID id, @RequestBody Student student) {
        return ResponseEntity.ok(studentService.updateStudent(id, student));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable UUID id) {
        studentService.softDeleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    // Enrollment Endpoints
    @PostMapping("/{student_id}/enroll/{course_id}")
    public ResponseEntity<Student> enroll(@PathVariable UUID student_id, @PathVariable Long course_id) {
        return ResponseEntity.ok(studentService.enrollStudentInCourse(student_id, course_id));
    }

    @GetMapping("/{student_id}/courses")
    public ResponseEntity<Set<Course>> getCourses(@PathVariable UUID student_id) {
        return ResponseEntity.ok(studentService.getStudentCourses(student_id));
    }
}
