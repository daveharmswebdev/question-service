package com.dave.questionservice.repositories;

import com.dave.questionservice.domain.QuestionnaireItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionnaireItemRepository extends JpaRepository<QuestionnaireItem, Long> {
}
