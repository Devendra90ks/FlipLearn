package pageModules;

import helper.GenericFunctions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import utils.Xls_Reader;

	public class BookStore {
	static WebDriver driver;
	Xls_Reader xls;
	GenericFunctions generic;
	
	public BookStore(WebDriver driver){
		BookStore.driver=driver;
		generic = new GenericFunctions(driver);
	}
	public static By booksImage=By.xpath("//div[@class='figure']");
	public static By viewDetail_lnk=By.xpath("//div[@class='figcaption']");
	public static By itemName=By.xpath("//h4//a");
	public static By itemPrice=By.xpath("//div[@class='pricefont']");
	public static By itemNameOnProductDetails=By.xpath("//span[@class='base']");
	public static By itemPriceOnProductDetails=By.xpath("//span[@class='price']");
	
	public String getActualItemName(){
		String ItemName = driver.findElement(itemName).getText();
		return ItemName;
	}
	public String getActualItemPrice(){
		String ItemPrice = driver.findElement(itemPrice).getText();
		return ItemPrice;
	}
	public void clickOnViewdetails(){
		GenericFunctions.WaitFor_visibility(driver, driver.findElement(booksImage));
		WebElement element = driver.findElement(booksImage);
		Actions actions = new Actions(driver);
		actions.moveToElement(element);
		actions.perform();
		generic.clickFunction(driver.findElement(viewDetail_lnk));
	}
	public String getItemNameOnProductDetails(){
		String ItemNameOnProductDetails = driver.findElement(itemNameOnProductDetails).getText();
		return ItemNameOnProductDetails;
	}
	public String getItemPriceOnProductDetails(){
		String ItemPriceOnProductDetails = driver.findElements(itemPriceOnProductDetails).get(1).getText();
		System.out.println("ItemPriceOnProductDetails:"+ ItemPriceOnProductDetails);
		return ItemPriceOnProductDetails;
	}
	public boolean checkItemNameOnProductDetailsWithActualName(){
		if(getActualItemName().equalsIgnoreCase(getItemNameOnProductDetails())){
			return true;
		}else{
			return false;
		}
	}
	
}
