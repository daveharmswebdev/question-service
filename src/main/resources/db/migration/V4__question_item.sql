create table questionnaire_item
(
    id                 bigint not null auto_increment primary key,
    question_id        bigint not null,
    preferred_id       bigint,
    unpreferred_id     bigint,
    created_date       timestamp,
    last_modified_date timestamp,
    constraint fk_questionnaire_item_question_question_id FOREIGN KEY (question_id) references question (id),
    constraint fk_questionnaire_choice_preferred_id FOREIGN KEY (preferred_id) references choice (id),
    constraint fk_questionnaire_choice_unpreferred_id FOREIGN KEY (unpreferred_id) references choice (id)
) engine = InnoDB;