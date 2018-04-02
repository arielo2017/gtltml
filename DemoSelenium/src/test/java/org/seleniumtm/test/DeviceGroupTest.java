package org.seleniumtm.test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.seleniumtm.dto.UserDTO;
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

public class DeviceGroupTest {
	public WebDriver driver;
	LoginPO login;
	DashBoardPO dashboard;
	DeviceGroupPO devicegroup;
	Boolean b_esperado;
	Boolean b_actual;
	String esperado;
	String actual;
	String devgp = "devicegroupautom" + SeleniumUtils.generateNumber(1000);
	String descr = "devicegroupautom description" + devgp;
	String newdevgp;
	UserDTO userdtologin;

	@Parameters({ "browser", "url" })
	@BeforeMethod
	public void beforeMethod(String browser, String url) {
		driver = WindowsManagerPO.launchApp(browser, url);
		login = PageFactory.initElements(driver, LoginPO.class);
		dashboard = PageFactory.initElements(driver, DashBoardPO.class);
		devicegroup = PageFactory.initElements(driver, DeviceGroupPO.class);
		// new user sysadmin to login
		userdtologin = new UserDTO("sysadmin", "p");
	}

	@AfterMethod
	public void afterMethod() {
		WindowsManagerPO.closeWindow();
	}

	@Test(priority = 1)
	public void createDeviceGroup() {
		String usuario = "sysadmin";
		String contraseña = "p";
		String parent = "GTL | Device Group 4 | Device Group 5";
		String templates[] = { "Test Template Create 1",
				"Test Template Create 2" };
		esperado = devgp;
		login.login(usuario, contraseña);

		// click devices
		dashboard.clikcOnDeviceGroups();
		// click create
		dashboard.clickCreate();
		// insert
		devicegroup.insertDeviceGroup(devgp, descr, parent, templates);
		// verify
		dashboard.goDeviceGroupName(devgp);
		actual = devicegroup.getDeviceGroupName();
		assertEquals(esperado, actual);
	}

	@Test(priority = 2)
	public void viewDeviceGroup() {
		String usuario = "sysadmin";
		String contraseña = "p";
		login.login(usuario, contraseña);
		// verify
		// click devices
		dashboard.clikcOnDeviceGroups();
		dashboard.goDeviceGroupName(devgp);
		actual = devicegroup.getDeviceGroupName();
		assertEquals(esperado, actual);
	}

	@Test(priority = 3)
	public void editDeviceGroup() {
		String usuario = "sysadmin";
		String contraseña = "p";
		String parent = "GTL";
		newdevgp = devgp + ".change";
		String templates[] = { "Test Template Create 3",
				"Test Template Create 4" };
		login.login(usuario, contraseña);
		// click devices
		dashboard.clikcOnDeviceGroups();
		dashboard.goDeviceGroupName(devgp);
		// click edit
		devicegroup.editDeviceGroup(newdevgp, descr, parent, templates);
		// verify
		dashboard.goDeviceGroupName(newdevgp);
		actual = devicegroup.getDeviceGroupName();
		devgp = parent + "|" + newdevgp;
		assertNotEquals(esperado, actual);
	}

	@Test(priority = 4)
	public void deleteDeviceGroup() {
		String usuario = "sysadmin";
		String contraseña = "p";
		String reasing = "Customers";
		login.login(usuario, contraseña);
		// click devices
		dashboard.clikcOnDeviceGroups();
		// delete
		dashboard.deleteDeviceGroup(devgp, reasing);
		// verif actual = dashboard.getFirstValueTable(newdevgp);
		assertNotEquals(esperado, actual);
	}
}
