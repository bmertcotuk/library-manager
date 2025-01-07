package com.bmcotuk.library_manager.repository;

import com.bmcotuk.library_manager.repository.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {
}
