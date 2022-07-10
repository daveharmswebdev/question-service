package com.dave.questionservice.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Question extends BaseEntity {

    private String title;
    private String questionText;

    @OneToMany(mappedBy = "question", cascade = CascadeType.PERSIST)
    private Set<Choice> choices;

    public void addChoice(Choice choice) {
        if (choices == null) {
            choices = new HashSet<>();
        }

        choices.add(choice);
        choice.setQuestion(this);
    }

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
        if (getQuestionText() != null ? !getQuestionText().equals(question.getQuestionText()) : question.getQuestionText() != null)
            return false;
        return getChoices() != null ? getChoices().equals(question.getChoices()) : question.getChoices() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
        result = 31 * result + (getQuestionText() != null ? getQuestionText().hashCode() : 0);
        result = 31 * result + (getChoices() != null ? getChoices().hashCode() : 0);
        return result;
    }
}
