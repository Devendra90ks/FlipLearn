package pageModules;

import java.awt.AWTException;
import java.awt.Robot;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import helper.GenericFunctions;
import helper.WallActions;

import org.apache.commons.io.FilenameUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.sun.glass.events.KeyEvent;





public class HomeWork {

	WebDriver driver;

	GenericFunctions generic;

	public HomeWork(WebDriver driver){
		this.driver=driver;
		generic = new GenericFunctions(driver);
	}

	public static By Post_Lnk=By.xpath("//div[@class='dropdown']/button[@class='btn btn-primary']");
	public static By Homework_Lnk=By.xpath("//button[@id='homework']");
	public static By Title_Txt_Fill=By.xpath("(//input[@id='title'])[2]");
	public static By Title_Txt_Edit=By.xpath("(//input[@id='title'])[1]");
	public static By ShareWith_DD=By.xpath("//span[@class='btn-contact-gallery']");
	public static By Group_Selection_Chk=By.xpath("//input[@name='group_code']");
	public static By SaveCloseGroup_Btn=By.xpath("//button[@class='btn btn-primary btn-block']");
	public static By selectSubject_DrpDwn=By.xpath("//div[@class='dropdown registration-form col-md-12 padding-0 ng-scope']");
	public static By Class_Subject=By.xpath(".//*[@id='mCSB_1_container']/div/div[1]/form[1]/div[3]/ul/li/span/input");
	public static By Sub_date=By.xpath(".//*[@id='endDate']");
	public static By Calender_date=By.xpath("html/body/div[6]/div[1]/table/tbody/tr[1]/td[7]");
	public static By SendSMS_Yes=By.xpath("(//div[@class='radiobutton']/label)[3]");
	public static By SendSMS_No=By.xpath("(//div[@class='radiobutton']/label)[4]");
	public static By Description_Txt=By.xpath("(//textarea[@id='textboxIssuereport'])[2]");
	public static By Upload_Attachment=By.xpath("//input[contains(@src,'/imagedrag.png')]");
	public static By Create_homework_Btn=By.xpath("(//button[@id='btn-next'])[2]");
	public static By Delete_Btn=By.xpath("//button[contains(@class,'delete-post')]");
    public static By update_btn=By.xpath(".//*[@id='btn-next']");
    public static By ClickEditWallGeneric=By.xpath("//div[contains(@class,'edit-button ng-scope')]/a/span");
    public static By WallUpdateButtonGeneric=By.xpath(".//*[@id='btn-next']");
    public static By WallDeleteButtonGeneric=By.xpath("//button[contains(@class,'delete-post')]");
    public static By clickOnDeletePopScreenGeneric=By.xpath(".//*[@id='delete']");
    public static By HomeWorkFillTitleE=By.xpath("//*[@id='title' and @aria-invalid='true']");
    public static By ClickHomeworkEdit=By.xpath("//div[contains(@class,'edit-button ng-scope')]");
    public static By HomeWorkWallTitle=By.xpath("//h4[@class='m-t-20 ng-binding']");
    public static By profileHover=By.xpath("//img[@class='profilePic ng-scope']");
    public static By announcementUser=By.xpath("//small[@class='pull-left text-color paddinglr0 ng-binding']");
	public static By loggedInUser=By.xpath("//h4[@class='margin-bottom-4 ng-binding']");
	public static By uploadFileOnWall=By.xpath("//li[@class='ng-binding ng-scope']");
	public static By DeleteAnnouncement=By.xpath("//button[contains(@class,'delete-post')]");
	public static By Username_Txt=By.id("Fname");
	public static By Password_Txt=By.id("password-lg1");
	public static By Login_Btn=By.xpath("//button[@class='btn btn-primary padding-10-60 m-t-20']");
	public static By schoolStaff_lnk=By.xpath("//a[@data-toggle='tab']");
	public static By allTeacherGroup_chkbox=By.xpath("//span[@class='custom-checkbox ng-scope']/input[contains(@class,'group_code')]");
	
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
	public void clickOnAPostOptions() throws InterruptedException{
		generic.waitPageGotLoad();
		GenericFunctions.WaitFor_visibility(driver, driver.findElement(Post_Lnk));
		Actions builder = new Actions(driver);
		builder.moveToElement(driver.findElement(Post_Lnk)).perform();
		builder.moveToElement(driver.findElement(Homework_Lnk)).click().perform();
	}
	String fileName1 ="",fileName2 ="",fileName3 ="",fileName4 ="";
	String FileExtension1 = "", FileExtension2 = "", FileExtension3 = "", FileExtension4 = "";
	public void uploadImage() throws Throwable{
		String filePath1=System.getProperty("user.dir") + "/dummyFiles/rohit.docx";
		String filePath2=System.getProperty("user.dir") + "/dummyFiles/satya.png";
		String filePath3=System.getProperty("user.dir") + "/dummyFiles/sample.xlsx";
		String filePath4=System.getProperty("user.dir") + "/dummyFiles/test.pdf";
		String NewFilepath1=filePath1.replace( "/","\\");
		String NewFilepath2=filePath2.replace( "/","\\");
		String NewFilepath3=filePath3.replace( "/","\\");
		String NewFilepath4=filePath4.replace( "/","\\");
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
	public String FillCreateHomeWorkDetails() throws InterruptedException, AWTException{
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		driver.switchTo().activeElement();
		Thread.sleep(1000);
		HomeWorkFillTitle("Qa web Homework Title_"+timeStamp);
		clickOnShareWithButton();
		Thread.sleep(4000);
		selectGroupByIndex(2);
		closeGroupSelection();
		Thread.sleep(2000);
		SelectSubject();
		DescriptionHomeWork("Qa web Homework Description "+timeStamp);
		Thread.sleep(3000);
		HomeWorkClickCreate();
		Thread.sleep(3000);
		return "Qa web Homework Title_"+timeStamp;
	}
	public String CreateHomeWorkDetailsToAllClasses() throws InterruptedException, AWTException{
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		driver.switchTo().activeElement();
		Thread.sleep(1000);
		HomeWorkFillTitle("Qa web Homework Title_"+timeStamp);
		clickOnShareWithButton();
		Thread.sleep(4000);
		selectGroupByIndex(1);
		closeGroupSelection();
		Thread.sleep(2000);
		SelectSubject();
		DescriptionHomeWork("Qa web Homework Description "+timeStamp);
		Thread.sleep(3000);
		HomeWorkClickCreate();
		Thread.sleep(3000);
		return "Qa web Homework Title_"+timeStamp;
	}
	public String fillCreateAnnouncementFormWithData(String title, String desc) throws Throwable{
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		driver.switchTo().activeElement();
		Thread.sleep(1000);
		HomeWorkFillTitle("Automation Title_" + title +timeStamp);
		clickOnShareWithButton();
		generic.GoToSleep(4000);
		selectGroupByIndex(2);
		closeGroupSelection();
		Thread.sleep(2000);
		SelectSubject();
		DescriptionHomeWork("Test Description_"+desc);
		generic.GoToSleep(2000);
		HomeWorkClickCreate();
		Thread.sleep(4000);
		return "Automation Title_" + title +timeStamp;	
	}
	public String fillCreateAnnouncementFormWithDifferentTab() throws Throwable{
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		driver.switchTo().activeElement();
		Thread.sleep(1000);
		HomeWorkFillTitle("Qa Web automation Title_" + timeStamp);
		clickOnShareWithButton();
		generic.GoToSleep(4000);
		driver.findElements(schoolStaff_lnk).get(1).click();
		generic.GoToSleep(3000);
		driver.findElements(allTeacherGroup_chkbox).get(0).click();
		closeGroupSelection();
		Thread.sleep(2000);
		SelectSubject();
		DescriptionHomeWork("Test Description_"+timeStamp);
		generic.GoToSleep(2000);
		HomeWorkClickCreate();
		Thread.sleep(4000);
		return "Qa Web automation Title_" + timeStamp;	
	}
	public String fillCreateHomeWorkWithUploadFile() throws Throwable{
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		driver.switchTo().activeElement();
		Thread.sleep(1000);
		HomeWorkFillTitle("Qa Web automation Title_" + timeStamp);
		clickOnShareWithButton();
		generic.GoToSleep(4000);
		selectGroupByIndex(2);
		closeGroupSelection();
		Thread.sleep(2000);
		SelectSubject();
		DescriptionHomeWork("Test Description_"+timeStamp);
		uploadImage();
		generic.GoToSleep(2000);
		HomeWorkClickCreate();
		Thread.sleep(3000);
		return "Qa Web automation Title_" + timeStamp;	
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
	public boolean isHomeWorkCreatorNameSameAsLoggedInUser (String role) throws InterruptedException{
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
	public boolean isFileupNameSameAsUploadedByUser(){
		if(getUploadedFileName1().contains(fileName1)&&getUploadedFileName1().contains(FileExtension1)&&
			getUploadedFileName2().contains(fileName2)&&getUploadedFileName1().contains(FileExtension1)&&
			getUploadedFileName3().contains(fileName3)&&getUploadedFileName1().contains(FileExtension1)&&
			getUploadedFileName4().contains(fileName4)&&getUploadedFileName1().contains(FileExtension1)){
			return true;
		}else{
			return false;
		}
	}
	public void HomeWorkFillTitle(String title) throws InterruptedException{
		

		if(title.trim().length()==0){
			driver.findElement(Title_Txt_Fill).clear();
			return;
		}
		Thread.sleep(2000);
	    driver.findElement(Title_Txt_Fill).clear();
		driver.findElement(Title_Txt_Fill).sendKeys(title);

	}

	public void SelectSubject() throws InterruptedException, AWTException{
		Thread.sleep(2000);
		driver.findElement(selectSubject_DrpDwn).click();
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_DOWN);
		robot.keyRelease(KeyEvent.VK_DOWN);
		Thread.sleep(1000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

		
	}

	public void SelectDate(){

		driver.findElement(Sub_date).click();
		driver.findElement(Sub_date).click();
		driver.findElement(Calender_date).click();

	}
	public void DescriptionHomeWork(String Description){

		driver.findElement(Description_Txt).click();
		driver.findElement(Description_Txt).clear();
		driver.findElement(Description_Txt).sendKeys(Description);
	//	driver.findElement(SendSMS_Yes).click();



	}

	public void HomeWorkClickCreate() throws InterruptedException{


		Actions actions = new Actions(driver);
		actions.keyDown(Keys.CONTROL).sendKeys(Keys.PAGE_DOWN).perform();
		Thread.sleep(1000);
		driver.findElement(Create_homework_Btn).click();
	}

	public String HomeWorkWallTitle(){
		return driver.findElement(HomeWorkWallTitle).getText();

	}
	public String getTitle() throws InterruptedException{
		Thread.sleep(4000);
		return driver.findElement(By.xpath("//h4[@class='m-t-20 ng-binding']")).getText();
	}
	public void Fill_Username_Txt(String inputdata){
		GenericFunctions.WaitFor_visibility(driver, driver.findElement(Username_Txt));
		driver.findElement(Username_Txt).clear();
		driver.findElement(Username_Txt).sendKeys(inputdata);
	}
	public void Fill_Password_Txt(String inputdata){
		driver.findElement(Password_Txt).clear();
		driver.findElement(Password_Txt).sendKeys(inputdata);
	}
	public void ClickOnLogin_Btn(){
		driver.findElement(Login_Btn).click();;
	}
	public void loginInToApplication(String uname, String password) throws InterruptedException{
		Fill_Username_Txt(uname);
		Fill_Password_Txt(password);
		Fill_Username_Txt(uname);
		ClickOnLogin_Btn();
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

	public void ClickHomeworkEdit() throws InterruptedException{

		List<WebElement> elements=driver.findElements(ClickHomeworkEdit);
		elements.get(0).click();



	}

	public void DeleteButtonHomework() throws InterruptedException{
		Thread.sleep(4000);
		driver.findElement(Delete_Btn).click();
	}


	
	public void scrollInModelDialog(){
		WebElement element = driver.findElement(Delete_Btn);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	
	public void HomeWorkFillTitleE(String title) throws InterruptedException{
		Thread.sleep(2000);
		if(title.trim().length()==0){
			driver.findElement(Title_Txt_Fill).clear();
			return;
		}
	    Thread.sleep(3000);
	    driver.findElement(Title_Txt_Edit).clear();
		driver.findElement(Title_Txt_Edit).sendKeys(title);
		driver.findElement(Title_Txt_Edit).sendKeys(title);
		

	}
	
	public String UpdateTitle() throws InterruptedException{

		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		Thread.sleep(1000);
		HomeWorkFillTitleE("Qa Homework updated_"+timeStamp);
		return "Qa Homework updated_"+timeStamp;

	}
	
	public void WallEditButton(){
		
		WallActions.ClickEditWallGeneric(ClickEditWallGeneric);

	}
	
	public void WallUpdateButton(){
		
		WallActions.WallUpdateButtonGeneric(WallUpdateButtonGeneric);
		generic.GoToSleep(1000);
	
	}
	public void clickOnDeleteHomeWork(){
		WebElement element = driver.findElement(DeleteAnnouncement);
		Actions actions = new Actions(driver);
		actions.moveToElement(element);
		actions.perform();
		element.click();
	}
	public void WallDeleteButton(){
		
		WallActions.WallDeleteButtonGeneric(WallDeleteButtonGeneric);
		
	}
	
	public void ConfirmDeletePop(){
		
		WallActions.clickOnDeletePopScreenGeneric(clickOnDeletePopScreenGeneric);
		generic.GoToSleep(1000);
	}
	
	
}

