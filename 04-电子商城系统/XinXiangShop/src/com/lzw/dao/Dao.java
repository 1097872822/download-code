package com.lzw.dao;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.lzw.form.ModifyMemberForm;
import com.lzw.model.TbBbs;
import com.lzw.model.TbGoods;
import com.lzw.model.TbManager;
import com.lzw.model.TbMember;
import com.lzw.model.TbOrder;
import com.lzw.model.TbSubType;
import com.lzw.model.TbSuperType;
import com.lzw.model.VGoods;
public class Dao extends HibernateDaoSupport {
	// 获取特价商品
	public List getShowSaleGoodsList() {
		return getHibernateTemplate().find(
				"from TbGoods where sale=1 order by INTime desc");
	}
	// 获取新品
	public List getShowNewGoodsList() {
		return getHibernateTemplate().find(
				"from TbGoods where newGoods=1 order by INTime desc");
	}
	// 获取单个商品信息
	public TbGoods getGoods(Long id) {
		return (TbGoods) getHibernateTemplate().get(TbGoods.class, id);
	}

	public List getGoodsByType(Integer type) {
		return getHibernateTemplate()
				.find("from VGoods where id.subId=" + type);
	}

	public List getGoodsByQuery(String query) {
		System.out.println(query+"\to");
		return getHibernateTemplate().find(query);
	}

	// 获取用户
	public TbMember getUser(String username, String pwd) {
		List list = null;
		TbMember user = null;
		list = getHibernateTemplate().find(
				"from TbMember where userName='" + username + "' and freeze=0");
		if (list.size() > 0) {
			user = (TbMember) list.get(0);
			if (pwd != null && !user.getPassWord().equals(pwd)) {
				user = null;
			}
		}
		return user;
	}

	// 获取公告
	public List getPlacard() {
		return getHibernateTemplate().find("from TbBbs order by INTime desc");
	}

	// 获取指定公告
	public TbBbs getPlacard(Integer id) {
		return (TbBbs) getHibernateTemplate().get(TbBbs.class, id);
	}

	public List getSellSort() {
		List list = getHibernateTemplate()
				.find(
						"select id.goodsId,id.goodsName from"
								+ " VOrderDetail group by id.goodsId,id.goodsName order by count(id.number) desc");
		return list;
	}
	// 获取商品类别列表
	public Map getTypeList() {
		Map model = new HashMap();
		List types = getHibernateTemplate()
				.find(
						"select id.id,id.superType from VType group by id.id,id.superType");
		Iterator iterator = types.iterator();
		while (iterator.hasNext()) {
			Object[] type = (Object[]) iterator.next();
			List subList = getHibernateTemplate().find(
					"from VType where id.id='" + type[0] + "'");
			model.put(type[1], subList);
		}
		return model;
	}
	// 获取搜索类型
	public List getSearchClassify() {
		return getHibernateTemplate()
				.find(
						"select id.id,id.superType from VType group by id.id,id.superType order by id.superType");
	}

	public TbSuperType getSuperType(Integer id) {
		return (TbSuperType) getHibernateTemplate().get(TbSuperType.class, id);
	}
	// 修改并更新用户信息
	public void updateUserInfo(ModifyMemberForm form) {
		TbMember oldUser = (TbMember) getHibernateTemplate().get(
				TbMember.class, new Integer(form.getId()));
		oldUser.setAddress(form.getAddress());
		oldUser.setCardNo(form.getCardno());
		oldUser.setCardType(form.getCardtype());
		oldUser.setCity(form.getCity());
		oldUser.setEmail(form.getEmail());
		oldUser.setPassWord(form.getPwd());
		oldUser.setPostcode(form.getPostcode());
		oldUser.setTel(form.getTel());
		oldUser.setTrueName(form.getTruename());
		getHibernateTemplate().update(oldUser);
	}

	public List getOrder(String username) {
		return getHibernateTemplate().find(
				"from TbOrder where username='" + username + "'");
	}

	public List getOrders() {
		return getHibernateTemplate().loadAll(TbOrder.class);
	}

	public TbOrder getOrder(Long id) {
		return (TbOrder) getHibernateTemplate().get(TbOrder.class, id);
	}

	// 获取会员折扣
	public float getUserRebate(String username) {
		float rebate = 1;
		List list = getHibernateTemplate().find(
				"select id.rebate from VMember where username='" + username
						+ "'");
		if (list.size() > 0) {
			System.out.println(list.get(0));
			rebate = Float.valueOf(list.get(0) + "").floatValue();
		}
		return rebate;
	}

