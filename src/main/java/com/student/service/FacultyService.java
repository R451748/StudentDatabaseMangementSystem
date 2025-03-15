package com.student.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student.entity.Faculty;
import com.student.repository.FacultyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FacultyService {

    @Autowired
    private FacultyRepository facultyRepository;

    // Create a new faculty
    public Faculty addFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    // Get all faculty members
    public List<Faculty> getAllFaculty() {
        return facultyRepository.findAll();
    }

    // Get faculty by ID
    public Optional<Faculty> getFacultyById(Long id) {
        return facultyRepository.findById(id);
    }

    // Update faculty
    public Faculty updateFaculty(Long id, Faculty facultyDetails) {
        Faculty faculty = facultyRepository.findById(id).orElseThrow(() -> new RuntimeException("Faculty not found"));

        faculty.setUsername(facultyDetails.getUsername());
        faculty.setEmail(facultyDetails.getEmail());
        faculty.setPassword(facultyDetails.getPassword());
        faculty.setDid(facultyDetails.getDid());

        return facultyRepository.save(faculty);
    }

    // Delete faculty
    public void deleteFaculty(Long id) {
        facultyRepository.deleteById(id);
    }
}
