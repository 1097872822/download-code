package com.wsy;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class connsqlserver {

	/** 
	 *   @param   args 
	 */
	private static Connection cn = null;

	private void getConnection() {
		if (cn != null) {
			return;
		}
		Context ctx;
		try {
			ctx = new InitialContext();
			DataSource ds = (DataSource) ctx
					.lookup("java:comp/env/jdbc/ConnectionPool");
			cn = ds.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return;
	}

	public ResultSet executeQuery(String sql) {
		if (cn == null)
			getConnection();
		try {
			return cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
		}
	}

	public int executeUpdate(String sql) {
		if (cn == null)
			getConnection();
		try {
			return cn.createStatement().executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} finally {
		}
	}

	public void close() {
		try {
			cn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			cn = null;
		}
	}
}
