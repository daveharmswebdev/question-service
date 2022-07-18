package com.dave.questionservice.controllers.v1;

import com.dave.questionservice.api.v1.model.QuestionDto;
import com.dave.questionservice.controllers.AbstractRestController;
import com.dave.questionservice.controllers.RestResponseEntityExceptionHandler;
import com.dave.questionservice.services.QuestionService;
import com.dave.questionservice.services.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class QuestionControllerTest extends AbstractRestController {

    @Mock
    QuestionService questionService;

    @InjectMocks
    QuestionController questionController;

    MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(questionController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }

    @Test
    void getAllUsers() throws Exception {
        QuestionDto q1 = QuestionDto.builder().title("title 1").build();
        QuestionDto q2 = QuestionDto.builder().title("title 2").build();
        QuestionDto q3 = QuestionDto.builder().title("title 3").build();

        List<QuestionDto> questions = Arrays.asList(q1, q2, q3);

        when(questionService.getAllQuestions()).thenReturn(questions);

        mockMvc.perform(get(QuestionController.BASE_URL).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].title", is("title 1")));
    }

    @Test
    public void getQuestionById() throws Exception {
        QuestionDto q1 = QuestionDto.builder().title("title 1").build();

        when(questionService.getQuestionById(anyLong())).thenReturn(q1);

        mockMvc.perform(get(QuestionController.BASE_URL + "/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", equalTo("title 1")));
    }

    @Test
    public void createNewQuestion() throws Exception {
        Timestamp now = new Timestamp(System.currentTimeMillis());

        QuestionDto requestDto = QuestionDto.builder()
                .title("title 1")
                .questionText("question text")
                .build();

        QuestionDto createdDto = QuestionDto.builder()
                .id(1L)
                .title("title 1")
                .questionText("question text")
                .createdDate(now)
                .build();

        when(questionService.createNewQuestion(ArgumentMatchers.any(QuestionDto.class))).thenReturn(createdDto);

        mockMvc.perform(post(QuestionController.BASE_URL).contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title", is("title 1")))
                .andExpect(jsonPath("$.questionText", is("question text")))
                .andExpect(jsonPath("$.createdDate", is(now.getTime())));
    }

    @Test
    public void updateQuestion() throws Exception {
        Timestamp now = new Timestamp(System.currentTimeMillis());

        QuestionDto requestDto = QuestionDto.builder()
                .title("title 1")
                .questionText("question text")
                .build();

        QuestionDto createdDto = QuestionDto.builder()
                .id(1L)
                .title("title 1")
                .questionText("question text")
                .createdDate(now)
                .build();

        when(questionService.saveQuestionByDto(anyLong(),ArgumentMatchers.any(QuestionDto.class))).thenReturn(createdDto);

        mockMvc.perform(put(QuestionController.BASE_URL + "/1").contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("title 1")))
                .andExpect(jsonPath("$.questionText", is("question text")))
                .andExpect(jsonPath("$.createdDate", is(now.getTime())));
    }

    @Test
    public void testDeleteQuestion() throws Exception {
        mockMvc.perform(delete(QuestionController.BASE_URL + "/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void testGetByIdNotFound() throws Exception {
        when(questionService.getQuestionById(anyLong())).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(get(QuestionController.BASE_URL + "/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}