package com.student.service;

import com.student.entity.Mark;
import com.student.repository.MarksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarkService {

    @Autowired
    private MarksRepository marksRepository;

    public List<Mark> getAllMarks() {
        return marksRepository.findAll();
    }

    public Optional<Mark> getMarkById(Long id) {
        return marksRepository.findById(id);
    }

    public Mark saveMark(Mark mark) {
        return marksRepository.save(mark);
    }

    public void deleteMark(Long id) {
        marksRepository.deleteById(id);
    }
}