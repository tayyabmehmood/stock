DELETE FROM stock;
INSERT INTO stock
(id, name, current_price, last_update)
VALUES((select
        nextval ('stock_sequence')), 'Football', 30, CURRENT_TIMESTAMP);
INSERT INTO stock
(id, name, current_price, last_update)
VALUES((select
        nextval ('stock_sequence')), 'Baseball bat', 80, CURRENT_TIMESTAMP);
INSERT INTO stock
(id, name, current_price, last_update)
VALUES((select
        nextval ('stock_sequence')), 'T-Shirt', 25, CURRENT_TIMESTAMP);