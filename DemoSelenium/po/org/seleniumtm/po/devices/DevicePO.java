package org.seleniumtm.po.devices;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DevicePO {

	protected WebDriver driver;
	private Wait<WebDriver> fluentWait;
	@FindBy(id = "inputUniqueId")
	private WebElement inputUniqueId;

	@FindBy(id = "inputName")
	private WebElement inputName;

	@FindBy(id = "inputDescription")
	private WebElement inputDescription;

	@FindBy(id = "inputHostname")
	private WebElement hostname;

	@FindBy(id = "search-table")
	private WebElement searchtable;

	@FindBy(id = "devicesTable")
	private WebElement searchtabledevice;

	public DevicePO(WebDriver driver) {
		this.driver = driver;
		this.fluentWait = new FluentWait<WebDriver>(driver).withTimeout(60,
				TimeUnit.SECONDS).pollingEvery(100, TimeUnit.MILLISECONDS);
	}

	private void esperarElemento(WebElement elemento) {
		fluentWait.until(ExpectedConditions.visibilityOf(elemento));
	}

	public String getFirstValueTable() {
		WebElement first = driver.findElement(By
				.cssSelector("[ng-click='vm.goToObjectDetails(row)']"));
		String text = first.getText();
		return text;
	}

	public void insertDevice(String uniqid, String namedev, String descrp,
			String ipadd, String devictyp, String devGroups) {
		// find
		esperarElemento(inputUniqueId);
		inputUniqueId.sendKeys(uniqid);
		inputName.clear();
		inputName.sendKeys(namedev);
		inputDescription.clear();
		inputDescription.sendKeys(descrp);
		hostname.clear();
		hostname.sendKeys(ipadd);
		// device type
		WebElement devtype2 = driver.findElement(By
				.cssSelector("[ng-model='vm.deviceType']"));
		WebElement span = devtype2.findElement(By
				.cssSelector("[ng-click='$select.activate()']"));
		span.click();
		WebElement devinput = devtype2.findElement(By
				.cssSelector("[ng-model='$select.search']"));
		devinput.clear();
		devinput.sendKeys(devictyp);

		// device group
		WebElement eldiv = driver.findElement(By
				.cssSelector("span.ui-select-choices-row-inner"));
		esperarElemento(eldiv);
		eldiv.click();
		WebElement search = driver
				.findElement(By
						.cssSelector("[class='ui-select-search input-xs ng-pristine ng-untouched ng-valid ng-empty']"));
		esperarElemento(search);
		search.click();
		search.sendKeys(devGroups);

		driver.findElement(By.cssSelector("span.ui-select-choices-row-inner"))
				.click();
		driver.findElement(By.cssSelector("fieldset")).click();

		WebElement div = driver.findElement(By
				.cssSelector("[class='col-md-6']"));
		esperarElemento(div);

		WebElement element = driver.findElement(By
				.cssSelector("[ng-click='vm.save()']"));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
		// submit.click();

	}

	public void editDevice(String uniqid, String namedev, String descr,
			String ipadd, String devictyp, String devGroups) {
		esperarElemento(driver.findElement(By
				.cssSelector("[ng-click='vm.goEdit()']")));
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		driver.findElement(By.cssSelector("[ng-click='vm.goEdit()']")).click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		esperarElemento(inputName);
		inputName.clear();
		inputName.sendKeys(namedev);
		inputDescription.clear();
		inputDescription.sendKeys(descr);
		hostname.clear();
		hostname.sendKeys(ipadd);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		WebElement search = driver
				.findElement(By
						.cssSelector("[class='ui-select-search input-xs ng-pristine ng-untouched ng-valid ng-empty']"));
		// delete device group
		driver.findElement(By.cssSelector("span.close.ui-select-match-close"))
				.click();
		driver.findElement(By.cssSelector("div.ibox-content")).click();
		search.clear();
		esperarElemento(search);
		search.click();
		search.sendKeys(devGroups);

		driver.findElement(By.cssSelector("span.ui-select-choices-row-inner"))
				.click();
		driver.findElement(By.cssSelector("fieldset")).click();

		List<WebElement> groups = driver.findElements(By
				.cssSelector("[class='form-group']"));
		WebElement check = groups.get(0).findElement(By.tagName("input"));
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		check.click();

		WebElement div = driver.findElement(By
				.cssSelector("[class='col-md-6']"));
		esperarElemento(div);

		WebElement submit = div.findElement(By
				.cssSelector("[ng-click='vm.save()']"));

		esperarElemento(submit);

		WebElement element = driver.findElement(By
				.cssSelector("[ng-click='vm.save()']"));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);

	}

	public String findDevice(String device) {
		WebElement e = driver.findElement(By
				.cssSelector("[class='form-control']"));
		esperarElemento(e);
		e.sendKeys(device);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		String text = driver.findElement(By.linkText(device)).getText();

		return text;
	}

	public void runSystemUpdate(String device, String systemup) {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		esperarElemento(searchtable);
		searchtable.clear();
		searchtable.sendKeys(device);
		try {
			// find

			List<WebElement> tr_collection = searchtabledevice.findElements(By
					.tagName("tr"));
			for (WebElement trElement : tr_collection) {
				List<WebElement> td_collection = trElement.findElements(By
						.tagName("td"));
				for (WebElement tdElement : td_collection) {
					List<WebElement> clickColl = driver
							.findElements(By
									.cssSelector("[ng-click='vm.goToObjectDetails(row)']"));
					for (WebElement webElement : clickColl) {
						if (webElement.getText().toLowerCase().equals(device)) {
							tdElement.click();
							driver.findElement(
									By.cssSelector("[class='btn btn-default btn-sm dropdown-toggle']"))
									.click();
							driver.findElement(
									By.cssSelector("[data-target='#system-update-execution']"))
									.click();
							// execute run
							WebElement divrun = driver.findElement(By
									.id("app-update"));
							divrun.findElement(
									By.cssSelector("[ng-click='vm.run()]"))
									.click();
							return;
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	

}
