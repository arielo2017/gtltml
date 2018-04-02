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
import org.seleniumtm.po.devices.DeviceGroupPO;
import org.seleniumtm.po.devices.DevicePO;
import org.seleniumtm.po.users.UserCreatePO;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public class LocationTest {
	public WebDriver driver;
	LoginPO login;
	DashBoardPO dashboard;
	DeviceGroupPO devicegrp;
	Boolean b_esperado;
	Boolean b_actual;
	String esperado;
	String actual;
	String deviceg = "devicegroupautom" + SeleniumUtils.generateNumber(1000);
	String descr = "deviceautom description" + deviceg;

	@Parameters({ "browser", "url" })
	@BeforeMethod
	public void beforeMethod(String browser, String url) {
		driver = WindowsManagerPO.launchApp(browser, url);
		login = PageFactory.initElements(driver, LoginPO.class);
		dashboard = PageFactory.initElements(driver, DashBoardPO.class);
		devicegrp = PageFactory.initElements(driver, DeviceGroupPO.class);
	}

	@AfterMethod
	public void afterMethod() {
		WindowsManagerPO.closeWindow();
	}

	@Test(priority = 1)
	public void createLocationDeviG() {
		String usuario = "sysadmin";
		String contraseña = "p";
		String uniqid = deviceg;
		String ipadd = "127.0.0.2";
		String parent = "GTL | Device Group 4 | Device Group 5";
		String templates[] = { "Test Template Create 1",
				"Test Template Create 2" };
		String devictyp = "Inspire 2.0";
		String devGroups = "GTL | Device Group 4 | Device Group 5";
		String address1 = "address1";
		String address2 = "address2";
		String city = "city";
		String state = "state";
		String postal = "2423423";
		String country = "Colombia";
		String reasing = "Customers";

		esperado = deviceg;
		login.login(usuario, contraseña);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// click devices
		dashboard.clikcOnDeviceGroups();
		// click create
		dashboard.clickCreate();
		// insert
		devicegrp.insertDeviceLocation(deviceg, descr, parent, templates,
				address1, address2, city, state, postal, country);
		// verify
		dashboard.goDeviceGroupName(deviceg);
		actual = devicegrp.getDeviceGroupName();
		assertEquals(esperado, actual);
	}

	@Test(priority = 2)
	public void locationVerifyNoSpacesUniqueId() {
		String usuario = "sysadmin";
		String contraseña = "p";
		String devgtest = "autom 123";
		esperado = "Unique ID should not contain spaces";
		login.login(usuario, contraseña);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// click create
		// click devices
		dashboard.clikcOnDeviceGroups();
		dashboard.clickCreate();
		// verify uniqueid
		actual = devicegrp.verifyIdSpaces(devgtest);
		assertEquals(esperado, actual);

	}

	@Test(priority = 3)
	public void uniqueId() {
		String usuario = "sysadmin";
		String contraseña = "p";
		String uniqid = deviceg;
		String ipadd = "127.0.0.2";
		String parent = "GTL | Device Group 4 | Device Group 5";
		String templates[] = { "Test Template Create 1",
				"Test Template Create 2" };
		String devictyp = "Inspire 2.0";
		String devGroups = "GTL | Device Group 4 | Device Group 5";
		String address1 = "address1";
		String address2 = "address2";
		String city = "city";
		String state = "state";
		String postal = "2423423";
		String country = "Colombia";
		String reasing = "Customers";
		
		login.login(usuario, contraseña);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// verify uniqueid
		// click devices
		dashboard.clikcOnDeviceGroups();
		// click create
		dashboard.clickCreate();
		// insert
		actual = devicegrp.verifyUniqueId(deviceg, descr, parent, templates,
				address1, address2, city, state, postal, country);
		assertEquals(esperado, actual);
	}
}
