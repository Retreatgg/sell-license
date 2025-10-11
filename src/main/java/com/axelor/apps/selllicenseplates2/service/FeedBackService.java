package com.axelor.apps.selllicenseplates2.service;

import com.axelor.apps.selllicenseplates2.dto.FeedBackCreateRequest;
import org.springframework.stereotype.Service;

@Service
public interface FeedBackService {
    void createFeedback(FeedBackCreateRequest request);
}
