package com.iconplus.gangguan;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public abstract class AbstractJDBCDomain implements Serializable, CrudOperation {

  private static final long serialVersionUID = 8953624504593744767L;
  private static final Logger LOG = LoggerFactory.getLogger(AbstractJDBCDomain.class);
  private static final String DELIMITER = ", ";
  private static final String QUESTION_CHARACTER = "?";
  private static final String EQUAL_QUESTION_CHARACTER = " = ?";
  private static final String TABLE_NAME_KEY = "{tableName}";
  private static final String ALL_FIELDS_KEY = "{allFields}";
  private static final String ALL_VALUES_KEY = "{allValues}";
  private static final String PRIMARY_KEY_FIELD = "{primaryKeyField}";
  private static final String PRIMARY_KEY_WHERE_CONDITION = " WHERE " + PRIMARY_KEY_FIELD
      + EQUAL_QUESTION_CHARACTER;
  private static final String INSERT_STATEMENT_TEMPLATE = "INSERT INTO " + TABLE_NAME_KEY + " ("
      + ALL_FIELDS_KEY + ") VALUES (" + ALL_VALUES_KEY + ")";
  private static final String DELETE_STATEMENT_TEMPLATE = "DELETE FROM " + TABLE_NAME_KEY
      + PRIMARY_KEY_WHERE_CONDITION;
  private static final String UPDATE_STATEMENT_TEMPLATE = "UPDATE " + TABLE_NAME_KEY + " SET "
      + ALL_FIELDS_KEY + PRIMARY_KEY_WHERE_CONDITION;

  @Override
  public String generateDeleteStatement() {
    return DELETE_STATEMENT_TEMPLATE.replace(TABLE_NAME_KEY, getTableName()).replace(
        PRIMARY_KEY_FIELD, getPrimaryKeyField());
  }

  @Override
  public String generateInsertStatement() {
    Field[] fields = this.getClass().getDeclaredFields();
    StringBuilder allFieldsBuilder = new StringBuilder();
    StringBuilder allValuesBuilder = new StringBuilder();
    Map<String, String> fieldToDatabaseColumnMap = getDatabaseDomainFieldMap();
    int index = 0;
    for (Field field : fields) {
      String databaseFieldName = fieldToDatabaseColumnMap.get(field.getName());
      LOG.info("field name : {}", field.getName());
      if (!StringUtils.isEmpty(databaseFieldName)) {
        LOG.info("found mapping for {} with value {}", new Object[] {field.getName(),
            databaseFieldName});
        allFieldsBuilder.append(databaseFieldName);
        allValuesBuilder.append(QUESTION_CHARACTER);
        if (index < (fieldToDatabaseColumnMap.size() - 1)) {
          allFieldsBuilder.append(DELIMITER);
          allValuesBuilder.append(DELIMITER);
        }
        index++;
      } else {
        LOG.info("ignoring field : {}", field.getName());
      }
    }
    return INSERT_STATEMENT_TEMPLATE.replace(TABLE_NAME_KEY, getTableName())
        .replace(ALL_FIELDS_KEY, allFieldsBuilder.toString())
        .replace(ALL_VALUES_KEY, allValuesBuilder.toString());
  }

  @Override
  public String generateUpdateStatement() {
    Field[] fields = this.getClass().getDeclaredFields();
    StringBuilder allFieldsBuilder = new StringBuilder();
    Map<String, String> fieldToDatabaseColumnMap = getDatabaseDomainFieldMap();
    fieldToDatabaseColumnMap.remove(getPrimaryKeyField());
    int index = 0;
    for (Field field : fields) {
      String databaseFieldName = fieldToDatabaseColumnMap.get(field.getName());
      LOG.info("field name : {}", field.getName());
      if (!StringUtils.isEmpty(databaseFieldName)) {
        LOG.info("found mapping for {} with value {}", new Object[] {field.getName(),
            databaseFieldName});
        allFieldsBuilder.append(databaseFieldName).append(EQUAL_QUESTION_CHARACTER);
        if (index < (fieldToDatabaseColumnMap.size() - 1)) {
          allFieldsBuilder.append(DELIMITER);
        }
        index++;
      } else {
        LOG.info("ignoring field : {}", databaseFieldName);
      }
    }
    return UPDATE_STATEMENT_TEMPLATE.replace(TABLE_NAME_KEY, getTableName())
        .replace(PRIMARY_KEY_FIELD, getPrimaryKeyField())
        .replace(ALL_FIELDS_KEY, allFieldsBuilder.toString());
  }

  public abstract Map<String, String> getDatabaseDomainFieldMap();

  public abstract String getPrimaryKeyField();

  public abstract String getTableName();

  @Override
  public String toString() {
    return "AbstractJDBCDomain [getDatabaseDomainFieldMap()=" + getDatabaseDomainFieldMap()
        + ", getPrimaryKeyField()=" + getPrimaryKeyField() + ", getTableName()=" + getTableName()
        + "]";
  }
}
