package com.student.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long depId;

    @Column(nullable = false)
    private String depName;

    @Column(nullable = false)
    private String depCode;

    // Constructors
    public Department() {}

    public Department(String depName, String depCode) {
        this.depName = depName;
        this.depCode = depCode;
    }

    // Getters and Setters
    public Long getDepId() {
        return depId;
    }

    public void setDepId(Long depId) {
        this.depId = depId;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public String getDepCode() {
        return depCode;
    }

    public void setDepCode(String depCode) {
        this.depCode = depCode;
    }
}