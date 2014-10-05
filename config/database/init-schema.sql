-- Table: MGT_USERS
-- DROP TABLE MGT_USERS;
CREATE TABLE MGT_USERS
(
  username character varying(255) NOT NULL,
  password character varying(255),
  firstname character varying(255),
  lastname character varying(255),
  CONSTRAINT mgt_user_pk PRIMARY KEY (username)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE MGT_USERS
  OWNER TO yudhik;
  
-- Table: MGT_ROLES
-- DROP TABLE MGT_ROLES;
CREATE TABLE MGT_ROLES
(
  name character varying(255),
  id character varying(32) NOT NULL,
  CONSTRAINT mgt_roles_pk PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE MGT_ROLES
  OWNER TO yudhik;

-- Table: MGT_USER_ROLES
-- DROP TABLE MGT_USER_ROLES;
CREATE TABLE MGT_USER_ROLES
(
  username character varying(255) NOT NULL,
  role_id character varying(32) NOT NULL,
  CONSTRAINT mgt_user_roles_pk PRIMARY KEY (username, role_id),
  CONSTRAINT mgt_user_roles_fk_role_id FOREIGN KEY (role_id)
      REFERENCES MGT_ROLES (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT mgt_user_roles_fk_username FOREIGN KEY (username)
      REFERENCES MGT_USERS (username) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE MGT_USER_ROLES
  OWNER TO yudhik;

-- Table: MGT_APP_MENUS
-- DROP TABLE MGT_APP_MENUS;
CREATE TABLE MGT_APP_MENUS
(
  id character varying(32) NOT NULL,
  parent character varying(32),
  label character varying(255),
  relative_url character varying(255),
  CONSTRAINT mgt_app_menus_pk PRIMARY KEY (id),
  CONSTRAINT mgt_app_menus_fk_self FOREIGN KEY (parent)
      REFERENCES MGT_APP_MENUS (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE MGT_APP_MENUS
  OWNER TO yudhik;
  
-- Table: MGT_ROLE_MENUS
-- DROP TABLE MGT_ROLE_MENUS;
CREATE TABLE MGT_ROLE_MENUS
(
  role_id character varying(32) NOT NULL,
  menu_id character varying(32) NOT NULL,
  CONSTRAINT role_menu_pk PRIMARY KEY (role_id, menu_id),
  CONSTRAINT role_menus_fk_menu_id FOREIGN KEY (menu_id)
      REFERENCES MGT_APP_MENUS (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT roles_menu_fk_role_id FOREIGN KEY (role_id)
      REFERENCES MGT_ROLES (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE MGT_ROLE_MENUS
  OWNER TO yudhik;

