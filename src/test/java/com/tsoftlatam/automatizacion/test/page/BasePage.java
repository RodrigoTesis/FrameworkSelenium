package com.tsoftlatam.automatizacion.test.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {
	WebDriver driver;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public abstract boolean isElementPresent();

	public abstract boolean isAlertPresent();

	public abstract boolean closeAlertAndGetItsText();
}
