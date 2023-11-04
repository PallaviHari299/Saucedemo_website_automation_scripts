package AssignmentScenarios;

import java.util.List;

import org.junit.Assert; 
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Scenario2 {
	

  @Test
  @Parameters({ "correct-user-name","correct-password" })
  public void testSortOption(String userName, String password) {
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
			//Finding all items on the webpage by classname and storing in a list
			List<WebElement> itemList = driver.findElements(By.className("inventory_item"));
			//Finding the number of items on the page
			int webPageItemCount = itemList.size();
			//Setting the expected number of items on the page
			int expectedItemCount = 6;
			if(webPageItemCount==expectedItemCount)
			{
				System.out.println("Number of items is " + webPageItemCount + " and is the expected value");
			}
			else
			{
				System.out.println("Number of items is " + webPageItemCount + " and is not the expected value");

			}
		
			//Selecting the required value(Price (low to high)) from the dropdown
			Select sortDropdown = new Select(driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/div/span/select")));
			sortDropdown.selectByVisibleText("Price (low to high)");
			//Price of first item
			String firstItemPrice= driver.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div[1]/div[2]/div[2]/div")).getText();
			// Removing $ sign and converting to float to get the price value
			float firstItemPriceValue = Float.parseFloat(firstItemPrice.replace("$",""));
			//Price of last item
			String lastItemPrice= driver.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div[6]/div[2]/div[2]/div")).getText();
			// Removing $ sign and converting to float to get the price value
			float lastItemPriceValue = Float.parseFloat(lastItemPrice.replace("$",""));
			//Finding all prices of items on the webpage by classname and storing in a list
			List<WebElement> priceList = driver.findElements(By.className("inventory_item_price"));
			float minPrice = 999999;
			float maxPrice = 0;
			
			for(WebElement price : priceList)
			{
				float priceValue = Float.parseFloat(price.getText().replace("$",""));
				if(priceValue>maxPrice)
				{
					maxPrice = priceValue;
				}
				
				if(priceValue<minPrice)
				{
					minPrice = priceValue;
				}
				
				
			}
			//Asserting that the first item price is equal to minimum price and last item price is equal to maximum price
			Assert.assertTrue(firstItemPriceValue==minPrice && lastItemPriceValue==maxPrice);
			
			
			
			//closing the driver instance
			driver.close();
  }
  
  
 
}
