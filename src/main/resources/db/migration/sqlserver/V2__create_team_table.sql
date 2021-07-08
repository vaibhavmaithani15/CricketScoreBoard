CREATE TABLE `scoreboard`.`team` (
                                     `team_id` INT UNSIGNED NOT NULL,
                                     `team_name` VARCHAR(45) NOT NULL,
                                     `team_desc` VARCHAR(45) NULL,
                                     `team_selector` VARCHAR(45) NOT NULL,
                                     `team_country` VARCHAR(45) NOT NULL,
                                     PRIMARY KEY (`team_id`));