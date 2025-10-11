package com.axelor.apps.selllicenseplates2.controller;

import com.axelor.apps.selllicenseplates2.dto.FeedBackCreateRequest;
import com.axelor.apps.selllicenseplates2.enums.FeedBackType;
import com.axelor.apps.selllicenseplates2.service.FeedBackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/feedbacks")
public class FeedBackController {

    private final FeedBackService feedBackService;

    @GetMapping("/types")
    public ResponseEntity<?> getFeedBackTypes() {
        return ResponseEntity.ok(FeedBackType.getAllCodes());
    }

    @PostMapping
    public void createFeedback(@RequestBody FeedBackCreateRequest request) {
        feedBackService.createFeedback(request);
    }

}
