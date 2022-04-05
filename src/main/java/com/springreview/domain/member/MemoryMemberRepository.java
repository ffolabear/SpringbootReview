package com.springreview.domain.member;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
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
}
