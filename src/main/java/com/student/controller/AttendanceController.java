package com.student.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.student.entity.Attendance;
import com.student.service.AttendanceService;

@Controller
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    // Show Attendance Form
    @GetMapping("/add")
    public String showAddAttendanceForm(Model model) {
        model.addAttribute("attendance", new Attendance());
        return "add_attendance";  // HTML page name
    }

    // Save Attendance
    @PostMapping("/save")
    public String saveAttendance(@ModelAttribute Attendance attendance) {
        attendanceService.addAttendance(attendance);
        return "redirect:/attendance/list";
    }

    // List Attendance
    @GetMapping("/list")
    public String listAttendance(Model model) {
        model.addAttribute("attendances", attendanceService.getAllAttendance());
        return "attendance_list";  // HTML page name
    }
    
}
