package com.tsoftlatam.automatizacion.test.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class MenuHeaderPage extends BasePage {

	public MenuHeaderPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(how = How.ID, using = "menu_admin_viewAdminModule")
	WebElement lknAdmin;

	@FindBy(how = How.ID, using = "menu_pim_viewPimModule")
	WebElement lknPim;

	@Override
	public boolean isElementPresent() {
		try {
			driver.findElement(By.className("menu"));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public SystemUserPage clickAdminMenu() {
		lknAdmin.click();
		return new SystemUserPage(driver);
	}

	public EmployeeInformationPage clickPimMenu() {
		lknPim.click();
		return new EmployeeInformationPage(driver);
	}

	@Override
	public boolean isAlertPresent() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean closeAlertAndGetItsText() {
		// TODO Auto-generated method stub
		return false;
	}

}
