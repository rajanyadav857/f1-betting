create database f1_betting_db;

CREATE TABLE users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    balance DOUBLE
);

INSERT INTO users (name, balance) VALUES ('Alice', 150.75);
INSERT INTO users (name, balance) VALUES ('Bob', 300.00);
INSERT INTO users (name, balance) VALUES ('Charlie', 95.50);
INSERT INTO users (name, balance) VALUES ('Diana', 500.25);
INSERT INTO users (name, balance) VALUES ('Ethan', 250.00);

CREATE TABLE bets (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT,
    event_id VARCHAR(255),
    driver_id VARCHAR(255),
    amount DOUBLE,
    odds INT,
    status VARCHAR(50),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE outcomes (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    event_id VARCHAR(255),
    winning_driver_id VARCHAR(255)
);
