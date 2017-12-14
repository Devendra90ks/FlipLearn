package pageModules;

import java.util.ArrayList;

import helper.GenericFunctions;
import helper.WallActions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.Xls_Reader;

public class RoleBaseLogin {
	GenericFunctions generic;
	WebDriver driver;
	Xls_Reader xls;
	
	public RoleBaseLogin(WebDriver driver){
		this.driver=driver;
		generic = new GenericFunctions(driver);
		xls=new Xls_Reader();
	}
	public static By userRole=By.xpath("//p[@class='text-color margin-bottom-0 padd0 ng-scope']");
	public static By logout=By.partialLinkText("Logout");
	public static By guestWallText1_Txt=By.xpath("//h4[contains(text(),'Explore curriculum mapped content')]");
	public static By guestWallText2_Txt=By.xpath("//h4[contains(text(),'Pay School Fee on Fliplearn')]");
	public static By guestUser_lnk=By.xpath("//a[@class='ng-binding']");
	public static By guestlogout_lnk=By.xpath("//a[contains(text(),'Logout')]");
	
	
	public String getUserROle(){
		WallActions.mouseHoverOnProfile();
		String UserROle = driver.findElement(userRole).getText();
		return UserROle;
	}
	public void Logout(){
		WallActions.mouseHoverOnProfile();
		driver.findElement(logout).click();	
	}
	public boolean isloggedInUserAsGuest(){
		ArrayList<String> ExpText = new ArrayList<String>();
		ArrayList<String> ActText = new ArrayList<String>();
		String WallText1 = driver.findElement(guestWallText1_Txt).getText();
		String WallText2 = driver.findElement(guestWallText2_Txt).getText();
		String Sheetname="RoleBaseLogin";
		int i,rowCount=xls.getRowCount(Sheetname);
		for(i=2;i<rowCount+1;i++){
			ExpText.add(xls.getCellData(Sheetname, "Text", i));
		}
		ActText.add(WallText1);
		ActText.add(WallText2);
		if(ActText.equals(ExpText)){
			return true;
		}
		else{
			return false;
		}
	}
	public void GuestLogout(){
		driver.findElement(guestUser_lnk).click();
		driver.findElement(guestlogout_lnk).click();
	}
}
