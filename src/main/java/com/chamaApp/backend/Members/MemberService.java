package com.chamaApp.backend.Members;

import com.chamaApp.backend.Exception.members.MemberNotCreatedException;
import com.chamaApp.backend.Exception.members.MemberNotDeletedException;
import com.chamaApp.backend.Exception.members.MemberNotFoundException;
import com.chamaApp.backend.Exception.members.MemberNotUpdatedException;
import com.chamaApp.backend.Members.Dto.MemberRequestDto;
import com.chamaApp.backend.Members.Dto.MemberResponseDto;

import java.util.List;

public interface MemberService {
    List<MemberResponseDto>  getAllMembers () throws MemberNotFoundException;
    MemberResponseDto getMember(Integer id)throws MemberNotFoundException;
    MemberResponseDto createMember(MemberRequestDto member) throws MemberNotCreatedException;
    String updateMember(Integer id, MemberRequestDto member) throws MemberNotUpdatedException;
    String deleteMember(Integer id) throws MemberNotDeletedException;
}
