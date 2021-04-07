create table Accounts(
Accounts_id INT not null auto_increment primary key,
Customer_id INT not null,
Debitcard_id INT not null,
Transactions_id INT not null,
Balance float not null,
FOREIGN KEY (Customer_id) references Customer (Customer_id),
FOREIGN KEY (Debitcard_id) references Debitcard (Debitcard_id),
FOREIGN KEY (Transactions_id) references Transactions (Transactions_id)
);