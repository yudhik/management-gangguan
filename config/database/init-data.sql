INSERT INTO MGT_USERS (username, password, firstname, lastname) VALUES ('administrator', '$2a$10$Fmocwx/og6rYS4g1RlBEKeDUxIj4x164bPxnrZoAWkp.Uv2jQ/0SS', 'administrator', '1');
-- passwordnya : passWord

INSERT INTO MGT_ROLES (id, name) values ('8CB5FF5280634880B6964DD190D67D0C', 'ROLE_USER');
INSERT INTO MGT_ROLES (id, name) values ('CDA14C84A90441F4A23AC17240152B96', 'ROLE_ADMINISTRATOR');
INSERT INTO MGT_USER_ROLES (username , role_id) values ('administrator', '8CB5FF5280634880B6964DD190D67D0C');
INSERT INTO MGT_USER_ROLES (username , role_id) values ('administrator', 'CDA14C84A90441F4A23AC17240152B96');

INSERT INTO MGT_APP_MENUS(id, parent, label, relative_url)
    VALUES ('BBDEE0F0114640D782D61044CD78B312', NULL, 'Administration', '#');

INSERT INTO MGT_APP_MENUS(id, parent, label, relative_url)
    VALUES ('61BED7EC3A544B51B68320AA5F6032C9', 'BBDEE0F0114640D782D61044CD78B312', 'User', '/view/administration/user/summary');

INSERT INTO MGT_APP_MENUS(id, parent, label, relative_url)
    VALUES ('AD2EDA4CBEF142AB992E5869275669BC', 'BBDEE0F0114640D782D61044CD78B312', 'Roles', '/view/administration/roles/summary');

INSERT INTO MGT_APP_MENUS(id, parent, label, relative_url)
    VALUES ('F0723EC8AF924891BCB704898712D414', 'BBDEE0F0114640D782D61044CD78B312', 'User Role', '/view/administration/userRole/summary');

INSERT INTO MGT_APP_MENUS(id, parent, label, relative_url)
    VALUES ('5E3F338BFC9A474B841BC4CAE2A61AFD', 'BBDEE0F0114640D782D61044CD78B312', 'Menu', '/view/administration/menu/summary');

INSERT INTO MGT_APP_MENUS(id, parent, label, relative_url)
    VALUES ('D179C670ABF04B18871CFBEAB0AE90CD', 'BBDEE0F0114640D782D61044CD78B312', 'Role Menu', '/view/administration/roleMenu/summary');

    
INSERT INTO MGT_ROLE_MENUS(
            role_id, menu_id)
    VALUES ('CDA14C84A90441F4A23AC17240152B96', 'BBDEE0F0114640D782D61044CD78B312');


