package com.dave.questionservice.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class Choice extends BaseEntity {

    private String name;
    private String description;

    @ManyToOne
    private Question question;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Choice)) return false;
        if (!super.equals(o)) return false;

        Choice choice = (Choice) o;

        if (getName() != null ? !getName().equals(choice.getName()) : choice.getName() != null) return false;
        if (getDescription() != null ? !getDescription().equals(choice.getDescription()) : choice.getDescription() != null)
            return false;
        return getQuestion() != null ? getQuestion().equals(choice.getQuestion()) : choice.getQuestion() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        return result;
    }
}
