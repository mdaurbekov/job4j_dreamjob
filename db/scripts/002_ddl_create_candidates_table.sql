create TABLE candidate (
         id SERIAL PRIMARY KEY,
         name TEXT,
         description TEXT,
         created timestamp,
         city_id integer,
         visible boolean,
         photo bytea
);