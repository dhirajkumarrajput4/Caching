package com.sping.Caching.service;

import com.sping.Caching.entities.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    Optional<Student> findById(Long id);

    List<Student> findAll();

    void save(Student student);


}
