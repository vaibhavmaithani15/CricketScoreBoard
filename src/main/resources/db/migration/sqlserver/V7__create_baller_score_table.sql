CREATE TABLE IF NOT EXISTS `scoreboard`.`baller_score`
(
    `baller_id`    INT(11) NOT NULL,
    `match_id`     INT(11) NOT NULL,
    `no_of_overs`  INT(11) NULL,
    `no_of_sixes`  INT(11) NULL,
    `no_of_fours`  INT(11) NULL,
    `no_of_threes` INT(11) NULL,
    `no_of_twos`   INT(11) NULL,
    `no_of_ones`   INT(11) NULL,
    `median_over`   INT(11) NULL,
    `missed_ball`  INT(11) NULL,
    `white_ball`   INT(11) NULL,
    `no_ball`      INT(11) NULL,
    `bouncer_ball` INT(11) NULL,
    `wicket_id`    INT(10) NULL,

    PRIMARY KEY (`baller_id`, `match_id`),
    CONSTRAINT `baller_constraint`
        FOREIGN KEY (`match_id`)
            REFERENCES `scoreboard`.`cricket_match` (`match_id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    FOREIGN KEY (`baller_id`)
        REFERENCES `scoreboard`.`player` (`player_id`)
#     FOREIGN KEY (`wicket_id`)
#         REFERENCES `scoreboard`.`wicket` (`wicket_id`)

);





