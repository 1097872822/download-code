package com.lzw.tiles;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.tiles.ComponentContext;
import org.springframework.web.servlet.view.tiles.ComponentControllerSupport;

import com.lzw.dao.Dao;

public class MemberTilesController extends ComponentControllerSupport {
	private Dao dao;
	private int pageSize=10;
	protected void doPerform(ComponentContext componentContext,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		dao=(Dao) getApplicationContext().getBean("daoProxyFactory");
		String pageStr=request.getParameter("page");
		if(pageStr==null)pageStr="0";
		List list=getDate();
		int recordCount=list.size(); 
		int maxPage=(recordCount-1)/pageSize+1;
		int page=Integer.parseInt(pageStr);
		if(page<1){
			page=1;
		}else{
			if(page>maxPage){
				page=maxPage;
			}
		}
		int currentRecord=(page-1)*pageSize;
		int step=currentRecord+pageSize>=recordCount?recordCount:currentRecord+pageSize;
		List list2=list.subList(currentRecord,step );
		componentContext.putAttribute("memberList", list2);
		componentContext.putAttribute("page", page);
		componentContext.putAttribute("maxPage", maxPage);
	}
	protected List getDate() {
		return dao.getMembers();
	}
	protected Dao getDao() {
		return dao;
	}
	protected void setDao(Dao dao) {
		this.dao = dao;
	}
}
