package testcases;

import helper.DriverSession;
import helper.GenericFunctions;
import pageModules.RoleBaseLogin;
import helper.UtilityTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageModules.LoginPage;
import utils.Xls_Reader;

public class RoleBaseLoginTestCases extends DriverSession {
	Xls_Reader xls;
	LoginPage login;
	GenericFunctions generic;
	UtilityTest util;
	RoleBaseLogin roleBaseLogin;

	@BeforeMethod
	public void OpenFliplearn() throws InterruptedException{
		driver.get(BASE_URL);
		roleBaseLogin = new RoleBaseLogin(driver);
		login=new LoginPage(driver);
		xls=new Xls_Reader();
		generic=new GenericFunctions(driver);
		generic.waitPageGotLoad();
		login.ClickOnLogin_link();
	}

	@Test(priority=1)
	public void checkAdminLogin() throws Throwable{
		String role="Admin";
		login.loginInToApplication(UtilityTest.getUserIDByRole(role),UtilityTest.getUserPasswordByRole(role));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}
		Assert.assertEquals(roleBaseLogin.getUserROle(), role);
		roleBaseLogin.Logout();
	}
	@Test(priority=2)
	public void checkTeacherLogin() throws Throwable{
		String role="Teacher";
		login.loginInToApplication(UtilityTest.getUserIDByRole(role),UtilityTest.getUserPasswordByRole(role));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}
		generic.waitPageGotLoad();
		Assert.assertEquals(roleBaseLogin.getUserROle(), role);
		roleBaseLogin.Logout();
	}
	@Test(priority=3)
	public void checkStudentLogin() throws Throwable{
		String role="Student";
		login.loginInToApplication(UtilityTest.getUserIDByRole(role),UtilityTest.getUserPasswordByRole(role));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}
		Assert.assertEquals(roleBaseLogin.getUserROle(), role);
		roleBaseLogin.Logout();
	}
	@Test(priority=4)
	public void checkParentLogin() throws Throwable{
		String role="Parent";
		login.loginInToApplication(UtilityTest.getUserIDByRole(role),UtilityTest.getUserPasswordByRole(role));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}
		Assert.assertEquals(roleBaseLogin.getUserROle(), role);
		roleBaseLogin.Logout();
	}
	@Test(priority=5)
	public void checkGuestLogin() throws Throwable{
		login.loginInToApplication(UtilityTest.getUserIDByRole("Guest"),UtilityTest.getUserPasswordByRole("Guest"));
		generic.waitPageGotLoad();
		if(generic.isElementPresent("Enter Your 10 digit Mobile Number")){
			login.skipMobileNumber();
		}
		Assert.assertTrue(roleBaseLogin.isloggedInUserAsGuest(), "Logged user is not guest user");
		roleBaseLogin.GuestLogout();
	}

}
