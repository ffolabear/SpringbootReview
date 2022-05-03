package com.springreview.domain.web.member;

import com.springreview.domain.member.Grade;
import com.springreview.domain.member.Member;
import com.springreview.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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


    //    @PostMapping("/add")
    public String addMember(@ModelAttribute Member member, RedirectAttributes redirectAttributes) {

        Member savedMember = memberRepository.save(member);
        log.info("member.username= {}", savedMember.getUsername());
        log.info("member.email= {}", savedMember.getEmail());
        log.info("member.age= {}", savedMember.getAge());
        log.info("member.grade= {}", savedMember.getGrade());

        redirectAttributes.addAttribute("memberId", savedMember.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/basic/members";
    }

    //검증로직 추가 버전
    @PostMapping("/add")
    public String addMemberValidation(@ModelAttribute Member member,
                                      BindingResult bindingResult,
                                      RedirectAttributes redirectAttributes,
                                      Model model) {

        //이름이 없으면 오류
        if (!StringUtils.hasText(member.getUsername())){
            bindingResult.addError(new FieldError("member", "username", member.getUsername(), false, null, null, "회원 이름은 필수입니다."));
        }

        //이메일이 없으면 오류
        if (!StringUtils.hasText(member.getEmail())) {
            bindingResult.addError(new FieldError("member", "email", member.getEmail(), false, null, null, "이메일 입력은 필수입니다."));
        }

        //이메일 정규식 수정 필요
        //이메일은 있지만 형식이 잘못된경우
        if (StringUtils.hasText(member.getUsername()) && !member.getEmail().matches("[@]]")) {
            bindingResult.addError(new FieldError("member", "email", member.getEmail(), false, null, null, "올바르지 않은 이메일 형식입니다."));

        }

        //강의에 있는 글로벌에러는 아직 적용할 대상이 없음

        if (member.getAge() == null || member.getAge() < 0) {
            bindingResult.addError(new FieldError("member", "age", member.getAge(), false, null, null, "올바르지 않은 나이입니다."));
        }

        if (bindingResult.hasErrors()) {
            log.info("bindingResult = {}", bindingResult);
            return "basic/addMemberForm";
        }

        Member savedMember = memberRepository.save(member);
        redirectAttributes.addAttribute("memberId", savedMember.getId());
        redirectAttributes.addAttribute("status", true);


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

}
