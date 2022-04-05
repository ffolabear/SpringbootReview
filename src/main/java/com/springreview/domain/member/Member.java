package com.springreview.domain.member;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Member {

    private Long id;
    private String username;
    private String email;
    private int age;
    Grade grade;

    public Member() {
    }

    public Member(String username, String email, int age, Grade grade) {
        this.username = username;
        this.email = email;
        this.age = age;
        this.grade = grade;
    }
}
