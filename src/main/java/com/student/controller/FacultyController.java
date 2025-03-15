package com.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.student.entity.Faculty;
import com.student.service.FacultyService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/faculty")
@CrossOrigin(origins = "http://localhost:3000") // Allow frontend requests
public class FacultyController {

    @Autowired
    private FacultyService facultyService;

    // Serve Add Faculty Page
    @GetMapping("/add")
    public String showAddFacultyPage() {
        return "add_faculty"; // Refers to src/main/resources/templates/add_faculty.html
    }

    // Serve Add Attendance Page
    @GetMapping("/add_attendance")
    public String showAddAttendancePage() {
        return "add_attendance"; // Refers to src/main/resources/templates/add_attendance.html
    }

    // REST API: Create Faculty
    @PostMapping("/api/add")
    public String addFaculty(Faculty faculty) {
        facultyService.addFaculty(faculty);
        return "redirect:/faculty/all"; // Redirect to show all faculty
    }

    // Serve All Faculty Page
    @GetMapping("/all")
    public String showAllFacultyPage(Model model) {
        List<Faculty> facultyList = facultyService.getAllFaculty();
        model.addAttribute("facultyList", facultyList);
        return "faculty_list"; // A new HTML page to show the list
    }

    // REST API: Get All Faculty
    @GetMapping("/api/all")
    @ResponseBody
    public List<Faculty> getAllFaculty() {
        return facultyService.getAllFaculty();
    }

    // REST API: Get Faculty by ID
    @GetMapping("/api/{id}")
    @ResponseBody
    public Optional<Faculty> getFacultyById(@PathVariable Long id) {
        return facultyService.getFacultyById(id);
    }

    // REST API: Update Faculty
    @PutMapping("/api/update/{id}")
    @ResponseBody
    public Faculty updateFaculty(@PathVariable Long id, @RequestBody Faculty facultyDetails) {
        return facultyService.updateFaculty(id, facultyDetails);
    }

    // REST API: Delete Faculty
    @DeleteMapping("/api/delete/{id}")
    @ResponseBody
    public String deleteFaculty(@PathVariable Long id) {
        facultyService.deleteFaculty(id);
        return "Faculty deleted successfully!";
    }
    
}
