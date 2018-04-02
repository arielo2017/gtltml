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
import org.seleniumtm.po.devices.DevicePO;
import org.seleniumtm.po.software.AppPO;
import org.seleniumtm.po.users.UserCreatePO;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public class RunSystemUpdateTest {
	public WebDriver driver;
	LoginPO login;
	DashBoardPO dashboard;
	AppPO app;
	DevicePO devicerun;
	Boolean b_esperado;
	Boolean b_actual;
	String esperado;
	String actual;
	String appcr = "appautom" + SeleniumUtils.generateNumber(1000);

	@Parameters({ "browser", "url" })
	@BeforeMethod
	public void beforeMethod(String browser, String url) {
		driver = WindowsManagerPO.launchApp(browser, url);
		login = PageFactory.initElements(driver, LoginPO.class);
		dashboard = PageFactory.initElements(driver, DashBoardPO.class);
		app = PageFactory.initElements(driver, AppPO.class);
		devicerun = PageFactory.initElements(driver, DevicePO.class);
	}

	@AfterMethod
	public void afterMethod() {
		WindowsManagerPO.closeWindow();
	}

	@Test(priority = 1)
	public void runSystempUpdateDevice() {
		String usuario = "sysadmin";
		String contraseña = "p";
		String device = "abc1234511";
		String systemupd="systemupdateautomr729";

		login.login(usuario, contraseña);
		// click system update
		dashboard.clikcOnDevices();
		// go to run
		devicerun.runSystemUpdate(device,systemupd);
		
	}



}
