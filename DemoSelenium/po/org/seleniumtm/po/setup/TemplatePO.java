package org.seleniumtm.po.setup;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

public class TemplatePO {

	protected WebDriver driver;
	private Wait<WebDriver> fluentWait;

	@FindBy(id = "name")
	private WebElement name;

	@FindBy(id = "description")
	private WebElement description;

	@FindBy(id = "inputRole")
	private WebElement role;

	@FindBy(id = "bootstrap-duallistbox-nonselected-list_")
	private WebElement listGroup;

	@FindBy(id = "inputConfigKey")
	private WebElement inputConfigKey;

	@FindBy(id = "inputDescription")
	private WebElement inputDescription;

	public TemplatePO(WebDriver driver) {
		this.driver = driver;
		this.fluentWait = new FluentWait<WebDriver>(driver).withTimeout(60,
				TimeUnit.SECONDS).pollingEvery(100, TimeUnit.MILLISECONDS);
	}

	private void esperarElemento(WebElement elemento) {
		fluentWait.until(ExpectedConditions.visibilityOf(elemento));
	}
	
	public void waitForAjax(int timeoutInSeconds) {
		System.out
				.println("Checking active ajax calls by calling jquery.active");
		try {
			if (driver instanceof JavascriptExecutor) {
				JavascriptExecutor jsDriver = (JavascriptExecutor) driver;

				for (int i = 0; i < timeoutInSeconds; i++) {
					Object numberOfAjaxConnections = jsDriver
							.executeScript("return jQuery.active");
					// return should be a number
					if (numberOfAjaxConnections instanceof Long) {
						Long n = (Long) numberOfAjaxConnections;
						System.out
								.println("Number of active jquery ajax calls: "
										+ n);
						if (n.longValue() == 0L)
							break;
					}
					Thread.sleep(1000);
				}
			} else {
				System.out.println("Web driver: " + driver
						+ " cannot execute javascript");
			}
		} catch (InterruptedException e) {
			System.out.println(e);
		}
	}

	public void insertTemplate(String templname, String tempdescrp,
			String idadd1, String devicegroup) {
		waitForAjax(10);
		esperarElemento(name);
		name.clear();
		name.sendKeys(templname);
		description.click();
		description.clear();
		description.sendKeys(tempdescrp);

		WebElement clicklist = driver.findElement(By
				.cssSelector("[ng-click='$select.toggle($event)']"));
		clicklist.click();
		WebElement eleSetts = driver.findElement(By
				.id("ui-select-choices-row-2-0"));
		eleSetts.click();

		new Select(driver.findElement(By.id("settingInformation.setting.key")))
				.selectByVisibleText("America/Denver");

		// select 1 device group
		WebElement elDeviceG = driver.findElement(By.name("Device groups"));
		Select oSelect2 = new Select(elDeviceG.findElement(By
				.id("bootstrap-duallistbox-nonselected-list_")));
		oSelect2.selectByValue(devicegroup);
		elDeviceG.findElement(By.cssSelector("[class='btn move btn-default']"))
				.click();

		driver.findElement(By.cssSelector("[ng-click='vm.save()']")).click();

	}

	public void editTemplate(String templname, String tempdescrp, String add1,
			String devgr) {
		esperarElemento(driver.findElement(By
				.cssSelector("[ng-click='vm.goEdit()']")));
		driver.findElement(By.cssSelector("[ng-click='vm.goEdit()']")).click();
		esperarElemento(name);
		waitForAjax(10);

		name.clear();
		name.sendKeys(templname);
		description.click();
		description.clear();
		description.sendKeys(tempdescrp);

		new Select(driver.findElement(By.id("settingInformation.setting.key")))
		.selectByVisibleText("US/Arizona");
		// new setting
		
		// select 1 device group delete the other one
		// remove all

		WebElement elDeviceG = driver.findElement(By.name("Device groups"));
		Select oSelect2 = new Select(elDeviceG.findElement(By
				.id("bootstrap-duallistbox-nonselected-list_")));
		oSelect2.selectByValue(devgr);
		elDeviceG.findElement(By.cssSelector("[class='btn move btn-default']"))
				.click();

		driver.findElement(By.cssSelector("[ng-click='vm.save()']")).click();
	}

}
