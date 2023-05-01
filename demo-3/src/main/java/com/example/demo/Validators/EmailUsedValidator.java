package com.example.demo.Validators;

import com.example.demo.entity.Student;
import com.example.demo.exceptions.AlreadyUsed;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmailUsedValidator implements Validator{
    @Autowired
    private StudentService studentService;

    @Override
    public void execute(Student student) {
        String studentEmail = student.getEmail();
        List<Student> students = studentService.getAllStudents();
        long count = students.stream()
                .map(x -> x.getEmail())
                .filter(email -> email.equals(studentEmail))
                .count();
        if(count > 0) {
            throw new AlreadyUsed("Email already used");
        }
    }
}
