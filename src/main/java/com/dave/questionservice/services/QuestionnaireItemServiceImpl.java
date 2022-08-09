package com.dave.questionservice.services;

import com.dave.questionservice.api.v1.mapper.ChoiceMapper;
import com.dave.questionservice.api.v1.mapper.QuestionnaireItemMapper;
import com.dave.questionservice.api.v1.model.QuestionnaireItemDto;
import com.dave.questionservice.domain.Choice;
import com.dave.questionservice.domain.QuestionnaireItem;
import com.dave.questionservice.repositories.QuestionnaireItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionnaireItemServiceImpl implements QuestionnaireItemService {

    private final QuestionnaireItemMapper questionnaireItemMapper;
    private final ChoiceMapper choiceMapper;
    private final QuestionnaireItemRepository questionnaireItemRepository;

    public QuestionnaireItemServiceImpl(QuestionnaireItemMapper questionnaireItemMapper, ChoiceMapper choiceMapper, QuestionnaireItemRepository questionnaireItemRepository) {
        this.questionnaireItemMapper = questionnaireItemMapper;
        this.choiceMapper = choiceMapper;
        this.questionnaireItemRepository = questionnaireItemRepository;
    }

    @Override
    public List<QuestionnaireItemDto> getAllQuestionnaireItems() {
        return questionnaireItemRepository.findAll()
                .stream()
                .map(questionnaireItemMapper::questionnaireItemToQuestionnaireItemDto).collect(Collectors.toList());
    }

    @Override
    public QuestionnaireItemDto getQuestionnaireItemById(Long id) {
        return questionnaireItemRepository.findById(id)
                .map(questionnaireItemMapper::questionnaireItemToQuestionnaireItemDto).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public QuestionnaireItemDto createNewQuestionnaireItem(QuestionnaireItemDto questionnaireItemDto) {
        QuestionnaireItem item = questionnaireItemMapper.questionnaireItemDtoToQuestionnaireItem(questionnaireItemDto);
        return saveAndReturnDto(item);
    }

    @Override
    public QuestionnaireItemDto saveQuestionnaireItemByDto(Long id, QuestionnaireItemDto questionnaireItemDto) {
        QuestionnaireItem itemToUpdate = questionnaireItemRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        Choice preferredChoice = choiceMapper.choiceDtoToChoice(questionnaireItemDto.getPreferredChoice());
        itemToUpdate.setPreferredChoice(preferredChoice);
        Choice unpreferredChoice = choiceMapper.choiceDtoToChoice(questionnaireItemDto.getUnpreferredChoice());
        itemToUpdate.setUnpreferredChoice(unpreferredChoice);
        return saveAndReturnDto(itemToUpdate);
    }

    @Override
    public void deleteQuestionnaireItemById(Long id) {
        getQuestionnaireItemById(id);
        questionnaireItemRepository.deleteById(id);
    }

    private QuestionnaireItemDto saveAndReturnDto(QuestionnaireItem questionnaireItem) {
        QuestionnaireItem savedQuestionnaireItem = questionnaireItemRepository.save(questionnaireItem);
        questionnaireItemRepository.refresh(savedQuestionnaireItem);
        return questionnaireItemMapper.questionnaireItemToQuestionnaireItemDto(savedQuestionnaireItem);
    }
}
