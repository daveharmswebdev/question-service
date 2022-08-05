package com.dave.questionservice.api.v1.mapper;

import com.dave.questionservice.api.v1.model.QuestionDto;
import com.dave.questionservice.domain.Question;
import org.mapstruct.Context;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface QuestionMapper {

    QuestionMapper INSTANCE = Mappers.getMapper(QuestionMapper.class);

    @InheritInverseConfiguration
    QuestionDto questionToQuestionDto(Question question, @Context CycleAvoidingMappingContext context);

    Question questionDtoToQuestion(QuestionDto questionDto, @Context CycleAvoidingMappingContext context);
}
