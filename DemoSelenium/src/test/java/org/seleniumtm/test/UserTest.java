package org.seleniumtm.test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.seleniumtm.dto.UserDTO;
import org.seleniumtm.po.DashBoardPO;
import org.seleniumtm.po.LoginPO;
import org.seleniumtm.po.SeleniumUtils;
import org.seleniumtm.po.WindowsManagerPO;
import org.seleniumtm.po.users.UserCreatePO;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public class UserTest {
	public WebDriver driver;
	LoginPO login;
	UserDTO userdtologin;
	UserDTO newuser;
	DashBoardPO dashboard;
	UserCreatePO usercreate;
	Boolean b_esperado;
	Boolean b_actual;
	String esperado;
	String actual;
	String usr ;
	String descr;

	@Parameters({ "browser", "url" })
	@BeforeMethod
	public void beforeMethod(String browser, String url) {
		driver = WindowsManagerPO.launchApp(browser, url);
		login = PageFactory.initElements(driver, LoginPO.class);
		dashboard = PageFactory.initElements(driver, DashBoardPO.class);
		usercreate = PageFactory.initElements(driver, UserCreatePO.class);
		//new user sysadmin to login
		userdtologin=new UserDTO("sysadmin", "p");
		
		
		
	}

	@AfterMethod
	public void afterMethod() {
		WindowsManagerPO.closeWindow();
	}

	@Test(priority = 1)
	public void createUser() {
		
		newuser= new UserDTO("p","System Administrator","xxx@sdas.com");
		esperado = usr;

		login.login(userdtologin.getUserlogin(), userdtologin.getPassword());

		// click user
		dashboard.clikcOnUser();
		// click create
		dashboard.clickCreate();
		usercreate.createUser(newuser.getUserlogin(),newuser.getUsername(),newuser.getPassword(),newuser.getEmail(), false, newuser.getUsergroup());
		// verificar
		dashboard.clikcOnUser();
		actual = dashboard.getFirstValueTable(newuser.getUsername());

		assertEquals(actual, esperado);

	}

	@Test(priority = 2)
	public void viewUser() {
		
		login.login(userdtologin.getUserlogin(), userdtologin.getPassword());

		String userCr = newuser.getUsername();

		// click user
		dashboard.clikcOnUser();
		// click view user
		try {
			usercreate.clickViewUSer(userCr);
			assertTrue(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			assertTrue(false);
		}

	}

	@Test(priority = 3)
	public void editUser() {
		
		
		String userCr = newuser.getUsername();
		

		String usgCR = "Power Support";
		String email = "xxx@sdas.change.com";
		String nwpsw="p";
		UserDTO userupdate = new UserDTO(nwpsw,usgCR,email);
	
		login.login(userdtologin.getUserlogin(), userdtologin.getPassword());

		// click user
		dashboard.clikcOnUser();
		// go to Edit
		dashboard.clickOnElement(userCr);
		usercreate.editUser(userupdate.getUsername(),usgCR,userupdate.getEmail(),userupdate.getDescription(),userupdate.getPassword());
		esperado = dashboard.getFirstValueTable(userupdate.getUsername());

		// review the user created
		actual = dashboard.getFirstValueTable(userupdate.getUsername());
		assertEquals(esperado, actual);

	}

	@Test(priority = 4)
	public void deleteUser() {
		
		login.login(userdtologin.getUserlogin(), userdtologin.getPassword());
		// click user
		dashboard.clikcOnUser();
		dashboard.deleteUser(esperado);
		actual = dashboard.getFirstValueTable(esperado);
		assertNotEquals(esperado, actual);
	}



}
