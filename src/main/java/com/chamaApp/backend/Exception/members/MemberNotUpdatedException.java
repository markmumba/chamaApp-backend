package com.chamaApp.backend.Exception.members;

public class MemberNotUpdatedException extends RuntimeException {
    public MemberNotUpdatedException(String message) {
        super(message);
    }
}
