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
import org.seleniumtm.po.operation.OperViewPO;
import org.seleniumtm.po.operation.OperationPO;
import org.seleniumtm.po.software.AppPO;
import org.seleniumtm.po.software.SystemUpdatePO;
import org.seleniumtm.po.users.UserCreatePO;
import org.seleniumtm.po.users.UserGroupCreatePO;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public class OperationTest {
	public WebDriver driver;
	LoginPO login;
	DashBoardPO dashboard;
	OperationPO operation;
	SystemUpdatePO system;
	OperViewPO operview;
	DeviceGroupPO devicegroup;
	Boolean b_esperado;
	Boolean b_actual;
	String expected;
	String actual;
	String version = String.valueOf(SeleniumUtils.generateNumber(1000));
	String namest = "systemupdateautomr" + version;
	String descr = "description" + namest;
	String device = "deviceautom" + SeleniumUtils.generateNumber(1000);
	String descrdev = "deviceautom description" + device;
	String devgp = "devicegroupautom" + SeleniumUtils.generateNumber(1000);

	DevicePO devicecreate;

	@Parameters({ "browser", "url" })
	@BeforeMethod
	public void beforeMethod(String browser, String url) {
		driver = WindowsManagerPO.launchApp(browser, url);
		login = PageFactory.initElements(driver, LoginPO.class);
		dashboard = PageFactory.initElements(driver, DashBoardPO.class);
		operation = PageFactory.initElements(driver, OperationPO.class);
		system = PageFactory.initElements(driver, SystemUpdatePO.class);
		devicecreate = PageFactory.initElements(driver, DevicePO.class);
		operview = PageFactory.initElements(driver, OperViewPO.class);
		devicegroup = PageFactory.initElements(driver, DeviceGroupPO.class);
	}

	@AfterMethod
	public void afterMethod() {
		WindowsManagerPO.closeWindow();
	}

	@Test(priority = 2)
	public void createOperationDevice() {

		String usuario = "sysadmin";
		String contraseña = "p";
		String devictyp = "Inspire 2.0";
		boolean release = true;
		String file = "C:\\Users\\Apenao\\Downloads\\apache-tomcat-7.0.77.zip";
		String uniqid = device;
		String namedev = device;
		String ipadd = "127.0.0.2";
		String devGroups = "GTL | Device Group 4 | Device Group 5";
		String operDevice = null;
		expected = namedev;
		int countoper = 0;

		login.login(usuario, contraseña);
		// create device
		// click devices
		dashboard.clikcOnDevices();
		// click create
		dashboard.clickCreate();
		// insert
		devicecreate.insertDevice(uniqid, namedev, descrdev, ipadd, devictyp,
				devGroups);

		dashboard.clickOnSystemUp();
		system.createSystemUpdate(namest, file, descr, devictyp, version,
				release);
		// run app
		dashboard.clickRefresh();
		// go to device group
		dashboard.clikcOnDevices();
		dashboard.runSystemDevice(namedev, namest);
		// go to operations and verify
		dashboard.clickOnOperation();
		// verify the operation for the device
		countoper = dashboard.countOperations();
		for (int i = 2; i < countoper + 2; i++) {
			// operation view
			operDevice = operview.getDeviceOperationOnO(namedev, i);
			if (operDevice != null) {
				break;
			} else {
				operview.clickOnOperationLink();
			}
		}
		// confirm equals
		assertEquals(operDevice, expected);
		// delete device created
		// click devices
		dashboard.clikcOnDevices();
		dashboard.deleteDevice(uniqid);
		// delete system
		dashboard.clickOnSystemUp();
		system.deleteSystmeupdate(version);

	}

	@Test(priority = 1)
	public void createOperationDeviceGroup() {
		String usuario = "sysadmin";
		String contraseña = "p";
		String devictyp = "Inspire 2.0";
		boolean release = true;
		String file = "C:\\Users\\Apenao\\Downloads\\apache-tomcat-7.0.77.zip";
		String uniqid = device;
		String namedev = device;
		String ipadd = "127.0.0.2";
		String devGroups = "GTL | Device Group 4 | Device Group 5";
		String parent = "GTL | Device Group 4 | Device Group 5";
		String templates[] = { "Test Template Create 1",
				"Test Template Create 2" };
		String operDevice = null;
		String descr = "devicegroup description" + devgp;
		expected = devgp;
		int countoper = 0;

		login.login(usuario, contraseña);
		// create device
		// click devices groups
		dashboard.clikcOnDeviceGroups();
		// click create
		dashboard.clickCreate();
		// insert devie group
		devicegroup.insertDeviceGroup(devgp, descr, parent, templates);
		dashboard.runSystemDeviceGrp(devgp, namest);
		// go to operations and verify
		dashboard.clickOnOperation();
		// verify the operation for the device
		countoper = dashboard.countOperations();
		for (int i = 2; i < countoper + 2; i++) {
			// operation view
			operDevice = operview.getDeviceOperationOnO(namedev, i);
			if (operDevice != null) {
				break;
			} else {
				operview.clickOnOperationLink();
			}
		}
		// confirm equals
		assertEquals(operDevice, expected);
		
	}
}
