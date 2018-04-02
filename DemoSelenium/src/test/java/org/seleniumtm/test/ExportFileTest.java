package org.seleniumtm.test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.seleniumtm.po.DashBoardPO;
import org.seleniumtm.po.LoginPO;
import org.seleniumtm.po.WindowsManagerPO;
import org.seleniumtm.po.devices.DeviceGroupPO;
import org.seleniumtm.po.devices.DevicePO;
import org.seleniumtm.po.expimp.ExportPO;
import org.seleniumtm.po.expimp.ImportPO;
import org.seleniumtm.po.users.UserCreatePO;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public class ExportFileTest {
	public WebDriver driver;
	LoginPO login;
	DashBoardPO dashboard;
	ExportPO exportPO;
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
		exportPO = PageFactory.initElements(driver, ExportPO.class);

	}

	@AfterMethod
	public void afterMethod() {
		WindowsManagerPO.closeWindow();
	}

	@Test(priority = 4)
	public void exportDevices() {
		String usuario = "sysadmin";
		String contraseña = "p";
		String filedev = "C:\\Users\\Apenao\\Downloads\\device_export.pdf";
		String format = "pdf";
		boolean exists = false;
		String properties[] = { "id", "name", "description", "hostname",
				"uniqueId" };
		// delete file if exists
		File f = new File(filedev);
		if (f.exists() && !f.isDirectory()) {
			// delete
			f.delete();
		}
		login.login(usuario, contraseña);
		// click devices
		dashboard.clikcOnDevices();
		// click on export
		dashboard.clickExportDev(format, properties);
		// verify the file
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (f.exists() && !f.isDirectory()) {
			exists = true;
		}
		assertEquals(exists, true);
	}

	@Test(priority = 2)
	public void exportDeviceGroups() {
		String usuario = "sysadmin";
		String contraseña = "p";
		String filedev = "C:\\Users\\Apenao\\Downloads\\device_groups_export.pdf";
		String format = "pdf";
		boolean exists = false;
		String properties[] = { "id", "name", "description", "hostname",
				"displayName" };
		// delete file if exists
		File f = new File(filedev);
		if (f.exists() && !f.isDirectory()) {
			// delete
			f.delete();
		}

		login.login(usuario, contraseña);
		// click devices
		dashboard.clikcOnDeviceGroups();
		// click on export
		dashboard.clickExportDevG(format, properties);
		// verify the file
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (f.exists() && !f.isDirectory()) {
			exists = true;
		}
		assertEquals(exists, true);
	}

	@Test(priority = 3)
	public void exportUsers() {
		String usuario = "sysadmin";
		String contraseña = "p";
		String filedev = "C:\\Users\\Apenao\\Downloads\\user_export.pdf";
		String format = "pdf";
		boolean exists = false;
		String properties[] = { "id", "fullName", "username",
				"authenticationType" };

		// delete file if exists
		File f = new File(filedev);
		if (f.exists() && !f.isDirectory()) {
			// delete
			f.delete();
		}

		login.login(usuario, contraseña);
		// click user
		dashboard.clikcOnUser();
		// click on export same for DEVG
		dashboard.clickExportDevG(format, properties);
		// verify the file
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (f.exists() && !f.isDirectory()) {
			exists = true;
		}
		assertEquals(exists, true);
	}

	@Test(priority = 5)
	public void exportUsersGroups() {
		String usuario = "sysadmin";
		String contraseña = "p";
		String filedev = "C:\\Users\\Apenao\\Downloads\\user_group_export.pdf";
		String format = "pdf";
		boolean exists = false;
		String properties[] = { "id", "name", "description", "role" };

		// delete file if exists
		File f = new File(filedev);
		if (f.exists() && !f.isDirectory()) {
			// delete
			f.delete();
		}
		login.login(usuario, contraseña);
		// click user
		dashboard.clickOnUserGroups();
		// click on export same for DEVG
		dashboard.clickExportDevG(format, properties);
		// verify the file
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (f.exists() && !f.isDirectory()) {
			exists = true;
		}
		assertEquals(exists, true);
	}

	@Test(priority = 6)
	public void exportApps() {
		String usuario = "sysadmin";
		String contraseña = "p";
		String filedev = "C:\\Users\\Apenao\\Downloads\\app_export.pdf";
		String format = "pdf";
		boolean exists = false;
		String properties[] = { "id", "name", "description", "role",
				"applicationId" };

		// delete file if exists
		File f = new File(filedev);
		if (f.exists() && !f.isDirectory()) {
			// delete
			f.delete();
		}
		login.login(usuario, contraseña);
		// click app
		dashboard.clikcOnApp();
		// click on export same for DEVG
		dashboard.clickExportDevG(format, properties);
		// verify the file
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (f.exists() && !f.isDirectory()) {
			exists = true;
		}
		assertEquals(exists, true);
	}

	@Test(priority = 7)
	public void exportSystems() {
		String usuario = "sysadmin";
		String contraseña = "p";
		String filedev = "C:\\Users\\Apenao\\Downloads\\system_update_export.pdf";
		String format = "pdf";
		boolean exists = false;
		String properties[] = { "id", "name", "description", "version",
				"fileName" };

		// delete file if exists
		File f = new File(filedev);
		if (f.exists() && !f.isDirectory()) {
			// delete
			f.delete();
		}
		login.login(usuario, contraseña);
		// click app
		dashboard.clickOnSystemUp();
		// click on export same for DEVG
		dashboard.clickExportDevG(format, properties);
		// verify the file
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (f.exists() && !f.isDirectory()) {
			exists = true;
		}
		assertEquals(exists, true);
	}

	@Test(priority = 8)
	public void exportTemplates() {
		String usuario = "sysadmin";
		String contraseña = "p";
		String filedev = "C:\\Users\\Apenao\\Downloads\\template_export.pdf";
		String format = "pdf";
		boolean exists = false;
		String properties[] = { "id", "name", "description" };

		// delete file if exists
		File f = new File(filedev);
		if (f.exists() && !f.isDirectory()) {
			// delete
			f.delete();
		}
		login.login(usuario, contraseña);
		// click app
		dashboard.clickOnTemplates();

	}

	@Test(priority = 1)
	public void exportSettings() {
		String usuario = "sysadmin";
		String contraseña = "p";
		String filedev = "C:\\Users\\Apenao\\Downloads\\setting_options_export.pdf";
		String format = "pdf";
		boolean exists = false;
		String properties[] = { "id", "name", "description" };

		// delete file if exists
		File f = new File(filedev);
		if (f.exists() && !f.isDirectory()) {
			// delete
			f.delete();
		}
		login.login(usuario, contraseña);
		// click settings
		dashboard.clikcOnSettings();
		// click on export same for DEVG
		dashboard.clickExportDevG(format, properties);
		// verify the file
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (f.exists() && !f.isDirectory()) {
			exists = true;
		}
		assertEquals(exists, true);
	}
}
