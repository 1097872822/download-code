package com.lzw;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import com.lzw.dao.Dao;
public class NewGoodsController extends MultiActionController{
	private Dao dao;
	private int saleGoodsLine=1;
	private int newGoodsLine=4;
	public ModelAndView goodsShow(HttpServletRequest request,HttpServletResponse response){
		List list1=dao.getShowSaleGoodsList();
		List list2=dao.getShowNewGoodsList();
		Map model=new HashMap();
		model.put("saleGoods", list1);
		model.put("newGoods", list2);
		model.put("saleGoodsLine", saleGoodsLine);
		model.put("newGoodsLine", newGoodsLine);
		return new ModelAndView("index",model);
	}
	public ModelAndView newGoods(HttpServletRequest request,HttpServletResponse response){
		List list=dao.getShowNewGoodsList();
		Map model=new HashMap();
		model.put("newGoods", list);
		return new ModelAndView("newGoods",model);
	}
	public ModelAndView saleGoods(HttpServletRequest request,HttpServletResponse response){
		List list=dao.getShowSaleGoodsList();
		Map model=new HashMap();
		model.put("saleGoods", list);
		return new ModelAndView("saleGoods",model);
	}
	public Dao getDao() {
		return dao;
	}
	public void setDao(Dao dao) {
		this.dao = dao;
	}
	public int getSaleGoodsLine() {
		return saleGoodsLine;
	}
	public void setSaleGoodsLine(int saleGoodsLine) {
		this.saleGoodsLine = saleGoodsLine;
	}
	public int getNewGoodsLine() {
		return newGoodsLine;
	}
	public void setNewGoodsLine(int newGoodsLine) {
		this.newGoodsLine = newGoodsLine;
	}
}
