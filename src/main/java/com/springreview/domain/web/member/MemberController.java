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

    @ModelAttribute("gradeType")
    public Grade[] grades() {
        return Grade.values();
    }


    @GetMapping("/{memberId}")
    public String member(@PathVariable long memberId, Model model) {

        Member member = memberRepository.findById(memberId);
        model.addAttribute("member", member);
        return "basic/member";
    }


    @GetMapping("/add")
    public String addMember(Model model) {
        model.addAttribute("member", new Member());
        return "basic/addMemberForm";
    }



    @PostMapping("/add")
    public String addMember(@ModelAttribute Member member, RedirectAttributes redirectAttributes) {

        Member savedMember = memberRepository.save(member);
        log.info("member.username= {}", savedMember.getUsername());
        log.info("member.email= {}", savedMember.getEmail());
        log.info("member.age= {}", savedMember.getAge());
        log.info("member.grade= {}", savedMember.getGrade());

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
