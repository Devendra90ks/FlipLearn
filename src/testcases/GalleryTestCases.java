package testcases;

import java.awt.AWTException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import helper.DriverSession;
import helper.GenericFunctions;
import helper.UtilityTest;
import helper.WallActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageModules.Announcement;
import pageModules.Gallery;
import pageModules.LoginPage;
import utils.LoadEnvProperty;
import utils.Xls_Reader;

public class GalleryTestCases extends DriverSession{


	Xls_Reader xls;
	LoginPage login;
	GenericFunctions generic;
	WebDriverWait wait;
	Gallery gallery;
	LoadEnvProperty envpro = new LoadEnvProperty();
	Announcement announce=new Announcement(driver);

	@BeforeMethod
	public void OpenURL() throws InterruptedException{
		wait=new WebDriverWait(driver, 25);
		driver.get(BASE_URL);
		generic.waitPageGotLoad();
		login=new LoginPage(driver);
		xls=new Xls_Reader();
		generic=new GenericFunctions(driver);
		gallery=new Gallery(driver);
		login.ClickOnLogin_link();
	}

	@Test(priority=1)
	public void createAlbum() throws Throwable{
		String role="Teacher";
		login.loginInToApplication(UtilityTest.getUserIDByRole(role),UtilityTest.getUserPasswordByRole(role));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}

