package com.dave.questionservice.domain;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Choice extends BaseEntity {

    private String name;
    private String description;

    @ManyToMany
    @JoinTable(name = "question_choice",
        joinColumns = @JoinColumn(name = "choice_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id"))
    private Set<Question> questions;

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

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Choice)) return false;
        if (!super.equals(o)) return false;

        Choice choice = (Choice) o;

        if (getName() != null ? !getName().equals(choice.getName()) : choice.getName() != null) return false;
        return getDescription() != null ? getDescription().equals(choice.getDescription()) : choice.getDescription() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        return result;
    }
}
