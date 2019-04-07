package com.wsy.struts.datasource;
import java.sql.*;
import javax.sql.DataSource;
public class DB {
	Connection connect=null;
	ResultSet rs=null;
	Statement stmt=null;
	public DB(){
		
	}
	public DB(DataSource dataSource){
		if (connect != null)
			return;
		try {
				connect = dataSource.getConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	public  ResultSet OpenSql(String sql){
		try{
			stmt=connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs=stmt.executeQuery(sql);	
		}catch(SQLException ex){
			ex.printStackTrace();
			try{
				if(stmt!=null)
					stmt.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return rs;
	}
	public int ExecSql(String sql){
		int result=0;
		try{
			stmt=connect.createStatement();
			result=stmt.executeUpdate(sql);
			//connect.commit();
			stmt.close();
		}catch(SQLException ex){
			System.err.print(ex.getMessage());
			try{
				if(stmt!=null)
					stmt.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return result;
	}
	public PreparedStatement PrepareInsert(String sql){
		PreparedStatement pstmt=null;
		try{
			pstmt=connect.prepareStatement(sql);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return pstmt;
	}
	 public void close() {
		  try {
		   if (stmt != null) {
		    stmt.close();
		    stmt = null;
		   }

		   if (connect != null) {
		    connect.close();
		    connect = null;
		    //System.out.println("***************** a connection is closed");
		   }
		  } catch (Exception e) {
		   System.err.println(e.getMessage());
		  }finally{
			  connect=null;
			  //System.out.println("***************** a connection is closed finally");
		  }
		 } 
	 public void finalize(){
		 //System.out.println("close db");
		 close();
	 }
}
