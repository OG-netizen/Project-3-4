create table Debitcard(
Debitcard_id VARCHAR(8) primary key not null,
Iban VARCHAR(16),
Pincode VARCHAR(4),
Attempts INT,
Blocked BIT(1)
);