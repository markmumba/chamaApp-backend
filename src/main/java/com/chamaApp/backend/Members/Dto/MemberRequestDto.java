package com.chamaApp.backend.Members.Dto;

public record MemberRequestDto(
    String name,
    String email,
    String password
) {
}
