package com.wsy;

import java.sql.ResultSet;


public class DownTable {
	int totalPages=1;
	int pageSize=10;
	int currentPage=1;
	ResultSet rs=null;
	int totalRows;
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
		connsqlserver con=new connsqlserver();
		try{
		rs=con.executeQuery("select count(*) from tb_business");
		if(rs.next())
		{
			totalRows=rs.getInt(1);
		}
		}
		catch(Exception e)
		{
		}
		return totalRows;
	}
}
