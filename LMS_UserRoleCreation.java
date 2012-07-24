import java.io.File;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.thoughtworks.selenium.SeleneseTestCase;

/**
 * @author somesh.bansal
 * Functionalities covered
 * 1> PES admin creates two users  
 * 2> Enroll them as non-editing teacher & student in same course 
 */
 
@SuppressWarnings("deprecation")
public class LMS_UserRoleCreation extends SeleneseTestCase {
	
	WebDriver driver;
	String baseUrl;
	
	@DataProvider(name = "DP1")
    public Object[][] createData1() throws Exception{		
		String filePathToUse = new File("resources/data/data1.xls").getAbsolutePath();
		System.out.println("path: "+filePathToUse);
        Object[][] retObjArr=getTableArray(filePathToUse,"DataPool", "LMS_UserRoleCreation");
        return(retObjArr);
    }	
	
	@BeforeClass
	public void setUp() throws Exception {
		System.out.println("LMS_UserRoleCreation RUN!");
		driver = new FirefoxDriver();		
	}

	@Test (dataProvider = "DP1")
	public void testLMS_UserRoleCreation(String url, String	usrPES, String	pswdPES, String tchrUserNm, String	tchrUsrPswd, 
		String tchrFNm, String	tchrSNm, String tchrEmailID, String city, String cntry, String timeZone,
		String stdtUserNm, String	stdtUsrPswd, String stdtFNm, String	stdtSNm, String stdtEmailID) throws Exception 
		{
		
		baseUrl = url;
		String tchrFullName=tchrFNm+" "+tchrSNm;
		String stdtFullName=stdtFNm+" "+stdtSNm;
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
		
		selenium.open("/local/login/index.php");
		selenium.type("id=username", usrPES);
		selenium.type("id=password", pswdPES);
		selenium.click("id=loginpage_submit_btn");
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Login");
			try { if (selenium.isElementPresent("//div[@id='settingsnav']/ul/li[2]/ul/li/p/span")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}
		selenium.click("//div[@id='settingsnav']/ul/li[2]/ul/li/p/span");
		selenium.click("//li[2]/ul/li/ul/li/p/span");
		
		//Create teacher
		selenium.click("link=Add a new user");
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Add a new user");
			try { if (selenium.isElementPresent("id=id_username")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}
		selenium.click("id=id_username");
		selenium.type("id=id_username", tchrUserNm);
		selenium.type("id=id_newpassword", tchrUsrPswd);
		selenium.type("id=id_firstname", tchrFNm);
		selenium.type("id=id_lastname", tchrSNm);
		selenium.type("id=id_email", tchrEmailID);
		selenium.type("id=id_city", city);
		selenium.select("id=id_country", "label="+ cntry);
		selenium.select("id=id_timezone", "label="+ timeZone);
		selenium.click("id=id_submitbutton");		
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Create a new user");
			try { if (selenium.isElementPresent("id=id_realname_op")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}		
		selenium.select("id=id_realname_op", "label=is equal to");
		selenium.type("id=id_realname", tchrFullName);
		selenium.click("id=id_addfilter");
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Searching user");
			try { if (selenium.isElementPresent("link="+tchrFullName)) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}
		
		//Create student
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Searching user");
			try { if (selenium.isElementPresent("link=Add a new user")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}
		selenium.click("link=Add a new user");
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Add a new user");
			try { if (selenium.isElementPresent("id=id_username")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}	
		
		selenium.click("id=id_username");
		selenium.type("id=id_username", stdtUserNm);
		selenium.type("id=id_newpassword", stdtUsrPswd);
		selenium.type("id=id_firstname", stdtFNm);
		selenium.type("id=id_lastname", stdtSNm);
		selenium.type("id=id_email", stdtEmailID);
		selenium.type("id=id_city", city);
		selenium.select("id=id_country", "label="+ cntry);
		selenium.select("id=id_timezone", "label="+ timeZone);
		selenium.click("id=id_submitbutton");
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Create a new user");
			try { if (selenium.isElementPresent("id=id_realname_op")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		selenium.click("id=id_removeall");
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Searching user");
			try { if (!selenium.isElementPresent("link="+tchrFullName)) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}
		
		selenium.select("id=id_realname_op", "label=is equal to");
		selenium.type("id=id_realname", stdtFullName);
		selenium.click("id=id_addfilter");
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Find a new user");
			try { if (selenium.isElementPresent("link="+stdtFullName)) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}
		
		//Add course to teacher
		actionBuilder("Profile","//ul[@id='nav']/li[2]/ul/li[4]/a");
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Courses screen");
			try { if (selenium.isTextPresent("Current Courses")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}
		
		selenium.click("link=Section 3- Test group");
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Accessing specific course");
			try { if (selenium.isElementPresent("//li[3]/p/span")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}
		selenium.click("//li[3]/p/span");
		selenium.click("link=Enrolled users");
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Enrolled users screen");
			try { if (selenium.isElementPresent("css=input[type=\"submit\"]")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}
		
		selenium.click("css=input[type=\"submit\"]");
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Enrolling user popup");
			try { if (selenium.isTextPresent("Enrolment options")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Enrolling user popup");
			try { if (selenium.isElementPresent("id=enrolusersearch")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Enrolling user popup");
			try { if (selenium.isTextPresent("users found")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}
		
		driver.findElement(By.id("enrolusersearch")).sendKeys(tchrFullName);
		driver.findElement(By.id("enrolusersearch")).sendKeys(Keys.RETURN);
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: User not found");
			try { if (selenium.isTextPresent("1 user found")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}
		
		selenium.select("id=menuroleid", "label=Non-editing teacher");
		selenium.removeSelection("//div[@id='groupids']/select", "label=None");
		selenium.addSelection("//div[@id='groupids']/select", "label=TEST-Pankov");
		selenium.click("//div[@id='useroptions']/input");
		selenium.click("//div[3]/input");		
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: User not enrolled");
			try { if (!selenium.isVisible("//div[@id='useroptions']/input")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}
		selenium.click("//div[3]/div[2]/input");
		assertTrue(selenium.isTextPresent("Enrolled users"));
		
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Enrolled users screen");
			try { if (selenium.isElementPresent("css=input[type=\"submit\"]")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}
		
		selenium.click("css=input[type=\"submit\"]");
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Enroll user");
			try { if (selenium.isTextPresent("Enrolment options")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Enrolling user popup");
			try { if (selenium.isElementPresent("id=enrolusersearch")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Enrolling user popup");
			try { if (selenium.isTextPresent("users found")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}
		
		driver.findElement(By.id("enrolusersearch")).sendKeys(stdtFullName);
		driver.findElement(By.id("enrolusersearch")).sendKeys(Keys.RETURN);
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: User not found");
			try { if (selenium.isTextPresent("1 user found")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}		
		
		selenium.select("id=menuroleid", "label=Student");
		selenium.removeSelection("//div[@id='groupids']/select", "label=None");
		selenium.addSelection("//div[@id='groupids']/select", "label=TEST-Pankov");
		selenium.click("//div[@id='useroptions']/input");
		selenium.click("//div[3]/input");
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: User not enrolled");
			try { if (!selenium.isVisible("//div[@id='useroptions']/input")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}
		selenium.click("//div[3]/div[2]/input");
		assertTrue(selenium.isTextPresent("Enrolled users"));
		
		//Verify Group
		selenium.click("link=Home");
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Accessing home screen");
			try { if (selenium.isTextPresent("Top news feed is not available for system roles.")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}
		
		selenium.click("//div/ul/li[3]/p");
		selenium.click("//div/ul/li[3]/ul/li/p");
		selenium.click("//li[3]/ul/li/ul/li/p");
		selenium.click("link=Browse list of users");
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Browse list of users");
			try { if (selenium.isElementPresent("id=id_realname_op")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}
		
		selenium.click("id=id_removeall");
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Searching user");
			try { if (!selenium.isElementPresent("link="+stdtFullName)) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}
		
		selenium.select("id=id_realname_op", "label=is equal to");
		selenium.type("id=id_realname", tchrFullName);
		selenium.click("id=id_addfilter");
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Searching user");
			try { if (selenium.isElementPresent("link="+tchrFullName)) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}
		
		selenium.click("link="+tchrFullName);
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Searching user profile");
			try { if (selenium.isElementPresent("//div[@id='info_section_info_educational']/div/div[2]/a")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}
		selenium.click("//div[@id='info_section_info_educational']/div/div[2]/a");
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Searching user details");
			try { if (selenium.isTextPresent("Non-editing teacher")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Searching user details");
			try { if (selenium.isTextPresent("TEST-Pankov")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}
		
		//Verify Group
		selenium.click("link=Home");
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Accessing home screen");
			try { if (selenium.isTextPresent("Top news feed is not available for system roles.")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}
		
		selenium.click("//div/ul/li[3]/p");
		selenium.click("//div/ul/li[3]/ul/li/p");
		selenium.click("//li[3]/ul/li/ul/li/p");
		selenium.click("link=Browse list of users");
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Browse list of users");
			try { if (selenium.isElementPresent("id=id_realname_op")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}
		
		selenium.click("id=id_removeall");
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Searching user");
			try { if (!selenium.isElementPresent("link="+tchrFullName)) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}	
		
		selenium.select("id=id_realname_op", "label=is equal to");
		selenium.type("id=id_realname", stdtFullName);
		selenium.click("id=id_addfilter");
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Searching user");
			try { if (selenium.isElementPresent("link="+stdtFullName)) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}
		
		selenium.click("link="+stdtFullName);
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: User profile");
			try { if (selenium.isElementPresent("//div[@id='info_section_info_educational']/div/div[2]/a")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}
		selenium.click("//div[@id='info_section_info_educational']/div/div[2]/a");
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: User deatils");
			try { if (selenium.isTextPresent("Student")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: User deatils");
			try { if (selenium.isTextPresent("TEST-Pankov")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}
		
		actionBuilderID("header_profile_firstname","//ul[@id='navright']/li[3]/ul/li[2]/a");			
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Logout");
			try { if (selenium.isElementPresent("id=loginpage_compatibility_btn")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}
		
	}
	
	private void actionBuilder(String menu, String menuOption) {
		// TODO Auto-generated method stub
		
		//get the element that shows menu with the mouseOver event
		WebElement visibleElememt = driver.findElement(By.linkText(menu));

		//the element that I want to click (hidden)
		WebElement hiddenElement = driver.findElement(By.xpath(menuOption));

		//build and perform the mouseOver with Advanced User Interactions API
		Actions builder1 = new Actions(driver);    
		builder1.moveToElement(visibleElememt).build().perform();

		//then click when menu option is visible
		hiddenElement.click();
		
	}
	
	private void actionBuilderID(String menu, String menuOption) {
		// TODO Auto-generated method stub
		
		//get the element that shows menu with the mouseOver event
		WebElement visibleElememt = driver.findElement(By.id(menu));

		//the element that I want to click (hidden)
		WebElement hiddenElement = driver.findElement(By.xpath(menuOption));

		//build and perform the mouseOver with Advanced User Interactions API
		Actions builder2 = new Actions(driver);    
		builder2.moveToElement(visibleElememt).build().perform();

		//then click when menu option is visible
		hiddenElement.click();		
		
	}
	
	public String[][] getTableArray(String xlFilePath, String sheetName, String tableName) throws Exception
	{
    	String[][] tabArray=null;
    
        Workbook workbook = Workbook.getWorkbook(new File(xlFilePath));
        Sheet sheet = workbook.getSheet(sheetName); 
        int startRow,startCol, endRow, endCol,ci,cj;
        
        Cell tableStart=sheet.findCell(tableName);
        startRow=tableStart.getRow();
        startCol=tableStart.getColumn();

        Cell tableEnd= sheet.findCell(tableName, startCol+1,startRow+1, 100, 64000,  false);                

        endRow=tableEnd.getRow();
        endCol=tableEnd.getColumn();
        System.out.println("startRow="+startRow+", endRow="+endRow+", " +
                "startCol="+startCol+", endCol="+endCol);
        tabArray=new String[endRow-startRow-1][endCol-startCol-1];
        ci=0;

        for (int i=startRow+1;i<endRow;i++,ci++){
            cj=0;
            for (int j=startCol+1;j<endCol;j++,cj++){
                tabArray[ci][cj]=sheet.getCell(j,i).getContents();	
            }
        }
    

        return(tabArray);
	}

	@AfterClass
	public void tearDown() throws Exception {
		selenium.stop();		
	}
	
	
}
