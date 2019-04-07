package com.wsy.struts.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

import com.wsy.struts.datasource.DB;



public class DownTable {

	
	int totalPages=1;
	int pageSize=5;
	int currentPage=0;
	ResultSet rs=null;
	Statement s=null;
	int totalRows;
	DB d;
	public DownTable(DataSource datasource){
		d=new DB(datasource);
	}
	public int getTotalPage(){
		if(getRows()%getPageSize()==0)
			return getRows()/getPageSize();
		else
			return getRows()/getPageSize()+1;
	}	
	public void setpageSize(int size){
		this.pageSize=size;	
	}
	public int getPageSize(){
		return pageSize;
	}
	public void setCurrentPage(int current){
		this.currentPage=current;
	}
	public int getCurrentPage(){
		return currentPage;
	}
	public int getRows()
	{
		try{
			
			rs=d.OpenSql("select count(*) from tb_forum");
			if(rs.next())
			{
				totalRows=rs.getInt(1);
			}
			s.close();
			d.close();
		}
		catch(Exception e)
		{
		}
		return totalRows;
	}
//	普通ＪＤＢＣ的连接
	/*public static Connection getConnection1(){
		int icon=0;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			do{
				conn=DriverManager.getConnection(ORACLECON,USER,PWD);
				if(conn==null) Thread.sleep(1000);
				//icon++;
				//System.out.println(icon);
			}while(conn==null&&icon<10);
			//logger.debug("===== 获得数据库资源 ========");
		}catch(Exception e){
			e.printStackTrace();
			try {
				if (conn != null)
					conn.close();
			} catch (Exception ex1) {
			}
			conn = null;
			return null;
		}
		return conn;
	}
	//连接池的连接
	/*public static Connection getConnection2(){
		int iconn = 0;
		try {
			do {
				conn = ConnectionManager.getInstance().getConnection("cmsdb");
				if (conn == null)
					Thread.sleep(1000);
			} while (conn == null && iconn <= 10);

			if (conn == null)
				return null;
			//logger.debug("===== 获得数据库资源 ========");

		} catch (Exception ex) {
			ex.printStackTrace();
			try {
				if (conn != null)
					conn.close();
			} catch (Exception ex1) {
			}
			conn = null;
			return null;
		}
		return conn;
	}
	public static void main(String args[]){
		DownTable d=new DownTable();
		d.setCurrentPage(0);
		d.setpageSize(3);
		System.out.println("rows"+d.getRows());
		System.out.println("current"+d.getCurrentPage());
		System.out.println("total"+d.getTotalPage());
	}*/
}
