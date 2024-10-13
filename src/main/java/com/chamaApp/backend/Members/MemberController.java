package com.chamaApp.backend.Members;

import com.chamaApp.backend.Members.Dto.MemberRequestDto;
import com.chamaApp.backend.Members.Dto.MemberResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public ResponseEntity<List<MemberResponseDto>> getAllMembers() {
        return ResponseEntity.ok(memberService.getAllMembers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponseDto> getMember(@PathVariable Integer id) {
        return ResponseEntity.ok(memberService.getMember(id));
    }

    @PostMapping
    public ResponseEntity<MemberResponseDto> createMember(@RequestBody MemberRequestDto memberRequestDto) {
        MemberResponseDto createdMember = memberService.createMember(memberRequestDto);
        return new ResponseEntity<>(createdMember, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateMember(@PathVariable Integer id, @RequestBody MemberRequestDto memberRequestDto) {
        return ResponseEntity.ok(memberService.updateMember(id,memberRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMember(@PathVariable Integer id) {
        return ResponseEntity.ok(memberService.deleteMember(id));
    }

}
