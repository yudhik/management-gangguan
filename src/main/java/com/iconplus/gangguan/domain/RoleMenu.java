package com.iconplus.gangguan.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class RoleMenu {

  public static final String TABLE_NAME = "MGT_ROLE_MENUS";

  public static RowMapper<RoleMenu> getRowMapper() {
    return new RowMapper<RoleMenu>() {
      @Override
      public RoleMenu mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new RoleMenu(rs.getString("role_id"), rs.getString("menu_id"));
      }
    };
  }

  private String roleId;

  private String menuId;

  public RoleMenu() {
    // nothing to do here
  }

  public RoleMenu(String roleId, String menuId) {
    this.roleId = roleId;
    this.menuId = menuId;
  }

  public String getMenuId() {
    return menuId;
  }

  public String getRoleId() {
    return roleId;
  }

  public void setMenuId(String menuId) {
    this.menuId = menuId;
  }

  public void setRoleId(String roleId) {
    this.roleId = roleId;
  }



}
