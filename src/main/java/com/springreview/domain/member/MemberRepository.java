package com.springreview.domain.member;

//추후에 DB 연동을 할 수 있으므로 회원 데이터에 접근하는 계층을 분리
public interface MemberRepository {

    //구현체 예시 :
    //☑️ 메모리 회원 저장소
    //⬜️ DB 회원 저장소
    //⬜ 외부 시스템 연동 회원 저장소

    Member save(Member member);

    Member findById(Long memberId);

}
