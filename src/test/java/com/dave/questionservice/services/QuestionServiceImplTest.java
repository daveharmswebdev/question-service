package com.dave.questionservice.services;

import com.dave.questionservice.api.v1.mapper.QuestionMapper;
import com.dave.questionservice.api.v1.mapper.QuestionMapperImpl;
import com.dave.questionservice.api.v1.model.QuestionDto;
import com.dave.questionservice.domain.Question;
import com.dave.questionservice.repositories.QuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class QuestionServiceImplTest {

    private static final Long ID = 1L;
    private static final Timestamp NOW = new Timestamp(System.currentTimeMillis());

    QuestionService questionService;
    QuestionMapper questionMapper;

    @Mock
    QuestionRepository questionRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        questionMapper = new QuestionMapperImpl();
        questionService = new QuestionServiceImpl(questionMapper, questionRepository);
    }

    @Test
    void getAllQuestions() {
        List<Question> questions = Arrays.asList(new Question(), new Question(), new Question());

        when(questionRepository.findAll()).thenReturn(questions);

        List<QuestionDto> dtos = questionService.getAllQuestions();

        assertEquals(3, dtos.size());
    }

    @Test
    void getQuestionById() {
        Question question = new Question();
        question.setId(ID);
        question.setTitle("title");
        question.setQuestionText("question text");
        question.setCreatedDate(NOW);
        question.setLastModifiedDate(NOW);

        when(questionRepository.findById(anyLong())).thenReturn(Optional.of(question));

        QuestionDto questionDto = questionService.getQuestionById(ID);

        assertEquals("title", questionDto.getTitle());
        assertEquals("question text", questionDto.getQuestionText());
        assertEquals(NOW, questionDto.getCreatedDate());
    }

    @Test
    void createNewQuestion() {
        QuestionDto questionToCreate = QuestionDto.builder()
                .title("make me")
                .questionText("what is make me")
                .build();

        Question madeQuestion = new Question();
        madeQuestion.setId(ID);
        madeQuestion.setTitle("make me");
        madeQuestion.setQuestionText("what is make me");
        madeQuestion.setCreatedDate(NOW);
        madeQuestion.setLastModifiedDate(NOW);

        when(questionRepository.save(any(Question.class))).thenReturn(madeQuestion);

        QuestionDto newDto = questionService.createNewQuestion(questionToCreate);

        assertEquals(ID, newDto.getId());
        assertEquals("make me", newDto.getTitle());
        assertEquals("what is make me", newDto.getQuestionText());
        assertEquals(NOW, newDto.getCreatedDate());
    }

    @Test
    void saveQuestionByDto() {
        QuestionDto questionToSave = QuestionDto.builder()
                .title("Make me")
                .questionText("What is make me?")
                .build();

        Question question = new Question();
        question.setId(ID);
        question.setTitle("make me");
        question.setQuestionText("what is make me");
        question.setCreatedDate(NOW);
        question.setLastModifiedDate(NOW);

        Question updatedQuestion = new Question();
        updatedQuestion.setId(ID);
        updatedQuestion.setTitle("Make me");
        updatedQuestion.setQuestionText("What is make me?");
        updatedQuestion.setCreatedDate(NOW);
        updatedQuestion.setLastModifiedDate(NOW);

        when(questionRepository.findById(anyLong())).thenReturn(Optional.of(question));
        when(questionRepository.save(any(Question.class))).thenReturn(updatedQuestion);

        QuestionDto savedDto = questionService.saveQuestionByDto(ID, questionToSave);

        assertEquals(ID, savedDto.getId());
        assertEquals("Make me", savedDto.getTitle());
        assertEquals("What is make me?", savedDto.getQuestionText());
        assertEquals(NOW, savedDto.getCreatedDate());
    }

    @Test
    void deleteQuestionById() {
        Question question = new Question();
        question.setId(ID);
        question.setTitle("title");
        question.setQuestionText("question text");
        question.setCreatedDate(NOW);
        question.setLastModifiedDate(NOW);

        when(questionRepository.findById(anyLong())).thenReturn(Optional.of(question));

        questionRepository.deleteById(ID);

        verify(questionRepository, times(1)).deleteById(anyLong());
    }

}