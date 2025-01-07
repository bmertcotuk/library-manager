package com.bmcotuk.library_manager.service;

import com.bmcotuk.library_manager.repository.model.Member;

public interface MemberService {
    Member saveMember(Member member);

    Member getMemberById(String id);

    Member updateMember(String id, Member member);

    Member deleteMemberById(String id);
}
