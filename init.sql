create table transaction (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    amount int,
    commerce varchar(100),
    user_tenpo varchar(30) ,
    date_transaction TIMESTAMP
);

INSERT INTO transaction (amount, commerce, user_tenpo, date_transaction)
VALUES
    (1000, 'lider', 'tenpo', '2025-01-10 10:00:00'),
    (5000, 'lider', 'tenpo', '2025-01-11 15:30:00'),
    (5000, 'lider', 'tenpo', '2025-01-11 15:30:00'),
    (5000, 'lider', 'tenpo', '2025-01-11 15:30:00'),
    (2000, 'jumbo', 'tenpo', '2025-01-12 09:15:00'),
    (1000, 'jumbo', 'tenpo', '2025-01-10 10:00:00'),
    (5000, 'jumbo', 'tenpo', '2025-01-11 15:30:00'),
    (5000, 'tottus', 'tenpo', '2025-01-11 15:30:00'),
    (5000, 'tottus', 'tenpo', '2025-01-11 15:30:00'),
    (2000, 'tottus', 'tenpo', '2025-01-12 09:15:00');