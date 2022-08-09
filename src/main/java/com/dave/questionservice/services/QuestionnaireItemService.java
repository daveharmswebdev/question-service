package com.dave.questionservice.services;

import com.dave.questionservice.api.v1.model.QuestionnaireItemDto;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface QuestionnaireItemService {

    List<QuestionnaireItemDto> getAllQuestionnaireItems();

    QuestionnaireItemDto getQuestionnaireItemById(Long id);

    QuestionnaireItemDto createNewQuestionnaireItem(QuestionnaireItemDto questionnaireItemDto);

    QuestionnaireItemDto saveQuestionnaireItemByDto(Long id, QuestionnaireItemDto questionnaireItemDto);

    void deleteQuestionnaireItemById(Long id);
}
