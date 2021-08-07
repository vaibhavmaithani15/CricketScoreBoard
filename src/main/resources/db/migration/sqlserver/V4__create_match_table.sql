CREATE TABLE IF NOT EXISTS `scoreboard`.`cricket_match`
(
    `match_id`         INT(11) PRIMARY KEY AUTO_INCREMENT,
    `first_team_name`  VARCHAR(10) NULL DEFAULT NULL,
    `second_team_name` VARCHAR(10) NULL DEFAULT NULL,
    `result`           VARCHAR(45) NOT NULL,
    `match_date`       DATE        NOT NULL,
    `umpire`           VARCHAR(45) NOT NULL,
    `country`          VARCHAR(45) NOT NULL,
    `city`             VARCHAR(45) NOT NULL,
    `stadium`          VARCHAR(45) NOT NULL,
    CONSTRAINT `fk_first_team_name`
        FOREIGN KEY (`first_team_name`)
            REFERENCES `scoreboard`.`team` (`name`)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    FOREIGN KEY (`second_team_name`)
        REFERENCES `scoreboard`.`team` (`name`)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);


INSERT INTO cricket_match (first_team_name, second_team_name, result, match_date, umpire, country, city, stadium)
values ('IND', 'AUS', 'NOT STARTED', '2021-09-12', 'Rahul', 'INDIA', 'DELHI', 'FIROSHA KOTLA');
INSERT INTO cricket_match (first_team_name, second_team_name, result, match_date, umpire, country, city, stadium)
values ('ENG', 'NEW', 'NOT STARTED', '2021-09-17', 'Rahul', 'INDIA', 'Mumbai', 'WANKHADE');

