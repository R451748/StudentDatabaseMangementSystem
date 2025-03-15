package com.student.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student.entity.Student;
import com.student.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    // ✅ Create a new student
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    // ✅ Get all students
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // ✅ Get student by ID (Long)
    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public Optional<Student> getStudentByRollNumber(String rollNumber) {
        Optional<Student> student = studentRepository.findByRollNumberIgnoreCase(rollNumber);
        
        if (student.isPresent()) {
            System.out.println("✅ Student Found: " + student.get().getName()); // Debugging statement
        } else {
            System.out.println("❌ Student Not Found with Roll Number: " + rollNumber); // Debugging statement
        }
        
        return student;
    }


    // ✅ Update student by ID (Long)
    public Student updateStudent(Long id, Student studentDetails) {
        Student student = studentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Student not found"));

        student.setRollNumber(studentDetails.getRollNumber());
        student.setName(studentDetails.getName());
        student.setSemester(studentDetails.getSemester());
        student.setGender(studentDetails.getGender());
        student.setBranch(studentDetails.getBranch());
        student.setEmail(studentDetails.getEmail());
        student.setAddress(studentDetails.getAddress());

        return studentRepository.save(student);
    }

    // ✅ Delete student by ID (Long)
    public void deleteStudent(Long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
        } else {
            throw new RuntimeException("Student not found with ID: " + id);
        }
    }
}
