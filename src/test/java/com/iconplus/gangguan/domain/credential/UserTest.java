package com.iconplus.gangguan.domain.credential;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iconplus.gangguan.helper.DomainHelper;

public class UserTest {

  private static final Logger LOG = LoggerFactory.getLogger(UserTest.class);
  private static final String USERNAME = "userName";
  private static final String PASSWORD = "passWord";
  private static final String FIRSTNAME = "firstName";
  private static final String LASTNAME = "lastName";

  private User user;

  @Before
  public void initialize() throws Exception {
    user = new User(USERNAME, DomainHelper.generatePassword(PASSWORD), FIRSTNAME, LASTNAME);
  }

  @Test
  public void testGeneratingDeleteStatement() throws Exception {
    String expectedDeleteStatement =
        "DELETE FROM " + User.TABLE_NAME + " WHERE " + user.getPrimaryKeyField() + " = ?";
    LOG.info("generateDeleteStatement : {}", user.generateDeleteStatement());
    Assert.assertThat(user.generateDeleteStatement(), Matchers.equalTo(expectedDeleteStatement));
  }

  @Test
  public void testGeneratingInsertStatement() throws Exception {
    String expectedInsertStatement =
        "INSERT INTO " + User.TABLE_NAME
            + " (username, password, firstname, lastname) VALUES (?, ?, ?, ?)";
    LOG.info("generateInsertStatement : {}", user.generateInsertStatement());
    Assert.assertThat(user.generateInsertStatement(), Matchers.equalTo(expectedInsertStatement));
  }

  @Test
  public void testGeneratingUpdateStatement() throws Exception {
    String expectedUpdateStatement =
        "UPDATE " + User.TABLE_NAME
            + " SET password = ?, firstname = ?, lastname = ? WHERE username = ?";
    LOG.info("generatedUpdateStatement : {}", user.generateUpdateStatement());
    Assert.assertThat(user.generateUpdateStatement(), Matchers.equalTo(expectedUpdateStatement));
    LOG.info("user = {}", user.toString());
  }
}
