package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

     public static WebDriver driver;
     public Logger logger; //log4j
     public Properties p;
	
	@BeforeClass(groups= {"Sanity","Regression","Master"}) 
	@Parameters({"os","browser"})
	public void setup(String os,String br) throws IOException
	{
		// To read the properties file we need to initially load the file
		FileInputStream file = new FileInputStream("./src/test/resources/properties");
		p = new Properties();
		p.load(file);
		
		logger = LogManager.getLogger(this.getClass()); // this step is going to take the class name dynamically
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities cap = new DesiredCapabilities();// to represent the OS and also the browser we use this speacial class called as desired capabilities
			// os
			if(os.equalsIgnoreCase("windows"))
			{
				cap.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("mac"))
			{
				cap.setPlatform(Platform.MAC);
			}
			else if(os.equalsIgnoreCase("linux"))
			{
				cap.setPlatform(Platform.LINUX);
			}
			else
			{
				System.out.println("There is no matching OS");
				return;
			}
			//browser
			
			switch(br.toLowerCase())
			{
			case "chrome" : cap.setBrowserName("chrome"); break;
			case "edge" : cap.setBrowserName("MicrosoftEdge"); break;
			case "firefox" : cap.setBrowserName("firefox"); break;
		    default: System.out.println("There is no matching browser");
			
			}
		
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
			
		}
	    
		if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		{
			switch(br)
			{
			case "chrome" : driver = new ChromeDriver(); break;
			case "edge" : driver = new EdgeDriver(); break;
			case "firefox" : driver = new FirefoxDriver(); break;
			default : System.out.println("This is an invalid browser"); return;
			
			}
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("appURL"));	
	}
	
	@AfterClass(groups= {"Sanity","Regression","Master"})
	public void teardown()
	{
		driver.quit();
	}
	
	
	public String captureScreen(String tname) throws IOException {

	    String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

	    TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
	    File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

	    String targetFilePath=System.getProperty("user.dir")+"\\Screenshots\\" + tname + "_" + timeStamp;
	    File targetFile=new File(targetFilePath);

	    sourceFile.renameTo(targetFile);

	    return targetFilePath;
	}

}