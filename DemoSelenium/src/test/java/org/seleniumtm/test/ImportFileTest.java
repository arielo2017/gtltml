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
import org.seleniumtm.po.devices.DeviceGroupPO;
import org.seleniumtm.po.devices.DevicePO;
import org.seleniumtm.po.expimp.ImportPO;
import org.seleniumtm.po.users.UserCreatePO;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public class ImportFileTest {
	public WebDriver driver;
	LoginPO login;
	DashBoardPO dashboard;
	ImportPO importPO;
	DeviceGroupPO devicegroup;
	Boolean b_esperado;
	Boolean b_actual;
	String esperado;
	String actual;

	@Parameters({ "browser", "url" })
	@BeforeMethod
	public void beforeMethod(String browser, String url) {
		driver = WindowsManagerPO.launchApp(browser, url);
		login = PageFactory.initElements(driver, LoginPO.class);
		dashboard = PageFactory.initElements(driver, DashBoardPO.class);
		importPO = PageFactory.initElements(driver, ImportPO.class);
		devicegroup = PageFactory.initElements(driver, DeviceGroupPO.class);
	}

	@AfterMethod
	public void afterMethod() {
		WindowsManagerPO.closeWindow();
	}

	@Test(priority = 1)
	public void importDevices() {
		String usuario = "sysadmin";
		String contraseña = "p";
		int devices=0;
		String filedev = "C:\\Users\\Apenao\\Desktop\\DeviceImport.xlsx";
		String devnamever[] = { "devicetestest1", "devicetestest2",
				"devicetestest3", "devicetestest4", "devicetestest5",
				"devicetestest6" };

		login.login(usuario, contraseña);
		// click devices
		dashboard.clikcOnDevices();
		// click import
		dashboard.clickImportDevices(filedev);
		// verify the 6 loaded
		for (String string : devnamever) {
			// search
			dashboard.clickRefresh();
			actual = dashboard.getFirstValueTable(string);
			if (actual.equals(string)) {
				devices++;
			}
		}
		assertEquals(devices, filedev.length());
		dashboard.clikcOnDevices();
		// delete devices
		for (String string : devnamever) {
			dashboard.deleteDevice(string);
		}
	}

	@Test(priority = 2)
	public void importDeviceGroups() {
		String usuario = "sysadmin";
		String contraseña = "p";
		String filedev = "C:\\Users\\Apenao\\Desktop\\DeviceGroupImport.xlsx";
		String devgrp[] = { "devicecxxGroup1", "devicecxxGroup2",
				"devicecxxGroup3", "devicecxxGroup4", "devicecxxGroup5",
				"devicecxxGroup6" };
		int devgroupsimport = devgrp.length;
		int actuldevgrps = 0;
		String reasing = "Customers";
		login.login(usuario, contraseña);
		// click devices groups
		dashboard.clikcOnDeviceGroups();
		// click import
		dashboard.clickImportDevicesGroups(filedev);
		// verify the 6 loaded
		for (String string : devgrp) {
			dashboard.clickRefresh();
			actual = dashboard.getDeviceGroupName(string);
			if (actual.equals(string)) {
				actuldevgrps++;
			}
		}
		assertEquals(actuldevgrps, devgroupsimport);
		// delete the device groups
		for (String string : devgrp) {
			dashboard.clickRefresh();
			// delete
			dashboard.deleteDeviceGroup(string, reasing);
			
		}

	}

	@Test(priority = 3)
	public void importUsers() {
		String usuario = "sysadmin";
		String contraseña = "p";
		String fileusers = "C:\\Users\\Apenao\\Desktop\\UserImport.xlsx";
		String users[] = { "userimport1", "userimport2", "userimport3",
				"userimport4", "userimport5", "userimport6" };
		int userimport = users.length;
		int userscount = 0;

		login.login(usuario, contraseña);
		// click user
		dashboard.clikcOnUser();
		// click import
		dashboard.clickImportUserGroups(fileusers);
		// verify the 6 loaded
		for (String string : users) {
			actual = dashboard.getFirstValueTable(string);
			if (actual.equals(string)) {
				userscount++;
			}
		}
		assertEquals(userimport, userscount);

		// delete the users
		for (String string : users) { // delete
			dashboard.deleteUser(string);
		}
	}

	@Test(priority = 4)
	public void importUsersGroups() {
		String usuario = "sysadmin";
		String contraseña = "p";
		String fileusers = "C:\\Users\\Apenao\\Desktop\\UserGroupImport.xlsx";
		String usersgrps[] = { "usergroupimport1", "usergroupimport2",
				"usergroupimport3", "usergroupimport4", "usergroupimport5",
				"usergroupimport6" };
		int usergrpimport = usersgrps.length;
		int usersgrpcount = 0;

		login.login(usuario, contraseña);
		// click user
		dashboard.clickOnUserGroups();
		// click import
		dashboard.clickImportUserGroups(fileusers);
		dashboard.clickOnUserGroups();
		// verify the 6 loaded
		for (String string : usersgrps) {
			actual = dashboard.getFirstValueTable(string);
			if (actual.equals(string)) {
				usersgrpcount++;
			}
		}
		assertEquals(usergrpimport, usersgrpcount);
		// delete the users groups
		for (String string : usersgrps) { // delete
			dashboard.deleteUserGroup(string);
		}
	}

}
