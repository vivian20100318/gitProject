package com.hjkj.common.db;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.*;

import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;

public class SqlMapManager {

  private static final SqlMapClient sqlMap;

  static {

    try {
      String resource = "com/hjkj/common/db/sql-map-config.xml";
      Reader reader = Resources.getResourceAsReader(resource);
      sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
    } catch (Exception e) {
      throw new RuntimeException("Could not initialize SqlMapClient.  Cause: " + e);
    }
  }

  public static SqlMapClient getSqlMap() {
    return sqlMap;
  }
  
  
  public static Connection getConnection() throws SQLException{
	  Connection conn = sqlMap.getCurrentConnection();
	  if(conn == null){
		  conn = sqlMap.getDataSource().getConnection();
	  }
	  return conn;
  }
  public static void main(String[] args) {
	  SqlMapClient client = getSqlMap();
  }
}
