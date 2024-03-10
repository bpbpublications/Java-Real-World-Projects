CREATE TABLE USER(
    id UUID PRIMARY KEY NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE GROUP(
    id UUID PRIMARY KEY NOT NULL,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE MEMBERSHIP(
    id int NOT NULL AUTO_INCREMENT,
    user_id UUID NOT NULL,
    group_id UUID NOT NULL,
    PRIMARY KEY (user_id, group_id),
    FOREIGN KEY (user_id) REFERENCES USER(id),
    FOREIGN KEY (group_id) REFERENCES GROUP(id)
);

INSERT INTO USER(id, email, password, name) VALUES('922dbcd5-d071-41bd-920b-00f83eb4bb46','admin@davivieira.dev',HASH('MD5','pass'),'Admin');
INSERT INTO USER(id, email, password, name) VALUES('922dbcd5-d071-41bd-920b-00f83eb4bb47','user1@davivieira.dev',HASH('MD5','pass'),'User 1');
INSERT INTO USER(id, email, password, name) VALUES('922dbcd5-d071-41bd-920b-00f83eb4bb48','user2@davivieira.dev',HASH('MD5','pass'),'User 2');
INSERT INTO USER(id, email, password, name) VALUES('922dbcd5-d071-41bd-920b-00f83eb4bb49','user3@davivieira.dev',HASH('MD5','pass'),'User 3');

INSERT INTO GROUP(id, name) VALUES('ca23800e-9b5a-11eb-a8b3-0242ac130003','Administrators');
INSERT INTO GROUP(id, name) VALUES('ca23800e-9b5a-11eb-a8b3-0242ac130004','Users');

INSERT INTO MEMBERSHIP(user_id, group_id) VALUES('922dbcd5-d071-41bd-920b-00f83eb4bb46','ca23800e-9b5a-11eb-a8b3-0242ac130003');
INSERT INTO MEMBERSHIP(user_id, group_id) VALUES('922dbcd5-d071-41bd-920b-00f83eb4bb47','ca23800e-9b5a-11eb-a8b3-0242ac130004');
INSERT INTO MEMBERSHIP(user_id, group_id) VALUES('922dbcd5-d071-41bd-920b-00f83eb4bb48','ca23800e-9b5a-11eb-a8b3-0242ac130004');
INSERT INTO MEMBERSHIP(user_id, group_id) VALUES('922dbcd5-d071-41bd-920b-00f83eb4bb49','ca23800e-9b5a-11eb-a8b3-0242ac130004');


CREATE TABLE ATTRIBUTE_DEFINITION(
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    type VARCHAR(255) NOT NULL
);

CREATE TABLE ATTRIBUTE_VALUE(
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    definition_id INT NOT NULL,
    value VARCHAR(255) NOT NULL,
    FOREIGN KEY (definition_id) REFERENCES ATTRIBUTE_DEFINITION(id)
);

INSERT INTO ATTRIBUTE_DEFINITION(id, name, type) VALUES(1, 'City', 'String');
INSERT INTO ATTRIBUTE_DEFINITION(id, name, type) VALUES(2, 'Year', 'Integer');

INSERT INTO ATTRIBUTE_VALUE(definition_id, value) VALUES(1, 'New York');
INSERT INTO ATTRIBUTE_VALUE(definition_id, value) VALUES(1, 'Berlin');
INSERT INTO ATTRIBUTE_VALUE(definition_id, value) VALUES(1, 'Tokyo');
INSERT INTO ATTRIBUTE_VALUE(definition_id, value) VALUES(2, '1980');
INSERT INTO ATTRIBUTE_VALUE(definition_id, value) VALUES(2, '2004');

CREATE TABLE PROFILE(
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    website VARCHAR(255) NOT NULL
);

CREATE TABLE ACCOUNT(
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    profile_id INT NOT NULL,
    FOREIGN KEY (profile_id) REFERENCES PROFILE(id)
);


INSERT INTO PROFILE(id, name, description, website) VALUES(1, 'User 1', 'description user 1', 'user1.com');
INSERT INTO PROFILE(id, name, description, website) VALUES(2, 'User 2', 'description user 2', 'user2.com');
INSERT INTO PROFILE(id, name, description, website) VALUES(3, 'User 3', 'description user 3', 'user3.com');
INSERT INTO ACCOUNT(id, email, password, profile_id) VALUES(1, 'user1@davivieira.dev', 'pass', 1);
INSERT INTO ACCOUNT(id, email, password, profile_id) VALUES(2, 'user1@davivieira.dev', 'pass', 2);
INSERT INTO ACCOUNT(id, email, password, profile_id) VALUES(3, 'user1@davivieira.dev', 'pass', 3);