/**
 * 
 */
package testProject.com.demo;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import test.com.demo.TestCaseUtils;

/**
 * @author User
 *
 */
public class DVLAExampleTest 
{

	public WebDriver driver;
	TestCaseUtils testCaseUtils = new TestCaseUtils();
	
	
	@Before
	public void setup(){
		testCaseUtils.launchSite();
	}
	
	/**
	 * Test Case 1 to validate test data as provided in the test datasheet i.e TestData1
	 * @throws IOException
	 */
	
	  @Test
	  public void test1() throws IOException 
	  {
		  String testCase = "TC1";   
		  testCaseUtils.testCaseValidator(testCase);
		  
	  }

	
		/**
		 * Test Case 2 to validate test data as provided in the test datasheet i.e TestData1
		 * @throws IOException
		 */
	  
	  @Test
	  public void test2() throws IOException 
	  {
		  	String testCase = "TC2";    
		  	testCaseUtils.testCaseValidator(testCase);
		  
	  }
	  
		/**
		 * Test Case 2 to validate test data as provided in the test datasheet i.e TestData1
		 * This is a negative test
		 * @throws IOException
		 */
	  
	  @Test
	  public void test3() throws IOException
	  {
		  String testCase = "TC3";  
		  testCaseUtils.testCaseValidator(testCase);
		  
	  }
	  
	  @After
	 public void afterTest(){
		  testCaseUtils.tearDown();
	 }
	  
	  

	}
