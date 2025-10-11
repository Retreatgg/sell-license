package com.axelor.apps.selllicenseplates2.controller;

import com.axelor.apps.selllicenseplates2.dto.RegionDto;
import com.axelor.apps.selllicenseplates2.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/regions")
@RequiredArgsConstructor
public class RegionController {

    private final RegionService regionService;

    @GetMapping
    public ResponseEntity<List<RegionDto>> getRegions() {
        return ResponseEntity.ok(regionService.getAllRegions());
    }
}
