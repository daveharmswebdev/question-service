create table question_choice (
    question_id bigint not null,
    choice_id bigint not null,
    primary key (question_id, choice_id),
    constraint fk_question_choice_question_question_id FOREIGN KEY (question_id) references question (id),
    constraint fk_question_choice_choice_choice_id FOREIGN KEY (choice_id) references choice (id)
);

insert into question (title, question_text, created_date, last_modified_date)
values ('question 1', 'text 1', now(), now());

insert into question (title, question_text, created_date, last_modified_date)
values ('question 2', 'text 2', now(), now());

insert into question (title, question_text, created_date, last_modified_date)
values ('question 3', 'text 3', now(), now());

insert into question (title, question_text, created_date, last_modified_date)
values ('question 4', 'text 4', now(), now());

insert into choice (name, description, created_date, last_modified_date)
values ('choice 1', 'description 1', now(), now());

insert into choice (name, description, created_date, last_modified_date)
values ('choice 2', 'description 2', now(), now());

insert into choice (name, description, created_date, last_modified_date)
values ('choice 3', 'description 3', now(), now());

insert into choice (name, description, created_date, last_modified_date)
values ('choice 4', 'description 4', now(), now());

insert into choice (name, description, created_date, last_modified_date)
values ('choice 5', 'description 5', now(), now());

insert into question_choice (question_id, choice_id)
select q.id, c.id FROM question q, choice c
where q.title = 'question 1' and c.name = 'choice 1';

insert into question_choice (question_id, choice_id)
select q.id, c.id FROM question q, choice c
where q.title = 'question 1' and c.name = 'choice 2';

insert into question_choice (question_id, choice_id)
select q.id, c.id FROM question q, choice c
where q.title = 'question 2' and c.name = 'choice 2';

insert into question_choice (question_id, choice_id)
select q.id, c.id FROM question q, choice c
where q.title = 'question 2' and c.name = 'choice 3';


