package com.example;

public class ChildPojo extends SimplePojoDto{

    @Redactable
    private String secondName;

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }
}
