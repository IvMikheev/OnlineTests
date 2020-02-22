CREATE DATABASE IF NOT EXISTS onlineTest
    COLLATE utf8_general_ci;

USE onlineTest;

DROP TABLE IF EXISTS user;

CREATE TABLE user
(
    id INT(20) NOT NULL AUTO_INCREMENT,
    username VARCHAR(20) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20),
    PRIMARY KEY (id)
);

INSERT INTO user(username, password, role) VALUES ('admin', '$2a$08$YStFzWfSnc/nyLS.ETPlie1Hxpw/aIOGvDOZvYBYhUt.twoEtKLiS', 'ADMIN');

DROP TABLE IF EXISTS useranswer;

CREATE TABLE useranswer
(
    id INT(20) NOT NULL AUTO_INCREMENT,
    username VARCHAR(20),
    questions VARCHAR(255),
    answers VARCHAR(255),
    correct BIT(1),
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS question;

CREATE TABLE question
(
    id INT(20) NOT NULL AUTO_INCREMENT,
    text VARCHAR(50),
    PRIMARY KEY (id)
);

INSERT INTO question(text)
VALUES ('What is 2 + 2?'),
       ('How many hours a day?'),
       ('What is the month after December?'),
       ('How many months a year?'),
       ('What is the name of our planet?');

DROP TABLE IF EXISTS answer;

CREATE TABLE answer
(
    id INT(20) NOT NULL AUTO_INCREMENT,
    text VARCHAR(50),
    parent_question VARCHAR(50),
    correct_answer BIT(1),
    PRIMARY KEY (id)
);

INSERT INTO answer(text, parent_question, correct_answer)
VALUES ('56', 'What is 2 + 2?', false),
       ('2', 'What is 2 + 2?', false),
       ('22', 'What is 2 + 2?', false),
       ('4', 'What is 2 + 2?', true),
       ('12', 'How many hours a day?', false),
       ('48', 'How many hours a day?', false),
       ('24', 'How many hours a day?', true),
       ('2', 'How many hours a day?', false),
       ('January', 'What is the month after December?', true),
       ('July', 'What is the month after December?', false),
       ('September', 'What is the month after December?', false),
       ('November', 'What is the month after December?', false),
       ('24', 'How many months a year?', false),
       ('12', 'How many months a year?', true),
       ('15', 'How many months a year?', false),
       ('8', 'How many months a year?', false),
       ('Earth', 'What is the name of our planet?', true),
       ('Mars', 'What is the name of our planet?', false),
       ('Sun', 'What is the name of our planet?', false),
       ('Moon', 'What is the name of our planet?', false);