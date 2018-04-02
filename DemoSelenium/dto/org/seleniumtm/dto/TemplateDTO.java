package org.seleniumtm.dto;

import org.seleniumtm.po.SeleniumUtils;

public class TemplateDTO {
	
	private String add1 ;
	private String devgr ;
	private String templ = "templ.test" + SeleniumUtils.generateNumber(1000);
	private String descr = "template description" + templ;
	
	public TemplateDTO(String templ, String descr) {
		super();
		this.templ = templ;
		this.descr = descr;
	}
	
	public String getAdd1() {
		return add1;
	}
	public void setAdd1(String add1) {
		this.add1 = add1;
	}
	public String getDevgr() {
		return devgr;
	}
	public void setDevgr(String devgr) {
		this.devgr = devgr;
	}
	public String getTempl() {
		return templ;
	}
	public void setTempl(String templ) {
		this.templ = templ;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}


}
