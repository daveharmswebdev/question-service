package com.dave.questionservice.controllers.v1;

import com.dave.questionservice.api.v1.model.ChoiceDto;
import com.dave.questionservice.controllers.RestResponseEntityExceptionHandler;
import com.dave.questionservice.services.ChoiceService;
import com.dave.questionservice.services.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import static com.dave.questionservice.controllers.AbstractRestController.asJsonString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ChoiceControllerTest {

    @Mock
    ChoiceService choiceService;

    @InjectMocks
    ChoiceController choiceController;

    MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(choiceController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }

    @Test
    void getAllQuestions() throws Exception {
        ChoiceDto c1 = ChoiceDto.builder().name("name 1").build();
        ChoiceDto c2 = ChoiceDto.builder().name("name 2").build();
        ChoiceDto c3 = ChoiceDto.builder().name("name 3").build();

        List<ChoiceDto> choices = Arrays.asList(c1, c2, c3);

        when(choiceService.getAllChoices()).thenReturn(choices);

        mockMvc.perform(get(ChoiceController.BASE_URL).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].name", is("name 1")));
    }

    @Test
    public void getChoiceById() throws Exception {
        ChoiceDto c1 = ChoiceDto.builder().name("name 1").build();

        when(choiceService.getChoiceById(anyLong())).thenReturn(c1);

        mockMvc.perform(get(ChoiceController.BASE_URL + "/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("name 1")));
    }

    @Test
    public void createNewChoice() throws Exception {
        Timestamp now = new Timestamp(System.currentTimeMillis());

        ChoiceDto requestDto = ChoiceDto.builder().name("name 2").description("description 2").build();
        ChoiceDto responseDto = ChoiceDto.builder().name("name 2").description("description 2").createdDate(now).build();

        when(choiceService.createNewChoice(any(ChoiceDto.class))).thenReturn(responseDto);

        mockMvc.perform(post(ChoiceController.BASE_URL).contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("name 2")))
                .andExpect(jsonPath("$.description", is("description 2")))
                .andExpect(jsonPath("$.createdDate", is(now.getTime())));
    }

    @Test
    public void updateChoice() throws Exception {
        Timestamp now = new Timestamp(System.currentTimeMillis());

        ChoiceDto requestDto = ChoiceDto.builder().name("name 2").description("description 2").build();
        ChoiceDto responseDto = ChoiceDto.builder().id(1L).name("name 2").description("description 2").createdDate(now).build();

        when(choiceService.saveChoiceByDto(anyLong() ,any(ChoiceDto.class))).thenReturn(responseDto);

        mockMvc.perform(put(ChoiceController.BASE_URL + "/2").contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("name 2")))
                .andExpect(jsonPath("$.description", is("description 2")))
                .andExpect(jsonPath("$.createdDate", is(now.getTime())));
    }

    @Test
    public void testDeleteQuestion() throws Exception {
        mockMvc.perform(delete(ChoiceController.BASE_URL + "/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void testGetByIdNotFound() throws Exception {
        when(choiceService.getChoiceById(anyLong())).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(get(ChoiceController.BASE_URL + "/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
