package helper;

import java.io.IOException;
import java.util.List;
import helper.GenericFunctions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class WallActions extends DriverSession{
	GenericFunctions generic;
	
	public static By profile=By.xpath("//img[@class='profilePic ng-scope']");
	public static By MyProfile_lnk=By.xpath("//a[@href='/home/account/profile']");
	public static By logout=By.partialLinkText("Logout");
	
	
	public WallActions(WebDriver driver){
		this.driver=driver;
		generic = new GenericFunctions(driver);
	}
	
	public static void mouseHoverOnProfile(){
		GenericFunctions.WaitFor_visibility(driver, driver.findElement(profile));
		WebElement element = driver.findElement(profile);
		Actions actions = new Actions(driver);
		actions.moveToElement(element);
		actions.perform();	
	}
	
	public static void clickOnDeletePopScreenGeneric(By Xpath){
		WebElement element = driver.findElement(Xpath);
		Actions actions = new Actions(driver);
		actions.moveToElement(element);
		actions.perform();
		driver.findElement(Xpath).click();
	}
	
	public static void ClickEditWallGeneric(By Xpath){
		
		List<WebElement> elements=driver.findElements(Xpath);
		elements.get(0).click();
}
	
	public static void WallUpdateButtonGeneric(By Xpath){
		
		driver.findElement(Xpath).click();;
	}
	
	public static void WallDeleteButtonGeneric(By Xpath){
	
		driver.findElement(Xpath).click();
		
	}
	
	public static void Logout(){
		mouseHoverOnProfile();
		driver.findElement(logout).click();	
	}
	public static void clickOnMyProfile(){
		mouseHoverOnProfile();
		driver.findElement(MyProfile_lnk).click();
	}
	
	public static void UploadFile(String FileType) throws IOException{
		
		
		String filePath = System.getProperty("user.dir");

		switch(FileType){
			
			case "jpg":
				Runtime.getRuntime().exec(filePath+"\\Image.exe");
				break;
				
			case "pdf":
				Runtime.getRuntime().exec(filePath+"\\Pdf.exe");
				break;
			case "word":
				
				Runtime.getRuntime().exec(filePath+"\\Word.exe");
				break;
				
			case "excel":
				Runtime.getRuntime().exec(filePath+"\\Excel.exe");
				
				break;
				
			default:
				System.out.println("File does not Exist");
		}
		
    }

}





