package org.seleniumtm.po;

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

public class SetPasswordPO {

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

	public SetPasswordPO(WebDriver driver) {
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


}
