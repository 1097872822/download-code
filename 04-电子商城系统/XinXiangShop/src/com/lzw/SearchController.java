package com.lzw;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;
import com.lzw.dao.Dao;
import com.lzw.form.SearchForm;
import com.lzw.model.TbGoods;
import com.lzw.model.TbSuperType;
public class SearchController extends AbstractCommandController {
	private Dao dao;
	public SearchController() {
		setCommandClass(SearchForm.class);
	}
	public Dao getDao() {
		return dao;
	}
	public void setDao(Dao dao) {
		this.dao = dao;
	}
	protected ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
		SearchForm form = (SearchForm) command;
		String sql = "";
		String typename = "";
		Integer type = form.getType();
		String key = form.getKey();
		if (form.getType() != null) {
			if (type != 0) {
				sql = "where superId=" + type;
				if (key != null && !key.equals("")) {
					sql = sql + " and goodsName like '%" + key + "%'";
				}
				TbSuperType superType = dao.getSuperType(type);
				if (superType != null) {
					typename = superType.getTypeName();
				}
			} else {
				typename = "全部分类";
				if (key != null && !key.equals("")) {
					sql = "where goodsName like '%" + key + "%'";
				}
			}
			sql = "from VGoods " + sql;
		}
		List list = dao.getGoodsByQuery(sql);
		System.out.println(list.size());
		Map model=new HashMap();
		model.put("typeName", typename);
		model.put("key", key);
		model.put("results",list);
		return new ModelAndView("searchResult",model);
	}
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("GBK");
		return super.handleRequestInternal(request, response);
	}
}
