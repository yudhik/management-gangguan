package com.iconplus.gangguan.domain.credential;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class UserRole {

  public static final String TABLE_NAME = "MGT_USER_ROLES";

  public static RowMapper<UserRole> getRowMapper() {
    RowMapper<UserRole> rowMapper = new RowMapper<UserRole>() {
      @Override
      public UserRole mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new UserRole(rs.getString("username"), rs.getString("role_id"));
      }
    };
    return rowMapper;
  }

  private String username;

  private String roleId;

  public UserRole() {
    // nothing to do here;
  }

  public UserRole(String username, String roleId) {
    this.username = username;
    this.roleId = roleId;
  }

  public String getRoleId() {
    return roleId;
  }

  public String getUsername() {
    return username;
  }

  public void setRoleId(String roleId) {
    this.roleId = roleId;
  }

  public void setUsername(String username) {
    this.username = username;
  }

}
