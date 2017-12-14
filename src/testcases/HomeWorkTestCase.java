package testcases;


import java.awt.AWTException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageModules.Announcement;
import pageModules.HomeWork;
import pageModules.LoginPage;
import utils.LoadEnvProperty;
import utils.Xls_Reader;
import helper.DriverSession;
import helper.GenericFunctions;
import helper.UtilityTest;
import helper.WallActions;

public class HomeWorkTestCase extends DriverSession {
	HomeWork homework;
	Xls_Reader xls;
	LoginPage login;
	GenericFunctions generic;
	WebDriverWait wait;
	Announcement announce=new Announcement(driver);
	LoadEnvProperty envpro = new LoadEnvProperty();


	@BeforeMethod
	public void OpenURL() throws InterruptedException{
		wait=new WebDriverWait(driver, 25);
		driver.get(BASE_URL);
		login=new LoginPage(driver);
		homework=new HomeWork(driver);
		announce=new Announcement(driver);
		xls=new Xls_Reader();
		generic=new GenericFunctions(driver);
		generic.waitPageGotLoad();
		login.ClickOnLogin_link();
	}
	@Test(priority=1)
	public void CreateHomeWork() throws InterruptedException, AWTException, IOException {
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
		homework.clickOnAPostOptions();
		String Title = homework.FillCreateHomeWorkDetails();
		if(homework.isTitleDisplayed(Title)){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false);
		}
		if(homework.isHomeWorkCreatorNameSameAsLoggedInUser(role)){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false);
		}
		WallActions.Logout();
	}
	@Test(priority=2)
	public void checkHomeWorkWithUpload_CreatedWithTeacher() throws Throwable{
		String role="Teacher";
		login.loginInToApplication(UtilityTest.getUserIDByRole(role),UtilityTest.getUserPasswordByRole(role));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}
		if(generic.isElementPresent("Continue")){
			announce.selectTeacherProfile();
		}
		Thread.sleep(7000);
		homework.clickOnAPostOptions();
		String Title = homework.fillCreateHomeWorkWithUploadFile();
		if(homework.isTitleDisplayed(Title)){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false);
		}
		if(homework.isHomeWorkCreatorNameSameAsLoggedInUser(role)){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false);
		}
		if(homework.isFileupNameSameAsUploadedByUser()){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false);
		}
		WallActions.Logout();
	}
	@Test(priority=3)
	public void EditHomeWork() throws InterruptedException, AWTException, IOException{
		Thread.sleep(5000);
		login.loginInToApplication(UtilityTest.getUserIDByRole("Teacher"),UtilityTest.getUserPasswordByRole("Teacher"));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}

		if(generic.isElementPresent("Continue")){
			announce.selectTeacherProfile();
		}
		Thread.sleep(5000);
		homework.clickOnAPostOptions();
		String Title = homework.FillCreateHomeWorkDetails();
		generic.GoToSleep(1000);
		if(homework.isTitleDisplayed(Title)){
			homework.WallEditButton();
			generic.GoToSleep(1000);
			String TitleE=homework.UpdateTitle();
			generic.GoToSleep(1000);
			homework.scrollInModelDialog();
			homework.WallUpdateButton();
			if(homework.isTitleDisplayed(TitleE)){
				Assert.assertTrue(true);
			}else{
				Assert.assertTrue(false);
			}
		}else{
			Assert.assertTrue(false);
		}
		WallActions.Logout();
	}
	@Test(priority=4)
	public void DeleteHomework() throws InterruptedException, AWTException, IOException{
		login.loginInToApplication(UtilityTest.getUserIDByRole("Teacher"),UtilityTest.getUserPasswordByRole("Teacher"));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}

		if(generic.isElementPresent("Continue")){
			announce.selectTeacherProfile();
		}
		Thread.sleep(5000);
		homework.clickOnAPostOptions();
		String Title = homework.FillCreateHomeWorkDetails();
		if (homework.isTitleDisplayed(Title)){
			homework.WallEditButton();	
			generic.GoToSleep(1000);
			homework.scrollInModelDialog();
			homework.clickOnDeleteHomeWork();
			generic.GoToSleep(1000);
			homework.ConfirmDeletePop();
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
					Assert.assertTrue(false);
				}
			}
		}else{
			Assert.assertTrue(false);
		}
		generic.GoToSleep(1000);
		WallActions.Logout();
	}
	@Test(priority=5)
	public void CreateHomeWork_ByAdmin() throws InterruptedException, AWTException, IOException {
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
		generic.GoToSleep(1000);
		homework.clickOnAPostOptions();
		String Title = homework.FillCreateHomeWorkDetails();
		if(homework.isTitleDisplayed(Title)){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false);
		}
		if(homework.isHomeWorkCreatorNameSameAsLoggedInUser(role)){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false);
		}
		WallActions.Logout();
	}
	@Test(priority=6)
	public void checkHomeWorkWithUpload_CreatedByAdmin() throws Throwable{
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
		homework.clickOnAPostOptions();
		String Title = homework.fillCreateHomeWorkWithUploadFile();
		if(homework.isTitleDisplayed(Title)){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false);
		}
		if(homework.isHomeWorkCreatorNameSameAsLoggedInUser(role)){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false);
		}
		if(homework.isFileupNameSameAsUploadedByUser()){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false);
		}
		WallActions.Logout();
	}
	@Test(priority=7)
	public void EditHomeWork_ByAdmin() throws InterruptedException, AWTException, IOException{
		Thread.sleep(5000);
		login.loginInToApplication(UtilityTest.getUserIDByRole("Admin"),UtilityTest.getUserPasswordByRole("Admin"));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}

		if(generic.isElementPresent("Continue")){
			announce.selectTeacherProfile();
		}
		Thread.sleep(5000);
		homework.clickOnAPostOptions();
		String Title = homework.FillCreateHomeWorkDetails();
		generic.GoToSleep(3000);
		if(homework.isTitleDisplayed(Title)){
			homework.WallEditButton();
			generic.GoToSleep(1000);
			String TitleE=homework.UpdateTitle();
			generic.GoToSleep(1000);
			homework.scrollInModelDialog();
			homework.WallUpdateButton();
			if(homework.isTitleDisplayed(TitleE)){
				Assert.assertTrue(true);
			}else{
				Assert.assertTrue(false);
			}
		}else{
			Assert.assertTrue(false);
		}
		WallActions.Logout();
	}
	@Test(priority=8)
	public void DeleteHomework_ByAdmin() throws InterruptedException, AWTException, IOException{
		login.loginInToApplication(UtilityTest.getUserIDByRole("Admin"),UtilityTest.getUserPasswordByRole("Admin"));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}

		if(generic.isElementPresent("Continue")){
			announce.selectTeacherProfile();
		}
		Thread.sleep(5000);
		homework.clickOnAPostOptions();
		String Title = homework.FillCreateHomeWorkDetails();
		if (homework.isTitleDisplayed(Title)){
			homework.WallEditButton();	
			generic.GoToSleep(1000);
			homework.scrollInModelDialog();
			homework.clickOnDeleteHomeWork();
			generic.GoToSleep(1000);
			homework.ConfirmDeletePop();
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
					Assert.assertTrue(false);
				}
			}
		}else{
			Assert.assertTrue(false);
		}
		WallActions.Logout();
	}
	@Test(priority=9)
	public void checkHomeWorkCreated_DisplayedToStudent() throws Throwable{
		login.loginInToApplication(UtilityTest.getUserIDByRole("Teacher"),UtilityTest.getUserPasswordByRole("Teacher"));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}

		if(generic.isElementPresent("Continue")){
			announce.selectTeacherProfile();
		}
		Thread.sleep(5000);
		homework.clickOnAPostOptions();
		String Title = homework.FillCreateHomeWorkDetails();
		String HomeWork_TeacherName = homework.getUserName();
		WallActions.Logout();
		generic.GoToSleep(2000);
		homework.loginInToApplication(UtilityTest.getUserIDByRole("Student"),UtilityTest.getUserPasswordByRole("Student"));
		generic.GoToSleep(3000);
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}
		if(generic.isElementPresent("Continue")){
			announce.selectTeacherProfile();
		}
		Thread.sleep(3000);
		if(homework.isTitleDisplayed(Title)){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false);
		}
		if(HomeWork_TeacherName.equalsIgnoreCase(homework.getUserName())){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false,"Teacher Role name mismatched");
		}
		WallActions.Logout();
	}
	@Test(priority=10)
	public void validateFilterforHomework() throws InterruptedException{
		login.loginInToApplication(UtilityTest.getUserIDByRole("Teacher"),UtilityTest.getUserPasswordByRole("Teacher"));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}
		Thread.sleep(3000);
		driver.findElement(Announcement.filTerDropdown_Lnk).click();
		generic.clickOnLinkText("Homework");
		Thread.sleep(3000);
		for(WebElement element : driver.findElements(Announcement.filterTypeHeader)){
			if(!(element.getText().equalsIgnoreCase("HOMEWORK"))){
				Assert.assertTrue(true);
			}
		}
		WallActions.Logout();
	}
	@Test(priority=11)
	public void checkHomeworkWithDataCombination() throws Throwable{
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
			homework.clickOnAPostOptions();
			title=xls.getCellData(Sheetname, "HomeworkTitle", i);
			desc=xls.getCellData(Sheetname, "HomeworkDesc", i);
			Title = homework.fillCreateAnnouncementFormWithData(title, desc);
			if(homework.isTitleDisplayed(Title)){
				xls.setCellData(Sheetname, "Result_Home", i, "Pass");
				Assert.assertTrue(true);
			}else{
				xls.setCellData(Sheetname, "Result_Home", i, "Fail");
				Assert.assertTrue(false,"Title Not Matched");
			}
			hmap=generic.getMessagesDetailFromDB(Title);
			if(!(hmap.get("title").equalsIgnoreCase(Title))){
				Assert.assertTrue(false,"Title Not Matched from database");
			}
		}
	}

	@Test(priority=12)
	public void createHomeWorkToAllClasses() throws Throwable{
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
		homework.clickOnAPostOptions();
		String Title = homework.CreateHomeWorkDetailsToAllClasses();
		if(homework.isTitleDisplayed(Title)){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false,"Title Not Matched");
		}
		if(homework.isHomeWorkCreatorNameSameAsLoggedInUser(role)){
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
	public void createHomeWorkToAllTeachersGroup() throws Throwable{
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
		homework.clickOnAPostOptions();
		String Title = homework.fillCreateAnnouncementFormWithDifferentTab();
		if(homework.isTitleDisplayed(Title)){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false,"Title Not Matched");
		}
		if(homework.isHomeWorkCreatorNameSameAsLoggedInUser(role)){
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






