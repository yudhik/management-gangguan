package com.iconplus.gangguan.domain;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iconplus.gangguan.helper.DomainHelper;

public class ApplicationMenuTest {

  private static final Logger LOG = LoggerFactory.getLogger(ApplicationMenuTest.class);

  private static final String APPLICATION_MENU_ID = DomainHelper.generateUUIDString();
  private static final String PARENT_ID = null;
  private static final String LABEL = "LABEL";
  private static final String RELATIVE_URL = "RELATIVE_URL";

  private ApplicationMenu applicationMenu;

  @Before
  public void initializeTest() {
    applicationMenu = new ApplicationMenu(APPLICATION_MENU_ID, PARENT_ID, LABEL, RELATIVE_URL);
  }

  @Test
  public void testGeneratingDeleteStatement() throws Exception {
    String expectedDeleteStatement =
        "DELETE FROM " + ApplicationMenu.TABLE_NAME + " WHERE "
            + applicationMenu.getPrimaryKeyField() + " = ?";
    LOG.info("generateDeleteStatement : {}", applicationMenu.generateDeleteStatement());
    Assert.assertThat(applicationMenu.generateDeleteStatement(),
        Matchers.equalTo(expectedDeleteStatement));
  }

  @Test
  public void testGeneratingInsertStatement() {
    String expectedInsertStatement =
        "INSERT INTO " + ApplicationMenu.TABLE_NAME
            + " (id, parent, label, relative_url) VALUES (?, ?, ?, ?)";
    LOG.info("generateInsertStatement : {}", applicationMenu.generateInsertStatement());
    Assert.assertThat(applicationMenu.generateInsertStatement(),
        Matchers.equalTo(expectedInsertStatement));
  }

  @Test
  public void testGeneratingUpdateStatement() {
    String expectedUpdateStatement =
        "UPDATE " + ApplicationMenu.TABLE_NAME
            + " SET parent = ?, label = ?, relative_url = ? WHERE id = ?";
    LOG.info("generatedUpdateStatement : {}", applicationMenu.generateUpdateStatement());
    Assert.assertThat(applicationMenu.generateUpdateStatement(),
        Matchers.equalTo(expectedUpdateStatement));
    LOG.info("applicationMenu = {}", applicationMenu.toString());
  }

}
