package com.wsy.struts.bean;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.wsy.struts.datasource.DB;
import com.wsy.struts.util.StringTrans;

public class ResponseBean {
	private String id;
	private String title;
	private String content;
	private String author;
	private String submittime;
	private String topicid;
	private String topicname;
	private String xq;
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
	public String getTopicid() {
		return topicid;
	}
	public void setTopicid(String topicid) {
		this.topicid = topicid;
	}
	public String getTopicname() {
		return topicname;
	}
	public void setTopicname(String topicname) {
		this.topicname = topicname;
	}
	public String getXq() {
		return xq;
	}
	public void setXq(String xq) {
		this.xq = xq;
	}
	public List getAllRecord(DataSource datasource){
		List list=new ArrayList();
		ResultSet rs=null;
		DB d=new DB(datasource);
		String sql="select * from tb_response";
		rs=d.OpenSql(sql);
		StringTrans s=new StringTrans();
		try{
			while(rs.next()){
				ResponseBean r=new ResponseBean();
				r.setId(rs.getString("id"));
				r.setTitle(rs.getString("title"));
				r.setAuthor(rs.getString("author"));
				System.out.println(s.tranC(rs.getString("topicname")));
				r.setSubmittime(rs.getString("submittime"));
				r.setTopicid(rs.getString("topicid"));
				r.setTopicname(rs.getString("topicname"));
				r.setXq(rs.getString("xq"));
				list.add(r);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		d.close();
		return list;
	}
	public int deleResponseRecord(String topicid,DataSource datasource){
		int i=0;
		DB d=new DB(datasource);
		String sql="delete from tb_response where topicid='"+topicid+"'";
		i=d.ExecSql(sql);
		return i;
	}
}	
