DROP TABLE IF EXISTS user;  
CREATE TABLE user (  
email VARCHAR(50) PRIMARY KEY NOT NULL,  
password VARCHAR(64) NOT NULL, 
role VARCHAR(50) NOT NULL
);
DROP TABLE IF EXISTS userdata; 
CREATE TABLE userdata (  
email VARCHAR(50) PRIMARY KEY NOT NULL,  
firstname VARCHAR(50) NOT NULL, 
lastname VARCHAR(50) NOT NULL, 
role VARCHAR(50) NOT NULL
);

INSERT INTO user(email,password,role) VALUES ('roger@gmail.com', '$2a$10$WcePHHPR18CSXAX8MxP.D.8A56cg4RWRydSzXybLNzPF9RUL1x4oq','ADMIN');
INSERT INTO user(email,password,role) VALUES ('steve@gmail.com', '$2a$10$nQIvA3m5XA6bx42bTwqcQedQQ7dDF/dFA9xJL.EL0J5EiCsxYOYha','ADMIN');
INSERT INTO user(email,password,role) VALUES ('zeba@yahoomail.com', '$2a$10$NQtRMLGT4zhHAJ19G/P0auVe6EoZgKAoGVEhgBktGwdUI.B2lxs7i', 'USER');

INSERT INTO userdata(email,firstname,lastname,role) VALUES ('roger@gmail.com', 'Roger', 'Kutcher','admin');
INSERT INTO userdata(email,firstname,lastname,role) VALUES ('steve@gmail.com', 'Steve', 'Martin','admin');
INSERT INTO userdata(email,firstname,lastname,role) VALUES ('zeba@yahoomail.com', 'Zeba', 'Zarin','candidate');