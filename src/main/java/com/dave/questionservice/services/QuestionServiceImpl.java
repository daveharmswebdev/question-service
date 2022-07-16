package com.dave.questionservice.services;

import com.dave.questionservice.api.v1.mapper.QuestionMapper;
import com.dave.questionservice.api.v1.model.QuestionDto;
import com.dave.questionservice.domain.Question;
import com.dave.questionservice.repositories.QuestionRepository;

import java.util.List;
import java.util.stream.Collectors;

public class QuestionServiceImpl implements QuestionService {

    private final QuestionMapper questionMapper;
    private final QuestionRepository questionRepository;

    public QuestionServiceImpl(QuestionMapper questionMapper, QuestionRepository questionRepository) {
        this.questionMapper = questionMapper;
        this.questionRepository = questionRepository;
    }

    @Override
    public List<QuestionDto> getAllQuestions() {
        return questionRepository.findAll()
                .stream()
                .map(questionMapper::questionToQuestionDto).collect(Collectors.toList());
    }

    @Override
    public QuestionDto getQuestionById(Long id) {
        return questionRepository.findById(id)
                .map(questionMapper::questionToQuestionDto).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public QuestionDto createNewQuestion(QuestionDto questionDto) {
        Question question = questionMapper.questionDtoToQuestion(questionDto);
        return saveAndReturnDto(question);
    }

    @Override
    public QuestionDto saveQuestionByDto(Long id, QuestionDto questionDto) {
        Question questionToUpdate = questionRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        questionToUpdate.setTitle(questionDto.getTitle());
        questionToUpdate.setQuestionText(questionDto.getQuestionText());
        return saveAndReturnDto(questionToUpdate);
    }

    @Override
    public void deleteQuestionById(Long id) {
        getQuestionById(id);
        questionRepository.deleteById(id);
    }

    private QuestionDto saveAndReturnDto(Question question) {
        Question savedQuestion = questionRepository.save(question);
        return questionMapper.questionToQuestionDto(savedQuestion);
    }
}
