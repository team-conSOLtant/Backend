package com.consoltant.consoltant.util.constant;

public enum ActivityType {
    EXTERNAL_ACTIVITY("대외활동"),
    CLUB("동아리"),
    VOLUNTEERING("봉사"),
    OTHER("기타");

    private final String value;

    ActivityType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
