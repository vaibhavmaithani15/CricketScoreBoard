CREATE TABLE IF NOT EXISTS `scoreboard`.`player` (
  `player_id` INT(11) NOT NULL AUTO_INCREMENT,
  `player_name` VARCHAR(45) NOT NULL,
  `player_dob` DATE NOT NULL,
  `player_country` VARCHAR(45) NOT NULL,
  `team_team_id` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`player_id`),
  CONSTRAINT `fk_player_team`
    FOREIGN KEY (`team_team_id`)
    REFERENCES `scoreboard`.`team` (`team_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);