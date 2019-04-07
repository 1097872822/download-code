package com.lzw;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.servlet.view.RedirectView;

import com.lzw.collect.GoodsElement;
import com.lzw.dao.Dao;
import com.lzw.model.TbGoods;
public class CartController extends MultiActionController {
	private Dao dao;
	public ModelAndView cartAdd(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String id = request.getParameter("goodsID");
		TbGoods goods = dao.getGoods(Long.valueOf(id));
		GoodsElement myGoodsElement = new GoodsElement();
		myGoodsElement.setID(goods.getId());
		myGoodsElement.setGoodsName(goods.getGoodsName());
		myGoodsElement.setNowPrice(goods.getNowPrice());
		myGoodsElement.setNumber(1);
		List<GoodsElement> cart = (List<GoodsElement>) session
				.getAttribute("cart");
		boolean Flag = true;
		if (cart == null) {
			cart = new ArrayList<GoodsElement>();
		} else {
			for (int i = 0; i < cart.size(); i++) {
				GoodsElement goodsitem = cart.get(i);
				if (goodsitem.ID == myGoodsElement.ID) {
					goodsitem.number++;
					Flag = false;
				}
			}
		}
		if (Flag) {
			cart.add(myGoodsElement);
			session.setAttribute("cart", cart);
		}
		return new ModelAndView(new RedirectView("cartSee.lzw"));
	}
	public ModelAndView cartModify(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		List<GoodsElement> cart = (List<GoodsElement>) session
				.getAttribute("cart");
		for (int i = 0; i < cart.size(); i++) {
			GoodsElement myGoodsElement = cart.get(i);
			String num = request.getParameter("num" + i);
			int newnum = Integer.parseInt(num);
			myGoodsElement.number = newnum;
			if (newnum <= 0) {
				cart.remove(myGoodsElement);
			}
		}
		return new ModelAndView(new RedirectView("cartSee.lzw"));
	}
	public ModelAndView cartClear(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		session.removeAttribute("cart");
		return new ModelAndView("cartSee");
	}
	public ModelAndView cartMove(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		List cart = (List) session.getAttribute("cart");
		int id = Integer.parseInt(request.getParameter("ID"));
		cart.remove(id);
		return new ModelAndView(new RedirectView("cartSee.lzw"));
	}
	public Dao getDao() {
		return dao;
	}
	public void setDao(Dao dao) {
		this.dao = dao;
	}
}
