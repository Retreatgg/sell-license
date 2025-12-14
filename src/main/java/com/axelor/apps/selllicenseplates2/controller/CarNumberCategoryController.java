package com.axelor.apps.selllicenseplates2.controller;

import com.axelor.apps.selllicenseplates2.enums.CarNumberCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/car-number-categories")
@RequiredArgsConstructor
public class CarNumberCategoryController {

    @GetMapping
    public ResponseEntity<List<Map<String, String>>> getCategories() {
        return ResponseEntity.ok(CarNumberCategory.getAllCategories());
    }

}
