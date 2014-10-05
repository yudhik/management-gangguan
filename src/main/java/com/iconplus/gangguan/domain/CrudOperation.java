package com.iconplus.gangguan.domain;

public interface CrudOperation {

  String generateDeleteStatement();

  String generateInsertStatement();

  String generateUpdateStatement();

}
