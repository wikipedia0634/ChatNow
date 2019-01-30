package com.example.peter.project2;

public class MemberData {
    private String name;
    private int time;

    public MemberData(String name, int time) {
        this.name = name;
        this.time = time;
    }

    // Add an empty constructor so we can later parse JSON into MemberData using Jackson
    public MemberData() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
