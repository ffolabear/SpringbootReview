package com.springreview.member;

import com.springreview.AutoAppConfig;
import com.springreview.domain.member.Grade;
import com.springreview.domain.member.Member;
import com.springreview.domain.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
    MemberService memberService;

    @BeforeEach
    public void beforeEach() {

        memberService = ac.getBean(MemberService.class);
    }

    @Test
    void join() {

        //given
        Member member = new Member("memberA", "abcd@gmail.com", 22, Grade.Expert);

        //when
        memberService.join(member);
        Member findMember = memberService.findMember(0L);

        //then
        Assertions.assertThat(member).isEqualTo(findMember);
    }


}
