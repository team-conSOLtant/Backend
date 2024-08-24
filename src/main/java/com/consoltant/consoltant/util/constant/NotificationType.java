package com.consoltant.consoltant.util.constant;

public enum NotificationType {
    PORTFOLIO_MATCHING("포트폴리오매칭"),
    PORTFOLIO_COMMENT("포트폴리오댓글"),
    PORTFOLIO_FOLLOW("포트폴리오팔로우");

    private final String value;

    NotificationType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
