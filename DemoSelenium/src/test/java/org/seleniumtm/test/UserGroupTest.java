package org.seleniumtm.test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.seleniumtm.dto.UserDTO;
import org.seleniumtm.dto.UserGropDTO;
import org.seleniumtm.po.DashBoardPO;
import org.seleniumtm.po.LoginPO;
import org.seleniumtm.po.SeleniumUtils;
import org.seleniumtm.po.WindowsManagerPO;
import org.seleniumtm.po.users.UserCreatePO;
import org.seleniumtm.po.users.UserGroupCreatePO;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public class UserGroupTest {
	public WebDriver driver;
	LoginPO login;
	DashBoardPO dashboard;
	UserGroupCreatePO usergroupcre;
	Boolean b_esperado;
	Boolean b_actual;
	String esperado;
	String actual;
	UserDTO userdtologin;
	UserGropDTO usergp;
	UserDTO newuser;

	@Parameters({ "browser", "url" })
	@BeforeMethod
	public void beforeMethod(String browser, String url) {
		driver = WindowsManagerPO.launchApp(browser, url);
		login = PageFactory.initElements(driver, LoginPO.class);
		dashboard = PageFactory.initElements(driver, DashBoardPO.class);
		usergroupcre = PageFactory
				.initElements(driver, UserGroupCreatePO.class);
		// new user sysadmin to login
		userdtologin = new UserDTO("sysadmin", "p");
		//create user for search
		newuser= new UserDTO("p","","xxx@sdas.com");
	}

	@AfterMethod
	public void afterMethod() {
		WindowsManagerPO.closeWindow();
	}

	@Test(priority = 1)
	public void createUserGroup() {


		String role = "ADMIN";
		String uscr = "System Administrator";
		String devg = "GTL";
		
		usergp= new UserGropDTO(role, uscr, devg);
		esperado = usergp.getUsrgr();
		login.login(userdtologin.getUserlogin(), userdtologin.getPassword());

		// click user
		dashboard.clickOnUserGroups();
		// click create
		dashboard.clickCreate();
		// insert
		usergroupcre.insertUserGroup(usergp.getUsrgr(), usergp.getDescr(), usergp.getRole(), usergp.getUscr(), usergp.getDevg());
		// verificar
		String actual = dashboard.getFirstValueTable(usergp.getUsrgr());

		assertEquals(actual, esperado);
	}

	@Test(priority = 2)
	public void listUsersGroups() {

		esperado = usergp.getUsrgr();
		login.login(userdtologin.getUserlogin(), userdtologin.getPassword());

		// click user
		dashboard.clickOnUserGroups();
		actual = dashboard.getFirstValueTable(usergp.getUsrgr());
		assertEquals(esperado, actual);
	}

	@Test(priority = 3)
	public void editUsersGroups() {

		String usrgrnew = "usuarioautom" + SeleniumUtils.generateNumber(100);
		String descrnew = "descripcion autom"
				+ SeleniumUtils.generateNumber(100);

		String role = "WEB_SERVICE";
		String uscr = "User 5";
		String devg = "GTL | Device Group 4 | Device Group 5";
		
		login.login(userdtologin.getUserlogin(), userdtologin.getPassword());


		// click user
		login.clickOnUserGroups();
		actual = usergroupcre.findUser(usergp.getUsrgr());
		// go to Edit
		dashboard.clickOnElement(usergp.getUsrgr());
		usergroupcre.editUserGroup(usrgrnew, descrnew, role, uscr, devg);
		esperado = dashboard.getFirstValueTable(usrgrnew);
		String usrgr = usrgrnew;
		// review the user created
		dashboard.clickOnUserGroups();
		actual = dashboard.getFirstValueTable(usergp.getUsrgr());
		assertEquals(esperado, actual);
	}

	@Test(priority = 4)
	public void deleteUser() {

		String esperado;
		login.login(userdtologin.getUserlogin(), userdtologin.getPassword());
		// click user
		dashboard.clickOnUserGroups();
		// delete
		esperado = dashboard.deleteUserGroup(usergp.getUsrgr());
		// search the delete
		// click user
		dashboard.clickOnUserGroups();
		actual = dashboard.getFirstValueTable(usergp.getUsrgr());
		assertNotEquals(esperado, actual);
	}

}
