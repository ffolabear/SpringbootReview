package com.springreview.domain.member;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Member {

    private Long id;
    private String username;
    private String email;
    private Integer age;
    private Grade grade;

    public Member() {
    }

    public Member(String username, String email, int age, Grade grade) {
        this.username = username;
        this.email = email;
        this.age = age;
        this.grade = grade;
    }
}
