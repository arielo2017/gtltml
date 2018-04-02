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

public class SetPasswordTest {
	public WebDriver driver;
	LoginPO login;
	DashBoardPO dashboard;
	UserCreatePO usercreate;
	SystemUpdatePO system;
	Boolean b_esperado;
	Boolean b_actual;
	String expected;
	String actual;
	String usr = "usuarioautom" + SeleniumUtils.generateNumber(1000);
	String descr = "descripcion autom" + usr;

	DevicePO devicecreate;

	@Parameters({ "browser", "url" })
	@BeforeMethod
	public void beforeMethod(String browser, String url) {
		driver = WindowsManagerPO.launchApp(browser, url);
		login = PageFactory.initElements(driver, LoginPO.class);
		dashboard = PageFactory.initElements(driver, DashBoardPO.class);
		usercreate = PageFactory.initElements(driver, UserCreatePO.class);

	}

	@AfterMethod
	public void afterMethod() {
		WindowsManagerPO.closeWindow();
	}

	@Test(priority = 1)
	public void editPassword() {
		String usuario = "sysadmin";
		String contraseña = "p";
		String userCr = usr;
		String userNamCr = usr;
		String passCr = "p";
		String usgCR = "us";
		String email = "xxx@sdas.com";
		String nwpsw = "xxx";
		expected = usr;

		login.login(usuario, contraseña);

		// click user
		dashboard.clikcOnUser();
		// click create
		dashboard.clickCreate();
		usercreate.createUser(userCr, userNamCr, passCr, email, false, usgCR);
		// editar
		// click user
		dashboard.clikcOnUser();
		dashboard.clickOnElement(userCr);
		usercreate.editUser(userCr, userNamCr, email, usgCR, nwpsw);

		usercreate.logout();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		login.login(userCr, nwpsw);
		// click user
		dashboard.clikcOnUser();
		actual = dashboard.getFirstValueTable(userCr);
		//delete
		dashboard.deleteUser(userCr);
		assertEquals(actual, expected);

	}
}
