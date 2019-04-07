package com.lzw.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;
import com.lzw.dao.Dao;
import com.lzw.manager.form.ProductCommand;

public class ProductManagerController extends AbstractCommandController {
	private Dao dao;

	private int pageSize = 10;

	public ProductManagerController() {
		setCommandClass(ProductCommand.class);
	}

	protected ModelAndView handle(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		ProductCommand form = (ProductCommand) command;
		Map model = new HashMap();
		List list = dao.getGoodsByQuery("from TbGoods order by intime Desc");
		int recordCount = list.size();
		int maxPage = (recordCount - 1) / pageSize + 1;
		int page = Integer.parseInt(form.getPage());
		if (page < 1) {
			page = 1;
		} else {
			if (page > maxPage) {
				page = maxPage;
			}
		}
		int currentRecord = (page - 1) * pageSize;
		int step = currentRecord + pageSize >= recordCount ? recordCount
				: currentRecord + pageSize;
		List list2 = list.subList(currentRecord, step);
		model.put("goodsList", list2);
		model.put("page", page);
		model.put("maxPage", maxPage);
		return new ModelAndView("indexM", model);
	}

	public Dao getDao() {
		return dao;
	}

	public void setDao(Dao dao) {
		this.dao = dao;
	}
}
