package com.mwq.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class BaseDao {

	// 查询多个记录
	protected Vector selectSomeNote(String sql) {
		Vector vector = new Vector();
		Connection conn = JDBC.getConnection();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			int columnCount = rs.getMetaData().getColumnCount();
			int row = 1;
			while (rs.next()) {
				Vector rowV = new Vector();
				rowV.add(new Integer(row++));
				for (int column = 1; column <= columnCount; column++) {
					rowV.add(rs.getObject(column));
				}
				vector.add(rowV);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vector;
	}

	// 查询单个记录
	protected Vector selectOnlyNote(String sql) {
		Vector vector = null;
		Connection conn = JDBC.getConnection();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			int columnCount = rs.getMetaData().getColumnCount();
			while (rs.next()) {
				vector = new Vector();
				for (int column = 1; column <= columnCount; column++) {
					vector.add(rs.getObject(column));
				}
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vector;
	}

	// 查询多个值
	protected Vector selectSomeValue(String sql) {
		Vector vector = new Vector();
		Connection conn = JDBC.getConnection();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				vector.add(rs.getObject(1));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vector;
	}

	// 查询单个值
	protected Object selectOnlyValue(String sql) {
		Object value = null;
		Connection conn = JDBC.getConnection();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				value = rs.getObject(1);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return value;
	}

	// 插入、修改、删除记录
	public boolean longHaul(String sql) {
		boolean isLongHaul = true;
		Connection conn = JDBC.getConnection();
		try {
			conn.setAutoCommit(false);
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
			conn.commit();
		} catch (SQLException e) {
			isLongHaul = false;
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return isLongHaul;
	}

}
