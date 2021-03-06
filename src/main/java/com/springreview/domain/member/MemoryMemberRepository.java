package com.springreview.domain.member;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> memberList = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(sequence++);
        memberList.put(member.getId(), member);
        return member;
    }

    @Override
    public List<Member> findAllMember() {
        return new ArrayList<>(memberList.values());
    }

    @Override
    public Member findById(Long memberId) {
        return memberList.get(memberId);
    }

    @Override
    public void update(Long memberId, Member updateMember) {
        Member findMember = findById(memberId);
        findMember.setUsername(updateMember.getUsername());
        findMember.setEmail(updateMember.getEmail());
        findMember.setAge(updateMember.getAge());
        findMember.setGrade(updateMember.getGrade());

    }
}
