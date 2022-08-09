package com.dave.questionservice.api.v1.mapper;

import com.dave.questionservice.api.v1.model.QuestionnaireItemDto;
import com.dave.questionservice.domain.QuestionnaireItem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface QuestionnaireItemMapper {

    QuestionnaireItemMapper INSTANCE = Mappers.getMapper(QuestionnaireItemMapper.class);

    QuestionnaireItemDto questionnaireItemToQuestionnaireItemDto(QuestionnaireItem questionnaireItem);

    QuestionnaireItem questionnaireItemDtoToQuestionnaireItem(QuestionnaireItemDto questionnaireItemDto);
}
