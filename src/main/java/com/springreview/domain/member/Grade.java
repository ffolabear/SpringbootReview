package com.springreview.domain.member;

public enum Grade {

    Basic("일반 사용자"),
    Expert("전문가"),
    ADMIN("관리자");

    private final String description;

    Grade(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
