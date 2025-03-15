package com.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.student.entity.Mark;
import com.student.entity.User;
import com.student.repository.MarksRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/marks")
public class MarksController {

    @Autowired
    private MarksRepository markRepository;

    // View marks
    @GetMapping("/view")
    public String viewMarks(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            model.addAttribute("username", user.getUsername());
        }
        model.addAttribute("marks", markRepository.findAll());
        return "view_marks"; // Thymeleaf template for viewing marks
    }

    // Add marks form
    @GetMapping("/add")
    public String showAddMarksForm(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            model.addAttribute("username", user.getUsername());
        }
        model.addAttribute("mark", new Mark());
        return "add_marks"; // Thymeleaf template for adding marks
    }

    // Save marks
    @PostMapping("/save")
    public String saveMarks(Mark mark) {
        markRepository.save(mark);
        return "redirect:/marks/view";
    }

    // Edit marks form
    @GetMapping("/edit/{id}")
    public String showEditMarksForm(@PathVariable Long id, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            model.addAttribute("username", user.getUsername());
        }

        Mark mark = markRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid mark ID: " + id));
        model.addAttribute("mark", mark);
        return "add_marks"; // Reusing the add form for editing
    }

    // Update marks
    @PostMapping("/update/{id}")
    public String updateMarks(@PathVariable Long id, Mark updatedMark) {
        Mark mark = markRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid mark ID: " + id));

        mark.setStudentName(updatedMark.getStudentName());
        mark.setSubject(updatedMark.getSubject());
        mark.setMarks(updatedMark.getMarks());

        markRepository.save(mark);
        return "redirect:/marks/view";
    }

    // Delete marks
    @GetMapping("/delete/{id}")
    public String deleteMarks(@PathVariable Long id) {
        markRepository.deleteById(id);
        return "redirect:/marks/view";
    }
}
