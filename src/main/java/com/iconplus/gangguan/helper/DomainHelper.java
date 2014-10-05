package com.iconplus.gangguan.helper;

import java.util.UUID;
import java.util.concurrent.RejectedExecutionException;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class DomainHelper {

  private static final String DELIMITER = "-";

  public static String generatePassword(String password) throws Exception {
    return new BCryptPasswordEncoder().encode(password);
  }

  public static final String generateStringFrom(UUID uuid) {
    return uuid.toString().replace("-", "").toUpperCase();
  }

  public static final UUID generateUUIDFrom(String uuidString) {
    // UUID FORMAT : 5bbe204e-7e62-4ab1-ab16-1485dfa07897
    StringBuilder uuidFromString = new StringBuilder(uuidString);
    uuidFromString.insert(8, DELIMITER);
    uuidFromString.insert(13, DELIMITER);
    uuidFromString.insert(18, DELIMITER);
    uuidFromString.insert(23, DELIMITER);
    return UUID.fromString(uuidFromString.toString().toLowerCase());
  }

  public static final String generateUUIDString() {
    return UUID.randomUUID().toString().replace("-", "").toUpperCase();
  }

  private DomainHelper() {
    throw new RejectedExecutionException("this constructor should not be executed");
  }


}
