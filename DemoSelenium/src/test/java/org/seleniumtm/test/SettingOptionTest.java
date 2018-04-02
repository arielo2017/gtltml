package org.seleniumtm.test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.seleniumtm.po.DashBoardPO;
import org.seleniumtm.po.LoginPO;
import org.seleniumtm.po.WindowsManagerPO;
import org.seleniumtm.po.setup.SettingOptionPO;
import org.seleniumtm.po.users.UserCreatePO;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public class SettingOptionTest {
	public WebDriver driver;
	LoginPO login;
	DashBoardPO dashboard;
	SettingOptionPO setting;
	Boolean b_esperado;
	Boolean b_actual;
	String esperado;
	String actual;
	String sett = "general.test" + generateNumber(100);
	String descr = "Inspire description" + sett;

	@Parameters({ "browser", "url" })
	@BeforeMethod
	public void beforeMethod(String browser, String url) {
		driver = WindowsManagerPO.launchApp(browser, url);
		login = PageFactory.initElements(driver, LoginPO.class);
		dashboard = PageFactory.initElements(driver, DashBoardPO.class);
		setting = PageFactory.initElements(driver, SettingOptionPO.class);
	}

	@AfterMethod
	public void afterMethod() {
		WindowsManagerPO.closeWindow();
	}

	@Test(priority = 2)
	public void listSettings() {
		String usuario = "sysadmin";
		String contraseña = "p";
		esperado = sett;
		login.login(usuario, contraseña);

		// click user
		dashboard.clikcOnSettings();
		actual = dashboard.getFirstValueTable(sett);
		assertEquals(esperado, actual);
	}

	@Test(priority = 1)
	public void createSettings() {
		String usuario = "sysadmin";
		String contraseña = "p";
		login.login(usuario, contraseña);

		String types = "STRING";
		String devictype = "Inspire 2.0";
		boolean devlvl = false;

		// click user
		dashboard.clikcOnSettings();
		// click create
		dashboard.clickCreate();
		// insert
		setting.insertSetting(sett, descr, devlvl, types, devictype);
		
	}

	@Test(priority = 3)
	public void editSettings() {
		String usuario = "sysadmin";
		String contraseña = "p";
		String descrnew = "Inspire description" + "general.test"
				+ generateNumber(100);
		String types = "STRING";
		String devictype = "Flex 1.0";
		boolean devlvl = true;
		esperado = sett;
		login.login(usuario, contraseña);
		// click user
		dashboard.clikcOnSettings();
		// go to Edit
		dashboard.clickOnElement(sett);
		// edit
		setting.editSetting(descrnew, devlvl, types, devictype);
		// verify
		// review the setting created
		dashboard.clikcOnSettings();
		actual = dashboard.getFirstValueTable(sett);
		assertEquals(esperado, actual);

	}

	@Test(priority = 4)
	public void deleteSetting() {
		String usuario = "sysadmin";
		String contraseña = "p";
		String esperado;
		login.login(usuario, contraseña);
		// click user
		dashboard.clikcOnSettings();
		// delete
		esperado = dashboard.deleteSetting(sett);
		dashboard.clikcOnSettings();
		actual = dashboard.getFirstValueTable(sett);
		assertNotEquals(esperado, actual);

	}

	public int generateNumber(int max) {

		Random rand = new Random();

		int n = rand.nextInt(max) + 1;
		return n;
	}

}
