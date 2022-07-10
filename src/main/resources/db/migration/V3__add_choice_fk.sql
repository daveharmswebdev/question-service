alter table choice add column question_id bigint;

alter table choice add constraint choice_question_fk
    foreign key (question_id) references question(id);