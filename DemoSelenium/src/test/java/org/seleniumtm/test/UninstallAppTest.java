package org.seleniumtm.test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.seleniumtm.po.DashBoardPO;
import org.seleniumtm.po.LoginPO;
import org.seleniumtm.po.SeleniumUtils;
import org.seleniumtm.po.WindowsManagerPO;
import org.seleniumtm.po.software.AppPO;
import org.seleniumtm.po.users.UserCreatePO;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public class UninstallAppTest {
	public WebDriver driver;
	LoginPO login;
	DashBoardPO dashboard;
	AppPO app;
	Boolean b_esperado;
	Boolean b_actual;
	String esperado;
	String actual;
	String appcr = "appautom" + SeleniumUtils.generateNumber(1000);
	String descr = "descripcion autom" + appcr;
	String file = "C:\\Users\\Apenao\\Downloads\\Apps\\com.kakao.talk_v6.1.7-1400266_Android-4.0.apk";

	@Parameters({ "browser", "url" })
	@BeforeMethod
	public void beforeMethod(String browser, String url) {
		driver = WindowsManagerPO.launchApp(browser, url);
		login = PageFactory.initElements(driver, LoginPO.class);
		dashboard = PageFactory.initElements(driver, DashBoardPO.class);
		app = PageFactory.initElements(driver, AppPO.class);
	}

	@AfterMethod
	public void afterMethod() {
		WindowsManagerPO.closeWindow();
	}

	@Test(priority = 1)
	public void uninstallApp() {
		String usuario = "sysadmin";
		String contraseña = "p";
		String notes = "Automation file created";
		String devictyp = "Inspire 2.0";

		login.login(usuario, contraseña);

		// click app
		dashboard.clikcOnApp();
		// click create
		dashboard.clickCreate();
		// create app
		esperado = app.createApp(file, appcr, descr, devictyp, notes);
		
	}

	@Test(priority = 2)
	public void uninstallSystem() {
		String usuario = "sysadmin";
		String contraseña = "p";
		// login
		login.login(usuario, contraseña);
		// click app
		dashboard.clikcOnApp();
		actual = dashboard.getFirstValueTable(appcr);
		assertEquals(esperado, actual);

	}

	@Test(priority = 3)
	public void editApp() {

		String usuario = "sysadmin";
		String contraseña = "p";
		String devictyp = "Inspire 2.0";
		String newappname = appcr + ".change";
		String pack;
		descr = descr + ".change";
		// login
		login.login(usuario, contraseña);
		// click app
		dashboard.clikcOnApp();
		// go to Edit
		pack = dashboard.getFirstValueTable(appcr);
		dashboard.clickOnElement(pack);
		app.editApp(newappname, descr, devictyp);
		actual = dashboard.getFirstValueTable(newappname);
		assertEquals(esperado, actual);
	}

	@Test(priority = 4)
	public void deleteApp() {
		String usuario = "sysadmin";
		String contraseña = "p";
		String pack;
		// login
		login.login(usuario, contraseña);
		// click apps
		dashboard.clikcOnApp();
		// delete
		pack = dashboard.getFirstValueTable(appcr);
		dashboard.deleteApp(pack);
		actual = dashboard.getFirstValueTable(appcr);
		assertNotEquals(esperado, actual);
	}

}
