package com.example.demo.exceptions;

public class AlreadyUsed extends RuntimeException{
    public AlreadyUsed(String message){
        super(message);
    }
}
