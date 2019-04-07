package com.wsy.struts.datasource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;

import javax.sql.DataSource;

import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;

public class DBnew implements PlugIn {

 private static DataSource dataSource = null;

 private Connection conn = null;

 private PreparedStatement preStmt = null;

 private Statement stmt = null;

 // 得到数据源
 public void init(ActionServlet servlet, ModuleConfig config) {
  dataSource = (DataSource) servlet.getServletContext().getAttribute(
    "dataSources");
   }

 public DBnew() throws SQLException {
  if (dataSource != null) 
     conn = dataSource.getConnection();
 }

 public ResultSet executeQuery(String sql) {
  ResultSet rs = null;
  try {
   if (stmt == null) {
    stmt = conn.createStatement();
   }
   rs = stmt.executeQuery(sql);
  } catch (SQLException e) {
   e.printStackTrace();
  }
  return rs;
 }

 public void executeUpdate(String sql) throws SQLException {
  if (stmt == null) {
   stmt = conn.createStatement();
  }
  stmt.executeUpdate(sql);
 }

 public Connection getConn() {
  return conn;
 }

 public void prepareStatement(String sqlStr) throws SQLException {
  preStmt = conn.prepareStatement(sqlStr);
 }

 public void setString(int index, String value) throws SQLException {
  preStmt.setString(index, value);
 }

 public void setInt(int index, int value) throws SQLException {
  preStmt.setInt(index, value);
 }

 public void setBoolean(int index, boolean value) throws SQLException {
  preStmt.setBoolean(index, value);
 }

 public void setLong(int index, long value) throws SQLException {
  preStmt.setLong(index, value);
 }

 public void setFloat(int index, float value) throws SQLException {
  preStmt.setFloat(index, value);
 }

 public void setBytes(int index, byte[] value) throws SQLException {
  preStmt.setBytes(index, value);
 }

 public void clearPreStmt() throws SQLException {
  preStmt.clearParameters();
  preStmt = null;
 }

 public ResultSet executeQuery() throws SQLException {
  if (preStmt != null) {
   return preStmt.executeQuery();
  } else
   return null;
 }

 public void executeUpdate() throws SQLException {
  if (preStmt != null)
   preStmt.executeUpdate();
 }

 public void close() {
  try {
   if (stmt != null) {
    stmt.close();
    stmt = null;
   }
   if (preStmt != null) {
    preStmt.close();
    preStmt = null;
   }
   if (conn != null) {
    conn.close();
    conn = null;
    System.out.println("***************** a connection is closed");
   }
  } catch (Exception e) {
   System.err.println(e.getMessage());
  }
 }

 public void destroy() { }
 public static void main(String args[]){
	 try{
		 DBnew db=new DBnew();
		 System.out.println(db.conn);
		  //Enumeration en = servlet.getServletContext().getAttributeNames();
		  //while (en.hasMoreElements()) {
		   //System.out.println(en.nextElement().toString());
		// }
		 System.out.println("1"!="1");
	 }catch(Exception e){
		 e.printStackTrace();
	 }
 }
}

