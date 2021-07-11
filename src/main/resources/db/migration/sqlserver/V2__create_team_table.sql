CREATE TABLE IF NOT EXISTS `scoreboard`.`team`
(
    name     VARCHAR(100)  NOT NULL PRIMARY KEY,
    description     VARCHAR(200) NULL,
    selector VARCHAR(100)  NULL,
    country  VARCHAR(100)  NOT NULL
);

INSERT INTO team
values ('IND', 'TOP team in ICC', 'ravi', 'INDIA');
INSERT INTO team
values ('ENG', 'SECOND Top team in ICC', 'mark', 'ENGLAND');
INSERT INTO team
values ('AUS', 'THIRD TOP team in ICC', 'andrew', 'AUSTRLIA');
INSERT INTO team
values ('NEW', 'FOURTH TOP team in ICC', 'sam', 'NEWZLAND');
