drop table if exists question cascade;

create table question (
    id bigint not null auto_increment primary key,
    title varchar(255),
    question_text varchar(255),
    created_date timestamp,
    last_modified_date timestamp
) engine=InnoDB;