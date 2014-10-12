package com.iconplus.gangguan.repository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.iconplus.gangguan.domain.ApplicationMenu;
import com.iconplus.gangguan.domain.RoleMenu;
import com.iconplus.gangguan.helper.DomainHelper;

@Repository
public class ApplicationMenuRepository {

  @Inject
  private JdbcTemplate jdbcTemplate;

  @Inject
  private RoleRepository roleRepository;

  private static final String FIND_ALL_ROLE_MENU = "SELECT * FROM " + RoleMenu.TABLE_NAME;
  private static final String FIND_AVAILABLE_ROLE_MENU = FIND_ALL_ROLE_MENU
      + " WHERE role_id in (:roles)";
  private static final String FIND_ALL_MENU = "SELECT * FROM " + ApplicationMenu.TABLE_NAME;
  private static final String FIND_ALL_MENU_WITH_RELATED_ID = FIND_ALL_MENU
      + " WHERE id = ? or parent = ?";
  private static final String FIND_ALL_MENU_WITH_ID = FIND_ALL_MENU + " WHERE id = ?";


  public void delete(ApplicationMenu applicationMenu) {
    getJdbcTemplate().update(applicationMenu.generateDeleteStatement(), applicationMenu.getId());
  }

  public List<ApplicationMenu> findAllMenu() {
    return getJdbcTemplate().query(FIND_ALL_MENU, ApplicationMenu.getRowMapper());
  }

  public List<ApplicationMenu> findAllMenu(Integer page, Integer size) {
    // TODO : implement pagination here
    throw new RuntimeException("please implement pagination in repository");
  }

  public List<ApplicationMenu> findAvailableMenuForRole(List<String> roles) {
    NamedParameterJdbcTemplate namedParameterJdbcTemplate =
        new NamedParameterJdbcTemplate(jdbcTemplate);
    MapSqlParameterSource parameters = new MapSqlParameterSource();
    List<String> roleIds = new ArrayList<String>();
    for (String roleName : roles) {
      roleIds.add(roleRepository.findRoleByName(roleName).getId());
    }
    parameters.addValue("roles", roleIds);
    List<RoleMenu> roleMenus =
        namedParameterJdbcTemplate.query(FIND_AVAILABLE_ROLE_MENU, parameters,
            RoleMenu.getRowMapper());
    List<ApplicationMenu> availableMenu = new ArrayList<ApplicationMenu>();
    for (RoleMenu roleMenu : roleMenus) {
      List<ApplicationMenu> applicationMenus =
          getJdbcTemplate().query(FIND_ALL_MENU_WITH_RELATED_ID,
              new Object[] {roleMenu.getMenuId(), roleMenu.getMenuId()},
              ApplicationMenu.getRowMapper());
      for (ApplicationMenu applicationMenu : applicationMenus) {
        availableMenu.add(applicationMenu);
      }
    }
    return availableMenu;
  }

  public ApplicationMenu findById(String id) {
    return getJdbcTemplate().queryForObject(FIND_ALL_MENU_WITH_ID, new Object[] {id},
        ApplicationMenu.class);
  }

  public JdbcTemplate getJdbcTemplate() {
    return jdbcTemplate;
  }

  public void save(ApplicationMenu applicationMenu) {
    getJdbcTemplate().update(applicationMenu.generateInsertStatement(),
        DomainHelper.generateUUIDString(), applicationMenu.getParent(), applicationMenu.getLabel(),
        applicationMenu.getRelativeUrl());
  }

  public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public void update(ApplicationMenu applicationMenu) {
    getJdbcTemplate().update(applicationMenu.generateUpdateStatement(), applicationMenu.getId(),
        applicationMenu.getParent(), applicationMenu.getLabel(), applicationMenu.getRelativeUrl());
  }


}
