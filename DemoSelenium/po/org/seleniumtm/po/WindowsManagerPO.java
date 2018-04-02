package org.seleniumtm.po;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class WindowsManagerPO {
	private static WebDriver driver;
	String browser;
	String url;

	public static WebDriver launchApp(String browser, String url) {
		ProfilesIni profile = new ProfilesIni();

		switch (browser) {
		case "firefox":
			driver = new FirefoxDriver();
			break;
		case "chrome":
			ChromeOptions options = new ChromeOptions();
			options.addArguments("chrome.switches", "--disable-infobars");
			options.addArguments("chrome.switches", "--disable-background-networking");
			options.addArguments("chrome.switches", "--disable-default-apps");
			options.addArguments("chrome.switches", "--disable-hang-monitor");
			options.addArguments("chrome.switches", "--disable-popup-blocking");
			options.addArguments("chrome.switches", "--disable-prompt-on-repost");
			options.addArguments("chrome.switches", "--disable-extensions");
			options.addArguments("chrome.switches", "--disable-sync");
			options.addArguments("chrome.switches", "--start-maximized");
			options.addArguments("chrome.switches",
					"--ignore-certificate-errors");
			DesiredCapabilities handlSSLErr = DesiredCapabilities.chrome();
			handlSSLErr.setCapability(CapabilityType.ACCEPT_SSL_CERTS, false);
			System.setProperty(
					"webdriver.chrome.driver",
					(System.getProperty("user.dir") + "//drivers/chromedriver-windows-32bit.exe"));
			driver = new ChromeDriver(options);
			break;
		case "ie":
			DesiredCapabilities capabilities = DesiredCapabilities
					.internetExplorer();
			System.setProperty(
					"webdriver.ie.driver",
					(System.getProperty("user.dir") + "//drivers/internetexplorerdriver-windows-32bit.exe"));
			capabilities.setCapability(
					InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, false);
			capabilities.setCapability(InternetExplorerDriver.NATIVE_EVENTS,
					true);
			InternetExplorerDriverService.Builder builder = new InternetExplorerDriverService.Builder();
			InternetExplorerDriverService service = builder.build();
			driver = new InternetExplorerDriver(service, capabilities);
			break;
		}
		getURL(url);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		return driver;
	}

	public static void getURL(String url) {
		
     /*   try {
        	Runtime.getRuntime().exec("C:/Users/Apenao/Desktop/certifi.exe");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
        driver.get(url);;
		// driver.manage().window().maximize();
	}

	public static void closeWindow() {
		driver.close();
	}

}
