package org.seleniumtm.po.devices;

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

public class DeviceGroupPO {

	protected WebDriver driver;
	private Wait<WebDriver> fluentWait;

	@FindBy(id = "inputName")
	private WebElement inputName;

	@FindBy(id = "inputDescription")
	private WebElement inputDescription;

	@FindBy(id = "inputHostname")
	private WebElement hostname;

	@FindBy(id = "search-table")
	private WebElement searchtable;

	@FindBy(id = "address1")
	private WebElement address1;

	@FindBy(id = "address2")
	private WebElement address2;

	@FindBy(id = "city")
	private WebElement city;

	@FindBy(id = "stateProvince")
	private WebElement stateProvince;

	@FindBy(id = "postCode")
	private WebElement postCode;

	@FindBy(id = "uniqueId")
	private WebElement uniqueId;

	public DeviceGroupPO(WebDriver driver) {
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

	public void insertDeviceGroup(String devgp, String descr, String parent,
			String[] templates) {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		uniqueId.click();
		uniqueId.sendKeys(devgp);
		esperarElemento(inputName);
		inputName.click();
		inputName.clear();
		inputName.sendKeys(devgp);
		inputDescription.clear();
		inputDescription.sendKeys(descr);
		// device group
		WebElement eldiv = driver.findElement(By
				.cssSelector("[class='ui-select-match ng-scope']"));
		eldiv.findElement(
				By.cssSelector("[class='btn btn-default form-control ui-select-toggle']"))
				.click();
		WebElement search = driver.findElement(By
				.cssSelector("[ng-model='$select.search'"));
		esperarElemento(search);
		search.click();
		search.sendKeys(parent);
		driver.findElement(By.cssSelector("[class='ui-select-highlight']"))
				.click();
		WebElement intemplates = driver.findElement(By
				.cssSelector("[ng-model='vm.settings.filterNonSelected']"));

		// fill the templates
		for (String string : templates) {
			intemplates.clear();
			intemplates.sendKeys(string);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			Select oSelect2 = new Select(driver.findElement(By
					.id("bootstrap-duallistbox-nonselected-list_")));
			for (int j = 0; j < oSelect2.getOptions().size(); j++) {
				if (oSelect2.getOptions().get(j).getText().equals(string)) {
					oSelect2.selectByValue(string);
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					oSelect2.selectByIndex(0);
					driver.findElement(
							By.cssSelector("[class='btn move btn-default']"))
							.click();
				}

			}

		}
		WebElement submit = driver.findElement(By
				.cssSelector("[form='deviceGroupForm']"));
		submit.click();

	}

	public String getDeviceGroupName() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return inputName.getAttribute("value");

	}

	public void editDeviceGroup(String devgp, String descr, String parent,
			String[] templates) {
		esperarElemento(driver.findElement(By
				.cssSelector("[ng-click='vm.goEdit()']")));
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.cssSelector("[ng-click='vm.goEdit()']")).click();
		esperarElemento(inputName);
		inputName.clear();
		inputName.sendKeys(devgp);
		inputDescription.clear();
		inputDescription.sendKeys(descr);
		// device group
		WebElement eldiv = driver.findElement(By
				.cssSelector("[class='ui-select-match ng-scope']"));
		eldiv.findElement(
				By.cssSelector("[class='btn btn-default form-control ui-select-toggle']"))
				.click();
		WebElement search = driver.findElement(By
				.cssSelector("[ng-model='$select.search'"));
		esperarElemento(search);
		search.clear();
		search.click();
		search.sendKeys(parent);
		driver.findElement(By.cssSelector("[class='ui-select-highlight']"))
				.click();
		driver.findElement(
				By.cssSelector("[class='btn removeall btn-default']")).click();

		WebElement intemplates = driver.findElement(By
				.cssSelector("[ng-model='vm.settings.filterNonSelected']"));

		// fill the templates
		for (String string : templates) {
			intemplates.clear();
			intemplates.sendKeys(string);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			Select oSelect2 = new Select(driver.findElement(By
					.id("bootstrap-duallistbox-nonselected-list_")));
			for (int j = 0; j < oSelect2.getOptions().size(); j++) {
				if (oSelect2.getOptions().get(j).getText().equals(string)) {
					oSelect2.selectByValue(string);
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					oSelect2.selectByIndex(0);
					driver.findElement(
							By.cssSelector("[class='btn move btn-default']"))
							.click();
				}

			}

		}

