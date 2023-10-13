package com.lecture101.constant;

public enum LectureType {
    /*ONE_TO_ONE, GROUP, ONLINE*/

    ONE_TO_ONE("1:1 강의"), GROUP("그룹 강의"), ONLINE("온라인 강의");

    private final String description;

    LectureType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
