package com.example.demo.Validators;

import com.example.demo.entity.Student;
import com.example.demo.exceptions.AlreadyUsed;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PasswordValidator implements Validator {
    @Autowired
    private StudentService studentService;

    @Override
    public void execute(Student student) {
                String studentPassword = student.getPassword();
                List<Student> students = studentService.getAllStudents();
                long count = students.stream()
                        .map(x -> x.getPassword())
                        .filter(password -> password.equals(studentPassword))
                        .count();
                if(count == 0) {
                    throw new AlreadyUsed("Incorrect Password");
                }
    }
}
