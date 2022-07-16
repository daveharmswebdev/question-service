package com.dave.questionservice.api.v1.mapper;

import com.dave.questionservice.api.v1.model.QuestionDto;
import com.dave.questionservice.domain.Question;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface QuestionMapper {

    QuestionMapper INSTANCE = Mappers.getMapper(QuestionMapper.class);

    QuestionDto questionToQuestionDto(Question question);

    Question questionDtoToQuestion(QuestionDto questionDto);
}
