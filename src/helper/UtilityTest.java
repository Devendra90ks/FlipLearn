package helper;

import org.openqa.selenium.WebDriver;

import utils.Xls_Reader;

public class UtilityTest {
	GenericFunctions generic;
	WebDriver driver;
	Xls_Reader xls=new Xls_Reader();
	
	public static  String getUserIDByRole(String roles){
		Xls_Reader xls=new Xls_Reader();
		String userId = null ;
		String Sheetname="Credentials";
		String role = "Role";
		String userIdColum = "UserId";
		int rowCount=xls.getRowCount(Sheetname);
			for(int i= 0; i<rowCount+1;i++){
				if(roles.equalsIgnoreCase(xls.getCellData(Sheetname, role, i))){
				userId= xls.getCellData(Sheetname, userIdColum, i);
				return userId;
				}
			}
			return userId;
		}
	
	public static  String getUserPasswordByRole(String roles){
		Xls_Reader xls=new Xls_Reader();
		String password = null ;
		String Sheetname="Credentials";
		String role = "Role";
		String passwordColum = "Password";
		int rowCount=xls.getRowCount(Sheetname);
			for(int i= 2; i<rowCount+1;i++){
				if(roles.equalsIgnoreCase(xls.getCellData(Sheetname, role, i))){
					password= xls.getCellData(Sheetname, passwordColum, i);
					return password;
				}
			}
			return password;
		}
	
	

}
