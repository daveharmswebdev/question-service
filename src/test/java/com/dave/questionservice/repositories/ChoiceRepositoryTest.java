package com.dave.questionservice.repositories;

import com.dave.questionservice.domain.Choice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("local")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ChoiceRepositoryTest {

    @Autowired
    ChoiceRepository choiceRepository;

    @Test
    void testSaveChoice() {
        Choice choice = new Choice();
        choice.setName("test name");
        choice.setDescription("test description");
        Choice savedChoice = choiceRepository.save(choice);

        assertNotNull(savedChoice);
        assertNotNull(savedChoice.getId());

        Choice fetchedChoice = choiceRepository.getReferenceById(savedChoice.getId());

        assertNotNull(fetchedChoice);
        assertNotNull(fetchedChoice.getId());
        assertNotNull(fetchedChoice.getCreatedDate());
        assertNotNull(fetchedChoice.getLastModifiedDate());
    }
}