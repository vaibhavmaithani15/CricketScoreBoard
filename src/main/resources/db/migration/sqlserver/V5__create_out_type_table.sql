CREATE TABLE IF NOT EXISTS `scoreboard`.`out_type`
(
    `type_id` INT(11)     NOT NULL,
    `type`    VARCHAR(45) NOT NULL,
    PRIMARY KEY (`type_id`)
);
INSERT INTO out_type values (1,'BOLD');
INSERT INTO out_type values (2,'RUNOUT');
INSERT INTO out_type values (3,'CATCHOUT');
INSERT INTO out_type values (4,'LBWOUT');
INSERT INTO out_type values (5,'HITWICKET');
INSERT INTO out_type values (6,'STUMPOUT');
INSERT INTO out_type values (7,'RETIREDHURT');