package com.tsoftlatam.automatizacion.test.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SystemUserPage extends BasePage {

	public SystemUserPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isElementPresent() {
		try {
			driver.findElement(By.id("employee-information"));
			// driver.findElement(By.className("systemUser-information"));
			return true;
		} catch (Exception e) {
			return false;
		}
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