		WebElement element = driver.findElement(By
				.cssSelector("[ng-click='vm.save()']"));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);

	}

	public void insertDeviceLocation(String deviceg, String descr,
			String parent, String[] templates, String address12,
			String address22, String city2, String state, String postal,
			String country) {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		esperarElemento(uniqueId);
		uniqueId.click();
		uniqueId.sendKeys(deviceg);
		esperarElemento(inputName);
		inputName.click();
		inputName.clear();
		inputName.sendKeys(deviceg);
		inputDescription.clear();
		inputDescription.sendKeys(descr);
		esperarElemento(address1);
		address1.click();
		address1.sendKeys(address12);
		address2.click();
		address2.sendKeys(address22);
		city.click();
		city.sendKeys(city2);
		stateProvince.click();
		stateProvince.sendKeys(state);
		postCode.click();
		postCode.sendKeys(postal);
		//country
		List<WebElement> divcountr = driver.findElements(By
				.cssSelector("[class='ui-select-match ng-scope']"));
		// device group
		WebElement eldiv = driver.findElement(By
				.cssSelector("[class='ui-select-match ng-scope']"));
		eldiv.findElement(
				By.cssSelector("[class='btn btn-default form-control ui-select-toggle']"))
				.click();
		WebElement search = driver.findElement(By
				.cssSelector("[ng-model='$select.search'"));
		esperarElemento(search);
		search.click();
		search.sendKeys(parent);
		driver.findElement(By.cssSelector("[class='ui-select-highlight']"))
				.click();
		WebElement intemplates = driver.findElement(By
				.cssSelector("[ng-model='vm.settings.filterNonSelected']"));
		//country 
		
		// fill the templates
		for (String string : templates) {
			intemplates.clear();
			intemplates.sendKeys(string);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			Select oSelect2 = new Select(driver.findElement(By
					.id("bootstrap-duallistbox-nonselected-list_")));
			for (int j = 0; j < oSelect2.getOptions().size(); j++) {
				if (oSelect2.getOptions().get(j).getText().equals(string)) {
					oSelect2.selectByValue(string);
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					oSelect2.selectByIndex(0);
					driver.findElement(
							By.cssSelector("[class='btn move btn-default']"))
							.click();
				}

			}

		}

		WebElement element = driver.findElement(By
				.cssSelector("[form='deviceGroupForm']"));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);

	}

	public String verifyIdSpaces(String deviceg) {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		esperarElemento(uniqueId);
		uniqueId.click();
		uniqueId.sendKeys(deviceg);
		esperarElemento(inputName);
		inputName.click();
		List<WebElement> div = driver.findElements(By
				.cssSelector(("[class='list-unstyled']")));
		WebElement li = div.get(1).findElement(By.tagName("li"));
		return li.getText();
	}

	

	public String verifyUniqueId(String deviceg, String descr,
			String parent, String[] templates, String address12,
			String address22, String city2, String state, String postal,
			String country) {
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		esperarElemento(uniqueId);
		uniqueId.click();
		uniqueId.sendKeys(deviceg);
		esperarElemento(inputName);
		inputName.click();
		inputName.clear();
		inputName.sendKeys(deviceg);
		inputDescription.clear();
		inputDescription.sendKeys(descr);
		esperarElemento(address1);
		address1.click();
		address1.sendKeys(address12);
		address2.click();
		address2.sendKeys(address22);
		city.click();
		city.sendKeys(city2);
		stateProvince.click();
		stateProvince.sendKeys(state);
		postCode.click();
		postCode.sendKeys(postal);
		//country
		List<WebElement> divcountr = driver.findElements(By
				.cssSelector("[class='ui-select-match ng-scope']"));
		// device group
		WebElement eldiv = driver.findElement(By
				.cssSelector("[class='ui-select-match ng-scope']"));
		eldiv.findElement(
				By.cssSelector("[class='btn btn-default form-control ui-select-toggle']"))
				.click();
		WebElement search = driver.findElement(By
				.cssSelector("[ng-model='$select.search'"));
		esperarElemento(search);
		search.click();
		search.sendKeys(parent);
		driver.findElement(By.cssSelector("[class='ui-select-highlight']"))
				.click();
		WebElement intemplates = driver.findElement(By
				.cssSelector("[ng-model='vm.settings.filterNonSelected']"));
		//country 
		
		// fill the templates
		for (String string : templates) {
			intemplates.clear();
			intemplates.sendKeys(string);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			Select oSelect2 = new Select(driver.findElement(By
					.id("bootstrap-duallistbox-nonselected-list_")));
			for (int j = 0; j < oSelect2.getOptions().size(); j++) {
				if (oSelect2.getOptions().get(j).getText().equals(string)) {
					oSelect2.selectByValue(string);
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					oSelect2.selectByIndex(0);
					driver.findElement(
							By.cssSelector("[class='btn move btn-default']"))
							.click();
				}

			}

		}

		WebElement element = driver.findElement(By
				.cssSelector("[form='deviceGroupForm']"));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
		WebElement msg = driver.findElement(By.className("[class='toast ng-scope toast-error']"));
		return null;
	}
}
