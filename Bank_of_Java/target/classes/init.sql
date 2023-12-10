
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




