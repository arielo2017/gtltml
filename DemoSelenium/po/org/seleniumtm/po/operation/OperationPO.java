package org.seleniumtm.po.operation;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class OperationPO {

	protected WebDriver driver;
	private Wait<WebDriver> fluentWait;
	
	@FindBy(id = "search-table")
	private WebElement searchtable;
	
	@FindBy(id = "tableBuilder")
	private WebElement searchtableoperation;

	
	

	public OperationPO(WebDriver driver) {
		this.driver = driver;
		this.fluentWait = new FluentWait<WebDriver>(driver).withTimeout(60,
				TimeUnit.SECONDS).pollingEvery(100, TimeUnit.MILLISECONDS);
	}

	private void esperarElemento(WebElement elemento) {
		fluentWait.until(ExpectedConditions.visibilityOf(elemento));
	}

	public String getDeviceOperationOnO(String namedev,int td) {

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			// find

			List<WebElement> tr_collection = searchtableoperation
					.findElements(By.tagName("tr"));
			for (WebElement trElement : tr_collection) {
				List<WebElement> td_collection = trElement.findElements(By
						.tagName("td"));
				for (WebElement tdElement : td_collection) {
					List<WebElement> clickColl = driver
							.findElements(By
									.cssSelector("[ng-click='vm.goToObjectDetails(row)']"));
					for (WebElement webElement : clickColl) {
						// if (webElement.getText().equals(namedev)) {
						tdElement.click();
						return tdElement.getText();
					
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

}
