package com.axelor.apps.selllicenseplates2.repository;

import com.axelor.apps.selllicenseplates2.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}