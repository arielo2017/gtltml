package org.seleniumtm.po.software;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

public class AppPO {

	protected WebDriver driver;
	private Wait<WebDriver> fluentWait;

	@FindBy(name = "appFile")
	private WebElement file;

	@FindBy(id = "inputName")
	private WebElement inputName;

	@FindBy(id = "inputDescription")
	private WebElement inputDescription;

	@FindBy(id = "inputVersionNotes")
	private WebElement inputVersionNotes;

	@FindBy(id = "search-table")
	private WebElement searchtable;

	public AppPO(WebDriver driver) {
		this.driver = driver;
		this.fluentWait = new FluentWait<WebDriver>(driver).withTimeout(60,
				TimeUnit.SECONDS).pollingEvery(100, TimeUnit.MILLISECONDS);
	}

	private void esperarElemento(WebElement elemento) {
		fluentWait.until(ExpectedConditions.visibilityOf(elemento));
	}

	public String createApp(String nameFile, String appcr, String descr,
			String devictyp, String notes) {
		esperarElemento(file);
		// file.click();
		file.clear();
		file.sendKeys(nameFile);
		inputName.clear();
		inputName.sendKeys(appcr);
		inputDescription.clear();
		inputDescription.sendKeys(descr);
		inputVersionNotes.click();
		inputVersionNotes.clear();
		inputVersionNotes.sendKeys(notes);
		WebElement devtype2 = driver.findElement(By
				.cssSelector("[ng-model='$select.search']"));
		esperarElemento(devtype2);
		devtype2.clear();
		devtype2.sendKeys(devictyp);
		driver.findElement(By.cssSelector("div.ng-binding.ng-scope")).click();
		WebElement submit = driver.findElement(By
				.cssSelector("[ng-click='vm.save()']"));
		submit.click();
		try {

			return getFirstValueTable(appcr);
		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}

	}

	public String getFirstValueTable(String app) {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
			return null;
		}
		esperarElemento(searchtable);
		searchtable.clear();
		searchtable.sendKeys(app);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
			return null;
		}
		String text = null;
		try {
			WebElement first = driver.findElement(By
					.cssSelector("[ng-click='vm.goToObjectDetails(row)']"));
			text = first.getText();
		} catch (Exception e) {
			// e.printStackTrace();
			return null;
		}
		return text;
	}

	public void editApp(String appcr, String descr,
			String devictyp) {
		esperarElemento(driver.findElement(By
				.cssSelector("[ng-click='vm.goEdit()']")));
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		driver.findElement(By.cssSelector("[ng-click='vm.goEdit()']")).click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {

			e1.printStackTrace();
		}
		inputName.clear();
		inputName.sendKeys(appcr);
		inputDescription.clear();
		inputDescription.sendKeys(descr);
		// delete device type
		driver.findElement(By.cssSelector("span.close.ui-select-match-close")).click();
		driver.findElement(By.cssSelector("div.ibox-content")).click();
		WebElement devtype2 = driver.findElement(By
				.cssSelector("[ng-model='$select.search']"));
		devtype2.clear();
		esperarElemento(devtype2);
		devtype2.click();
		devtype2.sendKeys(devictyp);
		driver.findElement(By.cssSelector("span.ui-select-choices-row-inner")).click();
	    driver.findElement(By.id("page-wrapper")).click();
	    WebElement submit = driver.findElement(By
				.cssSelector("[form='appForm']"));
		submit.click();
		
	}

}
