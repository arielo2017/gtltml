package org.seleniumtm.po.users;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.sun.enterprise.config.serverbeans.SystemApplications;

public class UserCreatePO {

	protected WebDriver driver;
	private Wait<WebDriver> fluentWait;
	@FindBy(id = "inputfullName")
	private WebElement fullName;

	@FindBy(id = "inputUsername")
	private WebElement username;

	@FindBy(id = "inputEmail")
	private WebElement email;

	@FindBy(id = "inputPassword")
	private WebElement password;

	@FindBy(id = "isActiveDirectory")
	private WebElement actvD;

	@FindBy(id = "search-table")
	private WebElement searchtable;

	public UserCreatePO(WebDriver driver) {
		this.driver = driver;
		this.fluentWait = new FluentWait<WebDriver>(driver).withTimeout(60,
				TimeUnit.SECONDS).pollingEvery(100, TimeUnit.MILLISECONDS);
	}

	private void esperarElemento(WebElement elemento) {
		fluentWait.until(ExpectedConditions.visibilityOf(elemento));
	}

	public void createUser(String name, String usern, String pass, String em,
			boolean actv, String usg) {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		esperarElemento(fullName);
		fullName.clear();
		fullName.sendKeys(name);
		username.clear();
		username.sendKeys(usern);
		password.click();
		password.clear();
		password.sendKeys(pass);
		email.clear();
		email.sendKeys(em);
		
		// user Group
		WebElement search = driver.findElement(By
				.cssSelector("[ng-click='$select.activate()']"));
		search.click();
		search.sendKeys(usg);
		waitForAjax(10);
		driver.findElement(By.cssSelector("span.ui-select-choices-row-inner"))
				.click();
		
		// actvD.click();

		WebElement save = driver.findElement(By
				.cssSelector("[ng-click='vm.save()']"));
		// driver.findElement(By.xpath("//button[@type='submit']")).click();
		esperarElemento(save);
		save.click();
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

	public String findUsuario(String user) {

		driver.findElement(By.id("search-table")).sendKeys(user);
		String text = driver.findElement(By.linkText(user)).getText();
		return text;
	}

	public String clickViewUSer(String user) {

		WebElement e = driver.findElement(By
				.cssSelector("[class='form-control']"));
		esperarElemento(e);
		e.sendKeys(user);
		waitForAjax(5);
		String text = driver.findElement(By.linkText(user)).getText();

		return text;

	}

	public void deleteUser(String userCr) {
		driver.findElement(By.id("search-table")).sendKeys(userCr);
		WebElement table_element = driver.findElement(By.id("tableBuilder"));

		List<WebElement> tr_collection = table_element.findElements(By
				.tagName("tr"));
		for (WebElement tr : tr_collection) {
			List<WebElement> TDCollection = tr.findElements(By.tagName("td"));
			for (WebElement td : TDCollection) {
				WebElement el = td.findElement(By.tagName("a"));
				if (userCr.equals(el.getText())) {
					// user find
					td.click();
				}
			}
		}

	}

	public void editUser(String userCr, String userNamCr, String email2,
			String usgCR,String passw) {
		esperarElemento(driver.findElement(By
				.cssSelector("[ng-click='vm.goEdit()']")));
		driver.findElement(By.cssSelector("[ng-click='vm.goEdit()']")).click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		esperarElemento(fullName);
		fullName.clear();
		fullName.sendKeys(userCr);
		username.clear();
		username.sendKeys(userCr);
		esperarElemento(driver.findElement(By.cssSelector("[class='ng-pristine ng-untouched ng-valid ng-empty']")));
		driver.findElement(By.cssSelector("[class='ng-pristine ng-untouched ng-valid ng-empty']")).click();
		esperarElemento(password);
		password.click();
		password.clear();
		password.sendKeys(passw);
		
		email.clear();
		email.sendKeys(email2);

		// user Group
		/*WebElement search = driver.findElement(By
				.cssSelector("[ng-click='$select.activate()']"));
		search.click();
		search.sendKeys(userNamCr);*/
		waitForAjax(10);
		/*driver.findElement(By.cssSelector("span.ui-select-choices-row-inner"))
				.click();*/


		WebElement save = driver.findElement(By
				.cssSelector("[ng-click='vm.save()']"));
		// driver.findElement(By.xpath("//button[@type='submit']")).click();
		esperarElemento(save);
		save.click();
	}

	public String getFirstValueTable(String usgr) {

		esperarElemento(searchtable);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		searchtable.clear();
		searchtable.sendKeys(usgr);

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

	public void logout() {
		
		WebElement div=driver.findElement(By.cssSelector("[class='nav navbar-top-links navbar-right']"));
		div.findElement(By.cssSelector("[ng-click='main.logout()']")).click();
		
	}
}
