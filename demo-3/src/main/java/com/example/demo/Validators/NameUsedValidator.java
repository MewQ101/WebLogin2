package com.example.demo.Validators;

import com.example.demo.entity.Student;
import com.example.demo.exceptions.AlreadyUsed;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NameUsedValidator implements Validator {
    @Autowired
    private StudentService studentService;

    @Override
    public void execute(Student student) {
        String studentUserName = student.getUserName();
        List<Student> students2 = studentService.getAllStudents();
        long count2 = students2.stream()
                .map(x -> x.getUserName())
                .filter(userName -> userName.equals(studentUserName))
                .count();
        if (count2 > 0) {
            throw new AlreadyUsed("UserName already used");
        }
    }
}
