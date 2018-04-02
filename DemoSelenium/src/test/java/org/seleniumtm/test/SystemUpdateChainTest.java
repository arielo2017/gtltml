package org.seleniumtm.test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.seleniumtm.po.DashBoardPO;
import org.seleniumtm.po.LoginPO;
import org.seleniumtm.po.SeleniumUtils;
import org.seleniumtm.po.WindowsManagerPO;
import org.seleniumtm.po.devices.DevicePO;
import org.seleniumtm.po.software.SystemUpdateChainPO;
import org.seleniumtm.po.software.SystemUpdatePO;
import org.seleniumtm.po.users.UserCreatePO;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public class SystemUpdateChainTest {
	public WebDriver driver;
	LoginPO login;
	DashBoardPO dashboard;
	SystemUpdatePO system;
	SystemUpdateChainPO systemchain;
	Boolean b_esperado;
	Boolean b_actual;
	String esperado;
	String actual;
	String version = String.valueOf(SeleniumUtils.generateNumber(1000));
	String name = "systemupdateautomr" + version;
	String descr = "description" + name;

	@Parameters({ "browser", "url" })
	@BeforeMethod
	public void beforeMethod(String browser, String url) {
		driver = WindowsManagerPO.launchApp(browser, url);
		login = PageFactory.initElements(driver, LoginPO.class);
		dashboard = PageFactory.initElements(driver, DashBoardPO.class);
		system = PageFactory.initElements(driver, SystemUpdatePO.class);
		systemchain = PageFactory.initElements(driver, SystemUpdateChainPO.class);
	}

	@AfterMethod
	public void afterMethod() {
		WindowsManagerPO.closeWindow();
	}

	@Test(priority = 1)
	public void createSystemUpdate() {
		String usuario = "sysadmin";
		String contraseña = "p";
		String file = "C:\\Users\\Apenao\\Downloads\\apache-tomcat-7.0.77.zip";
		boolean release = true;
		String devictyp = "Inspire 2.0";
		esperado = version;

		login.login(usuario, contraseña);
		dashboard.clickOnSystemUp();
		system.createSystemUpdate(name, file, descr, devictyp, version, release);
		// verify



	}
/*
	@Test(priority = 3)
	public void editSystemUpdate() {
		String usuario = "sysadmin";
		String contraseña = "p";
		String devictyp = "Flex 1.0";
		String nname = name + ".change";
		descr = descr + ".change";
		login.login(usuario, contraseña);
		dashboard.clickOnSystemUp();
		dashboard.clickOnElement(version);
		system.editSystemUpdate(nname, descr, devictyp);
		// verify
		actual = dashboard.getFirstValueName(version);
		assertNotEquals(esperado, actual);
		name=nname;
	}

	@Test(priority = 2)
	public void viewUpdate() {
		String usuario = "sysadmin";
		String contraseña = "p";
		descr = descr + ".change";
		login.login(usuario, contraseña);
		dashboard.clickOnSystemUp();

		// verify
		actual = dashboard.getFirstValueTable(version);
		assertEquals(esperado, actual);
	}
	
	@Test(priority = 4)
	public void deleteSystemUpdate() {
		String usuario = "sysadmin";
		String contraseña = "p";
	
		login.login(usuario, contraseña);
		dashboard.clickOnSystemUp();
		system.deleteSystmeupdate(version);
		//verify
		actual = dashboard.getFirstValueTable(name);
		assertNotEquals(esperado, actual);
	}*/

}
