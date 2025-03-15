package com.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.student.entity.Course;
import com.student.entity.User;
import com.student.service.CourseService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    // Show Course Form
    @GetMapping("/add")
    public String showAddCourseForm(Model model) {
        model.addAttribute("course", new Course());
        return "add_course";  // HTML page for adding a course
    }

    // Save Course
    @PostMapping("/save")
    public String saveCourse(@ModelAttribute Course course) {
        courseService.addCourse(course);
        return "redirect:/course/list";
    }

    @GetMapping("/list")
    public String listCourses(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            model.addAttribute("username", user.getUsername());
        }
        model.addAttribute("courses", courseService.getAllCourses());
        return "course_list";
    }
}
