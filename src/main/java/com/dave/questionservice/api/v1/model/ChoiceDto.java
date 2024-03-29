package com.dave.questionservice.api.v1.model;

import com.dave.questionservice.domain.Question;
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
public class ChoiceDto {

    private Long id;
    private String name;
    private String description;
    private Timestamp createdDate;
    private Set<Question> questions;

}
