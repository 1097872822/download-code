package com.lzw;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

import com.lzw.collect.GoodsElement;
import com.lzw.dao.Dao;
import com.lzw.form.CartCheckoutForm;
import com.lzw.model.TbGoods;
import com.lzw.model.TbMember;
import com.lzw.model.TbOrder;
import com.lzw.model.TbOrderDetail;
public class CartCheckoutController extends SimpleFormController {
	private Dao dao;
	public CartCheckoutController() {
		setCommandClass(CartCheckoutForm.class);
	}
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		HttpSession session = request.getSession();
		CartCheckoutForm form = (CartCheckoutForm) command;
		TbMember user = dao.getUser(form.getUsername(), null);
		if (user == null) {
			session.invalidate();
			return new ModelAndView(getFormView());
		}
		float rebate = dao.getUserRebate(form.getUsername());
		List<GoodsElement> cart = (List<GoodsElement>) session
				.getAttribute("cart");
		int number = 0;
		double nowprice = (float) 0.0;
		double sum = (float) 0;
		double Totalsum = (float) 0;
		long ID = -1;
		// 插入订单主表数据
		short bnumber = 0;
		if (cart != null) {
			bnumber = (short) cart.size();
		}
		TbOrder order = new TbOrder();
		order.setAddress(form.getAddress());
		order.setBnumber(bnumber);
		order.setBz(form.getBz());
		order.setCarry(form.getCarry());
		order.setPay(form.getPay());
		order.setPostcode(form.getPostcode());
		order.setRebate(rebate);
		order.setTel(form.getTel());
		order.setTruename(form.getTruename());
		order.setUsername(form.getUsername());
		order.setOrderDate(new Date());
		order.setEnforce(0);
		Set<TbOrderDetail> tbOrderDetails = order.getTbOrderDetails();
		// 插入订单明细表数据
		for (int i = 0; i < bnumber; i++) {
			GoodsElement myGoodsElement = cart.get(i);
			ID = myGoodsElement.getID();
			nowprice = myGoodsElement.getNowPrice() * rebate;
			number = myGoodsElement.number;
			sum = nowprice * number;
			TbOrderDetail details = new TbOrderDetail();
			details.setNumber(number);
			details.setPrice(nowprice);
			details.setTbOrder(order);
			TbGoods goods = dao.getGoods(ID);
			details.setTbGoods(goods);
			tbOrderDetails.add(details);
			Totalsum = Totalsum + sum;
		}
		// 更新会员信息和会员等级
		user.setAmount(user.getAmount() == null ? Totalsum : user.getAmount()
				+ Totalsum);
		int userGrade = dao.getGrade(user.getAmount());
		if (user.getGrade() == null || userGrade > user.getGrade())
			user.setGrade(userGrade);
		dao.insertObject(user, order);// 在事物中完成订单保存和会员的更新
		session.removeAttribute("cart");
		return new ModelAndView(new RedirectView("cartSee.lzw?orderId="
				+ order.getOrderId()));
	}
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("GBK");
		return super.handleRequestInternal(request, response);
	}
	public Dao getDao() {
		return dao;
	}
	public void setDao(Dao dao) {
		this.dao = dao;
	}
}
