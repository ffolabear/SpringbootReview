package com.springreview;

import com.springreview.domain.member.Grade;
import com.springreview.domain.member.Member;
import com.springreview.domain.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final MemoryMemberRepository memoryMemberRepository;

    //테스트용 데이터
    @PostConstruct
    private void init() {
        memoryMemberRepository.save(new Member("Bear", "bear@naver.com", 25, Grade.Basic));
        memoryMemberRepository.save(new Member("Kirin", "kirin@naver.com", 23, Grade.Basic));
    }

}
