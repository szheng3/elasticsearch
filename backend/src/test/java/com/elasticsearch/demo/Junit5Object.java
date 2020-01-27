package com.elasticsearch.demo;

public class Junit5Object {

    private String name;
    private Integer number;
    private JUNIT5 description;

    public Junit5Object(String name, Integer number, JUNIT5 description) {
        this.name = name;
        this.number = number;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public JUNIT5 getDescription() {
        return description;
    }

    public void setDescription(JUNIT5 description) {
        this.description = description;
    }
}
