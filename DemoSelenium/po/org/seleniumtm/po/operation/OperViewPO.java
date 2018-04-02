package org.seleniumtm.po.operation;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class OperViewPO {

	protected WebDriver driver;
	private Wait<WebDriver> fluentWait;

	@FindBy(id = "search-table")
	private WebElement searchtable;

	@FindBy(id = "tableBuilder")
	private WebElement searchtableoperation;

	public OperViewPO(WebDriver driver) {
		this.driver = driver;
		this.fluentWait = new FluentWait<WebDriver>(driver).withTimeout(60,
				TimeUnit.SECONDS).pollingEvery(100, TimeUnit.MILLISECONDS);
	}

	private void esperarElemento(WebElement elemento) {
		fluentWait.until(ExpectedConditions.visibilityOf(elemento));
	}

	public String getDeviceOperationOnO(String namedev, int i) {

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			WebElement tdElement;
			// find
			List<WebElement> tr_collection = searchtableoperation
					.findElements(By.tagName("tr"));
			WebElement test = tr_collection.get(i);
			List<WebElement> coltest = test.findElements(By.tagName("td"));

			List<WebElement> clickColl = driver.findElements(By
					.cssSelector("[ng-click='vm.goToObjectDetails(row)']"));
			tdElement = coltest.get(0);
			tdElement.click();
			// verify the device
			tdElement.findElement(
					By.cssSelector("[ng-click='vm.goToObjectDetails(row)']"))
					.click();
			WebElement appliesto = driver.findElement(By.id("appliesTo"));
			if (namedev.equals(appliesto.getAttribute("value"))) {
				return appliesto.getAttribute("value");
			} else {
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void clickOnOperationLink() {

		driver.findElement(By.linkText("Operations")).click();

	}

}
