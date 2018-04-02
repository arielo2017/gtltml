package org.seleniumtm.test;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.seleniumtm.po.DashBoardPO;
import org.seleniumtm.po.LoginPO;
import org.seleniumtm.po.WindowsManagerPO;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public class LoginTest {
	public WebDriver driver;
	LoginPO login;
	DashBoardPO dashboard;
	Boolean b_esperado;
	Boolean b_actual;
	String esperado;
	String actual;
	
	
	@Parameters({ "browser", "url" })
	@BeforeMethod
	public void beforeMethod(String browser,String url) {
		driver=WindowsManagerPO.launchApp(browser, url);
		login = PageFactory.initElements(driver,
				LoginPO.class);
		dashboard = PageFactory.initElements(driver,
				DashBoardPO.class);
	}

	@AfterMethod
	public void afterMethod() {
		WindowsManagerPO.closeWindow();
	}

	@Test(priority=1)
	public void LoginSuccess() {
		String usuario="sysadmin";
		String contraseña="p";
		esperado = "System Administrator";
		login.login(usuario,contraseña);
		actual= dashboard.verificarLogOut();
		assertEquals(actual, esperado);
	}
	
	@Test(priority=2)
	public void LoginDenied() {
		String usuario="userxx";
		String contraseña="p";
		esperado = "Error occurred while logging in, please contact administrator";
		login.login(usuario,contraseña);
		actual=login.getLoginError();
		assertEquals(actual, esperado);
	}
	
	@Test(priority=3)
	public void LoginAD() {
		String usuario="tuser1";
		String contraseña="ANiceLongPassword4GTLTablets1!";
		esperado = "tuser1@gtltablets.net";
		login.login(usuario,contraseña);
		actual= dashboard.verificarLogOut();
		assertEquals(actual, esperado);
	}
	
	

}
