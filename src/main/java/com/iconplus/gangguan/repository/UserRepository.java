package com.iconplus.gangguan.repository;

import java.util.concurrent.RejectedExecutionException;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.iconplus.gangguan.domain.credential.User;

@Repository
public class UserRepository {

  private static final String FIND_BY_USERNAME = "SELECT * FROM " + User.TABLE_NAME
      + " WHERE username = ?";
  // TODO:ADD Implementation here depending to your database;
  private static final String FIND_ALL_PAGINATION = "";

  @Inject
  private JdbcTemplate jdbcTemplate;

  public User findAllUser(int page, int size) {
    throw new RejectedExecutionException("Do implement Find All pagination at this class");
  }

  public User findByByUsername(String userName) {
    User user =
        jdbcTemplate.queryForObject(FIND_BY_USERNAME, new Object[] {userName}, User.getRowMapper());
    return user;
  }

  public JdbcTemplate getJdbcTemplate() {
    return jdbcTemplate;
  }

  public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }


}
