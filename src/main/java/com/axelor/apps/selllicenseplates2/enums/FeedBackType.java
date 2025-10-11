package com.axelor.apps.selllicenseplates2.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public enum FeedBackType {

    SELL_NUMBER_PLATE("Продать номерной знак"),
    BUY_NUMBER_PLATE("Купить номерной знак"),
    OTHER("Другое");

    private final String displayName;

    public static FeedBackType getEnumByCode(String code) {
        for (FeedBackType type : FeedBackType.values()) {
            if (type.getDisplayName().equals(code)) {
                return type;
            }
        }
        return null;
    }

    public static List<String> getAllCodes() {
        List<String> codes = new ArrayList<>();
        for (FeedBackType type : FeedBackType.values()) {
            codes.add(type.getDisplayName());
        }
        return codes;
    }
}
