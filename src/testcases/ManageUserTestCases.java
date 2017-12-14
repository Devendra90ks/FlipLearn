package testcases;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageModules.Announcement;
import pageModules.LoginPage;
import pageModules.ManageUser;

import utils.Xls_Reader;
import helper.DriverSession;
import helper.GenericFunctions;
import helper.UtilityTest;

public class ManageUserTestCases extends DriverSession {
	Xls_Reader xls;
	GenericFunctions generic;
	WebDriverWait wait;
	UtilityTest util;
	LoginPage login;
	Announcement announce;
	ManageUser manageUser;
	@BeforeMethod
	public void OpenFliplearn() throws InterruptedException{
		wait=new WebDriverWait(driver, 25);
		driver.get(BASE_URL);
		login=new LoginPage(driver);
		announce=new Announcement(driver);
		login.ClickOnLogin_link();
		xls=new Xls_Reader();
		generic=new GenericFunctions(driver);
		manageUser = new ManageUser(driver);
	}
	
	@Test(priority=1)
	public void checkHeaderContent() throws InterruptedException{
		login.loginInToApplication(UtilityTest.getUserIDByRole("Admin"),UtilityTest.getUserPasswordByRole("Admin"));
		generic.GoToSleep(3000);
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}
		if(generic.isElementPresent("Continue")){
			announce.selectTeacherProfile();
		}
		Assert.assertEquals(manageUser.isHeaderPresent(), true);
		Assert.assertEquals(manageUser.isManageSubModuleLink_present(), true);
	}
	

}
