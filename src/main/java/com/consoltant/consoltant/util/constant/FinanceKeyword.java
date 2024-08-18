package com.consoltant.consoltant.util.constant;

public enum FinanceKeyword {
    SMALL_HAPPINESS("소확행"),
    MIDDLE_HAPPINESS("중확행"),
    BIG_HAPPINESS("대확행");


    private final String value;

    FinanceKeyword(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
