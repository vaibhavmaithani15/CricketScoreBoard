CREATE TABLE IF NOT EXISTS `scoreboard`.`wicket` (
  `wicket_id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `baller_id` INT(11) NOT NULL,
  `batsman_id` INT(11) NOT NULL,
  `match_id` INT(11) NOT NULL,
  PRIMARY KEY (`wicket_id`));