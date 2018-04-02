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
import org.seleniumtm.po.users.UserCreatePO;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public class DeviceTest {
	public WebDriver driver;
	LoginPO login;
	DashBoardPO dashboard;
	DevicePO devicecreate;
	Boolean b_esperado;
	Boolean b_actual;
	String esperado;
	String actual;
	String device = "deviceautom" + SeleniumUtils.generateNumber(1000);
	String descr = "deviceautom description" + device;

	@Parameters({ "browser", "url" })
	@BeforeMethod
	public void beforeMethod(String browser, String url) {
		driver = WindowsManagerPO.launchApp(browser, url);
		login = PageFactory.initElements(driver, LoginPO.class);
		dashboard = PageFactory.initElements(driver, DashBoardPO.class);
		devicecreate = PageFactory.initElements(driver, DevicePO.class);
	}

	@AfterMethod
	public void afterMethod() {
		WindowsManagerPO.closeWindow();
	}

	@Test(priority = 2)
	public void viewDevice() {
		String usuario = "sysadmin";
		String contraseña = "p";
		esperado = device;
		login.login(usuario, contraseña);

		// click devices
		dashboard.clikcOnDevices();
		actual = dashboard.getFirstValueTable(device);
		assertEquals(esperado, actual);
	}

	@Test(priority = 1)
	public void createDevice() {
		String usuario = "sysadmin";
		String contraseña = "p";
		String uniqid = device;
		String namedev = device;
		String ipadd = "127.0.0.2";
		String devictyp = "Inspire 2.0";
		String devGroups = "GTL | Device Group 4 | Device Group 5";
		esperado=device;
		login.login(usuario, contraseña);

		// click devices
		dashboard.clikcOnDevices();
		// click create
		dashboard.clickCreate();
		// insert
		devicecreate.insertDevice(uniqid, namedev, descr, ipadd, devictyp,
				devGroups);
		// verify
		actual = dashboard.getFirstValueTable(device);
		assertEquals(esperado, actual);
	}

	@Test(priority = 3)
	public void editDevice() {
		String usuario = "sysadmin";
		String contraseña = "p";
		String namedev = device + ".change";
		String ipadd = "127.0.0.3";
		String devictyp = "Flex 1.0";
		String devGroups = "GTL | Device Group 4 | Device Group 5 | Device Group 6";
		// login
		login.login(usuario, contraseña);
		// click devices
		dashboard.clikcOnDevices();
		actual = devicecreate.findDevice(device);
		// go to Edit
		dashboard.clickOnElementDevice(device);
		devicecreate.editDevice(device, namedev, descr + ".change", ipadd,
				devictyp, devGroups);
		esperado = device;
		// review the device created
		actual = dashboard.getNameDevice(device);
		assertNotEquals(esperado, actual);
	}

	@Test(priority = 4)
	public void deleteDevice() {
		String usuario = "sysadmin";
		String contraseña = "p";

		// login
		login.login(usuario, contraseña);
		// click devices
		dashboard.clikcOnDevices();
		dashboard.deleteDevice(device);
		actual = dashboard.getFirstValueTable(device);
		assertNotEquals(esperado, actual);

	}

}
