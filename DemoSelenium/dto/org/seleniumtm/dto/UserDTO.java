package org.seleniumtm.dto;

import org.seleniumtm.po.SeleniumUtils;

public class UserDTO {
	
	private String userlogin;
	private String password;
	private String usergroup;
	private String email;
    private String username = "usuarioautom" + SeleniumUtils.generateNumber(1000);
	private String description = "descripcion autom" + username;
	
	public UserDTO( String password, String usergroup,
			String email) {
		super();
		this.userlogin = username;
		this.password = password;
		this.usergroup = usergroup;
		this.email = email;
	}

	public UserDTO(String usern, String paswd) {
		super();
		this.userlogin = usern;
		this.password = paswd;
	}

	public String getUserlogin() {
		return userlogin;
	}

	public void setUserlogin(String userlogin) {
		this.userlogin = userlogin;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsergroup() {
		return usergroup;
	}

	public void setUsergroup(String usergroup) {
		this.usergroup = usergroup;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	
	
	
}
