package com.springreview.domain.web.member;

import com.springreview.domain.member.Grade;
import com.springreview.domain.member.Member;
import com.springreview.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/basic/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    //관리자 권한으로 접속했을때만 사용할 수 있도록 변경 필요
    @GetMapping
    public String members(Model model) {

        List<Member> memberList = memberRepository.findAllMember();
        model.addAttribute("memberList", memberList);

        return "basic/memberList";
    }

    @GetMapping("/{memberId}")
    public String member(@PathVariable long memberId, Model model) {
        Member member = memberRepository.findById(memberId);
        model.addAttribute("member", member);
        return "basic/member";
    }


    @GetMapping("/add")
    public String addMember() {
        return "basic/addMemberForm";
    }



    @PostMapping("/add")
    public String addMember(@ModelAttribute Member member, RedirectAttributes redirectAttributes) {

        Member savedMember = memberRepository.save(member);
//        log.info("입력된 멤버 ID : {}", member.getId());
//        log.info("입력된 멤버 이름 : {}", member.getUsername());
//        log.info("입력된 멤버 메일 : {}", member.getEmail());
//        log.info("입력된 멤버 나이 : {}", member.getAge());
//        log.info("입력된 멤버 등급 : {}", member.getGrade());
//
//
//        log.info("저장된 멤버 ID : {}", savedMember.getId());
//        log.info("저장된 멤버 이름 : {}", savedMember.getUsername());
//        log.info("저장된 멤버 메일 : {}", savedMember.getEmail());
//        log.info("저장된 멤버 나이 : {}", savedMember.getAge());
//        log.info("저장된 멤버 등급 : {}", savedMember.getGrade());
        redirectAttributes.addAttribute("memberId", savedMember.getId());
        redirectAttributes.addAttribute("stasus", true);
        return "redirect:/basic/members";
    }

    @GetMapping("/{memberId}/edit")
    public String editForm(@PathVariable Long memberId, Model model) {
        Member member = memberRepository.findById(memberId);
        model.addAttribute("member", member);
        return "basic/editForm";
    }

    @PostMapping("/{memberId}/edit")
    public String editForm(@PathVariable Long memberId, @ModelAttribute Member member) {
        memberRepository.update(memberId, member);
        return "redirect:/basic/members/{memberId}";
    }


    //테스트용 데이터
    @PostConstruct
    private void init() {
        memberRepository.save(new Member("Bear", "bear@naver.com", 25, Grade.Basic));
        memberRepository.save(new Member("Kirin", "kirin@naver.com", 23, Grade.Basic));
    }


}
