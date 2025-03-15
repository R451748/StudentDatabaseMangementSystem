package com.student.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "attendance")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String studentName;

    @Column(nullable = false)
    private String rollNumber;

    @Column(nullable = false)
    private String courseId;

    @Column(nullable = false)
    private String attendanceStatus; // "Present" or "Absent"

    @Column(nullable = true)
    private Float attendancePercentage; // Added the attendance_percentage field

    public Attendance() {}

    public Attendance(String studentName, String rollNumber, String courseId, String attendanceStatus, Float attendancePercentage) {
        this.studentName = studentName;
        this.rollNumber = rollNumber;
        this.courseId = courseId;
        this.attendanceStatus = attendanceStatus;
        this.attendancePercentage = attendancePercentage;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getAttendanceStatus() {
        return attendanceStatus;
    }

    public void setAttendanceStatus(String attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }

    public Float getAttendancePercentage() {
        return attendancePercentage;
    }

    public void setAttendancePercentage(Float attendancePercentage) {
        this.attendancePercentage = attendancePercentage;
    }
}
