DROP TABLE IF EXISTS users;

CREATE TABLE users (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  username VARCHAR(250) NOT NULL UNIQUE,
  email VARCHAR(250) NOT NULL UNIQUE,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  password VARCHAR(250) NOT NULL
);

INSERT INTO users (username, email, first_name, last_name, password)
    values ( 'test', 'test@test.com', 'test_name', 'test_last', 'test_pass' );