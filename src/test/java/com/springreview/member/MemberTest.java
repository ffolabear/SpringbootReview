package com.springreview.member;

import com.springreview.AutoAppConfig;
import com.springreview.domain.member.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MemberTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
    MemberService memberService;
    MemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {

        memberService = ac.getBean(MemberService.class);
        memberRepository = ac.getBean(MemoryMemberRepository.class);
    }

    @Test
    void join() {

        //given
        Member member = new Member("memberA", "abcd@gmail.com", 22, Grade.Expert);

        //when
        memberService.join(member);
        Member findMember = memberService.findMember(member.getId());

        //then
        Assertions.assertThat(member).isEqualTo(findMember);
    }

    @Test
    void findAll() {
        //given
        Member member1 = new Member("lion", "lion@gmail.com", 22, Grade.Expert);
        Member member2 = new Member("tiger", "tiger@gmail.com", 23, Grade.Basic);
        memberRepository.save(member1);
        memberRepository.save(member2);

        //when
        List<Member> memberList = memberRepository.findAllMember();

        //then
        //MemberController 에서 테스트용 테이터 2개를 더한 4로 테스트.
        Assertions.assertThat(memberList.size()).isEqualTo(4);
        Assertions.assertThat(memberList).contains(member1, member2);

    }

    @Test
    void updateMember() {

        //given
        Member member = new Member("lion", "lion@gmail.com", 22, Grade.Expert);
        Member savedMember = memberRepository.save(member);
        Long memberId = savedMember.getId();

        //when
        Member updatedMember = new Member("tiger", "tiger@gmail.com", 42, Grade.Basic);
        memberRepository.update(memberId, updatedMember);

        //then
        Member findMember = memberRepository.findById(memberId);
        Assertions.assertThat(findMember.getUsername()).isEqualTo(updatedMember.getUsername());
        Assertions.assertThat(findMember.getEmail()).isEqualTo(updatedMember.getEmail());
    }

}
