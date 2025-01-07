package com.bmcotuk.library_manager.controller;

import com.bmcotuk.library_manager.controller.dto.MemberRestRequest;
import com.bmcotuk.library_manager.repository.model.Member;
import com.bmcotuk.library_manager.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService memberService;


    @PostMapping
    public ResponseEntity<Member> saveMember(@Valid @RequestBody MemberRestRequest restRequest) {
        Member member = memberService.saveMember(MemberRestRequest.toEntity(restRequest));
        return ResponseEntity.ok(member);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable String id) {
        return ResponseEntity.ok(memberService.getMemberById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Member> updateMemberById(
            @PathVariable String id,
            @Valid @RequestBody MemberRestRequest restRequest) {
        return ResponseEntity.ok(
                memberService.updateMember(id, MemberRestRequest.toEntity(restRequest)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Member> deleteMemberById(@PathVariable String id) {
        return ResponseEntity.ok(memberService.deleteMemberById(id));
    }
}
