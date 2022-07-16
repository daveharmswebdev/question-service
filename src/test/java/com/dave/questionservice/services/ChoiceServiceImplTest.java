package com.dave.questionservice.services;


import com.dave.questionservice.api.v1.mapper.ChoiceMapper;
import com.dave.questionservice.api.v1.mapper.ChoiceMapperImpl;
import com.dave.questionservice.api.v1.model.ChoiceDto;
import com.dave.questionservice.domain.Choice;
import com.dave.questionservice.repositories.ChoiceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class ChoiceServiceImplTest {

    private static final Long ID = 1L;
    private static final Timestamp NOW = new Timestamp(System.currentTimeMillis());

    ChoiceService choiceService;
    ChoiceMapper choiceMapper;

    @Mock
    ChoiceRepository choiceRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        choiceMapper = new ChoiceMapperImpl();
        choiceService = new ChoiceServiceImpl(choiceMapper, choiceRepository);
    }

    @Test
    void getAllChoices() {

        List<Choice> choices = Arrays.asList(new Choice(), new Choice(), new Choice());

        when(choiceRepository.findAll()).thenReturn(choices);

        List<ChoiceDto> choiceDtos = choiceService.getAllChoices();

        assertEquals(3, choiceDtos.size());
    }

    @Test
    void getChoiceById() {
        Choice choice = new Choice();
        choice.setId(ID);
        choice.setName("cinderella");
        choice.setDescription("everyone's favorite princess");
        choice.setCreatedDate(NOW);
        choice.setLastModifiedDate(NOW);

        when(choiceRepository.findById(anyLong())).thenReturn(Optional.of(choice));

        ChoiceDto choiceDto = choiceService.getChoiceById(ID);

        assertEquals(ID, choiceDto.getId());
        assertEquals("cinderella", choiceDto.getName());
        assertEquals("everyone's favorite princess", choiceDto.getDescription());
        assertEquals(NOW, choiceDto.getCreatedDate());
    }

    @Test
    void createNewChoice() {
        ChoiceDto choiceDto = ChoiceDto.builder()
                .name("cinderella")
                .description("everyone's favorite princess")
                .build();

        Choice choice = new Choice();
        choice.setId(ID);
        choice.setName("cinderella");
        choice.setDescription("everyone's favorite princess");
        choice.setCreatedDate(NOW);
        choice.setLastModifiedDate(NOW);

        when(choiceRepository.save(any(Choice.class))).thenReturn(choice);

        ChoiceDto newChoiceDto = choiceService.createNewChoice(choiceDto);

        assertEquals("cinderella", newChoiceDto.getName());
        assertEquals("everyone's favorite princess", newChoiceDto.getDescription());
        assertEquals(NOW, newChoiceDto.getCreatedDate());
        assertEquals(1L, newChoiceDto.getId());
    }

    @Test
    void saveChoiceByDto() {
        ChoiceDto choiceDto = ChoiceDto.builder()
                .name("Cinderella")
                .description("the best princess")
                .build();

        Choice choice = new Choice();
        choice.setId(ID);
        choice.setName("cinderella");
        choice.setDescription("everyone's favorite princess");
        choice.setCreatedDate(NOW);
        choice.setLastModifiedDate(NOW);

        Choice updatedChoice = new Choice();
        updatedChoice.setId(ID);
        updatedChoice.setName("Cinderella");
        updatedChoice.setDescription("the best princess");
        updatedChoice.setCreatedDate(NOW);
        updatedChoice.setLastModifiedDate(NOW);

        when(choiceRepository.findById(anyLong())).thenReturn(Optional.of(choice));
        when(choiceRepository.save(any(Choice.class))).thenReturn(updatedChoice);

        ChoiceDto savedChoiceByDto = choiceService.saveChoiceByDto(ID, choiceDto);

        assertEquals(ID, savedChoiceByDto.getId());
        assertEquals("Cinderella", savedChoiceByDto.getName());
        assertEquals("the best princess", savedChoiceByDto.getDescription());
        assertEquals(NOW, savedChoiceByDto.getCreatedDate());
        assertThat(savedChoiceByDto.getCreatedDate()).isNotNull();
    }

    @Test
    void deleteChoiceById() {
        Choice choice = new Choice();
        choice.setId(ID);
        choice.setName("cinderella");
        choice.setDescription("everyone's favorite princess");
        choice.setCreatedDate(NOW);
        choice.setLastModifiedDate(NOW);

        when(choiceRepository.findById(anyLong())).thenReturn(Optional.of(choice));

        choiceRepository.deleteById(ID);

        verify(choiceRepository, times(1)).deleteById(anyLong());
    }

}