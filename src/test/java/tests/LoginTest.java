package tests;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageobjects.AccountPage;
import pageobjects.LandingPage;
import pageobjects.LoginPage;
import resources.Base;


public class LoginTest extends Base {
	
	Logger log;
	public WebDriver driver;
	
	@BeforeMethod
	public void openApplication() throws IOException {
		log = LogManager.getLogger(LoginTest.class.getName());
		driver = initializeDriver();
		log.debug("Browser got launched");
		driver.get(prop.getProperty("url"));
		log.debug("Navigated to application URL");
		
		
	}
	
	@Test(dataProvider="getLoginData")
	public void login(String email, String password, String expectedStatus) throws IOException, InterruptedException {
		
		LandingPage landingPage = new LandingPage(driver);
		landingPage.myAccountDropdown().click();
		log.debug("Clicked on My Account dropdown");
		landingPage.loginOption().click();
		Thread.sleep(2000);
		log.debug("Clicked on login option");
		
		
		LoginPage loginPage = new LoginPage(driver);
		loginPage.emailAdressField().sendKeys(email);
		log.debug("Email andress got entered");
		loginPage.passwordField().sendKeys(password);
		log.debug("Password got entered");
		Thread.sleep(2000);
		
		loginPage.loginButton().click();
		log.debug("Clicked on Loggin Button");
		
		AccountPage accountPage = new AccountPage(driver);	
		
		String actualResult = null;
		try {
			
			
		if(accountPage.editAccountInfoLink().isDisplayed()) {
			actualResult = "Successful";
			log.debug("User got logged in");
		}
		
		}catch(Exception e) {
			log.debug("User didn't log in");
			actualResult = "Failure";
		}
		
		Assert.assertEquals(actualResult, expectedStatus);
		log.info("Login test got passed");
		
	}

	
	@AfterMethod
	public void tearDown() throws InterruptedException {
	
		driver.close();
		log.debug("Driver closed");
	}
	
	
	@DataProvider
	public Object[][] getLoginData() {
		
		Object[][] data = {{"pera123@gmail.com","pera123","Successful"}};
		return data;
	}

}
