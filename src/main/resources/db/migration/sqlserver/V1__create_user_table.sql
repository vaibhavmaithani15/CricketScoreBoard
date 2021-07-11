CREATE TABLE user (
                      user_name varchar(100) PRIMARY KEY,
                      password VARCHAR(100) NOT NULL,
                      first_name varchar(50),
                      last_name varchar(50),
                      role varchar(20),
                      enabled INT NOT NULL DEFAULT 1,
                      user_created_by varchar(50)
);

INSERT INTO user values ('admin','21232f297a57a5a743894a0e4a801fc3','rahul','maithani','adimn',1,'System');
