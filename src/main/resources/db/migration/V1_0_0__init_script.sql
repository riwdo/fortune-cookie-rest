create table fortune (
    id SERIAL PRIMARY KEY,
    created_at date,
    description varchar(255),
    author varchar(255)
);

insert into fortune (created_at, description, author)
values ('2023-12-30 12:30:00', 'Some fortune', 'John Doe');