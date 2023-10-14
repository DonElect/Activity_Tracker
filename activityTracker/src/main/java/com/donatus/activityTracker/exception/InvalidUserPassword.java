package com.donatus.activityTracker.exception;

public class InvalidUserPassword extends RuntimeException{
    public InvalidUserPassword(String massage) {
        super(massage);
    }
}
