package com.dave.questionservice.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

import java.sql.Timestamp;


@Entity
@Data
@Table(name = "questionnaire_item")
public class QuestionnaireItem extends BaseEntity {


    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;
    @ManyToOne
    @JoinColumn(name = "preferred_id")
    private Choice preferredChoice;
    @ManyToOne
    @JoinColumn(name = "unpreferred_id")
    private Choice unpreferredChoice;

    public QuestionnaireItem() {
        super();
    }

    @Builder
    public QuestionnaireItem(Long id, Timestamp createdDate, Timestamp lastModifiedDate, Question question, Choice preferredChoice, Choice unpreferredChoice) {
        super(id, createdDate, lastModifiedDate);
        this.question = question;
        this.preferredChoice = preferredChoice;
        this.unpreferredChoice = unpreferredChoice;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof QuestionnaireItem)) return false;
        if (!super.equals(o)) return false;

        QuestionnaireItem that = (QuestionnaireItem) o;

        if (getQuestion() != null ? !getQuestion().equals(that.getQuestion()) : that.getQuestion() != null)
            return false;
        if (getPreferredChoice() != null ? !getPreferredChoice().equals(that.getPreferredChoice()) : that.getPreferredChoice() != null)
            return false;
        return getUnpreferredChoice() != null ? getUnpreferredChoice().equals(that.getUnpreferredChoice()) : that.getUnpreferredChoice() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getQuestion() != null ? getQuestion().hashCode() : 0);
        result = 31 * result + (getPreferredChoice() != null ? getPreferredChoice().hashCode() : 0);
        result = 31 * result + (getUnpreferredChoice() != null ? getUnpreferredChoice().hashCode() : 0);
        return result;
    }

}
