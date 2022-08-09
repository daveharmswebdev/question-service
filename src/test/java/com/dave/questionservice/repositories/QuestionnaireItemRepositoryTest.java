package com.dave.questionservice.repositories;

import com.dave.questionservice.domain.Choice;
import com.dave.questionservice.domain.Question;
import com.dave.questionservice.domain.QuestionnaireItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("local")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class QuestionnaireItemRepositoryTest {

    @Autowired
    QuestionnaireItemRepository questionnaireItemRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    ChoiceRepository choiceRepository;

    @Test
    void testSaveQuestionnaireItem() {
        Timestamp now = new Timestamp(System.currentTimeMillis());

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

        QuestionnaireItem questionnaireItem = QuestionnaireItem.builder()
                .question(savedQuestion)
                .createdDate(now)
                .lastModifiedDate(now)
                .preferredChoice(choiceA)
                .unpreferredChoice(choiceB)
                .build();

        QuestionnaireItem savedQuestionnaireItem =
                questionnaireItemRepository.save(questionnaireItem);
        questionnaireItemRepository.flush();

        assertNotNull(savedQuestionnaireItem);

        QuestionnaireItem fetchedQuestionnaireItem =
                questionnaireItemRepository.getReferenceById(savedQuestionnaireItem.getId());

        assertNotNull(fetchedQuestionnaireItem);
    }

}