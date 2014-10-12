package com.iconplus.gangguan.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import com.iconplus.gangguan.AbstractJDBCDomain;

public class ApplicationMenu extends AbstractJDBCDomain {

  private static final long serialVersionUID = -2969264271953895482L;
  public static final String TABLE_NAME = "MGT_APP_MENUS";

  public static RowMapper<ApplicationMenu> getRowMapper() {
    return new RowMapper<ApplicationMenu>() {
      @Override
      public ApplicationMenu mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new ApplicationMenu(rs.getString("id"), rs.getString("parent"),
            rs.getString("label"), rs.getString("relative_url"));
      }
    };
  }

  private String id;
  private String parent;
  private String label;
  private String relativeUrl;

  public ApplicationMenu() {
    // nothing to do here
  }

  public ApplicationMenu(String id, String parent, String label, String relativeUrl) {
    this.id = id;
    this.parent = parent;
    this.label = label;
    this.relativeUrl = relativeUrl;
  }

  @Override
  public Map<String, String> getDatabaseDomainFieldMap() {
    Map<String, String> fields = new HashMap<String, String>();
    fields.put("id", "id");
    fields.put("parent", "parent");
    fields.put("label", "label");
    fields.put("relativeUrl", "relative_url");
    return fields;
  }

  public String getId() {
    return id;
  }

  public String getLabel() {
    return label;
  }

  public String getParent() {
    return parent;
  }

  @Override
  public String getPrimaryKeyField() {
    return "id";
  }

  public String getRelativeUrl() {
    return relativeUrl;
  }

  @Override
  public String getTableName() {
    return TABLE_NAME;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public void setParent(String parent) {
    this.parent = parent;
  }

  public void setRelativeUrl(String relativeUrl) {
    this.relativeUrl = relativeUrl;
  }

  @Override
  public String toString() {
    return "ApplicationMenu [id=" + id + ", parent=" + parent + ", label=" + label
        + ", relativeUrl=" + relativeUrl + ", toString()=" + super.toString() + "]";
  }

}
