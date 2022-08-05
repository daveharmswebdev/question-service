package com.dave.questionservice.api.v1.mapper;

import com.dave.questionservice.api.v1.model.QuestionDto;
import com.dave.questionservice.domain.Question;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class QuestionMapperTest {

    @Test
    public void shouldMapQuestionToQuestionDto() {
        Timestamp now = new Timestamp(System.currentTimeMillis());

        Question question = new Question();
        question.setId(1L);
        question.setTitle("title");
        question.setQuestionText("question text");
        question.setCreatedDate(now);
        question.setLastModifiedDate(now);

        QuestionDto questionDto = QuestionMapper.INSTANCE.questionToQuestionDto(question, new CycleAvoidingMappingContext());

        assertThat(questionDto).isNotNull();
        assertThat(questionDto.getId()).isEqualTo(1L);
        assertThat(questionDto.getTitle()).isEqualTo("title");
        assertThat(questionDto.getQuestionText()).isEqualTo("question text");
        assertThat(questionDto.getCreatedDate()).isEqualTo(now);

    }

    @Test
    public void shouldMapQuestionDtoToQuestion() {
        Timestamp now = new Timestamp(System.currentTimeMillis());

        QuestionDto questionDto = new QuestionDto();
        questionDto.setId(1L);
        questionDto.setTitle("title");
        questionDto.setQuestionText("question text");
        questionDto.setCreatedDate(now);

        Question question = QuestionMapper.INSTANCE.questionDtoToQuestion(questionDto, new CycleAvoidingMappingContext());

        assertThat(question).isNotNull();
        assertThat(question.getId()).isEqualTo(1L);
        assertThat(question.getTitle()).isEqualTo("title");
        assertThat(question.getQuestionText()).isEqualTo("question text");
        assertThat(question.getCreatedDate()).isEqualTo(now);
    }

}