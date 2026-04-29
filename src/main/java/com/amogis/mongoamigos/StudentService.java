package com.amogis.mongoamigos;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepo repo;


    public List<Student> getAll() {
    return repo.findAll();
    }
}
