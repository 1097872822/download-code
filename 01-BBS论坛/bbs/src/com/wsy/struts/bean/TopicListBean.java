package com.wsy.struts.bean;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.wsy.struts.datasource.DB;
import com.wsy.struts.util.Constants;
import com.wsy.struts.util.StringTrans;

public class TopicListBean {
	private String id;
	private String content;
	private String author;
	private String submittime;
	private String forumid;
	private String title;
	private String xq;//主题表中的字段
	private String rq;//人气
	private int reCount;//每个主题回复的个数
	private String lastTalk;//每个主题最新回复主题个数
	private String forumname;//论坛名称
	
	public String getForumname() {
		return forumname;
	}
	public void setForumname(String forumname) {
		this.forumname = forumname;
	}
	public int getReCount() {
		return reCount;
	}
	public void setReCount(int reCount) {
		this.reCount = reCount;
	}
	public String getLastTalk() {
		return lastTalk;
	}
	public void setLastTalk(String lastTalk) {
		this.lastTalk = lastTalk;
	}
	public String getXq() {
		return xq;
	}
	public void setXq(String xq) {
		this.xq = xq;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getForumid() {
		return forumid;
	}
	public void setForumid(String forumid) {
		this.forumid = forumid;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSubmittime() {
		return submittime;
	}
	public void setSubmittime(String submittime) {
		this.submittime = submittime;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List search(DataSource datasource,int pageid,String ForumId) throws Exception{
		StringTrans s=new StringTrans();
		List l=new ArrayList();
		String sql="select * from tb_topic where forumid="+ForumId+" order by id desc limit "+pageid*Constants.TOPIC_PAGE_SIZE+","+Constants.TOPIC_PAGE_SIZE;
		System.out.println(sql);
		DB d=new DB(datasource);
		ResultSet rs=d.OpenSql(sql);
		ResultSet rscount=null;
		ResultSet rscount2=null;
		int count=0;
		while(rs.next()){
			TopicListBean t=new TopicListBean();
			t.setId(rs.getString("id"));
			t.setContent(rs.getString("content"));
			t.setAuthor(rs.getString("author"));
			t.setSubmittime(rs.getString("submittime"));
			t.setForumid(rs.getString("forumid"));
			t.setForumname(rs.getString("forumname"));
			t.setTitle(rs.getString("title"));
			t.setXq(rs.getString("xq"));
			t.setRq(rs.getString("rq"));
			String sqlcount="select count(*) from tb_response where topicid='"+t.getId()+"'";//取每个主题的回复总数
			rscount=d.OpenSql(sqlcount);
			while(rscount.next()){
				t.setReCount(rscount.getInt(1));
				System.out.println("recount"+t.getReCount());
			}
			rscount=null;
			String sqlcount2="select * from tb_response where topicname='"+s.tranC(t.getTitle())+" order by id desc'";
			rscount2=d.OpenSql(sqlcount2);
			while(rscount2.next()){
				t.setLastTalk(rs.getString("submittime"));
			}
			rscount2=null;
			l.add(t);
		}
		rs=null;
		d.close();
		return l;
	}
	public int InsertData(String title,String content,String xq,String name,String forumid,String forumname,DataSource datasource){
		int i=0;
		DB d=new DB(datasource);
		PreparedStatement pstmt=null;
		String sql="insert into tb_topic(title,content,xq,author,forumid,forumname) values(?,?,?,?,?,?)";
		pstmt=d.PrepareInsert(sql);
		try{
			pstmt.setString(1,title);
			pstmt.setString(2,content);
			pstmt.setString(3,xq);
			pstmt.setString(4,name);
			pstmt.setString(5,forumid);
			pstmt.setString(6, forumname);
			i=pstmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
		d.close();
		return i;
	}
	/*插入tb_response表数据*/
	public int InsertDataResponse(String title,String content,String xq,String name,String topicid,String topicname,DataSource datasource){
		int i=0;
		DB d=new DB(datasource);

		String sql="insert into tb_response(title,content,author,topicid,topicname,xq)values ('"+title+"','"+content+"','"+name+"','"+topicid+"','"+topicname+"','"+xq+"')";
		System.out.println(sql);
		try{
			i=d.ExecSql(sql);

		}catch(Exception e){
			e.printStackTrace();
		}
		d.close();
		return i;
	}
	/*根据主题号在回复表中取信息*/
	public List getRecordResponse(String topicid,DataSource datasource){
		List list=new ArrayList();
		
		DB d=new DB(datasource);
		ResultSet rs=null;
		String sql="select * from tb_response where topicid='"+topicid+"'";
		try{
			rs=d.OpenSql(sql);
			while(rs.next()){
				TopicListBean t=new TopicListBean();
				t.setId(rs.getString("id"));
				t.setTitle(rs.getString("title"));
				t.setContent(rs.getString("content"));
				t.setAuthor(rs.getString("author"));
				t.setSubmittime(rs.getString("submittime"));
				t.setXq(rs.getString("xq"));
				list.add(t);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		d.close();
		return list;
	}
	public List getTotalRecordTopic(DataSource datasource){
		List list=new ArrayList();
		DB d=new DB(datasource);
		ResultSet rs=null;
		ResultSet rs2=null;
		String sql="select * from tb_topic";
		try{
			rs=d.OpenSql(sql);
			while(rs.next()){
				TopicListBean t=new TopicListBean();
				t.setId(rs.getString("id"));
				t.setTitle(rs.getString("title"));
				t.setForumid(rs.getString("forumid"));
				t.setForumname(rs.getString("forumname"));
				t.setContent(rs.getString("content"));
				t.setAuthor(rs.getString("author"));
				t.setSubmittime(rs.getString("submittime"));
				t.setXq(rs.getString("xq"));
				list.add(t);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		d.close();
		return list;
	}
	public int DelRecord(String id,DataSource datasource){
		int i=0;
		DB d=new DB(datasource);
		String sql="delete from tb_topic where id='"+id+"'";
		i=d.ExecSql(sql);
		d.close();
		return i;
	}
	public int DelRecordforForumn(String forumid,DataSource datasource){//由于删除某个论坛，所以相应这个论坛的主题也需要被删除
		int i=0;
		String id=null;
		DB d=new DB(datasource);
		String sql2="select id from tb_topic where forumid='"+forumid+"'";
		String sql="delete from tb_topic where forumid='"+forumid+"'";
		ResultSet rs=d.OpenSql(sql2);
		try{
			while(rs.next()){
				id=rs.getString(1);
				String sql3="delete from tb_response where topicid='"+id+"'";//将回复表中相关论坛回复删除
				System.out.println(sql3);
				System.out.println("topiclistBean"+d.ExecSql(sql3));
			}
		}catch(Exception e){
			e.printStackTrace();
		}

		i=d.ExecSql(sql);
		d.close();
		return i;
	}
	public String getRq() {
		return rq;
	}
	public void setRq(String rq) {
		this.rq = rq;
	}
	public int UpdateRq(String id,DataSource datasource){
		int i=0;
		DB d=new DB(datasource);
		String sql="update  tb_topic set rq=rq+1 where id='"+id+"'";
		i=d.ExecSql(sql);
		d.close();
		return i;
	}
	public int delRecordforUser(String name,DataSource datasource){
		int i=0;
		DB d=new DB(datasource);
		String sql="delete from tb_topic where author='"+name+"'";
		i=d.ExecSql(sql);
		d.close();
		return i;
	}
	public List getTotalRecordTopic(String id,DataSource datasource){
		List list=new ArrayList();
		DB d=new DB(datasource);
		ResultSet rs=null;
		ResultSet rs2=null;
		String sql="select * from tb_topic where id='"+id+"'";
		try{
			rs=d.OpenSql(sql);
			while(rs.next()){
				TopicListBean t=new TopicListBean();
				t.setId(rs.getString("id"));
				t.setTitle(rs.getString("title"));
				t.setForumid(rs.getString("forumid"));
				t.setForumname(rs.getString("forumname"));
				t.setContent(rs.getString("content"));
				t.setAuthor(rs.getString("author"));
				t.setSubmittime(rs.getString("submittime"));
				t.setXq(rs.getString("xq"));
				list.add(t);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		d.close();
		return list;
	}
}
