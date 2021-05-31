create table Debitcard(
Debitcard_id VARCHAR(255) primary key not null,
Iban VARCHAR(255) not null,
Pincode INT not null,
Attemps INT not null,
Blocked VARCHAR(255)
);