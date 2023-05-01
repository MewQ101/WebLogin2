package com.example.demo.service;

import com.example.demo.Validators.EmailUsedValidator;
import com.example.demo.Validators.LoginValidator;
import com.example.demo.Validators.NameUsedValidator;
import com.example.demo.Validators.Validator;
import com.example.demo.entity.Student;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

@Service
public class ValidatorService {
    private LinkedList<Validator> validators = new LinkedList<>();

    public ValidatorService(EmailUsedValidator validateAlreadyUsedEmail, NameUsedValidator nameUsedValidator) {
        validators.add(validateAlreadyUsedEmail);
        validators.add(nameUsedValidator);
    }

    public void executeAll(Student student) {
        validators.forEach(validator -> validator.execute(student));
    }
}