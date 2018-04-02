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
import org.seleniumtm.po.setup.SettingOptionPO;
import org.seleniumtm.po.setup.TemplatePO;
import org.seleniumtm.po.users.UserCreatePO;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public class TemplateTest {
	public WebDriver driver;
	LoginPO login;
	DashBoardPO dashboard;
	TemplatePO template;
	Boolean b_esperado;
	Boolean b_actual;
	String esperado;
	String actual;
	String templ = "templ.test" + SeleniumUtils.generateNumber(1000);
	String descr = "template description" + templ;

	@Parameters({ "browser", "url" })
	@BeforeMethod
	public void beforeMethod(String browser, String url) {
		driver = WindowsManagerPO.launchApp(browser, url);
		login = PageFactory.initElements(driver, LoginPO.class);
		dashboard = PageFactory.initElements(driver, DashBoardPO.class);
		template = PageFactory.initElements(driver, TemplatePO.class);
	}

	@AfterMethod
	public void afterMethod() {
		WindowsManagerPO.closeWindow();
	}

	@Test(priority = 1)
	public void createTemplate() {
		String usuario = "sysadmin";
		String contraseña = "p";
		login.login(usuario, contraseña);
		String add1 = "genesis.url";
		String devgr = "GTL | Device Group 4 | Device Group 5 | Device Group 6";
		// click user
		dashboard.clickOnTemplates();
		// click create
		dashboard.clickCreate();
		// insert template
		template.insertTemplate(templ, descr, add1, devgr);
		// verify
		actual = dashboard.getFirstValueTable(templ);
		assertEquals(templ, actual);

	}

	@Test(priority = 2)
	public void listTemplate() {
		String usuario = "sysadmin";
		String contraseña = "p";
		esperado = templ;
		login.login(usuario, contraseña);
		// click user
		dashboard.clickOnTemplates();
		actual = dashboard.getFirstValueTable(templ);
		assertEquals(esperado, actual);
	}

	@Test(priority = 3)
	public void editTemplate() {
		String usuario = "sysadmin";
		String contraseña = "p";
		esperado = templ + SeleniumUtils.generateNumber(1000) + ".change";
		login.login(usuario, contraseña);
		String add1value = "Yes";
		String devgr = "GTL | Device Group 4 | Device Group 5";
		// click templates
		dashboard.clickOnTemplates();
		// go to Edit
		dashboard.clickOnTemplateElement(templ);
		// edit
		template.editTemplate(esperado, descr + ".change", add1value, devgr);
		// verify
		// review the template created
		templ=esperado;
		actual = dashboard.getFirstValueTable(esperado);
		assertEquals(esperado, actual);

	}

	@Test(priority = 4)
	public void deleteTemplate() {
		String usuario = "sysadmin";
		String contraseña = "p";
		login.login(usuario, contraseña);
		// click template
		dashboard.clickOnTemplates();
		// delete
		dashboard.deleteTemplate(templ);
		actual = dashboard.getFirstValueTable(esperado);
		assertNotEquals(esperado, actual);
	}


}
