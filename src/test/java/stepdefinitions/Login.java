package stepdefinitions;

import java.io.IOException;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.AccountPage;
import pageobjects.LandingPage;
import pageobjects.LoginPage;
import resources.Base;

public class Login extends Base{
	
	WebDriver driver;
	LandingPage landingPage;
	LoginPage loginPage;
	AccountPage accountPage;
	
	@Given("^Open any Browser$")
	public void Open_any_Browser() throws IOException {
		
		driver = initializeDriver();
		
		
	}
	
	@And("^Navigate to Login page$")
	public void Navigate_to_Login_page() throws InterruptedException {
		
		driver.get(prop.getProperty("url"));
		landingPage = new LandingPage(driver);
		landingPage.myAccountDropdown().click();
		landingPage.loginOption().click();
		Thread.sleep(2000);
	}
	
	@When("^User enters username as \"([^\"]*)\" and password as \"([^\"]*)\" into the fields$")
	public void User_enters_username_as_something_and_password_as_something_into_the_fields
			(String email,String password) throws InterruptedException {
		
		loginPage = new LoginPage(driver);
		loginPage.emailAdressField().sendKeys(email);
		loginPage.passwordField().sendKeys(password);
		Thread.sleep(2000);
		
	}
	
	@And("^User cicks on Login button$")
	public void User_cicks_on_Login_button() {
		
		loginPage.loginButton().click();
	}
	
	@Then("^verify user is able to successfully login$")
	public void verify_user_is_able_to_successfully_login() {
		
		accountPage = new AccountPage(driver);
		Assert.assertTrue(accountPage.editAccountInfoLink().isDisplayed());
		
	}
	
	@After
	public void closeBrowser() {
		driver.close();
	}
}
