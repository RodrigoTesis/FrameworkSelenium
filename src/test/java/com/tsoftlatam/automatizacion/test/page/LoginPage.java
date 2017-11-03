package com.tsoftlatam.automatizacion.test.page;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(how = How.ID_OR_NAME, using = "txtUsername")
	WebElement txtUsuario;

	@FindBy(how = How.ID_OR_NAME, using = "txtPassword")
	WebElement txtContrasena;

	@FindBy(how = How.NAME, using = "Submit")
	WebElement btnLogin;

	public DashboardPage login(String usuario, String contrasena) {
		// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		txtUsuario.sendKeys(usuario);
		txtContrasena.sendKeys(contrasena);
		btnLogin.click();
		return new DashboardPage(driver);
	}

	@Override
	public boolean isElementPresent() {
		try {
			driver.findElement(By.id("frmLogin"));
			return true;
		} catch (NoSuchElementException e) {
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
