package com.iconplus.gangguan.domain.credential;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import com.iconplus.gangguan.AbstractJDBCDomain;

public class User extends AbstractJDBCDomain {

  private static final long serialVersionUID = -1475284770745311196L;

  public static final String TABLE_NAME = "MGT_USERS";

  public static RowMapper<User> getRowMapper() {
    RowMapper<User> rowMapper = new RowMapper<User>() {

      @Override
      public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new User(rs.getString("username"), rs.getString("password"),
            rs.getString("firstname"), rs.getString("lastname"));
      }

    };
    return rowMapper;
  }

  private String username;
  private String password;
  private String firstname;

  private String lastname;

  public User() {
    // nothing to do here
  }

  public User(String username, String password, String firstname, String lastname) {
    this.username = username;
    this.password = password;
    this.firstname = firstname;
    this.lastname = lastname;
  }

  @Override
  public Map<String, String> getDatabaseDomainFieldMap() {
    Map<String, String> databaseFieldMap = new HashMap<String, String>();
    databaseFieldMap.put("username", "username");
    databaseFieldMap.put("password", "password");
    databaseFieldMap.put("firstname", "firstname");
    databaseFieldMap.put("lastname", "lastname");
    return databaseFieldMap;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getPassword() {
    return password;
  }

  @Override
  public String getPrimaryKeyField() {
    return "username";
  }

  @Override
  public String getTableName() {
    return TABLE_NAME;
  }

  public String getUsername() {
    return username;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  @Override
  public String toString() {
    return "User [username=" + username + ", password=" + password + ", firstname=" + firstname
        + ", lastname=" + lastname + ", toString()=" + super.toString() + "]";
  }
}
