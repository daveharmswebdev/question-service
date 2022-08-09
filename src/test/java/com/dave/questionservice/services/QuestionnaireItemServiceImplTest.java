package com.dave.questionservice.services;

import com.dave.questionservice.api.v1.mapper.QuestionnaireItemMapper;
import com.dave.questionservice.api.v1.mapper.QuestionnaireItemMapperImpl;
import com.dave.questionservice.api.v1.model.QuestionnaireItemDto;
import com.dave.questionservice.domain.Question;
import com.dave.questionservice.domain.QuestionnaireItem;
import com.dave.questionservice.repositories.QuestionnaireItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class QuestionnaireItemServiceImplTest {
    private static final Long ID = 1L;
    private static final Timestamp NOW = new Timestamp(System.currentTimeMillis());

    QuestionnaireItemService questionnaireItemService;
    QuestionnaireItemMapper questionnaireItemMapper;

    @Mock
    QuestionnaireItemRepository questionnaireItemRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        questionnaireItemMapper = new QuestionnaireItemMapperImpl();
        questionnaireItemService = new QuestionnaireItemServiceImpl(questionnaireItemMapper, questionnaireItemRepository);
    }


    @Test
    void getAllQuestionnaireItems() {
        List<QuestionnaireItem> items = Arrays.asList(new QuestionnaireItem(), new QuestionnaireItem(), new QuestionnaireItem());

        when(questionnaireItemRepository.findAll()).thenReturn(items);

        List<QuestionnaireItemDto> dtos = questionnaireItemService.getAllQuestionnaireItems();

        assertEquals(3, dtos.size());
    }

    @Test
    void getQuestionnaireItemById() {
        Question question = new Question();
        question.setId(ID);
        question.setTitle("title");
        question.setQuestionText("question text");
        question.setCreatedDate(NOW);
        question.setLastModifiedDate(NOW);


        QuestionnaireItem questionnaireItem = QuestionnaireItem.builder()
                .id(1L)
                .question(question)
                .build();

        when(questionnaireItemRepository.findById(anyLong())).thenReturn(Optional.of(questionnaireItem));

        QuestionnaireItemDto dto = questionnaireItemService.getQuestionnaireItemById(1L);

        assertEquals(1L, dto.getId());
        assertEquals(ID, dto.getQuestion().getId());
    }

    @Test
    void createNewQuestionnaireItem() {
    }

    @Test
    void saveQuestionnaireItemByDto() {
    }

    @Test
    void deleteQuestionnaireItemById() {
    }
}