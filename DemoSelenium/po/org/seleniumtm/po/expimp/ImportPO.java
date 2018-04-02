package org.seleniumtm.po.expimp;


import java.util.concurrent.TimeUnit;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import org.openqa.selenium.support.ui.Wait;

public class ImportPO {

	protected WebDriver driver;
	private Wait<WebDriver> fluentWait;
	@FindBy(id = "inputUniqueId")
	private WebElement inputUniqueId;

	@FindBy(linkText = "Add")
	private WebElement add;

	public ImportPO(WebDriver driver) {
		this.driver = driver;
		this.fluentWait = new FluentWait<WebDriver>(driver).withTimeout(60,
				TimeUnit.SECONDS).pollingEvery(100, TimeUnit.MILLISECONDS);
	}

	private void esperarElemento(WebElement elemento) {
		fluentWait.until(ExpectedConditions.visibilityOf(elemento));
	}




}
