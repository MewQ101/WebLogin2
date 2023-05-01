package com.example.demo.Validators;

import com.example.demo.entity.Student;
import com.example.demo.exceptions.AlreadyUsed;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LoginValidator implements Validator {
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
        if (count2 == 0) {
            throw new AlreadyUsed("This account isn't created or incorrect UserName");
        } else {
            String studentEmail = student.getEmail();
            List<Student> students = studentService.getAllStudents();
            long count = students.stream()
                    .map(x -> x.getEmail())
                    .filter(email -> email.equals(studentEmail))
                    .count();
            if(count == 0) {
                throw new AlreadyUsed("This account isn't created or incorrect Email");
            } else {
                String studentPassword = student.getPassword();
                List<Student> students3 = studentService.getAllStudents();
                long count3 = students3.stream()
                        .map(x -> x.getPassword())
                        .filter(password -> password.equals(studentPassword))
                        .count();
                if(count3 == 0) {
                    throw new AlreadyUsed("This account isn't created or incorrect Password");
                }
            }
        }
    }
}
