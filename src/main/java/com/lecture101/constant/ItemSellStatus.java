package com.lecture101.constant;

public enum ItemSellStatus {

    SELL("강의중"), SOLD_OUT("강의종료");

    private final String description;

    ItemSellStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}