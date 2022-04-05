package com.springreview.domain.member;

public interface MemberService {

    void join(Member member);

    Member findMember(Long memberId);
}
