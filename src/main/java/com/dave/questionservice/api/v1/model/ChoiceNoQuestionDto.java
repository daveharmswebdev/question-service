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
public class ChoiceNoQuestionDto {
    private Long id;
    private String name;
    private String description;
    private Timestamp createdDate;
}
