package com.tsoftlatam.automatizacion.test.page;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class DashboardPage extends BasePage {

	public DashboardPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(how = How.XPATH, using = ".//*[@id='dashboard-quick-launch-panel-menu_holder']/table/tbody/tr/td[2]/div")
	WebElement lknLeaveList;

	@Override
	public boolean isElementPresent() {
		try {
			driver.findElement(By.id("welcome"));
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public LeaveListPage clickLeavelistLink() {
		// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		lknLeaveList.click();
		return new LeaveListPage(driver);
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
