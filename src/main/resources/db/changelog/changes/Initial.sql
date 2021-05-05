create sequence if not exists hibernate_sequence start 1 increment 1;
create table if not exists address (id numeric(19) not null, created_by varchar(255), created_dt timestamp, updated_by varchar(255), updated_dt timestamp, city varchar(255), state varchar(255), street_address varchar(255), zipcode varchar(255), primary key (id));
create table if not exists donation (id numeric(19) not null, created_by varchar(255), created_dt timestamp, updated_by varchar(255), updated_dt timestamp, donation_amount float8, donation_date timestamp, donation_notes varchar(2000), person_id numeric(19), primary key (id));
create table if not exists email (id numeric(19) not null, created_by varchar(255), created_dt timestamp, updated_by varchar(255), updated_dt timestamp, email varchar(255), email_type varchar(255), person_id numeric(19), primary key (id));
create table if not exists event (id numeric(19) not null, created_by varchar(255), created_dt timestamp, updated_by varchar(255), updated_dt timestamp, amount varchar(255), event_date timestamp, event_name varchar(255), person_id numeric(19), primary key (id));
create table if not exists person (id numeric(19) not null, created_by varchar(255), created_dt timestamp, updated_by varchar(255), updated_dt timestamp, current_member boolean, first_name varchar(100), general_notes varchar(2000), last_name varchar(255), membership_level varchar(255), middle_name varchar(255), preferred_name varchar(255), prefix varchar(255), suffix varchar(255), address_id numeric(19), primary key (id));
create table if not exists phone (id numeric(19) not null, created_by varchar(255), created_dt timestamp, updated_by varchar(255), updated_dt timestamp, phone_number varchar(15), phone_type varchar(100), person_id numeric(19), primary key (id));
alter table donation add constraint fk_donation_person foreign key (person_id) references person;
alter table email add constraint fk_email_person foreign key (person_id) references person;
alter table event add constraint fk_event_person foreign key (person_id) references person;
alter table person add constraint fk_person_person foreign key (address_id) references address;
alter table phone add constraint fk_phone_person foreign key (person_id) references person;