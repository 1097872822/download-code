/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.wsy.struts.form;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 * Creation date: 11-16-2007
 * 
 * XDoclet definition:
 * @struts.form name="topicmanagerForm"
 */
public class TopicmanagerForm extends ActionForm {
	/*
	 * Generated fields
	 */

	/** content property */
	private String content;

	/** id property */
	private String id;

	/** author property */
	private String author;

	/** title property */
	private String title;

	/** forumid property */
	private String forumid;

	/** submittime property */
	private String submittime;

	/** xq property */
	private String xq;

	/*
	 * Generated Methods
	 */

	/** 
	 * Method validate
	 * @param mapping
	 * @param request
	 * @return ActionErrors
	 */
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	/** 
	 * Method reset
	 * @param mapping
	 * @param request
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// TODO Auto-generated method stub
	}

	/** 
	 * Returns the content.
	 * @return String
	 */
	public String getContent() {
		return content;
	}

	/** 
	 * Set the content.
	 * @param content The content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/** 
	 * Returns the id.
	 * @return String
	 */
	public String getId() {
		return id;
	}

	/** 
	 * Set the id.
	 * @param id The id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/** 
	 * Returns the author.
	 * @return String
	 */
	public String getAuthor() {
		return author;
	}

	/** 
	 * Set the author.
	 * @param author The author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/** 
	 * Returns the title.
	 * @return String
	 */
	public String getTitle() {
		return title;
	}

	/** 
	 * Set the title.
	 * @param title The title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/** 
	 * Returns the forumid.
	 * @return String
	 */
	public String getForumid() {
		return forumid;
	}

	/** 
	 * Set the forumid.
	 * @param forumid The forumid to set
	 */
	public void setForumid(String forumid) {
		this.forumid = forumid;
	}

	/** 
	 * Returns the submittime.
	 * @return String
	 */
	public String getSubmittime() {
		return submittime;
	}

	/** 
	 * Set the submittime.
	 * @param submittime The submittime to set
	 */
	public void setSubmittime(String submittime) {
		this.submittime = submittime;
	}

	/** 
	 * Returns the xq.
	 * @return String
	 */
	public String getXq() {
		return xq;
	}

	/** 
	 * Set the xq.
	 * @param xq The xq to set
	 */
	public void setXq(String xq) {
		this.xq = xq;
	}
}