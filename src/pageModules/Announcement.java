package pageModules;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

import helper.GenericFunctions;
import helper.WallActions;
import net.sf.saxon.functions.Data;

import org.apache.commons.io.FilenameUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sun.jna.platform.win32.Netapi32Util.User;

import utils.Xls_Reader;

public class Announcement {
	WebDriver driver;
	LoginPage login;
	Xls_Reader xls;
	GenericFunctions generic;
	public Announcement(WebDriver driver){
		this.driver=driver;
		generic = new GenericFunctions(driver);
	}
	public static By WallUpdateButtonGeneric=By.xpath(".//*[@id='btn-next']");
	public static By selectTeacher=By.xpath("//p[contains(text(),'teacher')]");
	public static By continueButton=By.xpath("//button[@class='btn btn-primary paddingLR marginR20 ng-scope']");
	public static By clickOnDeletePopScreenGeneric=By.xpath(".//*[@id='delete']");
	public static By Post_Lnk=By.xpath("//div[@class='dropdown']/button[@class='btn btn-primary']");
	public static By Announcement_Lnk=By.xpath("//button[@id='announcement']");
	public static By Delete_Btn=By.xpath("//button[contains(@class,'delete-post')]");
	public static By Title_Txt=By.xpath("(//input[@id='title'])[2]");
	public static By Title_Txt_Alternate=By.xpath("(//input[@id='title'])[1]");
	public static By ShareWith_DD=By.xpath("//span[@class='btn-contact-gallery']");
	public static By SendSMS_Yes=By.xpath("(//div[@class='radiobutton']/label)[3]");
	public static By SendSMS_No=By.xpath("(//div[@class='radiobutton']/label)[4]");
	public static By Description_Txt=By.xpath("(//textarea[@id='textboxIssuereport'])[2]");
	public static By Upload_Attachment=By.xpath("//input[contains(@src,'/imagedrag.png')]");
	public static By Group_Selection_Chk=By.xpath("//input[@name='group_code']");
	public static By SaveCloseGroup_Btn=By.xpath("//button[@class='btn btn-primary btn-block']");
	public static By Create_announce_Btn=By.xpath("(//button[@id='btn-next'])[2]");
	public static By filTerDropdown_Lnk=By.xpath("//button[@class='btn btn-primary dropdown-toggle class-dropdown']");
	public static By filterTypeHeader=By.xpath("//div[contains(@class,'icon ng-scope')]/span");
	public static By announcementUser=By.xpath("//small[@class='pull-left text-color paddinglr0 ng-binding']");
	public static By loggedInUser=By.xpath("//h4[@class='margin-bottom-4 ng-binding']");
	public static By profileHover=By.xpath("//img[@class='profilePic ng-scope']");
	public static By uploadFileOnWall=By.xpath("//li[@class='ng-binding ng-scope']");
	public static By getTitle=By.xpath("//h4[@class='m-t-20 ng-binding']");
	public static By DeleteAnnouncement=By.xpath("//button[contains(@class,'delete-post')]");
	public static By schoolStaff_lnk=By.xpath("//a[@data-toggle='tab']");
	public static By allTeacherGroup_chkbox=By.xpath("//span[@class='custom-checkbox ng-scope']/input[contains(@class,'group_code')]");

