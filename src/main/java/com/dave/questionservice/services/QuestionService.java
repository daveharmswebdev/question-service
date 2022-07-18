package com.dave.questionservice.services;

import com.dave.questionservice.api.v1.model.QuestionDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QuestionService {
    List<QuestionDto> getAllQuestions();

    QuestionDto getQuestionById(Long id);

    QuestionDto createNewQuestion(QuestionDto questionDto);

    QuestionDto saveQuestionByDto(Long id, QuestionDto questionDto);

    void deleteQuestionById(Long id);
}
