package com.iconplus.gangguan;

public interface CrudOperation {

  String generateDeleteStatement();

  String generateInsertStatement();

  String generateUpdateStatement();

}
