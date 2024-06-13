package com.sping.Caching.controller;

import com.sping.Caching.entities.Student;
import com.sping.Caching.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/student")
    public ResponseEntity<?> findStudent(@RequestParam Long id) {
        Optional<Student> studentOptional = studentService.findById(id);
        Optional<Student> student = studentService.findById(id);
        if (studentOptional.isPresent()) {
            return ResponseEntity.ok(studentOptional.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No student found");
        }
    }

    @PostMapping("/student")
    public ResponseEntity<?> addStudent(@RequestBody Student student) {
        if (student == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please put the valid details");
        }
        studentService.save(student);
        return ResponseEntity.status(HttpStatus.CREATED).body("Student added successfully");
    }

    @PutMapping("/student")
    public ResponseEntity<?> updateStudent(@RequestBody Student student) {
        if (student.getId() != null) {
            Optional<Student> studentOptional = studentService.findById(student.getId());
            if (studentOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student Not_Found for update");
            }
            studentOptional.get().setName(student.getName());
            studentOptional.get().setSection(student.getSection());
            studentService.save(studentOptional.get());
            return ResponseEntity.status(HttpStatus.CREATED).body("Update Successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
    }

    @GetMapping("/students")
    public ResponseEntity<?> getAllStudent() {
        List<Student> studentList = studentService.findAll();
        return ResponseEntity.ok(studentList);
    }
}
