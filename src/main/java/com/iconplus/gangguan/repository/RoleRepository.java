package com.iconplus.gangguan.repository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.iconplus.gangguan.domain.credential.Role;
import com.iconplus.gangguan.domain.credential.UserRole;

@Repository
public class RoleRepository {

  private static final String FIND_BY_USERNAME = "SELECT * FROM " + UserRole.TABLE_NAME
      + " WHERE username = ?";
  private static final String FIND_BY_ID = "SELECT * FROM " + Role.TABLE_NAME + " WHERE id = ?";
  private static final String FIND_ROLE_BY_NAME = "SELECT * FROM " + Role.TABLE_NAME
      + " WHERE name = ?";

  @Inject
  private JdbcTemplate jdbcTemplate;

  public Role findRoleByName(String roleName) {
    return jdbcTemplate.queryForObject(FIND_ROLE_BY_NAME, new Object[] {roleName},
        Role.getRowMapper());
  }

  public List<Role> findRoleByUsername(String username) {
    List<Role> roles = new ArrayList<Role>();
    List<UserRole> userRoles =
        jdbcTemplate.query(FIND_BY_USERNAME, new Object[] {username}, UserRole.getRowMapper());
    for (UserRole userRole : userRoles) {
      roles.add(jdbcTemplate.queryForObject(FIND_BY_ID, new Object[] {userRole.getRoleId()},
          Role.getRowMapper()));
    }
    return roles;
  }

  public JdbcTemplate getJdbcTemplate() {
    return jdbcTemplate;
  }

  public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }
}
