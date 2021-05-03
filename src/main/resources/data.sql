DROP TABLE IF EXISTS link;
DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS link_table_sequence;

CREATE TABLE users (
    id LONG AUTO_INCREMENT  PRIMARY KEY,
    username VARCHAR(250) NOT NULL UNIQUE,
    email VARCHAR(250) NOT NULL UNIQUE,
    first_name VARCHAR(250) NOT NULL,
    last_name VARCHAR(250) NOT NULL,
    password VARCHAR(250) NOT NULL,
    created_at TIMESTAMP NOT NULL
);

CREATE TABLE link (
    id LONG PRIMARY KEY,
    name VARCHAR(250) NOT NULL,
    url VARCHAR(250) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    created_by LONG NOT NULL,
    FOREIGN KEY (created_by) REFERENCES users(id)
);

CREATE SEQUENCE link_table_sequence MINVALUE 1
    NOMAXVALUE
    INCREMENT BY 1;

INSERT INTO users (username, email, first_name, last_name, password, created_at)
    values ( 'test', 'test@test.com', 'test_name', 'test_last', 'test_pass', CURRENT_TIMESTAMP);

INSERT INTO link (id, name, url, created_at, updated_at, created_by)
    VALUES ( link_table_sequence.nextval, 'this is a demo name', 'http://localhost:8080/test-app/some-path', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1 );