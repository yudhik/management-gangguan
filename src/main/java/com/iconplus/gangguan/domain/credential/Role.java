package com.iconplus.gangguan.domain.credential;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import com.iconplus.gangguan.domain.AbstractJDBCDomain;

public class Role extends AbstractJDBCDomain {

  private static final long serialVersionUID = 3255710076599893263L;

  public static final String TABLE_NAME = "MGT_ROLES";

  public static RowMapper<Role> getRowMapper() {
    RowMapper<Role> rowMapper = new RowMapper<Role>() {
      @Override
      public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Role(rs.getString("id"), rs.getString("name"));
      }
    };
    return rowMapper;
  }

  private String id;

  private String name;

  public Role() {
    // nothing to do here
  }

  public Role(String id, String name) {
    this.id = id;
    this.name = name;
  }

  @Override
  public Map<String, String> getDatabaseDomainFieldMap() {
    Map<String, String> fields = new HashMap<String, String>();
    fields.put("id", "id");
    fields.put("name", "name");
    return fields;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  @Override
  public String getPrimaryKeyField() {
    return "id";
  }

  @Override
  public String getTableName() {
    return TABLE_NAME;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Role [id=" + id + ", name=" + name + ", toString()=" + super.toString() + "]";
  }



}
