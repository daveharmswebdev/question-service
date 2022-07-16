package com.dave.questionservice.api.v1.mapper;

import com.dave.questionservice.api.v1.model.ChoiceDto;
import com.dave.questionservice.domain.Choice;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ChoiceMapperTest {

    @Test
    public void shouldMapChoiceToChoiceDto() {

        Timestamp now = new Timestamp(System.currentTimeMillis());

        Choice choice = new Choice();
        choice.setId(1L);
        choice.setName("test name");
        choice.setDescription("test description");
        choice.setCreatedDate(now);
        choice.setLastModifiedDate(now);

        ChoiceDto choiceDto = ChoiceMapper.INSTANCE.choiceToChoiceDto(choice);

        assertThat(choiceDto).isNotNull();
        assertThat(choiceDto.getId()).isEqualTo(1L);
        assertThat(choiceDto.getName()).isEqualTo("test name");
        assertThat(choiceDto.getDescription()).isEqualTo("test description");
        assertThat(choiceDto.getCreatedDate()).isEqualTo(now);
    }



}