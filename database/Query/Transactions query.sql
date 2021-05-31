create table Transactions(
Transactions_id INT not null auto_increment primary key,
TransactionType VARCHAR(255) not null, 
Amount float not null,
TransactionDate datetime not null
);
