package pageModules;

import java.util.List;

import helper.GenericFunctions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import utils.Xls_Reader;

public class ManageUser {
	WebDriver driver;
	LoginPage login;
	Xls_Reader xls;
	GenericFunctions generic;
	public ManageUser(WebDriver driver){
		this.driver=driver;
		generic = new GenericFunctions(driver);
		xls=new Xls_Reader();
	}
	
	//div[@class='dropdown-content left-0']/a/span
	public static By Header_lnk=By.xpath("//div[contains(@class,'mobile-view')]");
	public static By Manage_lnk=By.xpath("//a[@class='dropbtn']");
	public static By ManageSubModule_lnk=By.xpath("//div[@class='dropdown-content left-0']/a");
	public static By Manage_User_lnk=By.xpath("//span[@class='user-dropdown']");
	public static By Manage_SchoolDetails_lnk=By.xpath("//span[@class='school_Details']");
	public static By Manage_ClassSection_lnk=By.xpath("//span[@class='class-section-img']");
	public boolean isHeaderPresent(){
		List<WebElement> elements = driver.findElements(Header_lnk);
			String Sheetname="ManageUser",HeaderLink;
			for(int i=2;i<elements.size()+2;i++){
				HeaderLink=xls.getCellData(Sheetname, "HeaderLink", i);
				Assert.assertEquals(HeaderLink, elements.get(i-2).getText(), "link not matched");
			}
		
		return true;
	}
	public void mouseHoverOnManageLnk(){
		WebElement element = driver.findElements(Manage_lnk).get(1);
		Actions actions = new Actions(driver);
		actions.moveToElement(element);
		actions.perform();	
	}
	public boolean isManageSubModuleLink_present(){
		mouseHoverOnManageLnk();
		List<WebElement> elements = driver.findElements(ManageSubModule_lnk);
		String Sheetname="ManageUser",ManageSubModule;
		for(int i=2;i<elements.size()+1;i++){
			ManageSubModule=xls.getCellData(Sheetname, "ManageSubModule", i).trim();
			Assert.assertEquals(ManageSubModule, elements.get(i-2).getText(), "module not matched");
		}
		return true;
	}
	
	

}
