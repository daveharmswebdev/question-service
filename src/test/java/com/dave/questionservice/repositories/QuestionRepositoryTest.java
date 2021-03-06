package com.dave.questionservice.repositories;

import com.dave.questionservice.domain.Choice;
import com.dave.questionservice.domain.Question;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("local")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class QuestionRepositoryTest {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    ChoiceRepository choiceRepository;

    @Test
    void testSavedChoice() {
        Question question = new Question();
        question.setTitle("test title");
        question.setQuestionText("test question");

        Choice choiceA = new Choice();
        choiceA.setName("test name");
        choiceA.setDescription("test description");
        Choice savedChoiceA = choiceRepository.saveAndFlush(choiceA);

        Choice choiceB = new Choice();
        choiceB.setName("test name b");
        choiceB.setDescription("test description b");
        Choice savedChoiceB = choiceRepository.saveAndFlush(choiceB);

        Set<Choice> choices = new HashSet<>();
        choices.add(savedChoiceA);
        choices.add(savedChoiceB);

        question.setChoices(choices);

        Question savedQuestion = questionRepository.save(question);
        questionRepository.flush();

        assertNotNull(savedQuestion);
        assertNotNull(savedQuestion.getId());
        assertNotNull(savedQuestion.getChoices());
        assertEquals(savedQuestion.getChoices().size(), 2);

        Question fetchedQuestion = questionRepository.getReferenceById(savedQuestion.getId());

        assertNotNull(fetchedQuestion);
        assertNotNull(fetchedQuestion.getId());
        assertNotNull(fetchedQuestion.getChoices());
        assertEquals(fetchedQuestion.getChoices().size(), 2);
    }

    @Test
    void testSaveQuestion() {
        Question question = new Question();
        question.setTitle("test title");
        question.setQuestionText("test question");
        Question savedQuestion = questionRepository.save(question);

        assertNotNull(savedQuestion);
        assertNotNull(savedQuestion.getId());

        Question fetchedQuestion = questionRepository.getReferenceById(savedQuestion.getId());

        assertNotNull(fetchedQuestion);
        assertNotNull(fetchedQuestion.getId());
        assertNotNull(fetchedQuestion.getCreatedDate());
        assertNotNull(fetchedQuestion.getLastModifiedDate());
    }

}