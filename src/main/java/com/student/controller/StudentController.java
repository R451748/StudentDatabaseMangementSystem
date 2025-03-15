package com.student.controller;

import java.util.Optional;  

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.student.entity.Student;
import com.student.repository.StudentRepository;
import com.student.service.StudentService;

@Controller  
@RequestMapping("/students")
@CrossOrigin(origins = "http://localhost:3000")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // ✅ Show Add Student Form
    @GetMapping("/add")
    public String showAddStudentForm(Model model) {
        model.addAttribute("student", new Student());  
        return "add-student";  
    }

    // ✅ Handle Add Student Form Submission
    @PostMapping("/add")
    public String addStudent(@ModelAttribute("student") Student student) {  
        Student savedStudent = studentService.addStudent(student);
        System.out.println("Saved student: " + savedStudent); // Just to check if this prints
        return "redirect:/students"; // Redirecting to the list page
    }


    @GetMapping("/success")
    public String showSuccessPage() {
        return "success"; // This will show a "success.html" page
    }
    // ✅ Show Student List Page
    @GetMapping
    public String listStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "student-list";  
    }

    // ✅ Show Student Details Page
    @GetMapping("/details/{id}")
    public String getStudentDetails(@PathVariable Long id, Model model) {
        Optional<Student> optionalStudent = studentService.getStudentById(id);

        if (optionalStudent.isPresent()) {
            model.addAttribute("student", optionalStudent.get());
            return "student-details";
        } else {
            return "error";  
        }
    }

    // ✅ Show All Student Details in Table
    @GetMapping("/details")
    public String showAllStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "student-details";  
    }

    // ✅ Show Edit Form for Student
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Optional<Student> optionalStudent = studentService.getStudentById(id);
        if (optionalStudent.isPresent()) {
            model.addAttribute("student", optionalStudent.get());
            return "edit-student";  // This should be a valid Thymeleaf template
        } else {
            model.addAttribute("error", "Student not found!");
            return "error";  // Ensure "error.html" exists
        }
    }


    // ✅ Handle Update Student Form Submission
    @PostMapping("/update")
    public String updateStudent(@ModelAttribute Student student) {
        studentService.updateStudent(student.getId(), student);
        return "redirect:/students";
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        try {
            studentService.deleteStudent(id);
            return ResponseEntity.ok("Student deleted successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting student.");
        }
    }
    // ✅ Search Student by Roll Number
    @GetMapping("/search")
    public String searchStudentByRollNumber(@RequestParam(value = "rollNumber", required = false) String rollNumber, Model model) {
        if (rollNumber == null || rollNumber.trim().isEmpty()) {
            model.addAttribute("error", "Roll Number cannot be empty!");
            return "search";  // Keep the user on the search page
        }

        System.out.println("🔍 Searching for student with Roll Number: " + rollNumber); // Debugging

        Optional<Student> studentOptional = studentService.getStudentByRollNumber(rollNumber);

        if (studentOptional.isPresent()) {
            System.out.println("✅ Student found: " + studentOptional.get().getName()); // Debugging
            model.addAttribute("student", studentOptional.get());
        } else {
            System.out.println("❌ No student found with Roll Number: " + rollNumber);
            model.addAttribute("error", "Student not found!");
        }

        return "search";  // Always return "search.html"
    }

}
