
DROP TABLE IF EXISTS customers;
CREATE TABLE customers (
  id INT AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(100),
  last_name VARCHAR(100),
  address VARCHAR(100),
  phone VARCHAR(20),
  username VARCHAR(50),
  password VARCHAR(50),
  registration_date DATE
);

DROP TABLE IF EXISTS bank_accounts;
CREATE TABLE bank_accounts (
  id INT AUTO_INCREMENT PRIMARY KEY,
  account_number VARCHAR(20),
  balance DECIMAL(10, 2),
  owner_id INT,
  FOREIGN KEY (owner_id) REFERENCES customers(id)
);

DROP TABLE IF EXISTS transactions;
CREATE TABLE transactions (
  id INT AUTO_INCREMENT PRIMARY KEY,
  description VARCHAR(100),
  amount DECIMAL(10, 2),
  account_id INT,
  FOREIGN KEY (account_id) REFERENCES bank_accounts(id)
);
