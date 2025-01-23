CREATE TABLE IF NOT EXISTS transaction (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    amount int,
    commerce varchar(100),
    user_tenpo varchar(30) ,
    date_transaction TIMESTAMP
);

INSERT INTO transaction (amount, commerce, user_tenpo, date_transaction) VALUES (100, 'Amazon', 'tenpo', '2021-01-01 00:00:00');