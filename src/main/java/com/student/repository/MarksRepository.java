package com.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.student.entity.Mark;

@Repository
public interface MarksRepository extends JpaRepository<Mark, Long> {
}