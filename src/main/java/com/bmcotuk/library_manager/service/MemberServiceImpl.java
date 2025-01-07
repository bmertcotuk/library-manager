package com.bmcotuk.library_manager.service;

import com.bmcotuk.library_manager.repository.MemberRepository;
import com.bmcotuk.library_manager.repository.model.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Member saveMember(Member member) {
        return memberRepository.save(member);
    }

    public Member getMemberById(String id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found"));
    }

    @Transactional
    public Member updateMember(String id, Member member) {
        Member existingMember = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found"));
        existingMember.setName(member.getName());
        existingMember.setEmail(member.getEmail());
        existingMember.setMembershipDate(member.getMembershipDate());
        return memberRepository.save(member);
    }

    @Transactional
    public Member deleteMemberById(String id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found"));
        memberRepository.deleteById(id);
        return member;
    }
}
