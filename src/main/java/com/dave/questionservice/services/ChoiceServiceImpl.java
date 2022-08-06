package com.dave.questionservice.services;

import com.dave.questionservice.api.v1.mapper.ChoiceMapper;
import com.dave.questionservice.api.v1.model.ChoiceDto;
import com.dave.questionservice.domain.Choice;
import com.dave.questionservice.repositories.ChoiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChoiceServiceImpl implements ChoiceService {

    private final ChoiceMapper choiceMapper;
    private final ChoiceRepository choiceRepository;

    public ChoiceServiceImpl(ChoiceMapper choiceMapper, ChoiceRepository choiceRepository) {
        this.choiceMapper = choiceMapper;
        this.choiceRepository = choiceRepository;
    }


    @Override
    public List<ChoiceDto> getAllChoices() {
        return choiceRepository.findAll()
                .stream()
                .map(choiceMapper::choiceToChoiceDto).collect(Collectors.toList());
    }

    @Override
    public ChoiceDto getChoiceById(Long id) {
        return choiceRepository.findById(id)
                .map(choiceMapper::choiceToChoiceDto).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public ChoiceDto createNewChoice(ChoiceDto choiceDto) {
        Choice choice = choiceMapper.choiceDtoToChoice(choiceDto);
        return saveAndReturnDto(choice);
    }

    @Override
    public ChoiceDto saveChoiceByDto(Long id, ChoiceDto choiceDto) {
        Choice choiceToUpdate = choiceRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        choiceToUpdate.setName(choiceDto.getName());
        choiceToUpdate.setDescription(choiceDto.getDescription());
        return saveAndReturnDto(choiceToUpdate);
    }

    @Override
    public void deleteChoiceById(Long id) {
        getChoiceById(id);
        choiceRepository.deleteById(id);
    }

    private ChoiceDto saveAndReturnDto(Choice choice) {
        Choice savedChoice = choiceRepository.save(choice);
        return choiceMapper.choiceToChoiceDto(savedChoice);
    }
}
