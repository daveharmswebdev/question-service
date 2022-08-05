package com.dave.questionservice.api.v1.mapper;

import com.dave.questionservice.api.v1.model.ChoiceDto;
import com.dave.questionservice.domain.Choice;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ChoiceMapper {

    ChoiceMapper INSTANCE = Mappers.getMapper(ChoiceMapper.class);

    ChoiceDto choiceToChoiceDto(Choice choice, @Context CycleAvoidingMappingContext context);

    Choice choiceDtoToChoice(ChoiceDto choiceDto, @Context CycleAvoidingMappingContext context);
}
