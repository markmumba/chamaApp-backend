package com.chamaApp.backend.Members.Impl;

import com.chamaApp.backend.Exception.members.MemberNotCreatedException;
import com.chamaApp.backend.Exception.members.MemberNotDeletedException;
import com.chamaApp.backend.Exception.members.MemberNotFoundException;
import com.chamaApp.backend.Exception.members.MemberNotUpdatedException;
import com.chamaApp.backend.Members.Dto.MemberMapper;
import com.chamaApp.backend.Members.Dto.MemberRequestDto;
import com.chamaApp.backend.Members.Dto.MemberResponseDto;
import com.chamaApp.backend.Members.Member;
import com.chamaApp.backend.Members.MemberRepository;
import com.chamaApp.backend.Members.MemberService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    public MemberServiceImpl(MemberRepository memberRepository, MemberMapper memberMapper) {
        this.memberRepository = memberRepository;
        this.memberMapper = memberMapper;
    }

    @Override
    public List<MemberResponseDto> getAllMembers() throws MemberNotFoundException {
        List<MemberResponseDto> members = memberRepository
                .findAll()
                .stream()
                .map(memberMapper::fromMemberToResponse)
                .toList();

        if (members.isEmpty()) {
            throw new MemberNotFoundException("No members found.");
        }

        return members;
    }

    @Override
    public MemberResponseDto getMember(Integer id) throws MemberNotFoundException {
        Member member = memberRepository.findById(id).orElseThrow(
                () -> new MemberNotFoundException("Member with id " + id + " not found")
        );
        return memberMapper.fromMemberToResponse(member);
    }

    @Override
    public MemberResponseDto createMember(MemberRequestDto member) throws MemberNotCreatedException {
        try {
            Member savedMember = memberRepository.save(
                    memberMapper.fromRequestToMember(member)
            );
            return memberMapper.fromMemberToResponse(savedMember);
        } catch (Exception e) {
            throw new MemberNotCreatedException("Failed to create member: " + e.getMessage());
        }
    }

    @Override
    public String updateMember(Integer id, MemberRequestDto member) throws MemberNotUpdatedException {
        try {
            Member existingMember = memberRepository.findById(id).orElseThrow(
                    () -> new MemberNotFoundException("Member with id " + id + " not found")
            );

            existingMember.setName(member.name());
            existingMember.setEmail(member.email());
            existingMember.setPassword(member.password());
            memberRepository.save(existingMember);

            return "Member updated successfully";
        } catch (Exception e) {
            throw new MemberNotUpdatedException("Failed to update member with id " + id + ": " + e.getMessage());
        }
    }

    @Override
    public String deleteMember(Integer id) throws MemberNotDeletedException {
        try {
            memberRepository.findById(id).orElseThrow(
                    () -> new MemberNotFoundException("Member with id " + id + " not found")
            );
            memberRepository.deleteById(id);
            return "Member deleted successfully";
        } catch (Exception e) {
            throw new MemberNotDeletedException("Failed to delete member with id " + id + ": " + e.getMessage());
        }
    }
}

