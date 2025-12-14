package com.axelor.apps.selllicenseplates2.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
public enum CarNumberCategory {
    IDENTICAL_NUMBERS("Одинаковые цифры"),
    IDENTICAL_LETTERS("Одинаковые буквы"),
    FIRST_TEN("Первая десятка"),
    ROUND("Ровные"),
    MIRROR("Зеркальные");

    private final String displayName;

    public static List<Map<String, String>> getAllCategories() {
        List<Map<String, String>> categories = new ArrayList<>();
        for (CarNumberCategory category : CarNumberCategory.values()) {
            Map<String, String> map = new HashMap<>();
            map.put("code", category.name());        // Например: "IDENTICAL_NUMBERS"
            map.put("name", category.getDisplayName()); // Например: "Одинаковые цифры"
            categories.add(map);
        }
        return categories;
    }

    // Опционально: если вдруг нужно найти Enum по русскому названию
    public static CarNumberCategory getByDisplayName(String displayName) {
        for (CarNumberCategory category : values()) {
            if (category.getDisplayName().equalsIgnoreCase(displayName)) {
                return category;
            }
        }
        return null;
    }
}
