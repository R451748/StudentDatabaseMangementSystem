package com.student.service;

import com.student.entity.Department;
import com.student.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Optional<Department> getDepartmentById(Long id) {
        return departmentRepository.findById(id);
    }

    public Department addDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public Department updateDepartment(Long id, Department updatedDepartment) {
        return departmentRepository.findById(id).map(department -> {
            department.setDepName(updatedDepartment.getDepName());
            department.setDepCode(updatedDepartment.getDepCode());
            return departmentRepository.save(department);
        }).orElseThrow(() -> new IllegalArgumentException("Department not found with id: " + id));
    }

    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }
}