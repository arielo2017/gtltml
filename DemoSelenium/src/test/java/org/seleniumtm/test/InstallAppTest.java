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

public class InstallAppTest {
	public WebDriver driver;
	LoginPO login;
	DashBoardPO dashboard;
	AppPO app;
	Boolean b_esperado;
	Boolean b_actual;
	String esperado;
	DevicePO devicecreate;
	String actual;
	String appcr = "appautom" + SeleniumUtils.generateNumber(1000);
	String descr = "descripcion autom" + appcr;
	String file = "C:\\Users\\Apenao\\Downloads\\Apps\\com.kakao.talk_v6.1.7-1400266_Android-4.0.apk";
	String device = "deviceautom" + SeleniumUtils.generateNumber(1000);

	@Parameters({ "browser", "url" })
	@BeforeMethod
	public void beforeMethod(String browser, String url) {
		driver = WindowsManagerPO.launchApp(browser, url);
		login = PageFactory.initElements(driver, LoginPO.class);
		dashboard = PageFactory.initElements(driver, DashBoardPO.class);
		app = PageFactory.initElements(driver, AppPO.class);
		devicecreate = PageFactory.initElements(driver, DevicePO.class);
	}

	@AfterMethod
	public void afterMethod() {
		WindowsManagerPO.closeWindow();
	}

	@Test(priority = 1)
	public void installApp() {
		String usuario = "sysadmin";
		String contrasena = "p";
		String notes = "Automation file created";
		String devictyp = "Inspire 2.0";

		String uniqid = device;
		String namedev = device;
		String ipadd = "127.0.0.2";
		String devGroups = "GTL";
		login.login(usuario, contrasena);
		String pack;
		String pack2;

		//delete app
		dashboard.clikcOnApp();
		// delete
		pack = "com.kakao.talk";
		pack2 = dashboard.getFirstValueTable(pack);
		if(pack2!=null)
		{
			dashboard.deleteApp(pack);
		}

		// click create
		dashboard.clickCreate();
		// create app
		esperado = app.createApp(file, appcr, descr, devictyp, notes);
		// create device
		// click devices
		dashboard.clikcOnDevices();
		// click create
		dashboard.clickCreate();
		// insert
		devicecreate.insertDevice(uniqid, namedev, descr, ipadd, devictyp,
				devGroups);
		// install app
		// click devices
		dashboard.clikcOnDevices();
		//install update
		dashboard.clickOnInstall(device,appcr);
		//go to operations and verify 
		//TODO 
	}
	
}
