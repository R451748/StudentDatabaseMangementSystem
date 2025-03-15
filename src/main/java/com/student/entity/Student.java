package com.student.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String rollNumber;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int semester;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private String branch;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String address;

    // Constructors
    public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

    
    public Student(Long id, String rollNumber, String name, int semester, String gender, String branch, String email,
			String address) {
		this.id = id;
		this.rollNumber = rollNumber;
		this.name = name;
		this.semester = semester;
		this.gender = gender;
		this.branch = branch;
		this.email = email;
		this.address = address;
	}


	// Getters and Setters
 

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
