package AssignmentScenarios;

import org.junit.Assert; 
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Scenario1 {
	

  @Test
  @Parameters({ "correct-user-name","correct-password" })
  public void testLoginSuccess(String userName, String password) {
	  		//setting the driver property
			System.setProperty("webdriver.firefox.marionette","D://Eclipse//drivers//geckodriver.exe");
			//creating the firefox driver instance
			WebDriver driver = new FirefoxDriver();
	
			//navigate to required url
			driver.get("https://www.saucedemo.com/");
			//enter the username in the username textbox
			driver.findElement(By.xpath("//*[@id=\"user-name\"]")).sendKeys(userName);
			//enter the password in the password textbox
			driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(password);
			//clicking on the log in  button
			driver.findElement(By.xpath("//*[@id=\"login-button\"]")).click();
			//getting the left menu title after login 
			String menuTitleAfterLogin = driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span")).getText();
			// Expected Menu Title
			String expectedMenuTitleAfterLogin = "Products";
			// Assert that the menu title matches with the expected title after login
			Assert.assertEquals(expectedMenuTitleAfterLogin,menuTitleAfterLogin);
			
			//closing the driver instance
			driver.close();
  }
  
  @Test
  @Parameters({ "wrong-user-name" })
  public void testLoginFailure(String userName) {
	  		//setting the driver property
			System.setProperty("webdriver.firefox.marionette","D://Eclipse//drivers//geckodriver.exe");
			//creating the firefox driver instance
			WebDriver driver = new FirefoxDriver();
	
			//navigate to required url
			driver.get("https://www.saucedemo.com/");
			//enter the username in the username textbox
			driver.findElement(By.xpath("//*[@id=\"user-name\"]")).sendKeys(userName);
			//enter the password in the password textbox
			driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("secret_sauce");
			//clicking on the log in  button
			driver.findElement(By.xpath("//*[@id=\"login-button\"]")).click();
			//Asserting that the element for login button is present as this is a test case for login failure
			Assert.assertTrue(driver.findElements(By.xpath("//*[@id=\"login-button\"]")).size() != 0);
			
			
			//closing the driver instance
			driver.close();
  }
  
 
}
