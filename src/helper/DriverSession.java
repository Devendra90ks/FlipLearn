package helper;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import utils.LoadEnvProperty;
import utils.LoadProperty;

public class DriverSession {
	public static WebDriver driver;
	public static String BASE_URL;
	public String env;
	LoadEnvProperty envpro = new LoadEnvProperty();
	LoadProperty loadProperty = new LoadProperty();
	public String parallelExecution = (String) loadProperty.getProperty("parallelExecution");
	public String currentEnvironment = (String) loadProperty.getProperty("project.env");
	public String browserType = (String) loadProperty.getProperty("browser");
	DesiredCapabilities capabilities;
	public String defaultPort = "4723";
	public String Admin_Count = (String) envpro.getProperty("Admin_Count");
	public String Student_Count = (String) loadProperty.getProperty("Student_Count");
	public String Parent_Count = (String) loadProperty.getProperty("Parent_Count");
	public String Princi_Count = (String) loadProperty.getProperty("Princi_Count");
	public String Tea_Count = (String) loadProperty.getProperty("Tea_Count");
	
	@BeforeMethod
	public void startDriver(){
		String path="";
		env = System.getProperty("os.name");
		System.out.println("Current environment is "+env);
		getBaseURL();
		if(browserType.equalsIgnoreCase("Chrome")){
			if(env.contains("Windows")){
				path = System.getProperty("user.dir") + "/drivers/chromedriverw.exe";
			}else if(env.equalsIgnoreCase("Linux")){
				path = System.getProperty("user.dir") + "/drivers/chromedriver2";
			}
			System.setProperty("webdriver.chrome.driver", path);
			driver = new ChromeDriver();
		}else if(browserType.equalsIgnoreCase("FF")){
			if(env.contains("Windows")){
				path = System.getProperty("user.dir") + "/drivers/geckodriver.exe";
			}else if(env.equalsIgnoreCase("Mac OS X")){
				path=System.getProperty("user.dir") + "/drivers/geckodriver";
				System.setProperty("webdriver.gecko.driver", path);
				driver = new FirefoxDriver();
			}
		}else if(browserType.equalsIgnoreCase("IE")){
			path = System.getProperty("user.dir") + "/drivers/IEDriverServer.exe";
			System.setProperty("webdriver.ie.driver", path);
			driver = new InternetExplorerDriver();
		}else if(browserType.equalsIgnoreCase("HtmlUnitDriver")){
			//driver = new HtmlUnitDriver(true);
			//driver.manage().window().maximize();
		}
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@AfterMethod
	public void stopDriver(){
		driver.quit();
	}

	public String getBaseURL(){
		if(currentEnvironment.equalsIgnoreCase("prod")){
			BASE_URL=envpro.readProperty("BASE_URL");
		}else if(currentEnvironment.equalsIgnoreCase("stag")){
			BASE_URL=envpro.readProperty("BASE_URL");
		}else if(currentEnvironment.equalsIgnoreCase("qa")){
			BASE_URL=envpro.readProperty("BASE_URL");
		}else{
			BASE_URL="http://www.fliplearn.com";
		}
		return BASE_URL;
	}

	public static WebDriver getLastExecutionDriver(){
		return driver;
	}
}