	// 获得TbRebate表数据等级为ID的记录
	public int getGrade(double amount) {
		int grade = 0;
		List list = getHibernateTemplate().find(
				"select grade from TbRebate where amount<" + amount);
		if (list.size() > 0) {
			grade = Integer.valueOf(list.get(0) + "");
		}
		return grade;
	}

	public boolean insertObject(Object obj) {
		try {
			getHibernateTemplate().save(obj);
			return true;
		} catch (DataAccessException e) {
			e.printStackTrace();
			return false;
		}
	}

	// 在事物中完成订单保存和会员的更新
	public void insertObject(TbMember user, TbOrder order) {
		getHibernateTemplate().saveOrUpdate(user);
		getHibernateTemplate().save(order);
	}

	// 获取管理员
	public TbManager getManager(String manager) {
		List list = getHibernateTemplate().find(
				"from TbManager where manager='" + manager + "'");
		TbManager user = null;
		if (list.size() > 0)
			user = (TbManager) list.get(0);
		return user;
	}

	public List getSuperTypes() {
		return getHibernateTemplate().find("from TbSuperType order by id Desc");
	}

	public List getSubTypes() {
		return getHibernateTemplate().find("from VType order by id.id Desc");
	}

	public boolean delSuperType(String ids) {
		String sql = "delete from TbSuperType Where id in(" + ids + ")";
		boolean del = false;
		try {
			getHibernateTemplate().bulkUpdate(sql);
			del = true;
		} catch (Exception e) {
			del = false;
		}
		return del;
	}

	public TbSuperType getSuperType(String typename) {
		TbSuperType tst = null;
		List list = getHibernateTemplate().find(
				"from TbSuperType where typeName='" + typename + "'");
		if (list.size() > 0)
			tst = (TbSuperType) list.get(0);
		return tst;
	}

	public boolean delSubType(String subTypeID) {
		String sql = "delete from TbSubType Where id in(" + subTypeID + ")";
		boolean del = false;
		try {
			getHibernateTemplate().bulkUpdate(sql);
			del = true;
		} catch (Exception e) {
			del = false;
		}
		return del;
	}

	public TbSubType getSubType(String typename) {
		TbSubType tst = null;
		List list = getHibernateTemplate().find(
				"from TbSubType where typeName='" + typename + "'");
		if (list.size() > 0)
			tst = (TbSubType) list.get(0);
		return tst;
	}

	public TbSubType getSubType(Integer typeId) {
		return (TbSubType) getHibernateTemplate().get(TbSubType.class, typeId);
	}

	public void insertOrUpdate(TbGoods form) {
		getHibernateTemplate().saveOrUpdate(form);
	}

	public VGoods getVGoods(Long id) {
		return (VGoods) getHibernateTemplate().get(VGoods.class, id);
	}

	public void delObject(Object obj) {
		getHibernateTemplate().delete(obj);
	}

	public List getMembers() {
		return getHibernateTemplate().loadAll(TbMember.class);
	}

	public int updateFreeze(int id) {
		String sql = "update TbMember set freeze=1 where ID=" + id;
		return getHibernateTemplate().bulkUpdate(sql);
	}

	public int updateThaw(int id) {
		String sql = "update TbMember set freeze=0 where ID=" + id;
		return getHibernateTemplate().bulkUpdate(sql);
	}

	public TbMember getMember(Integer id) {
		return (TbMember) getHibernateTemplate().get(TbMember.class, id);
	}

	public int updateEnforce(int id) {
		String sql = "update TbOrder set enforce=1 where orderID=" + id;
		return getHibernateTemplate().bulkUpdate(sql);
	}

	public boolean delPlacard(String id) {
		String sql = "delete from TbBbs Where id in(" + id + ")";
		boolean del = false;
		try {
			getHibernateTemplate().bulkUpdate(sql);
			del = true;
		} catch (Exception e) {
			del = false;
		}
		return del;
	}

	public TbBbs getBBS(String title) {
		List list = getHibernateTemplate().find(
				"from TbBbs where title='" + title + "'");
		TbBbs tbb = null;
		if (list.size() > 0)
			tbb = (TbBbs) list.get(0);
		return tbb;
	}
}
