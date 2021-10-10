CREATE TABLE user (
                      user_name varchar(100) PRIMARY KEY,
                      password VARCHAR(100) NOT NULL,
                      first_name varchar(50),
                      last_name varchar(50),
                      role varchar(20),
                      enabled INT NOT NULL DEFAULT 1,
                      user_created_by varchar(50)
);
INSERT INTO user (user_name,password,first_name,last_name,role,enabled,user_created_by) values ('admin','$2a$04$mSlIDuZEJRSAV98WiUeoSOtKpVIcpqw5X9eOAlhtNsTpSskYdpivW','rahul','maithani','admin',1,'System');
INSERT INTO user (user_name,password,first_name,last_name,role,enabled,user_created_by) values ('rahul','$2a$12$70N7pPSUswUgsZ3ZsA57.ON0eJV/w5SiQnfzn5x./QEMMgDIobXmC','rahul','maithani','admin',1,'System');
