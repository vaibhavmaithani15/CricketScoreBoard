CREATE TABLE IF NOT EXISTS `scoreboard`.`baller_score` (
  `baller_score_id` INT(11) NOT NULL AUTO_INCREMENT,
  `match_id` INT(11) NOT NULL,
  `player_id` INT(11) NOT NULL,
  `no_of_overs` INT(11) NOT NULL,
  `no_of_sixes` INT(11) NOT NULL,
  `no_of_four` INT(11) NOT NULL,
  `no_of_threes` INT(11) NOT NULL,
  `no_of_tows` INT(11) NOT NULL,
  `no_of_ones` INT(11) NOT NULL,
  `medin_over` INT(11) NOT NULL,
  `missed_ball` INT(11) NOT NULL,
  `white_ball` INT(11) NOT NULL,
  `no_ball` INT(11) NOT NULL,
  `bouncer_ball` INT(11) NOT NULL,
  PRIMARY KEY (`baller_score_id`));