		if(generic.isElementPresent("Continue")){
			gallery.selectTeacherProfile();
		}
		Thread.sleep(5000);
		gallery.clickOnAPostOptions();
		String Title = gallery.fillCreateAlbum_Form();
		if(gallery.isTitleDisplayed(Title)){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false);
		}
		if(gallery.isAlbumCreatorNameSameAsLoggedInUser(role)){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false,"Creater Role not matched");
		}
		if(gallery.isImageNameSameAsUploadedByUser()){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false,"Attachement was missing");
		}
		WallActions.Logout();
	}

	@Test(priority=2)
	public void checkAlbumIsGettingDeletedWithTeacher() throws Throwable{
		String role="Teacher";
		login.loginInToApplication(UtilityTest.getUserIDByRole(role),UtilityTest.getUserPasswordByRole(role));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}

		if(generic.isElementPresent("Continue")){
			gallery.selectTeacherProfile();
		}
		Thread.sleep(5000);
		gallery.clickOnAPostOptions();
		String Title = gallery.fillCreateAlbum_Form();
		if(gallery.isTitleDisplayed(Title)){
			gallery.clickOnEditAlbum();
			generic.GoToSleep(2000);
			gallery.scrollInModelDialog();
			generic.GoToSleep(4000);
			gallery.clickOnDeleteAlbum();
			gallery.ConfirmDeletePop();
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

	@Test(priority=3)
	public void checkAlbumIsGettingEditedWithTeacher() throws Throwable{
		String timeStamp="";
		String role="Teacher";
		login.loginInToApplication(UtilityTest.getUserIDByRole(role),UtilityTest.getUserPasswordByRole(role));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}

		if(generic.isElementPresent("Continue")){
			gallery.selectTeacherProfile();
		}
		Thread.sleep(5000);
		gallery.clickOnAPostOptions();
		String Title = gallery.fillCreateAlbum_Form();
		if(gallery.isTitleDisplayed(Title)){
			gallery.clickOnEditAlbum();
			generic.GoToSleep(2000);
			timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
			gallery.fillTitle("Qa Web updated_"+timeStamp);
			gallery.scrollInModelDialog();
			generic.GoToSleep(2000);
			gallery.albumUpdateButton();
			if(gallery.isTitleDisplayed("Qa Web updated_"+timeStamp)){
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
	public void createAlbumWithMultipleAttachment() throws Throwable{
		String role="Teacher";
		login.loginInToApplication(UtilityTest.getUserIDByRole(role),UtilityTest.getUserPasswordByRole(role));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}

		if(generic.isElementPresent("Continue")){
			gallery.selectTeacherProfile();
		}
		Thread.sleep(5000);
		gallery.clickOnAPostOptions();
		String Title = gallery.CreateAlbumWithMultipleAttachment();
		if(gallery.isTitleDisplayed(Title)){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false);
		}
		if(gallery.isAlbumCreatorNameSameAsLoggedInUser(role)){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false,"Creater Role not matched");
		}
		if(gallery.isFileNameSameAsUploadedByUser()){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false,"Attachement was missing");
		}
		WallActions.Logout();
	}
	@Test(priority=5)
	public void createAlbum_ByAdmin() throws Throwable{
		String role="Admin";
		login.loginInToApplication(UtilityTest.getUserIDByRole(role),UtilityTest.getUserPasswordByRole(role));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}
		if(generic.isElementPresent("Continue")){
			gallery.selectTeacherProfile();
		}
		Thread.sleep(5000);
		gallery.clickOnAPostOptions();
		String Title = gallery.fillCreateAlbum_Form();
		if(gallery.isTitleDisplayed(Title)){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false);
		}
		if(gallery.isAlbumCreatorNameSameAsLoggedInUser(role)){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false,"Creater Role not matched");
		}
		WallActions.Logout();
	}

	@Test(priority=6)
	public void checkAlbumIsGettingDeleted_ByAdmin() throws Throwable{
		String role="Admin";
		login.loginInToApplication(UtilityTest.getUserIDByRole(role),UtilityTest.getUserPasswordByRole(role));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}

		if(generic.isElementPresent("Continue")){
			gallery.selectTeacherProfile();
		}
		Thread.sleep(5000);
		gallery.clickOnAPostOptions();
		String Title = gallery.fillCreateAlbum_Form();
		if(gallery.isTitleDisplayed(Title)){
			gallery.clickOnEditAlbum();
			generic.GoToSleep(2000);
			gallery.scrollInModelDialog();
			generic.GoToSleep(4000);
			gallery.clickOnDeleteAlbum();
			gallery.ConfirmDeletePop();
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

	@Test(priority=7)
	public void checkAlbumIsGettingEdited_ByAdmin() throws Throwable{
		String timeStamp="";
		String role="Admin";
		login.loginInToApplication(UtilityTest.getUserIDByRole(role),UtilityTest.getUserPasswordByRole(role));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}

		if(generic.isElementPresent("Continue")){
			gallery.selectTeacherProfile();
		}
		Thread.sleep(5000);
		gallery.clickOnAPostOptions();
		String Title = gallery.fillCreateAlbum_Form();
		if(gallery.isTitleDisplayed(Title)){
			gallery.clickOnEditAlbum();
			generic.GoToSleep(2000);
			timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
			gallery.fillTitle("Qa Web updated_"+timeStamp);
			gallery.scrollInModelDialog();
			generic.GoToSleep(2000);
			gallery.albumUpdateButton();
			if(gallery.isTitleDisplayed("Qa Web updated_"+timeStamp)){
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
	public void validateFilterforGallery() throws InterruptedException{
		login.loginInToApplication(UtilityTest.getUserIDByRole("Teacher"),UtilityTest.getUserPasswordByRole("Teacher"));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}
		Thread.sleep(3000);
		driver.findElement(announce.filTerDropdown_Lnk).click();
		generic.clickOnLinkText("Album");
		Thread.sleep(2000);
		for(WebElement element : driver.findElements(announce.filterTypeHeader)){
			if(!(element.getText().equalsIgnoreCase("ALBUM"))){
				Assert.assertTrue(true);
			}
		}
		WallActions.Logout();
	}
	@Test(priority=9)
	public void checkGalleryWithDataCombination() throws Throwable{
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
			gallery.clickOnAPostOptions();
			title=xls.getCellData(Sheetname, "GalleryTitle", i);
			desc=xls.getCellData(Sheetname, "GalleryDesc", i);
			Title = gallery.fillCreateAlbum_FormWithData(title, desc);
			if(gallery.isTitleDisplayed(Title)){
				xls.setCellData(Sheetname, "Result_Gallery", i, "Pass");
				Assert.assertTrue(true);
			}else{
				xls.setCellData(Sheetname, "Result_Gallery", i, "Fail");
				Assert.assertTrue(false,"Title Not Matched");
			}
			hmap=generic.getMessagesDetailFromDB(Title);
			if(!(hmap.get("title").equalsIgnoreCase(Title))){
				Assert.assertTrue(false,"Title Not Matched from database");
			}
		}
	}

	@Test(priority=10)
	public void createAlbumToAllClasses() throws Throwable{
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
		gallery.clickOnAPostOptions();
		String Title = gallery.createAlbumFormToAllClasses();
		if(gallery.isTitleDisplayed(Title)){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false,"Title Not Matched");
		}
		if(gallery.isAlbumCreatorNameSameAsLoggedInUser(role)){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false,"Role name not matched");
		}
		hmap=generic.getMessagesDetailFromDB(Title);
		if(!(hmap.get("title").equalsIgnoreCase(Title))){
			Assert.assertTrue(false,"Title Not Matched from database");
		}
	}
	
	@Test(priority=11)
	public void createAlbumToAllTeachersGroup() throws Throwable{
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
		gallery.clickOnAPostOptions();
		String Title = gallery.fillCreateAlbumWithDifferentTab();
		if(gallery.isTitleDisplayed(Title)){
			Assert.assertTrue(true);
		}else{
			Assert.assertTrue(false,"Title Not Matched");
		}
		if(gallery.isAlbumCreatorNameSameAsLoggedInUser(role)){
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
