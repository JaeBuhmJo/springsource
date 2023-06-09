package com.spring.memo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.memo.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
