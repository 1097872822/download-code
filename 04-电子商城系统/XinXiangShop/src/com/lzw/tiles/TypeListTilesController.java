package com.lzw.tiles;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.tiles.ComponentContext;
import org.springframework.web.servlet.view.tiles.ComponentControllerSupport;
import com.lzw.dao.Dao;
public class TypeListTilesController extends ComponentControllerSupport {
	protected void doPerform(ComponentContext componentContext,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Dao dao=(Dao)getApplicationContext().getBean("daoProxyFactory");
		Map<String, List> model=dao.getTypeList();
		componentContext.putAttribute("typeList", model);
		componentContext.putAttribute("typeNameList", model.keySet());
	}
}
