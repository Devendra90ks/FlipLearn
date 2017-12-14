package testcases;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageModules.FeePayment;
import pageModules.LoginPage;
import utils.LoadEnvProperty;
import utils.Xls_Reader;
import helper.DriverSession;
import helper.GenericFunctions;

public class FeePaymentTestCases extends DriverSession{

	LoginPage login;
	GenericFunctions generic;
	WebDriverWait wait;
	Xls_Reader xls;
	LoadEnvProperty envpro = new LoadEnvProperty();
	FeePayment fee;

	@BeforeMethod
	public void OpenFliplearn() throws InterruptedException{
		wait=new WebDriverWait(driver, 25);
		driver.get(BASE_URL);
		fee=new FeePayment(driver);
		fee.ClickOnPayFee_link();
		xls=new Xls_Reader();
		generic=new GenericFunctions(driver);
	}

	@Test(priority=1)
	public void FillSchoolDetails() throws InterruptedException{
		String result="",Sheetname="Fee";
		int i,rowCount=xls.getRowCount(Sheetname);
		String schoolName, studentNumber, expected; 
		for(i=2;i<6;i++){
			schoolName=xls.getCellData(Sheetname, "SchoolName", i);
			studentNumber=xls.getCellData(Sheetname, "StudentNumber", i);
			expected = xls.getCellData(Sheetname, "Expected", i);
			generic.waitPageGotLoad();
			fee.fillSchoolDetails(schoolName, studentNumber);
			if(fee.checkConfirmDetailsPage()){
			//	fee.confirmDetails();
				result=result+"Pass,";
				xls.setCellData(Sheetname, "Result", i, "Pass");	
			}
			else{
				String ActualError = fee.getSchoolDetailsErrorMsg();
				if(expected.equalsIgnoreCase(ActualError)){
					xls.setCellData(Sheetname, "Actual", i, ActualError);
					result=result+"Pass,";
					xls.setCellData(Sheetname, "Result", i, "Pass");
				}
				else{
					xls.setCellData(Sheetname, "Actual", i, ActualError);
					result=result+"Fail,";
					xls.setCellData(Sheetname, "Result", i, "Fail");
				}
			}
		}
		if(result.contains("Fail")){
			Assert.assertTrue(false, result);
		}else{
			Assert.assertTrue(true);
		}
	}
	@Test(priority=2)
	public void proceed_FeePayment() throws InterruptedException{
		String result="",Sheetname="Fee";
		int i,rowCount=xls.getRowCount(Sheetname);
		String schoolName, studentNumber, parentName, mobileNumber, emailID, address, state, expected,PaymentOption; 
		for(i=6;i<rowCount+1;i++){
			schoolName=xls.getCellData(Sheetname, "SchoolName", i);
			studentNumber=xls.getCellData(Sheetname, "StudentNumber", i);
			parentName=xls.getCellData(Sheetname, "ParentName", i);
			mobileNumber =xls.getCellData(Sheetname, "MobileNumber", i);
			emailID =xls.getCellData(Sheetname, "EmailId", i);
			address =xls.getCellData(Sheetname, "Address", i);
			state =xls.getCellData(Sheetname, "State", i);
			PaymentOption =xls.getCellData(Sheetname, "PaymentOption", i);
			expected = xls.getCellData(Sheetname, "Expected", i);
			int payment = Integer.parseInt(PaymentOption);
			generic.waitPageGotLoad();
			fee.fillSchoolDetails(schoolName, studentNumber);
			fee.confirmDetails();
			generic.waitPageGotLoad();
			fee.fillParentDetails_PaymentOptions(parentName, mobileNumber, emailID, address, state,payment);
			if(fee.checkPaymentGateway()){
				fee.goBackToFlilearn();
				String actualpayment_Status = fee.verifyPayment();
				if(expected.equalsIgnoreCase(actualpayment_Status)){
					xls.setCellData(Sheetname, "Actual", i, actualpayment_Status);	
					result=result+"Pass,";
					xls.setCellData(Sheetname, "Result", i, "Pass");
				}else{
					xls.setCellData(Sheetname, "Actual", i, actualpayment_Status);
					result=result+"Fail,";
					xls.setCellData(Sheetname, "Result", i, "Fail");
				}
				
			}else{
				xls.setCellData(Sheetname, "Actual", i, "Got Error");
				result=result+"Fail,";
				xls.setCellData(Sheetname, "Result", i, "Fail");
			}
			if(result.contains("Fail")){
				Assert.assertTrue(false, result);
			}else{
				Assert.assertTrue(true);
			}

		}

	}
}
