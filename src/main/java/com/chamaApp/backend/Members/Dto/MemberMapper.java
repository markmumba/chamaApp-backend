package com.chamaApp.backend.Members.Dto;

import com.chamaApp.backend.Members.Member;
import org.springframework.stereotype.Service;

@Service
public class MemberMapper {

    public Member fromRequestToMember (MemberRequestDto memberRequestDto) {
        Member member = new Member();
        member.setEmail(memberRequestDto.email());
        member.setName(memberRequestDto.name());
        member.setPassword(memberRequestDto.password());
        return member;
    }

    public MemberResponseDto fromMemberToResponse (Member member) {
        return new MemberResponseDto(
                member.getEmail(),
                member.getName()
        );
    }

}