	public void selectTeacherProfile()
	{
		driver.findElement(selectTeacher).click();
		driver.findElement(continueButton).click();
	}
	public void clickOnAPostOptions() throws InterruptedException{
		generic.waitPageGotLoad();
		GenericFunctions.WaitFor_visibility(driver, driver.findElement(Post_Lnk));
		Actions builder = new Actions(driver);
		builder.moveToElement(driver.findElement(Post_Lnk)).perform();
		builder.moveToElement(driver.findElement(Announcement_Lnk)).click().perform();
	}
	public void fillTitle(String value){
		try {
			if(value.trim().length()==0){
				driver.findElement(Title_Txt).clear();
				return;
			}
			driver.findElement(Title_Txt).clear();
			driver.findElement(Title_Txt).sendKeys(value);
		}catch (Exception e) {
			if(value.trim().length()==0){
				driver.findElement(Title_Txt_Alternate).clear();
				return;
			}
			driver.findElement(Title_Txt_Alternate).clear();
			driver.findElement(Title_Txt_Alternate).sendKeys(value);
		}
	}	
	public void clickOnShareWithButton(){
		try {
			driver.findElement(ShareWith_DD).click();
		} catch (Exception e) {
			driver.findElement(ShareWith_DD).click();
		}
	}
	public void selectGroupByIndex(int index) throws InterruptedException{
		generic.waitPageGotLoad();
		List<WebElement> elements = driver.findElements(Group_Selection_Chk);
		elements.get(index-1).click();
	}
	public void closeGroupSelection(){
		driver.findElement(SaveCloseGroup_Btn).click();
	}
	public void clickOnSendSMSNO(){
		driver.findElement(SendSMS_No).click();
	}
	String fileName1 ="",fileName2 ="",fileName3 ="",fileName4 ="";
	String FileExtension1 = "", FileExtension2 = "", FileExtension3 = "", FileExtension4 = "";
	public void uploadImage() throws Throwable{
		String NewFilepath1,NewFilepath2,NewFilepath3,NewFilepath4="";
		String filePath1=System.getProperty("user.dir") + "/dummyFiles/rohit.docx";
		String filePath2=System.getProperty("user.dir") + "/dummyFiles/satya.png";
		String filePath3=System.getProperty("user.dir") + "/dummyFiles/sample.xlsx";
		String filePath4=System.getProperty("user.dir") + "/dummyFiles/test.pdf";
		String OSName = generic.getCurrentEnvironment();
		System.out.println(OSName);
		if(OSName.contains("Windows")){
			NewFilepath1=filePath1.replace( "/","\\");
			NewFilepath2=filePath2.replace( "/","\\");
			NewFilepath3=filePath3.replace( "/","\\");
			NewFilepath4=filePath4.replace( "/","\\");
		}else{
			NewFilepath1=filePath1.replace( "/","/");
			NewFilepath2=filePath2.replace( "/","/");
			NewFilepath3=filePath3.replace( "/","/");
			NewFilepath4=filePath4.replace( "/","/");
		}

		Actions actions = new Actions(driver);
		actions.keyDown(Keys.CONTROL).sendKeys(Keys.PAGE_DOWN).perform();
		generic.GoToSleep(3000);
		driver.findElements(Upload_Attachment).get(1).click();
		generic.GoToSleep(1000);
		generic.UploadFile(NewFilepath1);
		generic.GoToSleep(1000);
		driver.findElements(Upload_Attachment).get(1).click();
		generic.GoToSleep(1000);
		generic.UploadFile(NewFilepath2);
		generic.GoToSleep(1000);
		driver.findElements(Upload_Attachment).get(1).click();
		generic.GoToSleep(1000);
		generic.UploadFile(NewFilepath3);
		generic.GoToSleep(1000);
		driver.findElements(Upload_Attachment).get(1).click();
		generic.GoToSleep(1000);
		generic.UploadFile(NewFilepath4);
		generic.GoToSleep(1000);
		fileName1 = FilenameUtils.getBaseName(NewFilepath1);
		FileExtension1 = FilenameUtils.getExtension(NewFilepath1);
		fileName2 = FilenameUtils.getBaseName(NewFilepath2);
		FileExtension2 = FilenameUtils.getExtension(NewFilepath2);
		fileName3 = FilenameUtils.getBaseName(NewFilepath3);
		FileExtension3 = FilenameUtils.getExtension(NewFilepath3);
		fileName4 = FilenameUtils.getBaseName(NewFilepath4);
		FileExtension4 = FilenameUtils.getExtension(NewFilepath4);
	}
	public void fillDescription(String value){
		if(value.trim().length()==0){
			driver.findElement(Description_Txt).clear();
			return;
		}
		driver.findElement(Description_Txt).sendKeys(value);
	}
	public void clickOnCreateButton(){
		driver.findElement(Create_announce_Btn).click();
	}
	public String fillCreateAnnouncementForm() throws Throwable{
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		driver.switchTo().activeElement();
		Thread.sleep(1000);
		fillTitle("Qa Web automation Title_" + timeStamp);
		clickOnShareWithButton();
		generic.GoToSleep(4000);
		selectGroupByIndex(2);
		closeGroupSelection();
		Thread.sleep(2000);
		clickOnSendSMSNO();
		fillDescription("Test Description_"+timeStamp);
		generic.GoToSleep(2000);
		clickOnCreateButton();
		Thread.sleep(4000);
		return "Qa Web automation Title_" + timeStamp;	
	}
	public String CreateAnnouncementFormToAllClasses() throws Throwable{
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		driver.switchTo().activeElement();
		Thread.sleep(1000);
		fillTitle("Qa Web automation Title_" + timeStamp);
		clickOnShareWithButton();
		generic.GoToSleep(4000);
		selectGroupByIndex(1);
		closeGroupSelection();
		Thread.sleep(2000);
		clickOnSendSMSNO();
		fillDescription("Test Description_"+timeStamp);
		generic.GoToSleep(2000);
		clickOnCreateButton();
		Thread.sleep(4000);
		return "Qa Web automation Title_" + timeStamp;	
	}

	public String fillCreateAnnouncementFormWithDifferentTab() throws Throwable{
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		driver.switchTo().activeElement();
		Thread.sleep(1000);
		fillTitle("Qa Web automation Title_" + timeStamp);
		clickOnShareWithButton();
		generic.GoToSleep(4000);
		driver.findElements(schoolStaff_lnk).get(1).click();
		generic.GoToSleep(1000);
		driver.findElements(allTeacherGroup_chkbox).get(0).click();
		generic.GoToSleep(1000);
		closeGroupSelection();
		Thread.sleep(2000);
		clickOnSendSMSNO();
		fillDescription("Test Description_"+timeStamp);
		generic.GoToSleep(2000);
		clickOnCreateButton();
		Thread.sleep(4000);
		return "Qa Web automation Title_" + timeStamp;	
	}

