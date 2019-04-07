package com.wsy.struts.bean;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.wsy.struts.datasource.DB;

public class RegisterBean {
	String username;
	String password1;
	String sex;
	String birthday;
	String tel;
	String qq;
	String mail;
	String lxdz;
	String tx;
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getLxdz() {
		return lxdz;
	}
	public void setLxdz(String lxdz) {
		this.lxdz = lxdz;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPassword1() {
		return password1;
	}
	public void setPassword1(String password1) {
		this.password1 = password1;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
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
	public int InsertData(String username,String password1,String sex,String signature,String tel,String qq,String mail,String lxdz,String tx,String grzy,String realname,DataSource datasource){
		int i=0;
		DB d=new DB(datasource);
		PreparedStatement pstmt=null;
		String sql="insert into tb_user(username,password,sex,email,oicq,signature,grade,lxdz,tx,grzy,realname) values(?,?,?,?,?,?,?,?,?,?,?)";
		pstmt=d.PrepareInsert(sql);
		try{
			pstmt.setString(1,username);
			pstmt.setString(2,password1);
			pstmt.setString(3,sex);
			pstmt.setString(4,mail);
			pstmt.setString(5,qq);
			pstmt.setString(6,signature);
			pstmt.setString(7,"user");
			pstmt.setString(8,lxdz);
			pstmt.setString(9,tx);
			pstmt.setString(10,grzy);
			pstmt.setString(11,realname);
			i=pstmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
		d.close();
		return i;
	}
	public int deleResponseRecord(String id,DataSource datasource){
		int i=0;
		DB d=new DB(datasource);
		String sql="delete from tb_response where id='"+id+"'";
		i=d.ExecSql(sql);
		return i;
	}
}
