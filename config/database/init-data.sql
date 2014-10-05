INSERT INTO MGT_USERS (username, password, firstname, lastname) VALUES ('administrator', '$2a$10$Fmocwx/og6rYS4g1RlBEKeDUxIj4x164bPxnrZoAWkp.Uv2jQ/0SS', 'administrator', '1');
-- passwordnya : passWord
INSERT INTO MGT_ROLES (id, name) values ('8CB5FF5280634880B6964DD190D67D0C', 'ROLE_USER');
INSERT INTO MGT_ROLES (id, name) values ('CDA14C84A90441F4A23AC17240152B96', 'ROLE_ADMINISTRATOR');
INSERT INTO MGT_USER_ROLES (username , role_id) values ('administrator', '8CB5FF5280634880B6964DD190D67D0C');
INSERT INTO MGT_USER_ROLES (username , role_id) values ('administrator', 'CDA14C84A90441F4A23AC17240152B96');

