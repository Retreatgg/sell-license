package com.axelor.apps.selllicenseplates2.service.impl;

import com.axelor.apps.selllicenseplates2.dto.FeedBackCreateRequest;
import com.axelor.apps.selllicenseplates2.model.Feedback;
import com.axelor.apps.selllicenseplates2.repository.FeedbackRepository;
import com.axelor.apps.selllicenseplates2.service.FeedBackService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeedBackServiceImpl implements FeedBackService {

    private final FeedbackRepository feedbackRepository;

    @Override
    public void createFeedback(FeedBackCreateRequest request) {
        Feedback feedback = Feedback.builder()
                .phoneNumber(request.getPhoneNumber())
                .fullName(request.getFullName())
                .carNumber(request.getCarNumber())
                .feedbackType(request.getFeedbackType())
                .build();

        feedbackRepository.save(feedback);
    }
}
