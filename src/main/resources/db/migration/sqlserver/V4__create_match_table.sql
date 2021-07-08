CREATE TABLE IF NOT EXISTS `scoreboard`.`match` (
  `match_id` INT(11) NOT NULL,
  `first_team_id` INT(11) NULL DEFAULT NULL,
  `second_team_id` INT(11) NULL DEFAULT NULL,
  `match_result` VARCHAR(45) NOT NULL,
  `match_date` DATE NOT NULL,
  `match_umpire` VARCHAR(45) NOT NULL,
  `match_country` VARCHAR(45) NOT NULL,
  `match_city` VARCHAR(45) NOT NULL,
  `match_stadium` VARCHAR(45) NOT NULL,
  `team_team_id` INT(10) UNSIGNED NOT NULL,
  CONSTRAINT `fk_match_team1`
    FOREIGN KEY (`team_team_id`)
    REFERENCES `scoreboard`.`team` (`team_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);