CREATE TABLE IF NOT EXISTS `scoreboard`.`batsman_score` (
  `batsman_id` INT(11) NOT NULL,
  `match_id` INT(11) NOT NULL,
  `no_of_sixes` INT(11) NULL,
  `no_of_fours` INT(11) NULL,
  `no_of_threes` INT(11)  NULL,
  `no_of_twos` INT(11)  NULL,
  `no_of_ones` INT(11)  NULL,
  `no_of_zero` INT(11)  NULL,
  `wicket_by` INT(10) NULL,
  `reason_of_out` INT(11) NULL,
  PRIMARY KEY (`batsman_id`,`match_id`),
   CONSTRAINT `batsman_constraint`
        FOREIGN KEY (`match_id`)
            REFERENCES `scoreboard`.`cricket_match` (`match_id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    FOREIGN KEY (`batsman_id`)
        REFERENCES `scoreboard`.`player` (`player_id`)
--         FOREIGN KEY (`reason_of_out`)
--         REFERENCES `scoreboard`.`out_type` (`type_id`)
        );
