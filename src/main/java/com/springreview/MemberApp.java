package com.springreview;

import com.springreview.domain.member.Grade;
import com.springreview.domain.member.Member;
import com.springreview.domain.member.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AutoAppConfig.class);
        MemberService memberService = applicationContext.getBean("memberServiceImpl", MemberService.class);

        Member member = new Member("testmember", "test@cmail.com", 22, Grade.Basic);
        member.setId(1L);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);

    }

}
