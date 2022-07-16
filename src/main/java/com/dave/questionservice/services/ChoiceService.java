package com.dave.questionservice.services;

import com.dave.questionservice.api.v1.model.ChoiceDto;

import java.util.List;


public interface ChoiceService {
    List<ChoiceDto> getAllChoices();

    ChoiceDto getChoiceById(Long id);

    ChoiceDto createNewChoice(ChoiceDto choiceDto);

    ChoiceDto saveChoiceByDto(Long id, ChoiceDto choiceDto);

    void deleteChoiceById(Long id);
}
