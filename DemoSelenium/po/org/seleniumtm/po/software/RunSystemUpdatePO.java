package org.seleniumtm.po.software;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class RunSystemUpdatePO {

	protected WebDriver driver;
	private Wait<WebDriver> fluentWait;

	@FindBy(id = "inputName")
	private WebElement inputName;

	@FindBy(id = "inputDescription")
	private WebElement inputDescription;

	@FindBy(id = "inputVersion")
	private WebElement inputVersion;

	@FindBy(name = "systemFile")
	private WebElement systemFile;
	
	@FindBy(id = "search-table")
	private WebElement searchtable;

	public RunSystemUpdatePO(WebDriver driver) {
		this.driver = driver;
		this.fluentWait = new FluentWait<WebDriver>(driver).withTimeout(60,
				TimeUnit.SECONDS).pollingEvery(100, TimeUnit.MILLISECONDS);
	}

	private void esperarElemento(WebElement elemento) {
		fluentWait.until(ExpectedConditions.visibilityOf(elemento));
	}

	public void createSystemUpdate(String name, String filezip, String descrp,
			String deviceTy, String version, boolean released) {
		esperarElemento(driver.findElement(By.cssSelector("i.fa.fa-plus")));
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.cssSelector("i.fa.fa-plus")).click();
		esperarElemento(systemFile);
		systemFile.clear();
		systemFile.sendKeys(filezip);
		inputName.clear();
		inputName.sendKeys(name);
		inputDescription.clear();
		inputDescription.sendKeys(descrp);
		inputVersion.clear();
		inputVersion.sendKeys(version);
		if (released) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			driver.findElement(By.id("released")).click();
		}
		// device type
		WebElement div = driver.findElement(By.name("deviceTypes"));
		WebElement inpt = div.findElement(By.tagName("input"));
		inpt.click();
		driver.findElement(By.cssSelector("div.ng-binding.ng-scope")).click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement submit = driver.findElement(By
				.cssSelector("[form='systemUpdateForm']"));
		submit.click();
	}

	public void editSystemUpdate(String name, String descr, String devictyp) {
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
		inputName.sendKeys(name);
		inputDescription.clear();
		inputDescription.sendKeys(descr);
		// change device type
		driver.findElement(By.cssSelector("span.close.ui-select-match-close"))
				.click();
		// verify
		WebElement div = driver.findElement(By.name("deviceTypes"));
		WebElement inpt = div.findElement(By.tagName("input"));
		inpt.click();
		inpt.sendKeys(devictyp);
		driver.findElement(By.cssSelector("div.ng-binding.ng-scope")).click();
		WebElement submit = driver.findElement(By
				.cssSelector("[form='systemUpdateForm']"));
		submit.click();
	}

	public void deleteSystmeupdate(String name) {
		esperarElemento(searchtable);
		searchtable.clear();
		searchtable.sendKeys(name);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement table_element = driver.findElement(By.id("tableBuilder"));
		List<WebElement> tr_collection = table_element.findElements(By
				.tagName("tr"));
		for (WebElement trElement : tr_collection) {
			List<WebElement> td_collection = trElement.findElements(By
					.tagName("td"));
			for (WebElement tdElement : td_collection) {
				// WebElement el = tdElement.findElement(By.tagName("a"));
				List<WebElement> a_collection = trElement.findElements(By
						.tagName("a"));
				for (WebElement aelement : a_collection) {
					if (aelement.getText().equals(name)) {
						tdElement.click();
						driver.findElement(
								By.cssSelector("[class='btn btn-default btn-sm dropdown-toggle']"))
								.click();
						driver.findElement(
								By.cssSelector("[data-target='#delete-modal']"))
								.click();
						WebElement botoneliminar = driver.findElement(By
								.cssSelector("[ng-click='vm.deleteSystemUpdate()']"));
						esperarElemento(botoneliminar);
						try {
							Thread.sleep(3000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						botoneliminar.click();
						System.out.println("System update:" + name + "deteled.");
						return;
					}

				}
			}
		}
		
	}

}
