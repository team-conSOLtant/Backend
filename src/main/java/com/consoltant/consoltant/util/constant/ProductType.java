package com.consoltant.consoltant.util.constant;

public enum ProductType {
    DEMAND_DEPOSIT("수시입출금"),
    DEPOSIT("예금"),
    SAVING("적금"),
    LOAN("대출");

    private final String value;

    ProductType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
