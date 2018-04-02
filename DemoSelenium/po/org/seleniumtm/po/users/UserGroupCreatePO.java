package org.seleniumtm.po.users;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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

public class UserGroupCreatePO {

	protected WebDriver driver;
	private Wait<WebDriver> fluentWait;

	@FindBy(id = "inputName")
	private WebElement fullName;

	@FindBy(id = "inputDescription")
	private WebElement description;

	@FindBy(id = "inputRole")
	private WebElement role;

	@FindBy(id = "bootstrap-duallistbox-nonselected-list_")
	private WebElement listGroup;
	
	@FindBy(id = "search-table")
	private WebElement searchtable;

	public UserGroupCreatePO(WebDriver driver) {
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
	
	public void insertUserGroup(String usrgr, String descr, String rolep,
			String uscr, String devg) {
		waitForAjax(20);
		//esperarElemento(fullName);
		fullName.clear();
		fullName.sendKeys(usrgr);
		description.clear();
		description.sendKeys(descr);
		role.click();
		new Select(role).selectByVisibleText(rolep);
		driver.findElement(By.cssSelector("option[value=\"ADMIN\"]")).click();
		// select user div
		WebElement elUsers = driver.findElement(By.name("Users"));
		Select oSelect = new Select(elUsers.findElement(By
				.id("bootstrap-duallistbox-nonselected-list_")));
		oSelect.selectByValue(uscr);
		elUsers.findElement(By.cssSelector("[class='btn move btn-default']"))
				.click();
		// select device groups div
		WebElement elDeviceG = driver.findElement(By.name("Device groups"));
		Select oSelect2 = new Select(elDeviceG.findElement(By
				.id("bootstrap-duallistbox-nonselected-list_")));
		oSelect2.selectByValue(devg);
		elDeviceG.findElement(By.cssSelector("[class='btn move btn-default']"))
				.click();
		// click Save
		WebElement save = driver.findElement(By
				.cssSelector("[ng-click='vm.save()']"));
		save.click();

	}

	public void clickCreate() {
		// TODO Auto-generated method stub

	}

	public String getFirstValueTable(String usrgr) {
		esperarElemento(searchtable);
		searchtable.clear();
		searchtable.sendKeys(usrgr);
		
		String text=null;
		try {
			WebElement first = driver.findElement(By
					.cssSelector("[ng-click='vm.goToObjectDetails(row)']"));
			text = first.getText();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return text;
	}

	public String findUser(String usrgr) {

		WebElement e = driver.findElement(By
				.cssSelector("[class='form-control']"));
		esperarElemento(e);
		e.sendKeys(usrgr);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String text = driver.findElement(By.linkText(usrgr)).getText();

		return text;
	}

	public void editUserGroup(String usrgr, String descr, String role2,
			String uscr, String devg) {
		esperarElemento(driver.findElement(By.cssSelector("[ng-click='vm.goEdit()']")));
		driver.findElement(By.cssSelector("[ng-click='vm.goEdit()']")).click();
		esperarElemento(fullName);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fullName.clear();
		fullName.sendKeys(usrgr);
		description.clear();
		description.sendKeys(descr);
		role.click();
		new Select(role).selectByVisibleText(role2);
		driver.findElement(By.cssSelector("option[value=\"ADMIN\"]")).click();
		// select user div
		WebElement elUsers = driver.findElement(By.name("Users"));
		Select oSelect = new Select(elUsers.findElement(By
				.id("bootstrap-duallistbox-nonselected-list_")));
		oSelect.selectByValue(uscr);
		elUsers.findElement(By.cssSelector("[class='btn move btn-default']"))
				.click();
		// select device groups div
		WebElement elDeviceG = driver.findElement(By.name("Device groups"));
		Select oSelect2 = new Select(elDeviceG.findElement(By
				.id("bootstrap-duallistbox-nonselected-list_")));
		oSelect2.selectByValue(devg);
		elDeviceG.findElement(By.cssSelector("[class='btn move btn-default']"))
				.click();
		// click Save
		WebElement save = driver.findElement(By
				.cssSelector("[ng-click='vm.save()']"));
		save.click();
		
	}

}