	public String fillCreateAnnouncementFormWithData(String title, String desc) throws Throwable{
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		driver.switchTo().activeElement();
		Thread.sleep(1000);
		fillTitle("Automation Title_" + title +timeStamp);
		clickOnShareWithButton();
		generic.GoToSleep(4000);
		selectGroupByIndex(2);
		closeGroupSelection();
		Thread.sleep(2000);
		clickOnSendSMSNO();
		fillDescription("Test Description_"+desc);
		generic.GoToSleep(2000);
		clickOnCreateButton();
		Thread.sleep(4000);
		return "Automation Title_" + title +timeStamp;	
	}



	public String fillCreateAnnouncementWithUploadFile() throws Throwable{
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		driver.switchTo().activeElement();
		Thread.sleep(1000);
		fillTitle("Qa Web automation Title_" + timeStamp);
		clickOnShareWithButton();
		generic.GoToSleep(6000);
		selectGroupByIndex(2);
		closeGroupSelection();
		Thread.sleep(2000);
		clickOnSendSMSNO();
		fillDescription("Test Description_"+timeStamp);
		uploadImage();
		generic.GoToSleep(2000);
		clickOnCreateButton();
		Thread.sleep(5000);
		return "Qa Web automation Title_" + timeStamp;	
	}
	public String getTitle() throws InterruptedException{
		Thread.sleep(3000);
		return driver.findElement(getTitle).getText();
	}
	public boolean isTitleDisplayed(String Title) throws InterruptedException{
		int i=0;
		generic.waitPageGotLoad();
		String ActualTitle=getTitle();
		while(!(ActualTitle.equalsIgnoreCase(Title)) && i<20){
			driver.navigate().refresh();
			ActualTitle=getTitle();
			i++;
			generic.GoToSleep(1000);
		}
		if(!(ActualTitle.equalsIgnoreCase(Title))){
			return false;
		}else{
			return true;
		}
	}
	public String getloggedInUser(String role) throws InterruptedException{
		Thread.sleep(2000);
		WebElement element = driver.findElement(profileHover);
		Actions actions = new Actions(driver);
		actions.moveToElement(element);
		actions.perform();
		String UserName;
		UserName=driver.findElement(loggedInUser).getText();
		UserName=UserName.split(role.toLowerCase())[0].trim();
		return UserName;
	}
	public String getUserName() throws InterruptedException{
		Thread.sleep(2000);
		String UserName= driver.findElements(announcementUser).get(0).getText();
		return UserName;
	}
	public boolean isAnnouncementCreatorNameSameAsLoggedInUser (String role) throws InterruptedException{
		if(getUserName().equalsIgnoreCase(getloggedInUser(role))){
			return true;
		}else{
			return false;
		}
	}
	public String getUploadedFileName1(){
		String UploadedFileName= driver.findElements(uploadFileOnWall).get(0).getText();
		return UploadedFileName;
	}
	public String getUploadedFileName2(){
		String UploadedFileName= driver.findElements(uploadFileOnWall).get(1).getText();
		return UploadedFileName;
	}
	public String getUploadedFileName3(){
		String UploadedFileName= driver.findElements(uploadFileOnWall).get(2).getText();
		return UploadedFileName;
	}
	public String getUploadedFileName4(){
		String UploadedFileName= driver.findElements(uploadFileOnWall).get(3).getText();
		return UploadedFileName;
	}
	public boolean isFileNameSameAsUploadedByUser(){
		if(getUploadedFileName1().contains(fileName1)&&getUploadedFileName1().contains(FileExtension1)&&
				getUploadedFileName2().contains(fileName2)&&getUploadedFileName1().contains(FileExtension1)&&
				getUploadedFileName3().contains(fileName3)&&getUploadedFileName1().contains(FileExtension1)&&
				getUploadedFileName4().contains(fileName4)&&getUploadedFileName1().contains(FileExtension1)){
			return true;
		}else{
			return false;
		}
	}
	public void clickOnEditAnnuncement(){
		generic.GoToSleep(2000);
		List<WebElement> elements = driver.findElements(By.xpath("//div[contains(@class,'edit-button ng-scope')]/a/span"));
		elements.get(0).click();
	}
	public void scrollInModelDialog(){
		WebElement element = driver.findElement(Delete_Btn);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}
	public void clickOnDeleteAnnouncement(){
		WebElement element = driver.findElement(DeleteAnnouncement);
		Actions actions = new Actions(driver);
		actions.moveToElement(element);
		actions.perform();
		element.click();
	}
	public void ConfirmDeletePop() throws InterruptedException{
		Thread.sleep(2000);
		WallActions.clickOnDeletePopScreenGeneric(clickOnDeletePopScreenGeneric);
		generic.GoToSleep(5000);
	}
	public void announcementUpdateButton(){
		WallActions.WallUpdateButtonGeneric(WallUpdateButtonGeneric);
		generic.GoToSleep(1000);

	}
	public void clickOnUpdateButton(){
		driver.findElement(Create_announce_Btn).click();
	}
}
