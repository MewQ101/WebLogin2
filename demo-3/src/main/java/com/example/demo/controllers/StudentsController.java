package com.example.demo.controllers;

import com.example.demo.Validators.EmailUsedValidator;
import com.example.demo.Validators.LoginValidator;
import com.example.demo.entity.Student;
import com.example.demo.exceptions.AlreadyUsed;
import com.example.demo.service.StudentService;
import com.example.demo.service.ValidatorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class StudentsController {

    private StudentService studentService;
    private ValidatorService validatorService;
    private LoginValidator loginValidator;


    @GetMapping("/students")
    public String students(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "students";
    }

    @GetMapping("/students/new")
    public String createStudentsForm(Model model) {
        model.addAttribute("student", new Student());
        return "create_student";
    }

    @PostMapping("/students")
    public String saveStudents(@ModelAttribute("student") Student student) {
        validatorService.executeAll(student);
        studentService.saveStudent(student);
        return "redirect:/students";

    }

    @GetMapping("/students/error_used")
    public String throwError(Model model) {
        return "error_used";
    }

    @GetMapping("/students/{id}")
    public String deleteStudentById(@PathVariable Long id) {
        deleteStudent(id);
        return "redirect:/students";
    }

    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudentById(id);
    }

    @PostMapping("/students/edit/{id}")
    public String updateStudent(@PathVariable Long id, @ModelAttribute("editStudent") Student student) {
        Student existingStudent = studentService.getStudentById(id);
        existingStudent.setUserName(student.getUserName());
        existingStudent.setPassword(student.getPassword());
        existingStudent.setEmail(student.getEmail());
        studentService.updateStudent(existingStudent);
        return "redirect:/students";
    }

    @GetMapping("/students/edit/{id}")
    public String editStudentForm(@PathVariable Long id, Model model) {
        model.addAttribute("editStudent", studentService.getStudentById(id));
        return "edit";
    }

    @GetMapping("/students/login")
    public String login(Model model) {
        model.addAttribute("login", studentService.getAllStudents());
        return "login";
    }

    @PostMapping("/students/login")
    public String loginStudent(@ModelAttribute("login") Student student) {
        loginValidator.execute(student);
        return "redirect:/students";
    }
}