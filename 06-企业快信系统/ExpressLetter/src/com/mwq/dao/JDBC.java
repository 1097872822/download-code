package com.mwq.dao;

import java.io.File;
import java.sql.*;

import javax.swing.JOptionPane;

public class JDBC {

	private static final String DRIVERCLASS = "org.apache.derby.jdbc.EmbeddedDriver"; // 数据库驱动

	private static final String URL = "jdbc:derby:db_ExpressLetter";// 数据库URL

	private static final ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>(); // 创建用来保存数据库连接的线程

	private static Connection conn = null;// 数据库连接

	static { // 通过静态方法加载数据库驱动，并且在数据库不存在的情况下创建数据库
		try {
			Class.forName(DRIVERCLASS).newInstance(); // 加载数据库驱动
			File dbExpressLetterFile = new File("db_ExpressLetter");// 创建数据库文件对象
			if (!dbExpressLetterFile.exists()) {// 判断数据库文件是否存在
				String[] sqls = new String[6];// 定义创建数据库的SQL语句
				sqls[0] = "create table tb_info (num int not null,type_id int not null,content varchar(500) not null,primary key (num))";
				sqls[1] = "create table tb_personnel (num int not null,type_id int not null,name varchar(8) not null,sex char(2) not null,birthday date not null,company varchar(50) not null,dept varchar(40) not null,duty varchar(30) not null,handset varchar(15) not null,email varchar(30) not null,primary key (num))";
				sqls[2] = "create table tb_type (id int not null,name varchar(20) not null,used char(4) not null,primary key (id))";
				sqls[3] = "create table tb_user (id int not null,name varchar(8) not null,sex char(2) not null,birthday date not null,id_card varchar(20) not null,password varchar(20) not null,freeze char(4) not null,primary key (id))";
				sqls[4] = "create view v_info_type  as SELECT tb_info.num, tb_type.name AS type_name, tb_info.content FROM tb_info INNER JOIN tb_type ON tb_info.type_id = tb_type.id ";
				sqls[5] = "create view v_personnel_type as SELECT tb_personnel.num, tb_type.name AS type_name, tb_personnel.name, tb_personnel.sex, tb_personnel.birthday, tb_personnel.company, tb_personnel.dept, tb_personnel.duty, tb_personnel.handset, tb_personnel.email FROM tb_personnel INNER JOIN tb_type ON tb_personnel.type_id = tb_type.id";
				conn = DriverManager.getConnection(URL + ";create=true");// 创建数据库连接
				threadLocal.set(conn);// 保存数据库连接
				Statement stmt = conn.createStatement();// 创建数据库连接状态对象
				for (int i = 0; i < sqls.length; i++) {// 通过执行SQL语句创建数据库
					stmt.execute(sqls[i]);// 执行SQL语句
				}
				stmt.close();// 关闭数据库连接状态对象
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() { // 创建数据库连接的方法
		conn = (Connection) threadLocal.get(); // 从线程中获得数据库连接
		if (conn == null) { // 没有可用的数据库连接
			try {
				conn = DriverManager.getConnection(URL);// 创建新的数据库连接
				threadLocal.set(conn); // 将数据库连接保存到线程中
			} catch (Exception e) {
				String[] infos = { "未能成功连接数据库！", "请确认本软件是否已经运行！" };
				JOptionPane.showMessageDialog(null, infos);// 弹出连接数据库失败的提示
				System.exit(0);// 关闭系统
				e.printStackTrace();
			}
		}
		return conn;
	}

	public static boolean closeConnection() { // 关闭数据库连接的方法
		boolean isClosed = true; // 默认关闭成功
		conn = (Connection) threadLocal.get(); // 从线程中获得数据库连接
		threadLocal.set(null); // 清空线程中的数据库连接
		if (conn != null) { // 数据库连接可用
			try {
				conn.close(); // 关闭数据库连接
			} catch (SQLException e) {
				isClosed = false; // 关闭失败
				e.printStackTrace();
			}
		}
		return isClosed;
	}

}
