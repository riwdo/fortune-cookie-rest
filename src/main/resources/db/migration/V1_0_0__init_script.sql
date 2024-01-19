DROP TABLE IF EXISTS "order";
DROP TABLE IF EXISTS fortune;

create table fortune (
    id SERIAL PRIMARY KEY,
    created_at date,
    description varchar(255),
    author varchar(255)
);

create table "order" (
    id SERIAL PRIMARY KEY,
    created_at date,
    status varchar(255),
    address JSON,
    fortune_id SERIAL references fortune (id)
);