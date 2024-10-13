package com.chamaApp.backend.Exception.members;

public class MemberNotDeletedException extends RuntimeException {
    public MemberNotDeletedException(String message) {
        super(message);
    }
}
