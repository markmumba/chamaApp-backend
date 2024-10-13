package com.chamaApp.backend.Exception.members;

public class MemberNotCreatedException extends RuntimeException {
    public MemberNotCreatedException(String message) {
        super(message);
    }
}
