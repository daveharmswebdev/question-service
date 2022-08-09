package com.dave.questionservice.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionnaireItemDto {

    private Long id;
    private QuestionDto question;
    private ChoiceDto preferredChoice;
    private ChoiceDto unpreferredChoice;
    private Timestamp createdDate;
    private Timestamp modifiedDate;
}
