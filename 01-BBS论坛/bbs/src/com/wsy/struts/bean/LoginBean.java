package com.wsy.struts.bean;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.wsy.struts.datasource.DB;


public class LoginBean {
	private int id;
	private String username;
	private String password;
	private String sex;
	private String email;
	private String oicq;
	private String signature;
	private String grade;
	private String lxdz;
	private String tx;
	private String grzy;
	private String realname;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getGrzy() {
		return grzy;
	}
	public void setGrzy(String grzy) {
		this.grzy = grzy;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLxdz() {
		return lxdz;
	}
	public void setLxdz(String lxdz) {
		this.lxdz = lxdz;
	}
	public String getOicq() {
		return oicq;
	}
	public void setOicq(String oicq) {
		this.oicq = oicq;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getTx() {
		return tx;
	}
	public void setTx(String tx) {
		this.tx = tx;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int login(String name,String password,DataSource datasource){
		ResultSet rs=null;
		String names=null;
		String passwords=null;
		String owner=null;
		int i=0;
		DB d=new DB(datasource);
		try{
			
			String sql="select * from tb_user where username='"+name+"' and password='"+password+"'";
			rs=d.OpenSql(sql);
			while(rs.next()){
				if(rs.getString(1)!=null||rs.getString(1).length()!=0){
					i=1;
				}
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		d.close();
		return i;
	}
	public List getRecordForUser(String name,DataSource datasource){
		List l=new ArrayList();
		ResultSet rs=null;
		DB d=new DB(datasource);
		String sql="select * from tb_user where username='"+name+"'";
		try{
			rs=d.OpenSql(sql);
			while(rs.next()){
				LoginBean login=new LoginBean();
				login.setId(rs.getInt("id"));
				login.setUsername(rs.getString("username"));
				login.setPassword(rs.getString("password"));
				login.setSex(rs.getString("sex"));
				login.setEmail(rs.getString("email"));
				login.setOicq(rs.getString("oicq"));
				login.setSignature(rs.getString("signature"));
				login.setGrade(rs.getString("grade"));
				login.setLxdz(rs.getString("lxdz"));
				login.setTx(rs.getString("tx"));
				login.setGrzy(rs.getString("grzy"));
				login.setRealname(rs.getString("realname"));
				l.add(login);
			}
		}catch(Exception e){
			e.printStackTrace();
			
		}
		d.close();
		return l;
	}
	public List getTotalRecord(DataSource datasource){
		List l=new ArrayList();
		ResultSet rs=null;
		DB d=new DB(datasource);
		String sql="select * from tb_user ";
		try{
			rs=d.OpenSql(sql);
			while(rs.next()){
				LoginBean login=new LoginBean();
				login.setId(rs.getInt("id"));
				login.setUsername(rs.getString("username"));
				login.setPassword(rs.getString("password"));
				login.setSex(rs.getString("sex"));
				login.setEmail(rs.getString("email"));
				login.setOicq(rs.getString("oicq"));
				login.setSignature(rs.getString("signature"));
				login.setGrade(rs.getString("grade"));
				login.setLxdz(rs.getString("lxdz"));
				login.setTx(rs.getString("tx"));
				login.setGrzy(rs.getString("grzy"));
				login.setRealname(rs.getString("realname"));
				l.add(login);
			}
		}catch(Exception e){
			e.printStackTrace();
			
		}
		d.close();
		return l;
	}
	public int UpdateRecord(String id,String name,String grade,DataSource datasource){
		int i=0;
		DB d=new DB(datasource);
		String sql="update tb_user set username='"+name+"',grade='"+grade+"' where id='"+id+"'";
		i=d.ExecSql(sql);
		d.close();
		return i;
	}
	public int deleteRecord(String id,DataSource datasource){
		int i=0;
		DB d=new DB(datasource);
		String sql="delete  from tb_user where id='"+id+"'";
		i=d.ExecSql(sql);
		d.close();
		return i;
	}
	public int CheckUser(String username,DataSource datasource){
		int i=0;
		DB d=new DB(datasource);
		String sql="select username from tb_user where username='"+username+"'";
		ResultSet rs=d.OpenSql(sql);
		try{
			while(rs.next()){
				LoginBean login=new LoginBean();
				login.setUsername(rs.getString(1));
				if(login.getUsername()!=null){
					i=1;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return i;
	}
}
