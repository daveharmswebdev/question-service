create table choice (
                          id bigint not null auto_increment primary key,
                          name varchar(255),
                          description varchar(255),
                          created_date timestamp,
                          last_modified_date timestamp
) engine=InnoDB;