package com.lzw.tiles;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.tiles.ComponentContext;
import org.springframework.web.servlet.view.tiles.ComponentControllerSupport;
import com.lzw.dao.Dao;
public class sellSortTilesController extends ComponentControllerSupport {
	protected void doPerform(ComponentContext componentContext,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Dao dao=(Dao)getApplicationContext().getBean("daoProxyFactory");
		List list=dao.getSellSort();
		componentContext.putAttribute("sellSort", list);
	}
}
