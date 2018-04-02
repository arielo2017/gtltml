package org.seleniumtm.po.setup;

import java.util.ArrayList;
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

public class SettingOptionPO {

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

	@FindBy(id = "inputConfigKey")
	private WebElement inputConfigKey;

	@FindBy(id = "inputDescription")
	private WebElement inputDescription;

	public SettingOptionPO(WebDriver driver) {
		this.driver = driver;
		this.fluentWait = new FluentWait<WebDriver>(driver).withTimeout(60,
				TimeUnit.SECONDS).pollingEvery(100, TimeUnit.MILLISECONDS);
	}

	private void esperarElemento(WebElement elemento) {
		fluentWait.until(ExpectedConditions.visibilityOf(elemento));
	}

	


	public void insertSetting(String configk, String descr, boolean devlvl,
			String types, String devictype) {
		inputConfigKey.sendKeys(configk);
		inputDescription.sendKeys(descr);
		new Select(driver.findElement(By.id("setting-option-types")))
				.selectByVisibleText(types);
		WebElement search = driver.findElement(By
				.cssSelector("[ng-click='$select.activate()']"));
		search.click();
		search.sendKeys(devictype);
		List<WebElement> devices=driver.findElements(By.id("ui-select-choices-0"));
		for (WebElement webElement : devices) {
			WebElement ele=driver.findElement(By.cssSelector("[class='ui-select-choices-row-inner']"));
			
			if(ele.getText().equals(devictype))
			{
				ele.click();
				break;
			}
		}
		driver.findElement(By.cssSelector("[ng-click='vm.save()']")).click();
	}

	public void editSetting(String descrnew, boolean devlvl,
			String types, String devictype) {
		esperarElemento(driver.findElement(By.cssSelector("[ng-click='vm.goEdit()']")));
		driver.findElement(By.cssSelector("[ng-click='vm.goEdit()']")).click();
		esperarElemento(inputDescription);
		inputDescription.sendKeys(descrnew);
		new Select(driver.findElement(By.id("setting-option-types")))
				.selectByVisibleText(types);
		WebElement search = driver.findElement(By
				.cssSelector("[ng-click='$select.activate()']"));
		search.click();
		search.sendKeys(devictype);
		List<WebElement> devices=driver.findElements(By.id("ui-select-choices-0"));
		for (WebElement webElement : devices) {
			WebElement ele=driver.findElement(By.cssSelector("[class='ui-select-choices-row-inner']"));
			
			if(ele.getText().equals(devictype))
			{
				ele.click();
				break;
			}
		}
		driver.findElement(By.cssSelector("[ng-click='vm.save()']")).click();
		
	}

}
