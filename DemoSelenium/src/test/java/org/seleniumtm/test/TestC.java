package org.seleniumtm.test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.seleniumtm.po.DashBoardPO;
import org.seleniumtm.po.LoginPO;
import org.seleniumtm.po.SeleniumUtils;
import org.seleniumtm.po.WindowsManagerPO;
import org.seleniumtm.po.operation.TestPO;
import org.seleniumtm.po.users.UserCreatePO;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public class TestC {
	public WebDriver driver;
	TestPO test;
	Boolean b_esperado;
	Boolean b_actual;
	String esperado;
	String actual;
	String usr = "usuarioautom" + SeleniumUtils.generateNumber(1000);
	String device = "deviceautom" + SeleniumUtils.generateNumber(1000);
	String descrev = "deviceautom description" + device;
	String descrusr = "descripcion autom" + usr;

	@Parameters({ "browser", "url" })
	@BeforeMethod
	public void beforeMethod(String browser, String url) {
		driver = WindowsManagerPO.launchApp(browser, url);
		test = PageFactory.initElements(driver, TestPO.class);
	
	}

	@AfterMethod
	public void afterMethod() {
		WindowsManagerPO.closeWindow();
	}

	@Test(priority = 1)
	public void testfor1() throws IOException, InterruptedException {
		 
		    test.open();
	 
		    

	}
	/*
	 * @Test(priority = 1) public void createUser() { String usuario =
	 * "sysadmin"; String contraseña = "p"; String userCr = usr; String
	 * userNamCr = usr; String passCr = "p"; String usgCR = "us"; String email =
	 * "xxx@sdas.com"; esperado = usr;
	 * 
	 * login.login(usuario, contraseña);
	 * 
	 * // click user dashboard.clikcOnUser(); // click create
	 * dashboard.clickCreate(); usercreate.createUser(userCr, userNamCr, passCr,
	 * email, false, usgCR); // verificar String actual =
	 * dashboard.getFirstValueTable(userCr);
	 * 
	 * assertEquals(actual, esperado);
	 * 
	 * }
	 * 
	 * @Test(priority = 2) public void createUserGroup() { String usuario =
	 * "sysadmin"; String contraseña = "p"; String userCr = usr;
	 * login.login(usuario, contraseña);
	 * 
	 * // click user dashboard.clikcOnUser(); // click view user try {
	 * usercreate.clickViewUSer(userCr); assertTrue(true); } catch (Exception e)
	 * { // TODO Auto-generated catch block assertTrue(false); }
	 * 
	 * }
	 * 
	 * @Test(priority = 3) public void createTemplate() { String usuario =
	 * "sysadmin"; String contraseña = "p"; String userCr = usr; String usrnew =
	 * "usuarioautom" + SeleniumUtils.generateNumber(100); String descrnew =
	 * "descripcion autom" + SeleniumUtils.generateNumber(100); String userNamCr
	 * = usrnew; String usgCR = "ps"; String email = "xxx@sdas.change.com";
	 * login.login(usuario, contraseña);
	 * 
	 * // click user dashboard.clikcOnUser(); // go to Edit
	 * dashboard.clickOnElement(userCr); usercreate.editUser(usrnew, userNamCr,
	 * email, usgCR, email); esperado = dashboard.getFirstValueTable(usrnew);
	 * usr = usrnew; // review the user created actual =
	 * dashboard.getFirstValueTable(usrnew); assertEquals(esperado, actual);
	 * 
	 * }
	 * 
	 * @Test(priority = 4) public void createSettingOpt() { String usuario =
	 * "sysadmin"; String contraseña = "p";
	 * 
	 * login.login(usuario, contraseña); // click user dashboard.clikcOnUser();
	 * dashboard.deleteUser(usr); actual = dashboard.getFirstValueTable(usr);
	 * assertNotEquals(esperado, actual); }
	 * 
	 * @Test(priority = 5) public void createDeviceGroup() { String usuario =
	 * "sysadmin"; String contraseña = "p";
	 * 
	 * login.login(usuario, contraseña); // click user dashboard.clikcOnUser();
	 * dashboard.deleteUser(usr); actual = dashboard.getFirstValueTable(usr);
	 * assertNotEquals(esperado, actual); }
	 */

}
