CREATE TABLE IF NOT EXISTS `scoreboard`.`player` (
                                                     `player_id` INT(11) NOT NULL AUTO_INCREMENT,
                                                     `name` VARCHAR(45) NOT NULL,
                                                     `dob` DATE NOT NULL,
                                                     `country` VARCHAR(45) NOT NULL,
                                                     `team_name` VARCHAR(45),
                                                     PRIMARY KEY (`player_id`),
                                                     FOREIGN KEY (`team_name`)  REFERENCES `scoreboard`.`team`(`name`)
                                                    );


INSERT INTO player (name, dob, country, team_name) values ('Sachin','1998-03-12','INDIA','IND');
INSERT INTO player (name, dob, country, team_name) values ('Gautam','1998-03-12','INDIA','IND');
INSERT INTO player (name, dob, country, team_name) values ('Raina','1998-03-12','INDIA','IND');
INSERT INTO player (name, dob, country, team_name) values ('Dhoni','1998-03-12','INDIA','IND');
INSERT INTO player (name, dob, country, team_name) values ('Virat','1998-03-12','INDIA','IND');
INSERT INTO player (name, dob, country, team_name) values ('Jadega','1998-03-12','INDIA','IND');
INSERT INTO player (name, dob, country, team_name) values ('Suresh','1998-03-12','INDIA','IND');
INSERT INTO player (name, dob, country, team_name) values ('Yuvi','1998-03-12','INDIA','IND');
INSERT INTO player (name, dob, country, team_name) values ('Ishant','1998-03-12','INDIA','IND');
INSERT INTO player (name, dob, country, team_name) values ('Pant','1998-03-12','INDIA','IND');
INSERT INTO player (name, dob, country, team_name) values ('Ashwin','1998-03-12','INDIA','IND');



INSERT INTO player (name, dob, country, team_name) values ('Stiv','1998-03-12','ENGLAND','ENG');
INSERT INTO player (name, dob, country, team_name) values ('Jeff','1998-03-12','ENGLAND','ENG');
INSERT INTO player (name, dob, country, team_name) values ('John','1998-03-12','ENGLAND','ENG');
INSERT INTO player (name, dob, country, team_name) values ('Chak','1998-03-12','ENGLAND','ENG');
INSERT INTO player (name, dob, country, team_name) values ('Dev','1998-03-12','ENGLAND','ENG');
INSERT INTO player (name, dob, country, team_name) values ('Ron','1998-03-12','ENGLAND','ENG');
INSERT INTO player (name, dob, country, team_name) values ('Harry','1998-03-12','ENGLAND','ENG');
INSERT INTO player (name, dob, country, team_name) values ('Tom','1998-03-12','ENGLAND','ENG');
INSERT INTO player (name, dob, country, team_name) values ('DJ','1998-03-12','ENGLAND','ENG');
INSERT INTO player (name, dob, country, team_name) values ('JC','1998-03-12','ENGLAND','ENG');
INSERT INTO player (name, dob, country, team_name) values ('Jersy','1998-03-12','ENGLAND','ENG');



INSERT INTO player (name, dob, country, team_name) values ('StivAUS','1998-03-12','AUSTRILIA','AUS');
INSERT INTO player (name, dob, country, team_name) values ('JeffAUS','1998-03-12','AUSTRILIA','AUS');
INSERT INTO player (name, dob, country, team_name) values ('JohnAUS','1998-03-12','AUSTRILIA','AUS');
INSERT INTO player (name, dob, country, team_name) values ('ChakAUS','1998-03-12','AUSTRILIA','AUS');
INSERT INTO player (name, dob, country, team_name) values ('DevAUS','1998-03-12','AUSTRILIA','AUS');
INSERT INTO player (name, dob, country, team_name) values ('RonAUS','1998-03-12','AUSTRILIA','AUS');
INSERT INTO player (name, dob, country, team_name) values ('HarryAUS','1998-03-12','AUSTRILIA','AUS');
INSERT INTO player (name, dob, country, team_name) values ('TomAUS','1998-03-12','AUSTRILIA','AUS');
INSERT INTO player (name, dob, country, team_name) values ('DJAUS','1998-03-12','AUSTRILIA','AUS');
INSERT INTO player (name, dob, country, team_name) values ('JCAUS','1998-03-12','AUSTRILIA','AUS');
INSERT INTO player (name, dob, country, team_name) values ('JersyAUS','1998-03-12','AUSTRILIA','AUS');





INSERT INTO player (name, dob, country, team_name) values ('StivNEZ','1998-03-12','NEWZLAND','NEW');
INSERT INTO player (name, dob, country, team_name) values ('JeffNEZ','1998-03-12','NEWZLAND','NEW');
INSERT INTO player (name, dob, country, team_name) values ('JohnNEZ','1998-03-12','NEWZLAND','NEW');
INSERT INTO player (name, dob, country, team_name) values ('ChakNEZ','1998-03-12','NEWZLAND','NEW');
INSERT INTO player (name, dob, country, team_name) values ('DevNEZ','1998-03-12','NEWZLAND','NEW');
INSERT INTO player (name, dob, country, team_name) values ('RonNEZ','1998-03-12','NEWZLAND','NEW');
INSERT INTO player (name, dob, country, team_name) values ('HarryNEZ','1998-03-12','NEWZLAND','NEW');
INSERT INTO player (name, dob, country, team_name) values ('TomNEZ','1998-03-12','NEWZLAND','NEW');
INSERT INTO player (name, dob, country, team_name) values ('DJNEZ','1998-03-12','NEWZLAND','NEW');
INSERT INTO player (name, dob, country, team_name) values ('JCNEZ','1998-03-12','NEWZLAND','NEW');
INSERT INTO player (name, dob, country, team_name) values ('JersyNEZ','1998-03-12','NEWZLAND','NEW');