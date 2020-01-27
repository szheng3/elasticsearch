package com.elasticsearch.demo;

public enum JUNIT5 {
    FIRST("first","1"),
    SECOND ("second","1");

    private final String first;
    private final String second;

    JUNIT5(String first, String s) {
        this.first=first;
        this.second=s;
    }

    public String getCode() {
        return first;
    }
}
