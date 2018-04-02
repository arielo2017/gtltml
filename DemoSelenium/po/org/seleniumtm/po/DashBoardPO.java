package org.seleniumtm.po;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class DashBoardPO {

	protected WebDriver driver;
	private Wait<WebDriver> fluentWait;

	public DashBoardPO(WebDriver driver) {
		this.driver = driver;
		this.fluentWait = new FluentWait<WebDriver>(driver).withTimeout(500,
				TimeUnit.SECONDS).pollingEvery(500, TimeUnit.MILLISECONDS);
	}

	private void esperarElemento(WebElement elemento) {
		fluentWait.until(ExpectedConditions.visibilityOf(elemento));
	}

	@FindBy(id = "tableBuilder")
	private WebElement searchtableoperation;

	@FindBy(id = "username")
	private WebElement ingresarNombreUsuario;

	@FindBy(id = "password")
	private WebElement ingresarContraseña;

	// change!!!
	@FindBy(tagName = "button")
	private WebElement botonIngresar;

	@FindBy(linkText = "Users")
	private WebElement userLink;

	@FindBy(linkText = "Devices")
	private WebElement deviceLink;

	@FindBy(linkText = "System Setup")
	private WebElement system;

	@FindBy(linkText = "Software")
	private WebElement softwareLink;

	@FindBy(linkText = "Operations")
	private WebElement operLink;

	@FindBy(linkText = "Add")
	private WebElement add;

	@FindBy(id = "search-table")
	private WebElement searchtable;

	@FindBy(id = "devicesTable")
	private WebElement searchtabledevice;

	@FindBy(id = "inputName")
	private WebElement inputName;

	public String verificarLogOut() {
		WebElement LogOut = driver.findElement(By
				.cssSelector("[class='font-bold ng-binding']"));
		String text = LogOut.getText();
		return text;
	}

	public void clikcOnUser() {
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//esperarElemento(userLink);
		waitForAjax(50);
		userLink.click();
		WebElement userlink = driver.findElement(By
				.cssSelector("[ui-sref='users.list']"));
		esperarElemento(userlink);
		userlink.click();

	}

	public String getFirstValueTable(String usgr) {
		waitForAjax(10);
		searchtable.clear();
		searchtable.sendKeys(usgr);
		esperarElemento(searchtable);
		waitForAjax(10);
		String text = null;
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			WebElement first = driver.findElement(By
					.cssSelector("[ng-click='vm.goToObjectDetails(row)']"));
			esperarElemento(first);
			text = first.getText();
		} catch (Exception e) {

			return null;
		}
		
		return text;
	}

	public void clickCreate() {

		esperarElemento(add);
		add.click();
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

	public void clickOnUserGroups() {
		waitForAjax(20);
		userLink.click();
		WebElement group = driver.findElement(By
				.cssSelector("[ui-sref='usergroups.list']"));
		esperarElemento(group);
		group.click();

	}

	public void clikcOnDevices() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		esperarElemento(deviceLink);

		deviceLink.click();
		WebElement devices = driver.findElement(By
				.cssSelector("[ui-sref='devices.list']"));
		esperarElemento(devices);
		devices.click();
	}

	public void deleteUser(String usrgr) {
		esperarElemento(searchtable);
		searchtable.clear();
		searchtable.sendKeys(usrgr);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			// find
			WebElement table_element = driver
					.findElement(By.id("tableBuilder"));
			List<WebElement> tr_collection = table_element.findElements(By
					.tagName("tr"));
			for (WebElement trElement : tr_collection) {
				List<WebElement> td_collection = trElement.findElements(By
						.tagName("td"));
				for (WebElement tdElement : td_collection) {
					List<WebElement> clickColl = driver
							.findElements(By
									.cssSelector("[ng-click='vm.goToObjectDetails(row)']"));
					for (WebElement webElement : clickColl) {
						if (webElement.getText().equals(usrgr)) {
							// found delete it
							tdElement.click();
							WebElement opt = driver
									.findElement(By
											.cssSelector("[class='btn btn-default btn-sm dropdown-toggle']"));
							esperarElemento(opt);
							opt.click();
							WebElement del = driver
									.findElement(By
											.cssSelector("[data-target='#delete-modal']"));
							del.click();
							try {
								Thread.sleep(5000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							WebElement delbtn = driver
									.findElement(By
											.cssSelector("[ng-click='vm.deleteUser()']"));
							esperarElemento(delbtn);
							delbtn.click();
							return;
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String deleteUserGroup(String usrgr) {
		esperarElemento(searchtable);
		searchtable.clear();
		searchtable.sendKeys(usrgr);
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
					if (aelement.getText().equals(usrgr)) {
						tdElement.click();
						driver.findElement(
								By.cssSelector("[class='btn btn-default btn-sm dropdown-toggle']"))
								.click();
						driver.findElement(
								By.cssSelector("[data-target='#delete-modal']"))
								.click();
						WebElement botoneliminar = driver
								.findElement(By
										.cssSelector("[ng-click='vm.deleteUserGroup()']"));
						esperarElemento(botoneliminar);
						try {
							Thread.sleep(3000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						botoneliminar.click();
						return usrgr;
					}

				}
			}
		}
		return null;

	}

	public void clikcOnSettings() {
		system.click();
		WebElement settings = driver.findElement(By
				.cssSelector("[ui-sref='settingoptions.list']"));
		esperarElemento(settings);
		settings.click();

	}

	public void clickOnElement(String usrgr) {
		esperarElemento(searchtable);
		searchtable.clear();
		searchtable.sendKeys(usrgr);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			// find
			WebElement table_element = driver
					.findElement(By.id("tableBuilder"));
			List<WebElement> tr_collection = table_element.findElements(By
					.tagName("tr"));
			for (WebElement trElement : tr_collection) {
				List<WebElement> td_collection = trElement.findElements(By
						.tagName("td"));
				for (WebElement tdElement : td_collection) {
					List<WebElement> clickColl = driver
							.findElements(By
									.cssSelector("[ng-click='vm.goToObjectDetails(row)']"));
					for (WebElement webElement : clickColl) {
						if (webElement.getText().equals(usrgr)) {
							webElement.click();
							return;
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void clickOnElementDevice(String usrgr) {
		esperarElemento(searchtable);
		searchtable.clear();
		searchtable.sendKeys(usrgr);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			// find
			WebElement table_element = driver
					.findElement(By.id("devicesTable"));
			List<WebElement> tr_collection = table_element.findElements(By
					.tagName("tr"));
			for (WebElement trElement : tr_collection) {
				List<WebElement> td_collection = trElement.findElements(By
						.tagName("td"));
				for (WebElement tdElement : td_collection) {
					List<WebElement> clickColl = driver
							.findElements(By
									.cssSelector("[ng-click='vm.goToObjectDetails(row)']"));
					for (WebElement webElement : clickColl) {
						if (webElement.getText().equals(usrgr)) {
							webElement.click();
							return;
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String deleteSetting(String sett) {
		searchtable.sendKeys(sett);
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
					if (aelement.getText().equals(sett)) {
						tdElement.click();
						driver.findElement(
								By.cssSelector("[class='btn btn-default btn-sm dropdown-toggle']"))
								.click();
						driver.findElement(
								By.cssSelector("[data-target='#delete-modal']"))
								.click();
						WebElement botoneliminar = driver
								.findElement(By
										.cssSelector("[ng-click='vm.deleteSettingOption()']"));
						esperarElemento(botoneliminar);
						try {
							Thread.sleep(3000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						botoneliminar.click();
						return sett;
					}

				}
			}
		}
		return null;
	}

	public String getNameDevice(String uniqid) {
		esperarElemento(searchtable);
		searchtable.clear();
		searchtable.sendKeys(uniqid);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			// find
			WebElement table_element = driver
					.findElement(By.id("devicesTable"));
			List<WebElement> tr_collection = table_element.findElements(By
					.tagName("tr"));
			for (WebElement trElement : tr_collection) {
				List<WebElement> td_collection = trElement.findElements(By
						.tagName("td"));
				for (WebElement tdElement : td_collection) {
					List<WebElement> clickColl = driver
							.findElements(By
									.cssSelector("[ng-click='vm.goToObjectDetails(row)']"));
					for (WebElement webElement : clickColl) {
						if (webElement.getText().equals(uniqid)) {
							// found return name
							List<WebElement> list = driver.findElements(By
									.cssSelector("[class='ng-binding']"));
							String name = list.get(3).getText();
							return name;
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void deleteDevice(String device) {
		esperarElemento(searchtable);
		searchtable.clear();
		searchtable.sendKeys(device);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement table_element = driver.findElement(By.id("devicesTable"));
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
					if (aelement.getText().equals(device)) {
						tdElement.click();
						driver.findElement(
								By.cssSelector("[class='btn btn-default btn-sm dropdown-toggle']"))
								.click();
						driver.findElement(
								By.cssSelector("[data-target='#delete-modal']"))
								.click();
						WebElement botoneliminar = driver.findElement(By
								.cssSelector("[ng-click='vm.deleteDevice()']"));
						esperarElemento(botoneliminar);
						try {
							Thread.sleep(3000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						botoneliminar.click();
						System.out.println("Device:" + device + "deteled.");
						return;
					}

				}
			}
		}

	}

	public void clickOnTemplates() {
		system.click();
		WebElement settings = driver.findElement(By
				.cssSelector("[ui-sref='template.list']"));
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		esperarElemento(settings);
		settings.click();
	}

	public void clickOnTemplateElement(String templ) {
		esperarElemento(searchtable);
		searchtable.clear();
		searchtable.sendKeys(templ);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			// find
			WebElement table_element = driver.findElement(By
					.id("templatesTable"));
			List<WebElement> tr_collection = table_element.findElements(By
					.tagName("tr"));
			for (WebElement trElement : tr_collection) {
				List<WebElement> td_collection = trElement.findElements(By
						.tagName("td"));
				for (WebElement tdElement : td_collection) {
					List<WebElement> clickColl = driver
							.findElements(By
									.cssSelector("[ng-click='vm.goToObjectDetails(row)']"));
					for (WebElement webElement : clickColl) {
						if (webElement.getText().equals(templ)) {
							webElement.click();
							return;
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void deleteTemplate(String templ) {
		searchtable.sendKeys(templ);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement table_element = driver.findElement(By.id("templatesTable"));
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
					if (aelement.getText().equals(templ)) {
						tdElement.click();
						driver.findElement(
								By.cssSelector("[class='btn btn-default btn-sm dropdown-toggle']"))
								.click();
						driver.findElement(
								By.cssSelector("[data-target='#delete-modal']"))
								.click();
						WebElement botoneliminar = driver
								.findElement(By
										.cssSelector("[ng-click='vm.deleteTemplate()']"));
						esperarElemento(botoneliminar);
						try {
							Thread.sleep(3000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						botoneliminar.click();

					}

				}
			}
		}
	}

	public void clikcOnApp() {
		esperarElemento(softwareLink);
		softwareLink.click();
		WebElement apps = driver.findElement(By
				.cssSelector("[ui-sref='apps.list']"));
		esperarElemento(apps);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		apps.click();

	}

	public void clickOnSystemUp() {
		esperarElemento(softwareLink);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		softwareLink.click();
		driver.findElement(By.linkText("System Updates")).click();

	}

	public void deleteApp(String appcr) {
		searchtable.clear();
		searchtable.sendKeys(appcr);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
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
					if (aelement.getText().equals(appcr)) {
						tdElement.click();
						driver.findElement(
								By.cssSelector("[class='btn btn-default btn-sm dropdown-toggle']"))
								.click();
						driver.findElement(
								By.cssSelector("[data-target='#delete-modal']"))
								.click();
						WebElement botoneliminar = driver.findElement(By
								.cssSelector("[ng-click='vm.deleteApp()']"));
						esperarElemento(botoneliminar);
						try {
							Thread.sleep(3000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						botoneliminar.click();
						return;
					}

				}
			}
		}

	}

	public void clickImportDevices(String file) {
		esperarElemento(driver
				.findElement(By
						.cssSelector("[class='btn btn-default btn-sm dropdown-toggle']")));
		driver.findElement(
				By.cssSelector("[class='btn btn-default btn-sm dropdown-toggle']"))
				.click();
		esperarElemento(driver.findElement(By.linkText("Import")));
		driver.findElement(By.linkText("Import")).click();
		esperarElemento(driver.findElement(By.name("importFile")));
		driver.findElement(By.name("importFile")).clear();
		driver.findElement(By.name("importFile")).sendKeys(file);
		WebElement submit = driver.findElement(By
				.cssSelector("[form='formImport']"));
		esperarElemento(submit);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		submit.click();

	}

	public int verifyDeviceImport(String[] devnamever) {
		// search all and verify
		int devices = 0;
		for (String string : devnamever) {
			// search
			String device = getFirstValueTable(string);
			if (device.equals(string)) {
				devices++;
			}
		}
		return devices;
	}

	public void clikcOnDeviceGroups() {
		esperarElemento(deviceLink);
		deviceLink.click();
		WebElement devices = driver.findElement(By
				.cssSelector("[ui-sref='devicegroups.list']"));
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();

		}
		esperarElemento(devices);
		devices.click();

	}

	public void goDeviceGroupName(String devgp) {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();

		}
		esperarElemento(searchtable);
		searchtable.clear();
		searchtable.sendKeys(devgp);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();

		}

		WebElement first = driver.findElement(By
				.cssSelector("[ng-click='vm.goToObjectDetails(row)']"));
		first.click();
	}

	public void deleteDeviceGroup(String devgp, String reasign) {
		esperarElemento(searchtable);
		searchtable.clear();
		searchtable.sendKeys(devgp);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement table_element = driver.findElement(By
				.id("deviceGroupsTable"));
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
					String[] test = StringUtils.split(aelement.getText(), "|");
					if (test[test.length - 1].trim().equals(devgp)) {
						tdElement.click();
						driver.findElement(
								By.cssSelector("[class='btn btn-default btn-sm dropdown-toggle']"))
								.click();
						driver.findElement(
								By.cssSelector("[data-target='#delete-modal']"))
								.click();
						// reasign
						WebElement eldiv = driver
								.findElement(By
										.cssSelector("[ng-model='vm.deviceGroupToReAssign']"));
						esperarElemento(eldiv);
						WebElement span = eldiv
								.findElement(By
										.cssSelector("[ng-click='$select.activate()']"));

						span.click();
						WebElement inpt = eldiv
								.findElement(By.tagName("input"));
						esperarElemento(inpt);
						inpt.sendKeys(reasign);

						driver.findElement(
								By.cssSelector("[class='ui-select-highlight']"))
								.click();

						WebElement botoneliminar = driver
								.findElement(By
										.cssSelector("[ng-click='vm.deleteDeviceGroup()']"));
						botoneliminar.click();
						return;
					}

				}
			}
		}

	}

	public void clickImportDevicesGroups(String filedev) {
		esperarElemento(driver
				.findElement(By
						.cssSelector("[class='btn btn-default btn-sm dropdown-toggle']")));
		driver.findElement(
				By.cssSelector("[class='btn btn-default btn-sm dropdown-toggle']"))
				.click();
		driver.findElement(By.linkText("Import")).click();
		esperarElemento(driver.findElement(By.name("importFile")));
		driver.findElement(By.name("importFile")).clear();
		driver.findElement(By.name("importFile")).sendKeys(filedev);

		WebElement submit = driver.findElement(By
				.cssSelector("[form='formImport']"));
		esperarElemento(submit);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		submit.click();

	}

	public void clickImportUserGroups(String fileusers) {
		esperarElemento(driver
				.findElement(By
						.cssSelector("[class='btn btn-default btn-sm dropdown-toggle']")));
		driver.findElement(
				By.cssSelector("[class='btn btn-default btn-sm dropdown-toggle']"))
				.click();
		driver.findElement(By.linkText("Import")).click();
		esperarElemento(driver.findElement(By.name("importFile")));
		driver.findElement(By.name("importFile")).clear();
		driver.findElement(By.name("importFile")).sendKeys(fileusers);
		WebElement submit = driver.findElement(By
				.cssSelector("[form='formImport']"));
		esperarElemento(submit);
		submit.click();

	}

	public String getFirstValueName(String version) {
		esperarElemento(searchtable);
		searchtable.clear();
		searchtable.sendKeys(version);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			// find
			WebElement table_element = driver
					.findElement(By.id("tableBuilder"));
			List<WebElement> tr_collection = table_element.findElements(By
					.tagName("tr"));
			for (WebElement trElement : tr_collection) {
				List<WebElement> td_collection = trElement.findElements(By
						.tagName("td"));
				for (WebElement tdElement : td_collection) {
					List<WebElement> clickColl = driver
							.findElements(By
									.cssSelector("[ng-click='vm.goToObjectDetails(row)']"));
					for (WebElement webElement : clickColl) {
						if (webElement.getText().equals(version)) {
							// found return name
							List<WebElement> list = driver.findElements(By
									.cssSelector("[class='ng-binding']"));
							String name = list.get(3).getText();
							return name;
						}
					}
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String getDeviceGroupName(String devgp) {
		esperarElemento(searchtable);
		searchtable.clear();
		searchtable.sendKeys(devgp);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			// find
			WebElement table_element = driver.findElement(By
					.id("deviceGroupsTable"));
			List<WebElement> tr_collection = table_element.findElements(By
					.tagName("tr"));
			for (WebElement trElement : tr_collection) {
				List<WebElement> td_collection = trElement.findElements(By
						.tagName("td"));
				for (WebElement tdElement : td_collection) {
					List<WebElement> clickColl = driver
							.findElements(By
									.cssSelector("[ng-click='vm.goToObjectDetails(row)']"));
					for (WebElement webElement : clickColl) {
						String[] test = StringUtils.split(webElement.getText(),
								"|");
						if (test[test.length - 1].trim().equals(devgp)) {
							// found return device group found
							return test[test.length - 1].trim();
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void clickRefresh() {
		WebElement btref = driver.findElement(By
				.cssSelector("[ng-click='vm.reload()']"));
		esperarElemento(btref);
		btref.click();
	}

	public void clickOnOperation() {
		esperarElemento(driver.findElement(By
				.cssSelector("[ui-sref='operations.list']")));
		WebElement oplink = driver.findElement((By
				.cssSelector("[ui-sref='operations.list']")));
		oplink.click();

	}

	public void runSystemDevice(String device, String system) {
		esperarElemento(searchtable);
		searchtable.clear();
		searchtable.sendKeys(device);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			// find
			WebElement table_element = driver
					.findElement(By.id("devicesTable"));
			List<WebElement> tr_collection = table_element.findElements(By
					.tagName("tr"));
			for (WebElement trElement : tr_collection) {
				List<WebElement> td_collection = trElement.findElements(By
						.tagName("td"));
				for (WebElement tdElement : td_collection) {
					List<WebElement> clickColl = driver
							.findElements(By
									.cssSelector("[ng-click='vm.goToObjectDetails(row)']"));
					for (WebElement webElement : clickColl) {
						if (webElement.getText().equals(device)) {
							// click on run
							tdElement.click();
							driver.findElement(
									By.cssSelector("[class='btn btn-default btn-sm dropdown-toggle']"))
									.click();
							WebElement runsys = driver
									.findElement(By
											.cssSelector("[data-target='#system-update-execution']"));
							runsys.click();
							// pop up to run
							WebElement divlist = driver
									.findElement(By
											.cssSelector("[ng-model='vm.systemUpdate']"));
							esperarElemento(divlist);
							WebElement span = divlist
									.findElement(By
											.cssSelector("[ng-click='$select.activate()']"));
							esperarElemento(span);
							span.click();
							WebElement inpt = divlist.findElement(By
									.tagName("input"));
							Thread.sleep(5000);
							esperarElemento(inpt);
							inpt.sendKeys(system);
							driver.findElement(
									By.cssSelector("[class='ui-select-choices-row-inner']"))
									.click();
							WebElement btnrun = driver
									.findElement(By
											.cssSelector("[form='vm.formSystemUpdateExecution']"));
							btnrun.click();
							return;
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public int countOperations() {

		try {
			// find
			Thread.sleep(5000);
			List<WebElement> tr_collection = searchtableoperation
					.findElements(By.tagName("tr"));
			for (WebElement trElement : tr_collection) {
				List<WebElement> td_collection = trElement.findElements(By
						.tagName("td"));
				for (WebElement tdElement : td_collection) {
					List<WebElement> clickColl = driver
							.findElements(By
									.cssSelector("[ng-click='vm.goToObjectDetails(row)']"));

					// if (webElement.getText().equals(namedev)) {

					return clickColl.size();

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;

	}

	public void clickExportDev(String format, String[] properties) {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		esperarElemento(driver.findElement(By
				.cssSelector("[data-target='#export-modal']")));
		driver.findElement(By.cssSelector("[data-target='#export-modal']"))
				.click();

		WebElement divinput = driver
				.findElement(By
						.cssSelector("[class='btn btn-default form-control ui-select-toggle']"));
		divinput.click();
		List<WebElement> spandivs = driver.findElements(By
				.cssSelector("[class='ui-select-choices-row-inner']"));
		spandivs.get(0).click();
		WebElement divlist = driver
				.findElement(By
						.cssSelector("[ng-model='vm.propertiesUISelect.selectedProperties']"));
		WebElement inp = divlist.findElement(By
				.cssSelector("[ng-model='$select.search']"));
		inp.click();
		List<WebElement> list = driver
				.findElements(By
						.cssSelector("[class='ui-select-choices ui-select-choices-content ui-select-dropdown dropdown-menu ng-scope']"));

		for (int i = 0; i < properties.length; i++) {
			for (WebElement webElement : list) {
				WebElement lisel = webElement.findElement(By
						.cssSelector("[class='ng-binding ng-scope']"));
				if (lisel.getText().equals(properties[i])) {
					lisel.click();
				}
			}
		}
		inp.sendKeys(Keys.ESCAPE);
		esperarElemento(driver.findElement(By
				.cssSelector("[ng-click='vm.exportFile()']")));
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.cssSelector("[ng-click='vm.exportFile()']"))
				.click();

	}

	public void clickExportDevG(String format, String[] properties) {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		esperarElemento(driver.findElement(By
				.cssSelector("[data-target='#export-modal']")));
		driver.findElement(By.cssSelector("[data-target='#export-modal']"))
				.click();

		WebElement divinput = driver
				.findElement(By
						.cssSelector("[class='ui-select-placeholder text-muted ng-binding']"));
		divinput.click();
		List<WebElement> spandivs = driver.findElements(By
				.cssSelector("[class='ui-select-choices-row-inner']"));
		spandivs.get(0).click();
		WebElement divlist = driver
				.findElement(By
						.cssSelector("[ng-model='vm.propertiesUISelect.selectedProperties']"));
		WebElement inp = divlist.findElement(By
				.cssSelector("[ng-model='$select.search']"));
		inp.click();
		List<WebElement> list = driver
				.findElements(By
						.cssSelector("[class='ui-select-choices ui-select-choices-content ui-select-dropdown dropdown-menu ng-scope']"));

		for (int i = 0; i < properties.length; i++) {
			for (WebElement webElement : list) {
				WebElement lisel = webElement.findElement(By
						.cssSelector("[class='ng-binding ng-scope']"));
				if (lisel.getText().equals(properties[i])) {
					lisel.click();
				}
			}
		}
		inp.sendKeys(Keys.ESCAPE);
		esperarElemento(driver.findElement(By
				.cssSelector("[ng-click='vm.exportFile()']")));
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.cssSelector("[ng-click='vm.exportFile()']"))
				.click();
		

	}

	public void runSystemDeviceGrp(String namedevG, String system) {
		esperarElemento(searchtable);
		searchtable.clear();
		searchtable.sendKeys(namedevG);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			// find
			WebElement table_element = driver
					.findElement(By.id("deviceGroupsTable"));
			List<WebElement> tr_collection = table_element.findElements(By
					.tagName("tr"));
			for (WebElement trElement : tr_collection) {
				List<WebElement> td_collection = trElement.findElements(By
						.tagName("td"));
				for (WebElement tdElement : td_collection) {
					List<WebElement> clickColl = driver
							.findElements(By
									.cssSelector("[ng-click='vm.goToObjectDetails(row)']"));
					for (WebElement webElement : clickColl) {
						if (webElement.getText().equals(namedevG)) {
							// click on run
							tdElement.click();
							driver.findElement(
									By.cssSelector("[class='btn btn-default btn-sm dropdown-toggle']"))
									.click();
							WebElement runsys = driver
									.findElement(By
											.cssSelector("[data-target='#system-update-execution']"));
							runsys.click();
							// pop up to run
							WebElement divlist = driver
									.findElement(By
											.cssSelector("[ng-model='vm.systemUpdate']"));
							esperarElemento(divlist);
							WebElement span = divlist
									.findElement(By
											.cssSelector("[ng-click='$select.activate()']"));
							esperarElemento(span);
							span.click();
							WebElement inpt = divlist.findElement(By
									.tagName("input"));
							Thread.sleep(5000);
							esperarElemento(inpt);
							inpt.sendKeys(system);
							driver.findElement(
									By.cssSelector("[class='ui-select-choices-row-inner']"))
									.click();
							WebElement btnrun = driver
									.findElement(By
											.cssSelector("[form='vm.formSystemUpdateExecution']"));
							btnrun.click();
							return;
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		
	}

	public void clickDevGrLink() {
		
		WebElement devgLink= driver.findElement(By
				.cssSelector("[href='#!/devicegroup/list']"));
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();

		}
		esperarElemento(devgLink);
		devgLink.click();
		
	}

	public void logout() {
		esperarElemento(driver.findElement(By.cssSelector("[ng-click='main.logout()']")));
		driver.findElement(By.cssSelector("[ng-click='main.logout()']"))
		.click();
		
	}

	public void clickOnInstall(String device,String app) {
		esperarElemento(searchtable);
		searchtable.clear();
		searchtable.sendKeys(device);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		WebElement table_element = driver.findElement(By.id("devicesTable"));
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
					if (aelement.getText().equals(device)) {
						tdElement.click();
						driver.findElement(
								By.cssSelector("[class='btn btn-default btn-sm dropdown-toggle']"))
								.click();
						driver.findElement(
								By.cssSelector("[data-target='#app-update']"))
								.click();
						//div 
						WebElement searchinp = driver.findElement(By.name("app"));
						searchinp.click();
						List<WebElement> divlistpack = driver.findElements(By.cssSelector("[ng-if='$select.open']"));
						for (WebElement webElement : divlistpack) {
							if(webElement.getText().contains(app))
							{
								//select
								webElement.click();
							}
						}
						WebElement version = driver.findElement(By.name("version"));
						esperarElemento(version);
						version.click();
						
						WebElement selversion = driver.findElement(By.cssSelector("[ng-if='$select.open']"));
						selversion.click();
						//execute
						WebElement divrun = driver.findElement(By
								.id("app-update"));
						divrun.findElement(
								By.cssSelector("[ng-click='vm.run()']"))
								.click();
						
						return;
					}

				}
			}
		}
		
	
		
	}



	

}
