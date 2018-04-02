package org.seleniumtm.dto;

import org.seleniumtm.po.SeleniumUtils;

public class UserGropDTO {
	
	
	String role;
	String uscr ;
	String devg ;
	String usrgr = "usergrpautom" + SeleniumUtils.generateNumber(100);
	String descr = "descripcion autom" + SeleniumUtils.generateNumber(100);
	
	public UserGropDTO(String role, String uscr, String devg) {
		super();
		this.role = role;
		this.uscr = uscr;
		this.devg = devg;

	}
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getUscr() {
		return uscr;
	}
	public void setUscr(String uscr) {
		this.uscr = uscr;
	}
	public String getDevg() {
		return devg;
	}
	public void setDevg(String devg) {
		this.devg = devg;
	}
	public String getUsrgr() {
		return usrgr;
	}
	public void setUsrgr(String usrgr) {
		this.usrgr = usrgr;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}

}
