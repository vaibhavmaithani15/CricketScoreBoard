CREATE TABLE IF NOT EXISTS `scoreboard`.`wicket`
(
    `wicket_id`  INT(10) NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `baller_id`  INT(11) NOT NULL,
    `batsman_id` INT(11) NOT NULL,
    `catch_by`   INT(11) NULL,
    `runout_by`  INT(11) NULL,
    `stump_by`   INT(11) NULL,
    `match_id`   INT(11) NOT NULL,
    CONSTRAINT `wicket_constraint`
        FOREIGN KEY (`baller_id`)
            REFERENCES `scoreboard`.`player` (`player_id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    FOREIGN KEY (`batsman_id`)
        REFERENCES `scoreboard`.`player` (`player_id`),
--     FOREIGN KEY (`catch_by`)
--         REFERENCES `scoreboard`.`player` (`player_id`),
    FOREIGN KEY (`runout_by`)
        REFERENCES `scoreboard`.`player` (`player_id`),
    FOREIGN KEY (`stump_by`)
        REFERENCES `scoreboard`.`player` (`player_id`),
    FOREIGN KEY (`match_id`)
        REFERENCES `scoreboard`.`cricket_match` (`match_id`));