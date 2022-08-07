package com.dave.questionservice.domain;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Question extends BaseEntity {

    private String title;
    private String questionText;

    @ManyToMany
    @JoinTable(name = "question_choice",
        joinColumns = @JoinColumn(name = "question_id"),
            inverseJoinColumns = @JoinColumn(name = "choice_id"))
    private Set<Choice> choices;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public Set<Choice> getChoices() {
        return choices;
    }

    public void setChoices(Set<Choice> choices) {
        this.choices = choices;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Question)) return false;
        if (!super.equals(o)) return false;

        Question question = (Question) o;

        if (getTitle() != null ? !getTitle().equals(question.getTitle()) : question.getTitle() != null) return false;
        return getQuestionText() != null ? getQuestionText().equals(question.getQuestionText()) : question.getQuestionText() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
        result = 31 * result + (getQuestionText() != null ? getQuestionText().hashCode() : 0);
        return result;
    }
}
