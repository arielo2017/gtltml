package org.seleniumtm.po;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class LoginPO {
	
	protected WebDriver driver;
	private Wait<WebDriver> fluentWait;
	
	public LoginPO(WebDriver driver) {
		this.driver = driver;
		this.fluentWait = new FluentWait<WebDriver>(driver)
			    .withTimeout(60, TimeUnit.SECONDS)
			    .pollingEvery(100, TimeUnit.MILLISECONDS);
	}
	
	private void esperarElemento(WebElement elemento) {
		fluentWait.until(ExpectedConditions.visibilityOf(elemento));
	}
	
	@FindBy(id= "username")
	private WebElement ingresarNombreUsuario;
	
	@FindBy(id= "password")
	private WebElement ingresarContraseña;
	
	
	@FindBy(tagName = "button")
	private WebElement botonIngresar;
	
	
	@FindBy(id= "error-message")
	private WebElement errorMensaje;
	
	@FindBy(linkText = "Users")
	private WebElement userLink;

	@FindBy(linkText = "Devices")
	private WebElement deviceLink;
	

	
	public void login(String nombreUsuario,String password) {
		ingresarNombreUsuario.sendKeys(nombreUsuario);
		ingresarContraseña.sendKeys(password);
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		botonIngresar.click();
		
	}
	
	public void waitForAjax(int timeoutInSeconds) {
		System.out.println("Checking active ajax calls by calling jquery.active");
		try {
		if (driver instanceof JavascriptExecutor) {
		       JavascriptExecutor jsDriver = (JavascriptExecutor)driver;

		for (int i = 0; i< timeoutInSeconds; i++) 
		    {
		       Object numberOfAjaxConnections = jsDriver.executeScript("return jQuery.active");
		       // return should be a number
		       if (numberOfAjaxConnections instanceof Long) {
		       Long n = (Long)numberOfAjaxConnections;
		       System.out.println("Number of active jquery ajax calls: " + n);
		       if (n.longValue() == 0L)
		       break;
		     }
		     Thread.sleep(1000);
		     }
		}
		     else {
		           System.out.println("Web driver: " + driver + " cannot execute javascript");
		           }
		}
		          catch (InterruptedException e) {
		          System.out.println(e);
		          }
		}
	
	public String getLoginError()
	{
		WebElement error = driver.findElement(By.id("error-message"));
		esperarElemento(error);
		return error.getText();
	}

	public void clickOnUserGroups() {
		userLink.click();
		WebElement group = driver.findElement(By
				.cssSelector("[ui-sref='usergroups.list']"));
		esperarElemento(group);
		group.click();

	}
}
