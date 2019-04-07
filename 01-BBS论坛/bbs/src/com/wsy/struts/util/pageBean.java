package com.wsy.struts.util;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

import com.wsy.struts.datasource.DB;
public class pageBean
{
	public int maxPage;//总页数
	public int maxRowCount;//总行数
	public int rowsPerPage=5;//每页显示的行数
	public pageBean()
	{
		this.countMaxPage();

	}
	public void getAvailableCount(DataSource datasource,String id)
	{		
		try
		{
			DB d=new DB(datasource);
			String strSql="select count(*) from tb_topic where forumid='"+id+"'";
			ResultSet rset=d.OpenSql(strSql);
			while(rset.next())
			{
			this.maxRowCount=rset.getInt(1);
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	public void countMaxPage()
	{
		if(this.maxRowCount%this.rowsPerPage==0)
		{
			this.maxPage=this.maxRowCount/this.rowsPerPage;
		}
		else
		{
			this.maxPage=this.maxRowCount/this.rowsPerPage+1;
		}
	}
	

	
}