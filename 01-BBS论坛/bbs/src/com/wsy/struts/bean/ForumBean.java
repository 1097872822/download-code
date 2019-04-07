package com.wsy.struts.bean;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.wsy.struts.datasource.DB;
import com.wsy.struts.datasource.DBnew;
import com.wsy.struts.util.StringTrans;

public class ForumBean {
	private int id;
	private String forumnname;
	private String manager;
	private String createtime;
	private int count;//每个论坛中主题的总数
	private int ztcount;//总的主题数
	private int ztAndResponseCount;//总帖子数，即主题数加回复帖子数
	private int todaycount;//今天的帖子总数
	public int getTodaycount() {
		return todaycount;
	}
	public void setTodaycount(int todaycount) {
		this.todaycount = todaycount;
	}
	public int getZtAndResponseCount() {
		return ztAndResponseCount;
	}
	public void setZtAndResponseCount(int ztAndResponseCount) {
		this.ztAndResponseCount = ztAndResponseCount;
	}
	public int getZtcount() {
		return ztcount;
	}
	public void setZtcount(int ztcount) {
		this.ztcount = ztcount;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getForumnname() {
		return forumnname;
	}
	public void setForumnname(String forumnname) {
		this.forumnname = forumnname;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	public List getRecord(DataSource datasource,String id){
		ResultSet rs=null;
		//String name=null;
		//String password=null;
		DB d=new DB(datasource);
		List l=new ArrayList();
		try{
			String sql="select * from tb_forum where id='"+id+"'";
			rs=d.OpenSql(sql);
			StringTrans s=new StringTrans();
			while(rs.next()){
				ForumBean f=new ForumBean();
				f.setId(rs.getInt("id"));
				f.setForumnname(rs.getString("forumname"));
				f.setManager(rs.getString("manager"));
				f.setCreatetime(rs.getString("createtime"));
				//f.setCount(f.getCount(datasource,rs.getInt(1)));
				//f.setZtcount(f.getCount(datasource));
				l.add(f);
			}
			rs=null;
		}catch(Exception e){
			e.printStackTrace();
		}
		d.close();
		return l;
	}
	public List getRecord(DataSource datasource){
		ResultSet rs=null;
		//String name=null;
		//String password=null;
		DB d=new DB(datasource);
		List l=new ArrayList();
		try{
			String sql="select * from tb_forum";
			rs=d.OpenSql(sql);
			StringTrans s=new StringTrans();
			while(rs.next()){
				ForumBean f=new ForumBean();
				f.setId(rs.getInt("id"));
				f.setForumnname(rs.getString("forumname"));
				f.setManager(rs.getString("manager"));
				f.setCreatetime(rs.getString("createtime"));
				f.setCount(f.getCount(datasource,rs.getInt(1)));
				f.setZtcount(f.getCount(datasource));
				l.add(f);
			}
			rs=null;
		}catch(Exception e){
			e.printStackTrace();
		}
		d.close();
		return l;
	}
	public List getRecord(){//刚进入系统时候使用的查询方法
		ResultSet rs=null;
		List l=new ArrayList();
		DBnew dbnew=null;
		try{
			String sql="select * from tb_forum";
			dbnew=new DBnew();
			rs=dbnew.executeQuery(sql);
			StringTrans s=new StringTrans();
			while(rs.next()){
				ForumBean f=new ForumBean();
				f.setId(rs.getInt("id"));
				f.setForumnname(rs.getString("forumname"));
				f.setManager(rs.getString("manager"));
				f.setCreatetime(rs.getString("createtime"));
				//f.setCount(f.getCount(datasource,rs.getInt(1)));
				//f.setZtcount(f.getCount(datasource));
				l.add(f);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		dbnew.close();
		return l;
	}
	public int getCount(DataSource datasource,int forumid){//取每个论坛中主题的总数
		ResultSet rs=null;
		//String name=null;
		//String password=null;
		DB d=new DB(datasource);
		int i=0;
		try{
			String sql="select count(*) from tb_topic where forumid='"+forumid+"'";
			rs=d.OpenSql(sql);
			while(rs.next()){
				i=rs.getInt(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		d.close();
		return i;
	}
	public static int getCount(DataSource datasource){//取主题总数
		ResultSet rs=null;
		//String name=null;
		//String password=null;
		DB d=new DB(datasource);
		int i=0;
		try{
			String sql="select count(*) from tb_topic ";
			rs=d.OpenSql(sql);
			while(rs.next()){
				i=rs.getInt(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		d.close();
		return i;
	}
	public static int getResponseCount(DataSource datasource){//取主题总数
		ResultSet rs=null;
		//String name=null;
		//String password=null;
		DB d=new DB(datasource);
		int i=0;
		try{
			String sql="select count(*) from tb_response ";
			rs=d.OpenSql(sql);
			while(rs.next()){
				i=rs.getInt(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		d.close();
		return i;
	} 
	public int getZtAndResponseCount(DataSource datasource) {//取总贴子总数
		return getResponseCount(datasource)+getCount(datasource);
	}
	public static int gettodayTopicCount(DataSource datasource){//取今天主题的总数
		ResultSet rs=null;
		//String name=null;
		//String password=null;
		DB d=new DB(datasource);
		int i=0;
		try{
			String sql="select count(*) from tb_topic where (TO_DAYS(submittime)-TO_DAYS(now())=0)";
			rs=d.OpenSql(sql);
			while(rs.next()){
				i=rs.getInt(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		d.close();
		return i;
	}
	public static int gettodayResponseCount(DataSource datasource){//取今天回复主题的总数
		ResultSet rs=null;
		//String name=null;
		//String password=null;
		DB d=new DB(datasource);
		int i=0;
		try{
			String sql="select count(*) from tb_response where (TO_DAYS(submittime)-TO_DAYS(now())=0)";
			rs=d.OpenSql(sql);
			while(rs.next()){
				i=rs.getInt(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		d.close();
		return i;
	}
	public int getTodayResponseCount(DataSource datasource) {//取总贴子总数
		return gettodayTopicCount(datasource)+gettodayResponseCount(datasource);
	}
	/*public List getRecord(int i){
		ResultSet rs=null;
		//String name=null;
		//String password=null;
		DB d=new DB(datasource);
		List l=new ArrayList();
		try{
			String sql="select * from tb_forum";
			rs=d.OpenSql(sql);
			while(rs.next()){
				ForumBean f=new ForumBean();
				f.setId(rs.getInt(1));
				f.setForumnname(rs.getString(2));
				f.setManager(rs.getString(3));
				f.setCreatetime(rs.getString(4));
				l.add(f);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return l;
	}*/
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int UpdateRecord(String forumname,String name,DataSource datasource){
		int i=0;
		DB d=new DB(datasource);
		String sql="update tb_forum set manager='"+name+"' where forumname='"+forumname+"'";
		i=d.ExecSql(sql);
		return i;
	}
	public int deleteRecord(String id,DataSource datasource){
		int i=0;
		DB d=new DB(datasource);
		String sql="delete  from tb_forum where id='"+id+"'";
		i=d.ExecSql(sql);
		d.close();
		return i;
	}
	public int UpdateRecord(String forumname,DataSource datasource){
		int i=0;
		DB d=new DB(datasource);
		String sql="insert into tb_forum(forumname) values('"+forumname+"')";
		i=d.ExecSql(sql);
		d.close();
		return i;
	}
}
