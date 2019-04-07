package com.mwq.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JOptionPane;

public class Dao extends BaseDao {
	// 数据库驱动
	private static Dao dao = null;

	private Dao() {
	}

	public static Dao getInstance() {// 获取DAO实例
		if (dao == null)
			dao = new Dao();
		return dao;
	}

	// tb_type
	public Vector sTypeByUsed(String used) {
		return this.selectSomeNote("select * from tb_type where used='" + used
				+ "'");
	}

	public int sTypeIdByUsedAndName(String used, String name) {
		return (Integer) this
				.selectOnlyValue("select id from tb_type where used='" + used
						+ "' and name='" + name + "'");
	}

	public Vector sTypeByUsedExcept(String used, int id) {
		return this.selectSomeNote("select * from tb_type where used='" + used
				+ "' and id!=" + id);
	}

	public void iType(String name, String used) {
		int id = 1;
		if (this.selectOnlyValue("select max(id) from tb_type") != null)
			id = (Integer) this.selectOnlyValue("select max(id) from tb_type") + 1;
		this.longHaul("insert into tb_type(id,name,used) values(" + id + ",'"
				+ name + "','" + used + "')");
	}

	public void uTypeNameByName(String used, String nowName, String newName) {
		this.longHaul("update tb_type set name='" + newName + "' where used='"
				+ used + "' and name='" + nowName + "'");
	}

	public void dTypeByName(String used, String name) {
		this.longHaul("delete from tb_type where used='" + used
				+ "' and name='" + name + "'");
	}

	// tb_personnel
	public Vector sPersonnelByTypeId(int typeId) {
		return this.selectSomeNote("select * from tb_personnel where type_id="
				+ typeId);
	}

	public int sPersonnelIdOfMax() {
		int id = 100000;
		Object maxId = this
				.selectOnlyValue("select max(num) from tb_personnel");
		if (maxId != null)
			id = (Integer) maxId;
		return id;
	}

	public Vector sPersonnelByNum(int num) {
		return this.selectOnlyNote("select * from tb_personnel where num="
				+ num);
	}

	public Vector sPersonnelVByNum(int num) {
		return this.selectOnlyNote("select * from v_personnel_type where num="
				+ num);
	}

	public Vector sPersonnelVByTypeName(String typeName) {
		return this
				.selectSomeNote("select num,name,sex,birthday,company,dept,duty,handset,email from v_personnel_type where type_name='"
						+ typeName + "'");
	}

	public void iPersonnel(Vector personnelV) {
		int cardId = this.sTypeIdByUsedAndName("card", personnelV.get(1)
				.toString());
		String sql = "insert into tb_personnel values(" + personnelV.get(0)
				+ "," + cardId + ",'" + personnelV.get(2) + "','"
				+ personnelV.get(3) + "','" + personnelV.get(4) + "','"
				+ personnelV.get(5) + "','" + personnelV.get(6) + "','"
				+ personnelV.get(7) + "','" + personnelV.get(8) + "','"
				+ personnelV.get(9) + "')";
		this.longHaul(sql);
	}

	public void uPersonnel(Vector personnelV) {
		int cardId = this.sTypeIdByUsedAndName("card", personnelV.get(1)
				.toString());
		String sql = "update tb_personnel set type_id=" + cardId + ",name='"
				+ personnelV.get(2) + "',sex='" + personnelV.get(3)
				+ "',birthday='" + personnelV.get(4) + "',company='"
				+ personnelV.get(5) + "',dept='" + personnelV.get(6)
				+ "',duty='" + personnelV.get(7) + "',handset='"
				+ personnelV.get(8) + "',email='" + personnelV.get(9)
				+ "' where num=" + personnelV.get(0);
		System.out.println(sql);
		this.longHaul(sql);
	}

	public void uPersonnelTypeIdByTypeId(int nowTypeId, int newTypeId) {
		this.longHaul("update tb_personnel set type_id=" + newTypeId
				+ " where type_id=" + nowTypeId);
	}

	public void dPersonnelByTypeId(int typeId) {
		this.longHaul("delete from tb_personnel where type_id=" + typeId);
	}

	public void dPersonnelByNum(int num) {
		this.longHaul("delete from tb_personnel where num=" + num);
	}

	// tb_info
	public Vector sInfoByTypeId(int typeId) {
		return this.selectSomeNote("select * from tb_info where type_id="
				+ typeId);
	}

	public Vector sInfoVByTypeName(String typeName) {
		return this
				.selectSomeNote("select num,content from v_info_type where type_name='"
						+ typeName + "'");
	}

	public int sInfoIdOfMax() {
		int id = 100000;
		Object maxId = this.selectOnlyValue("select max(num) from tb_info");
		if (maxId != null)
			id = (Integer) maxId;
		return id;
	}

	public void iInfo(String typeName, String content) {
		int cardId = this.sTypeIdByUsedAndName("info", typeName);
		this.longHaul("insert into tb_info values(" + (this.sInfoIdOfMax() + 1)
				+ "," + cardId + ",'" + content + "')");
	}

	public void uInfoByNum(int num, String typeName, String nowContent) {
		int typeId = this.sTypeIdByUsedAndName("info", typeName);
		this.longHaul("update tb_info set type_id=" + typeId + ",content='"
				+ nowContent + "' where num=" + num);
	}

	public void uInfoTypeIdByTypeId(int nowTypeId, int newTypeId) {
		this.longHaul("update tb_info set type_id=" + newTypeId
				+ " where type_id=" + nowTypeId);
	}

	public void dInfoByTypeId(int typeId) {
		this.longHaul("delete from tb_info where type_id=" + typeId);
	}

	public void dInfoByNum(int num) {
		this.longHaul("delete from tb_info where num=" + num);
	}

	// tb_user
	public Vector sUser() {
		return this
				.selectSomeNote("select name,sex,birthday,id_card,freeze from tb_user where freeze='正常'");
	}

	public Vector sUserByName(String name) {
		return selectOnlyNote("select * from tb_user where name='" + name + "'");
	}

	public Vector sUserNameOfNotFreeze() {
		return selectSomeValue("select name from tb_user where freeze='正常'");
	}

	public boolean iUser(String values[]) {
		int id = 1;
		Object maxId = this.selectOnlyValue("select max(id) from tb_user");
		if (maxId != null)
			id += (Integer) maxId;
		String sql = "insert into tb_user(id,name,sex,birthday,id_card,password,freeze) values("
				+ id
				+ ",'"
				+ values[0]
				+ "','"
				+ values[1]
				+ "','"
				+ values[2]
				+ "','"
				+ values[3]
				+ "','"
				+ values[4]
				+ "','"
				+ values[5]
				+ "')";
		return longHaul(sql);
	}

	public boolean uPasswordByName(String name, String password) {
		return super.longHaul("update tb_user set password='" + password
				+ "' where name='" + name + "'");
	}

	public boolean uFreezeByName(String name, String freeze) {
		return super.longHaul("update tb_user set freeze='" + freeze
				+ "' where name='" + name + "'");
	}

}
