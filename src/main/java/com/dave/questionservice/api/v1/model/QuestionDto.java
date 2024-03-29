package com.dave.questionservice.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionDto {

    private Long id;
    private String title;
    private String questionText;
    private Timestamp createdDate;
    private Set<ChoiceNoQuestionDto> choices;

}
