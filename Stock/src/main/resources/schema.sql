CREATE TABLE IF NOT EXISTS stock (
       id int8 not null,
        name varchar not null,
        current_price numeric(10,2) not null,	
        last_update TIMESTAMP WITHOUT TIME ZONE not null,
        primary key (id)
    );
   
DROP SEQUENCE stock_sequence;
CREATE SEQUENCE IF NOT EXISTS stock_sequence;
