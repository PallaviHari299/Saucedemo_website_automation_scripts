package AssignmentScenarios;

import org.junit.Assert; 
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Scenario3 {
	

  @Test
  @Parameters({ "correct-user-name", "correct-password" , "expected-items-count"})
  public void testBasketItemsCount(String userName, String password,int expectedNumberOfItems) {
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
			//adding 1st element in cart
			driver.findElement(By.xpath("//*[@id=\"add-to-cart-sauce-labs-onesie\"]")).click();
			//adding 2nd element in cart
			driver.findElement(By.xpath("//*[@id=\"add-to-cart-sauce-labs-bike-light\"]")).click();
			//adding 3rd element in cart
			driver.findElement(By.xpath("//*[@id=\"add-to-cart-sauce-labs-bolt-t-shirt\"]")).click();
			//Number of items in cart in webpage
			int cartItemsNumber = Integer.parseInt(driver.findElement(By.className("shopping_cart_badge")).getText());
			
			//Asserting that the number of items added is equal to the expected number of items(3)
			Assert.assertTrue(cartItemsNumber==expectedNumberOfItems);
			
			
			//Remove 1 item from cart
			driver.findElement(By.xpath("//*[@id=\"remove-sauce-labs-bike-light\"]")).click();
			//checking the new cart number after removing 1 item
			int newCartItemsNumber = Integer.parseInt(driver.findElement(By.className("shopping_cart_badge")).getText());
			
			//Asserting that the number of items in the cart now is equal to the earlier number -1 (3-1 = 2)
			Assert.assertTrue(newCartItemsNumber==expectedNumberOfItems-1);
			
			
			
			//closing the driver instance		
			driver.close();
			
			
		
  }
  
  
 
}
