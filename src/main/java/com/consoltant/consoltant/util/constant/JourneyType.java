package com.consoltant.consoltant.util.constant;

public enum JourneyType {
    FRESHMAN("대학교 1학년"),
    SOPHOMORE("대학교 2학년"),
    JUNIOR("대학교 3학년"),
    SENIOR("대학교 4학년"),
    THIRTIES("취업 후 ~ 30대"),
    FORTIES("40대"),
    FIFTIES("50대"),
    RETIRED("은퇴");

    private final String value;

    JourneyType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
