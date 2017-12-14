package testcases;

import helper.DriverSession;
import org.junit.Assert;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageModules.BookStore;

public class BookStoreTestCases extends DriverSession{
	WebDriverWait wait;
	BookStore bookstore;
	
	@BeforeMethod
	public void OpenFliplearn() throws InterruptedException{
		wait=new WebDriverWait(driver, 25);
		bookstore=new BookStore(driver);
		driver.get("http://stgmarketplace.fliplearn.com/solr/products/lists");
		Thread.sleep(5000);
	}
	
	@Test(priority=1)
	public void BookStore() throws InterruptedException{
		Thread.sleep(5000);
		String ActualItemName = bookstore.getActualItemName();
		String ActualItemPrice = bookstore.getActualItemPrice();
		System.out.println("ActualItemPrice: "+ActualItemPrice);
		bookstore.clickOnViewdetails();
		if(ActualItemName.equalsIgnoreCase(bookstore.getItemNameOnProductDetails())){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false);
		}
		if(ActualItemPrice.equalsIgnoreCase(bookstore.getItemPriceOnProductDetails())){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false);
		}
	}
	
	
	
	
}
