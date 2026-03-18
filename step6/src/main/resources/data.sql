-- Add default Roles
INSERT INTO ROLE(id,role_name) VALUES(1,'ROLE_ADMIN');
INSERT INTO ROLE(id,role_name) VALUES(2,'ROLE_USER');

-- Add default User
INSERT INTO APPUSER(user_id,username, password) VALUES(1,'admin', '$2a$10$dq/6L9dKOq1O.Tlmm16tdeNJ0VBH6cfWE2I6MbRIHX2EQZsRV39f.');

-- Add default User - Role Association
INSERT INTO USER_ROLE(USER_id,ROLE_id) VALUES(1,1);
INSERT INTO USER_ROLE(USER_id,ROLE_id) VALUES(1,2);

INSERT INTO HERO(name, super_power_name,super_power_value, img_url) VALUES('Superman', 'strengh',100, 'https://i.gifer.com/200C.gif');
INSERT INTO HERO(name, super_power_name,super_power_value, img_url) VALUES('Falsh', 'speed',50, 'https://i.gifer.com/UJgv.gif');
INSERT INTO HERO(name, super_power_name,super_power_value, img_url) VALUES('Batman', 'technology',30, 'https://i.gifer.com/KfU.gif');
INSERT INTO HERO(name, super_power_name,super_power_value, img_url) VALUES('Thor', 'storm',100, 'https://i.gifer.com/D2Tc.gif');