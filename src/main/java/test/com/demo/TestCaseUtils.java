/**
 * 
 */
package test.com.demo;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * @author User
 *
 */
public class TestCaseUtils 
{
	
	public static WebDriver driver;
	

	
	/**
	 * @param testCase
	 * @throws IOException
	 */
	public void testCaseValidator(String testCase) throws IOException {
		Map mapTemp;
		GetFileInformation getFileInformation = new GetFileInformation();
		mapTemp = getFileInformation.getFileContent();
		for (int i =0; i< mapTemp.size(); i++)
		{
		Map tempMap = new HashMap();
		tempMap = (Map) mapTemp.get(i); //{regNo=EF07LSN, colour=White, make=Toyota}
		if (testCase.equals((String)tempMap.get("TestCaseName")))
		{
				validateData(tempMap);
		
		
		}
		}
	}

	/**
	 * @param tempMap
	 */
	 public void validateData(Map tempMap) 
	 {

		 //launchSite();
		 
		 String regNoExpected = (String)tempMap.get("regNo");
		 String colourExpected = (String)tempMap.get("Colour");
		 String makeExpected = (String)tempMap.get("Make");
		
		driver.findElement(By.id("Vrm")).clear();
		driver.findElement(By.id("Vrm")).sendKeys(regNoExpected);
		driver.findElement(By.name("Continue")).click();
		
		String regNoActual = driver.findElement(By.id("Vrm")).getAttribute("value");
		String makeActual = driver.findElement(By.id("Make")).getAttribute("value");
		String colorActual = driver.findElement(By.id("Colour")).getAttribute("value");
		
		Assert.assertEquals(regNoActual.toLowerCase(), regNoExpected.toLowerCase());
		Assert.assertEquals(makeActual.toLowerCase(), makeExpected.toLowerCase());
		Assert.assertEquals(colorActual.toLowerCase(), colourExpected.toLowerCase());
		
	}
	 
	 public void launchSite() {
			System.setProperty("webdriver.gecko.driver", "C:\\Users\\User\\Downloads\\geckodriver-v0.18.0-win64\\geckodriver.exe");

			driver = new FirefoxDriver();
			
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			driver.get("https://www.gov.uk/get-vehicle-information-from-dvla");
			
			driver.findElement(By.linkText("Start now")).click();
		}
	 
	 public void tearDown(){
		  driver.quit();
	 }
}
