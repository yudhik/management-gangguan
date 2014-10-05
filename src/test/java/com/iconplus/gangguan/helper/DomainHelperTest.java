package com.iconplus.gangguan.helper;

import java.util.UUID;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DomainHelperTest {

  private static final Logger LOG = LoggerFactory.getLogger(DomainHelperTest.class);

  private String uuidString;

  @Before
  public void initialize() {
    uuidString = DomainHelper.generateUUIDString();
  }

  @Test
  public void testCreatingUUID() {
    LOG.info("UUID Format = {}", UUID.randomUUID().toString());

    StringBuilder uuidFromString = new StringBuilder(uuidString);
    uuidFromString.insert(8, "-");
    uuidFromString.insert(13, "-");
    uuidFromString.insert(18, "-");
    uuidFromString.insert(23, "-");
    LOG.info("UUID Format = {}", uuidFromString.toString().toLowerCase());

    UUID generatedUUID = DomainHelper.generateUUIDFrom(uuidString);
    LOG.info("generated UUID = {}, uuidString = {}", new Object[] {generatedUUID, uuidString});
    Assert.assertThat(DomainHelper.generateStringFrom(generatedUUID),
        Matchers.equalTo(uuidString));
  }

}
