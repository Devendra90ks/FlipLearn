package testcases;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageModules.Announcement;
import pageModules.LoginPage;
import utils.LoadEnvProperty;
import utils.Xls_Reader;
import helper.DriverSession;
import helper.GenericFunctions;
import helper.UtilityTest;
import helper.WallActions;
public class AnnouncementTestCases extends DriverSession{
	Announcement announce;
	Xls_Reader xls;
	LoginPage login;
	GenericFunctions generic;
	WebDriverWait wait;
	UtilityTest util;
	LoadEnvProperty envpro = new LoadEnvProperty();

	@BeforeMethod
	public void OpenFliplearn() throws InterruptedException{
		
		wait=new WebDriverWait(driver, 25);
		login=new LoginPage(driver);
		xls=new Xls_Reader();
		generic=new GenericFunctions(driver);
		announce=new Announcement(driver);
		driver.get(BASE_URL);
		generic.waitPageGotLoad();
		login.ClickOnLogin_link();
	}

	@Test(priority=1)
	public void checkAnnouncementIsGettingCreatedWithTeacher() throws Throwable{
		String role="Teacher";
		login.loginInToApplication(UtilityTest.getUserIDByRole(role),UtilityTest.getUserPasswordByRole(role));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}
		if(generic.isElementPresent("Continue")){
			announce.selectTeacherProfile();
		}
		Thread.sleep(5000);
		announce.clickOnAPostOptions();
		String Title = announce.fillCreateAnnouncementForm();
		if(announce.isTitleDisplayed(Title)){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false,"Title Not Matched");
		}
		if(announce.isAnnouncementCreatorNameSameAsLoggedInUser(role)){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false,"Role name not matched");
		}
		WallActions.Logout();
	}
	@Test(priority=2)
	public void checkAnnouncementWithUpload_CreatedWithTeacher() throws Throwable{
		String role="Teacher";
		login.loginInToApplication(UtilityTest.getUserIDByRole(role),UtilityTest.getUserPasswordByRole(role));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}
		if(generic.isElementPresent("Continue")){
			announce.selectTeacherProfile();
		}
		Thread.sleep(5000);
		announce.clickOnAPostOptions();
		String Title = announce.fillCreateAnnouncementWithUploadFile();
		if(announce.isTitleDisplayed(Title)){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false,"Title Not matched");
		}
		if(announce.isAnnouncementCreatorNameSameAsLoggedInUser(role)){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false,"Creater Role not matched");
		}
		if(announce.isFileNameSameAsUploadedByUser()){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false,"Attachement was missing");
		}
		WallActions.Logout();
	}
	@Test(priority=3)
	public void checkAnnouncementIsGettingDeletedWithTeacher() throws Throwable{
		login.loginInToApplication(UtilityTest.getUserIDByRole("Teacher"),UtilityTest.getUserPasswordByRole("Teacher"));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}

		if(generic.isElementPresent("Continue")){
			announce.selectTeacherProfile();
		}
		Thread.sleep(5000);
		announce.clickOnAPostOptions();
		String Title = announce.fillCreateAnnouncementForm();
		if(announce.isTitleDisplayed(Title)){
			announce.clickOnEditAnnuncement();
			generic.GoToSleep(2000);
			announce.scrollInModelDialog();
			generic.GoToSleep(2000);
			announce.clickOnDeleteAnnouncement();
			announce.ConfirmDeletePop();
			if(!generic.isElementPresent(Title)){
				Assert.assertTrue(true);
			}else{
				int i=0;
				while((generic.isElementPresent(Title)) && i<10){
					driver.navigate().refresh();
					generic.GoToSleep(1000);
					i++;
				}
				if(!generic.isElementPresent(Title)){
					Assert.assertTrue(true);
				}else{
					Assert.assertTrue(false,"Title is still displaying after deleting");
				}
			}
		}else{
			Assert.assertTrue(false,"Creted Announcement not displayed");
		}
		WallActions.Logout();
	}
	@Test(priority=4)
	public void checkAnnouncementIsGettingEditedWithTeacher() throws Throwable{
		String timeStamp="";
		login.loginInToApplication(UtilityTest.getUserIDByRole("Teacher"),UtilityTest.getUserPasswordByRole("Teacher"));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}
		if(generic.isElementPresent("Continue")){
			announce.selectTeacherProfile();
		}
		Thread.sleep(5000);
		announce.clickOnAPostOptions();
		String Title = announce.fillCreateAnnouncementForm();
		if(announce.isTitleDisplayed(Title)){
			announce.clickOnEditAnnuncement();
			generic.GoToSleep(2000);
			timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
			announce.fillTitle("Qa Web updated_"+timeStamp);
			announce.scrollInModelDialog();
			generic.GoToSleep(2000);
			announce.announcementUpdateButton();
			if(announce.isTitleDisplayed("Qa Web updated_"+timeStamp)){
				Assert.assertTrue(true);
			}else{
				Assert.assertTrue(false,"Edited announcement not displayed");
			}
		}else{
			Assert.assertTrue(false,"Created Announcement not displayed");
		}
		WallActions.Logout();
	}
	@Test(priority=5)
	public void checkAnnouncementIsGettingCreatedWithAdmin() throws Throwable{
		String role="Admin";
		login.loginInToApplication(UtilityTest.getUserIDByRole(role),UtilityTest.getUserPasswordByRole(role));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}
		if(generic.isElementPresent("Continue")){
			announce.selectTeacherProfile();
		}
		Thread.sleep(5000);
		announce.clickOnAPostOptions();
		String Title = announce.fillCreateAnnouncementForm();
		if(announce.isTitleDisplayed(Title)){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false,"Title Not Matched");
		}
		if(announce.isAnnouncementCreatorNameSameAsLoggedInUser(role)){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false,"Role name not matched");
		}
		WallActions.Logout();
	}
	@Test(priority=6)
	public void checkAnnouncementWithUpload_CreatedWithAdmin() throws Throwable{
		String role="Admin";
		login.loginInToApplication(UtilityTest.getUserIDByRole(role),UtilityTest.getUserPasswordByRole(role));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}
		if(generic.isElementPresent("Continue")){
			announce.selectTeacherProfile();
		}
		Thread.sleep(5000);
		announce.clickOnAPostOptions();
		String Title = announce.fillCreateAnnouncementWithUploadFile();
		if(announce.isTitleDisplayed(Title)){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false,"Title Not matched");
		}
		if(announce.isAnnouncementCreatorNameSameAsLoggedInUser(role)){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false,"Creater Role not matched");
		}
		if(announce.isFileNameSameAsUploadedByUser()){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false,"Attachement was missing");
		}
		WallActions.Logout();
	}
	@Test(priority=7)
	public void checkAnnouncementIsGettingDeletedWithAdmin() throws Throwable{
		login.loginInToApplication(UtilityTest.getUserIDByRole("Admin"),UtilityTest.getUserPasswordByRole("Admin"));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}

		if(generic.isElementPresent("Continue")){
			announce.selectTeacherProfile();
		}
		Thread.sleep(5000);
		announce.clickOnAPostOptions();
		String Title = announce.fillCreateAnnouncementForm();
		if(announce.isTitleDisplayed(Title)){
			announce.clickOnEditAnnuncement();
			generic.GoToSleep(2000);
			announce.scrollInModelDialog();
			generic.GoToSleep(2000);
			announce.clickOnDeleteAnnouncement();
			announce.ConfirmDeletePop();
			if(!generic.isElementPresent(Title)){
				Assert.assertTrue(true);
			}else{
				int i=0;
				while((generic.isElementPresent(Title)) && i<10){
					driver.navigate().refresh();
					generic.GoToSleep(1000);
					i++;
				}
				if(!generic.isElementPresent(Title)){
					Assert.assertTrue(true);
				}else{
					Assert.assertTrue(false,"Title is still displaying after deleting");
				}

			}
		}else{
			Assert.assertTrue(false,"Creted Announcement not displayed");
		}
		WallActions.Logout();
	}
	@Test(priority=8)
	public void checkAnnouncementIsGettingEditedWithAdmin() throws Throwable{
		String timeStamp="";
		login.loginInToApplication(UtilityTest.getUserIDByRole("Admin"),UtilityTest.getUserPasswordByRole("Admin"));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}
		if(generic.isElementPresent("Continue")){
			announce.selectTeacherProfile();
		}
		Thread.sleep(5000);
		announce.clickOnAPostOptions();
		String Title = announce.fillCreateAnnouncementForm();
		if(announce.isTitleDisplayed(Title)){
			announce.clickOnEditAnnuncement();
			generic.GoToSleep(2000);
			timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
			announce.fillTitle("Qa Web updated_"+timeStamp);
			announce.scrollInModelDialog();
			generic.GoToSleep(2000);
			announce.announcementUpdateButton();
			if(announce.isTitleDisplayed("Qa Web updated_"+timeStamp)){
				Assert.assertTrue(true);
			}else{
				Assert.assertTrue(false,"Edited announcement not displayed");
			}
		}else{
			Assert.assertTrue(false,"Created Announcement not displayed");
		}
		WallActions.Logout();
	}
	@Test(priority=9)
	public void checkAnnouncementCreated_DisplayedToStudent() throws Throwable{
		login.loginInToApplication(UtilityTest.getUserIDByRole("Teacher"),UtilityTest.getUserPasswordByRole("Teacher"));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}
		if(generic.isElementPresent("Continue")){
			announce.selectTeacherProfile();
		}
		Thread.sleep(5000);
		announce.clickOnAPostOptions();
		String Title = announce.fillCreateAnnouncementForm();
		String Announcement_TeacherName = announce.getUserName();
		WallActions.Logout();
		generic.GoToSleep(2000);
		driver.navigate().refresh();
		login.ClickOnLogin_link();
		login.loginInToApplication(UtilityTest.getUserIDByRole("Student"),UtilityTest.getUserPasswordByRole("Student"));
		generic.GoToSleep(3000);
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}

		if(generic.isElementPresent("Continue")){
			announce.selectTeacherProfile();
		}
		Thread.sleep(3000);
		if(announce.isTitleDisplayed(Title)){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false,"Created Title not displayed");
		}
		if(Announcement_TeacherName.equalsIgnoreCase(announce.getUserName())){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false,"Teacher Role name mismatched");
		}
		WallActions.Logout();
	}
	@Test(priority=10)
	public void validateFilterforAnnouncement() throws InterruptedException{
		login.loginInToApplication(UtilityTest.getUserIDByRole("Teacher"),UtilityTest.getUserPasswordByRole("Teacher"));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}
		Thread.sleep(5000);
		driver.findElement(Announcement.filTerDropdown_Lnk).click();
		generic.clickOnLinkText("Announcement");
		Thread.sleep(3000);
		for(WebElement element : driver.findElements(Announcement.filterTypeHeader)){
			if(!(element.getText().equalsIgnoreCase("ANNOUNCEMENT"))){
				Assert.assertTrue(true);
			}
		}	
		WallActions.Logout();
	}
	@Test(priority=11)
	public void checkAnnouncementWithDataCombination() throws Throwable{
		String role="Teacher";
		String Title,title,desc,Sheetname="WallPage";
		HashMap<String, String> hmap = new HashMap<>();
		login.loginInToApplication(UtilityTest.getUserIDByRole(role),UtilityTest.getUserPasswordByRole(role));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}
		if(generic.isElementPresent("Continue")){
			announce.selectTeacherProfile();
		}
		Thread.sleep(5000);
		for(int i=2;i<xls.getRowCount(Sheetname)+1;i++){
			announce.clickOnAPostOptions();
			title=xls.getCellData(Sheetname, "AnnouncementTitle", i);
			desc=xls.getCellData(Sheetname, "AnnounceDesc", i);
			Title = announce.fillCreateAnnouncementFormWithData(title, desc);
			if(announce.isTitleDisplayed(Title)){
				xls.setCellData(Sheetname, "Result_Announce", i, "Pass");
				Assert.assertTrue(true);
			}else{
				xls.setCellData(Sheetname, "Result_Announce", i, "Fail");
				Assert.assertTrue(false,"Title Not Matched");
			}
			hmap=generic.getMessagesDetailFromDB(Title);
			if(!(hmap.get("title").equalsIgnoreCase(Title))){
				Assert.assertTrue(false,"Title Not Matched from database");
			}
		}
	}
	@Test(priority=12)
	public void createAnnouncementToAllClasses() throws Throwable{
		String role="Teacher";
		HashMap<String, String> hmap = new HashMap<>();
		login.loginInToApplication(UtilityTest.getUserIDByRole(role),UtilityTest.getUserPasswordByRole(role));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}
		if(generic.isElementPresent("Continue")){
			announce.selectTeacherProfile();
		}
		Thread.sleep(5000);
		announce.clickOnAPostOptions();
		String Title = announce.CreateAnnouncementFormToAllClasses();
		if(announce.isTitleDisplayed(Title)){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false,"Title Not Matched");
		}
		if(announce.isAnnouncementCreatorNameSameAsLoggedInUser(role)){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false,"Role name not matched");
		}
		hmap=generic.getMessagesDetailFromDB(Title);
		if(!(hmap.get("title").equalsIgnoreCase(Title))){
			Assert.assertTrue(false,"Title Not Matched from database");
		}
	}
	@Test(priority=13)
	public void createAnnouncementToAllTeachersGroup() throws Throwable{
		String role="Teacher";
		HashMap<String, String> hmap = new HashMap<>();
		login.loginInToApplication(UtilityTest.getUserIDByRole(role),UtilityTest.getUserPasswordByRole(role));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}
		if(generic.isElementPresent("Continue")){
			announce.selectTeacherProfile();
		}
		Thread.sleep(5000);
		announce.clickOnAPostOptions();
		String Title = announce.fillCreateAnnouncementFormWithDifferentTab();
		if(announce.isTitleDisplayed(Title)){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false,"Title Not Matched");
		}
		if(announce.isAnnouncementCreatorNameSameAsLoggedInUser(role)){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false,"Role name not matched");
		}
		hmap=generic.getMessagesDetailFromDB(Title);
		if(!(hmap.get("title").equalsIgnoreCase(Title))){
			Assert.assertTrue(false,"Title Not Matched from database");
		}
	}
	
}
