package com.dave.questionservice.api.v1.mapper;

import com.dave.questionservice.api.v1.model.QuestionnaireItemDto;
import com.dave.questionservice.domain.Choice;
import com.dave.questionservice.domain.Question;
import com.dave.questionservice.domain.QuestionnaireItem;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class QuestionnaireItemMapperTest {

    @Test
    public void shouldMapQuestionnaireItemToDto() {
        Timestamp now = new Timestamp(System.currentTimeMillis());

        Question question = new Question();
        question.setId(1L);
        question.setTitle("title");
        question.setQuestionText("question text");
        question.setCreatedDate(now);
        question.setLastModifiedDate(now);

        Choice choice1 = new Choice();
        choice1.setId(1L);
        choice1.setName("choice 1");
        choice1.setDescription("choice 1 description");
        choice1.setCreatedDate(now);
        choice1.setLastModifiedDate(now);

        Choice choice2 = new Choice();
        choice2.setId(2L);
        choice2.setName("choice 2");
        choice2.setDescription("choice 2 description");
        choice2.setCreatedDate(now);
        choice2.setLastModifiedDate(now);

        Set<Choice> choices = new HashSet<>();
        choices.add(choice1);
        choices.add(choice2);

        question.setChoices(choices);

        QuestionnaireItem questionnaireItem = QuestionnaireItem
                .builder()
                .id(1L)
                .question(question)
                .preferredChoice(choice1)
                .unpreferredChoice(choice2)
                .createdDate(now)
                .build();

        QuestionnaireItemDto questionnaireItemDto = QuestionnaireItemMapper.INSTANCE.questionnaireItemToQuestionnaireItemDto(questionnaireItem);

        assertThat(questionnaireItemDto).isNotNull();
        assertThat(questionnaireItemDto.getId()).isEqualTo(1l);
        assertThat(questionnaireItemDto.getQuestion().getId()).isEqualTo(1l);
        assertThat(questionnaireItemDto.getPreferredChoice().getId()).isEqualTo(1l);
        assertThat(questionnaireItemDto.getCreatedDate()).isEqualTo(now);
        assertThat(questionnaireItemDto.getUnpreferredChoice().getId()).isEqualTo(2l);
        assertThat(questionnaireItemDto.getQuestion().getChoices().size()).isEqualTo(2);
    }

}