package testBase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

    static public WebDriver driver;
    public Logger logger;
    public Properties property;
	
	@BeforeClass(groups= {"sanity","regression","master"})
	@Parameters({"os","browser"})
	public void setup(String ostype,String br) throws IOException {
		
		//loading Property file
		FileReader file= new FileReader(".//src//test//resources//config.properties");
		property= new Properties();
		property.load(file);
		
		
		//loading log4j file
		logger=LogManager.getLogger(this.getClass()); //Log4j
		
		
		//Remote execution
		if(property.getProperty("execution_env").equalsIgnoreCase("remote")) {
			
			DesiredCapabilities capabilities= new DesiredCapabilities();
			//OS condition
			if(ostype.equalsIgnoreCase("windows")) {
				capabilities.setPlatform(Platform.WIN10);
				
			}
			else if(ostype.equalsIgnoreCase("mac")) {
				capabilities.setPlatform(Platform.MAC);
			}
			else {
				System.out.println("No match founded for OS type");
				return;
			}
			
			//browser type
			switch(br.toLowerCase()) {
			case "chrome": capabilities.setBrowserName("chrome"); break;
			case "edge": capabilities.setBrowserName("MicrosoftEdge");break;
			default: System.out.println("No matching found for browser type");return;
			}
			
		}
		
		//launching browser based on condition //locally executing
		switch(br.toLowerCase()) {
		
		case "chrome" : driver= new ChromeDriver(); break;
		case "edge" : driver= new EdgeDriver(); break;
		default: System.out.println("No matching found"); return;
		}
		
		
		driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get(property.getProperty("appURL"));
		driver.manage().window().maximize();
	}
	
	@AfterClass(groups= {"sanity","regression","master"})
	public void tearDown()
	{
		driver.quit();
	}
	
	public String randomeString()
	{
		String randomName=RandomStringUtils.randomAlphabetic(5);
		return randomName;
	}
	
	public String randomNumber() {
		String randomNO= RandomStringUtils.randomNumeric(10);
		return randomNO;
		
	}
	
	public String randomAlphaNumeric()
	{
		String str=RandomStringUtils.randomAlphabetic(3);
		String num=RandomStringUtils.randomNumeric(3);
		
		return (str+"@"+num);
	}
	
	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
			
		return targetFilePath;

	}
}
