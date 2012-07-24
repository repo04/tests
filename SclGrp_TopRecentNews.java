import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.openqa.selenium.By;
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
 * 1> Teacher logs in, Navigate to student's wall & post URL
 * 2> Teacher post URL on course wall, creates a Social Group & Live Session
 * 3> Student logs in, find/join & posts in Teacher's Social Group
 * 4> Student creates a Social Group & Live Session
 * 5> Student navigates to its wall and verifies all posts(wall & course) posted by Teacher
 * 6> Teacher again logs in, join/leave student's Social Group
 * 7> Teacher verifies all posts on its wall and recent news section
 * 8> Teacher delete self created Social Group
 */
 
@SuppressWarnings("deprecation")
public class SclGrp_TopRecentNews extends SeleneseTestCase {
	
	WebDriver driver;
	DateFormat dateFormat;
	Date date;
	String baseUrl;
	
	@DataProvider(name = "DP1")
    public Object[][] createData1() throws Exception{		
		String filePathToUse = new File("resources/data/data1.xls").getAbsolutePath();
		System.out.println("path: "+filePathToUse);
        Object[][] retObjArr=getTableArray(filePathToUse,"DataPool", "dataLMS");
        return(retObjArr);
    }	
	
	@BeforeClass
	public void setUp() throws Exception {
		System.out.println("SclGrp_TopRecentNews RUN!");
		driver = new FirefoxDriver();		
	}
	
	
	@Test (dataProvider = "DP1")
	public void testSclGrp_TopRecentNews(String url, String usrTchr, String pswdTchr, String stdtFullName,
			String tchrUrlPostStdtWall, String tchrUrlPostCrsWall, String tchrSclGrp, String tchrLiveSession, 
			String usrStdt, String pswdStdt, String stdtUrlPostTchrSclGrp, String stdtSclGrp, String stdtLiveSession) 
			throws Exception {
		baseUrl = url;
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
		dateFormat = new SimpleDateFormat("mmss");
				
		selenium.open("/local/login/index.php");
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Access URL");
			try { if (selenium.isElementPresent("css=div.loginform > img")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}
		selenium.type("id=username", usrTchr);
		selenium.type("id=password", pswdTchr);
		selenium.click("id=loginpage_submit_btn");
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Login");
			try { if (selenium.isTextPresent("Top News - Wall")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: More link");
			try { if (selenium.isElementPresent("link=More...")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		selenium.click("link=More...");
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Search Contacts");
			try { if (selenium.isElementPresent("id=txtSearchContacts")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}
		
		// Teacher post URL on Student's Wall
		selenium.type("id=txtSearchContacts", stdtFullName);
		selenium.click("id=searchcontactsbutton");
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Search user");
			try { if (selenium.isElementPresent("link="+stdtFullName)) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		selenium.click("link="+stdtFullName);
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: User info");
			try { if (selenium.isTextPresent("Basic Information")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		selenium.click("id=leftnav_wall");
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: User wall");
			try { if (selenium.isTextPresent("`s - Wall")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		selenium.click("id=wall_publisher_textarea_noedit");
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: URL post button");
			try { if (selenium.isElementPresent("id=ext-gen250")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}
		
		selenium.click("id=ext-gen250");
		selenium.type("id=wall-attachment-url", "http://"+tchrUrlPostStdtWall+".com");
		selenium.click("id=ext-gen258");
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Wall post");
			try { if (selenium.isTextPresent("exact:http://"+tchrUrlPostStdtWall+".com")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}
		
		// Teacher post URL on Course		
		actionBuilder("My Courses","//div[@id='region-pre']/div/ul/li/ul/li/ul/li/a");
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Course screen");
			try { if (selenium.isElementPresent("css=p.course-fullname")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}
		
		selenium.click("id=wall_publisher_textarea_noedit");
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: URL post button");
			try { if (selenium.isElementPresent("id=ext-gen250")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}
		
		
		selenium.click("id=ext-gen250");
		selenium.type("id=wall-attachment-url", "http://"+tchrUrlPostCrsWall+".com");
		selenium.click("id=ext-gen258");
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Course post");
			try { if (selenium.isElementPresent("link=exact:http://"+tchrUrlPostCrsWall+".com")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		//Teacher starts a Social Group		
		actionBuilder("Groups","//ul[@id='nav']/li[3]/ul/li/a");		
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Social group screen");
			try { if (selenium.isElementPresent("link=Start a Social Group!")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		selenium.click("link=Start a Social Group!");
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Start a Group");
			try { if (selenium.isTextPresent("Start a Group")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}
		
		selenium.type("id=id_groupname", tchrSclGrp);
		date = new Date();
		selenium.type("id=id_shortname", tchrSclGrp+dateFormat.format(date));
		selenium.type("id=id_aboutgroup", "About the group");
		selenium.type("id=id_whoshouldjoin", "other student should join");
		selenium.type("id=id_topic1", "Global Warming");
		selenium.type("id=id_topic2", "Social N/W sites");
		selenium.click("id=id_submitbutton");
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Verify group creation");
			try { if (selenium.isElementPresent("link="+tchrSclGrp)) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Social group wall");
			try { if (selenium.isTextPresent(tchrSclGrp+" - Wall")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		selenium.click("id=sgroup_edit_profile");
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Edit social group");
			try { if (selenium.isElementPresent("id=id_aboutgroup")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}
		selenium.type("id=id_aboutgroup", "About the group edit");
		selenium.click("id=id_submitbutton");
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Social group wall");
			try { if (selenium.isTextPresent(tchrSclGrp+" - Wall")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}
		
		//Teacher creates a Live Session
		selenium.click("id=leftnav_livemeetings");
		selenium.waitForPageToLoad("30000");
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Create live session");
			try { if (selenium.isElementPresent("id=create_session")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}
		
		selenium.click("id=create_session");
		selenium.type("id=eventname", tchrLiveSession);
		date = new Date();
		selenium.type("id=edit-description", tchrLiveSession+dateFormat.format(date));
		selenium.click("//table[@id='submit_id']/tbody/tr[2]/td[2]/em/button");
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Verify live session creation");
			try { if (selenium.isTextPresent(tchrLiveSession)) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}
		
		actionBuilderID("header_profile_firstname","//ul[@id='navright']/li[3]/ul/li[2]/a");
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Logout");
			try { if (selenium.isElementPresent("id=loginpage_compatibility_btn")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}
		
		//Student login
		selenium.type("id=username", usrStdt);
		selenium.type("id=password", pswdStdt);
		selenium.click("id=loginpage_submit_btn");
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Login");
			try { if (selenium.isTextPresent("Top News - Wall")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		//Student join Teacher's SocialGroup		
		actionBuilder("Groups","//ul[@id='nav']/li[3]/ul/li/a");
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Social group sreen");
			try { if (selenium.isElementPresent("id=leftnav_find_group")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		selenium.click("id=leftnav_find_group");
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Find social group sreen");
			try { if (selenium.isElementPresent("id=search_text")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}
		selenium.type("id=search_text", tchrSclGrp);
		selenium.click("id=search_button");
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Finding social group");
			try { if (selenium.isElementPresent("link="+tchrSclGrp)) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Finding social group");
			try { if (selenium.isElementPresent("link=Join Now")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		selenium.click("link=Join Now");
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Join social group popup");
			try { if (selenium.isTextPresent("exact:Are you sure you want to join the group \""+tchrSclGrp+"\"?")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}
		selenium.click("id=ext-gen71");
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Successfully join social group popup");
			try { if (selenium.isTextPresent("You have successfully joined the group \""+tchrSclGrp+"\".")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		selenium.click("id=ext-gen69");
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Social group joined");
			try { if (selenium.isTextPresent("Joined")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		selenium.click("link="+tchrSclGrp);
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Social group wall");
			try { if (selenium.isTextPresent(tchrSclGrp+" - Wall")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}
		
		//Student post on Teacher's Social Group
		selenium.click("id=wall_publisher_textarea_noedit");
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: URL post button");
			try { if (selenium.isElementPresent("id=ext-gen250")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}
		selenium.click("id=ext-gen250");
		selenium.type("id=wall-attachment-url", "http://"+stdtUrlPostTchrSclGrp+".com");
		selenium.click("id=ext-gen258");
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Stdt post on teacher's social group");
			try { if (selenium.isElementPresent("link=exact:http://"+stdtUrlPostTchrSclGrp+".com")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}
		
		//Student start a Social Group		
		actionBuilder("Groups","//ul[@id='nav']/li[3]/ul/li/a");
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Social group screen");
			try { if (selenium.isElementPresent("id=leftnav_find_group")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}
		
		selenium.click("link=Start a Social Group!");
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Start a group by student");
			try { if (selenium.isTextPresent("Start a Group")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}
		selenium.type("id=id_groupname", stdtSclGrp);
		date = new Date();
		selenium.type("id=id_shortname", stdtSclGrp+dateFormat.format(date));
		selenium.type("id=id_aboutgroup", "About the group");
		selenium.type("id=id_whoshouldjoin", "other student should join");
		selenium.type("id=id_topic1", "Global Warming");
		selenium.type("id=id_topic2", "Social N/W sites");
		selenium.click("id=id_submitbutton");
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Verify student social group creation");
			try { if (selenium.isElementPresent("link="+stdtSclGrp)) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Verify student social group creation");
			try { if (selenium.isTextPresent(stdtSclGrp+" - Wall")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}
		
		//Student creates a Live Session
		selenium.click("id=leftnav_livemeetings");
		selenium.waitForPageToLoad("30000");
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Live session creation");
			try { if (selenium.isElementPresent("id=create_session")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}
		
		selenium.click("id=create_session");
		selenium.type("id=eventname", stdtLiveSession);
		date = new Date();
		selenium.type("id=edit-description", stdtLiveSession+dateFormat.format(date));
		selenium.click("//table[@id='submit_id']/tbody/tr[2]/td[2]/em/button");
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Verify live session created");
			try { if (selenium.isTextPresent(stdtLiveSession)) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}
		
		// Student verifies URL's post on Wall \ Course posted by Teacher		
		selenium.click("link=Home");
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Top News - Wall");
			try { if (selenium.isTextPresent("Top News - Wall")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}
		
		for (int second = 0;; second++) {
		if (second >= 60) fail("Timeout: Teacher's url wall post");
		try { if (selenium.isElementPresent("link=exact:http://"+tchrUrlPostStdtWall+".com")) break; } catch (Exception e) {}
		Thread.sleep(1000);
		}

		for (int second = 0;; second++) {
		if (second >= 60) fail("Timeout: Teacher's url course wall post");
		try { if (selenium.isElementPresent("link=exact:http://"+tchrUrlPostCrsWall+".com")) break; } catch (Exception e) {}
		Thread.sleep(1000);
		}
		
		actionBuilderID("header_profile_firstname","//ul[@id='navright']/li[3]/ul/li[2]/a");
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Student logout");
			try { if (selenium.isElementPresent("id=loginpage_compatibility_btn")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		//Teacher logins again		
		selenium.type("id=username", usrTchr);
		selenium.type("id=password", pswdTchr);
		selenium.click("id=loginpage_submit_btn");
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Login");
			try { if (selenium.isTextPresent("Top News - Wall")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}
		
		//Teacher join Student's SocialGroup		
		actionBuilder("Groups","//ul[@id='nav']/li[3]/ul/li/a");
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Social group screen");
			try { if (selenium.isElementPresent("id=leftnav_find_group")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		selenium.click("id=leftnav_find_group");
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Find social group screen");
			try { if (selenium.isElementPresent("id=search_text")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}
		selenium.type("id=search_text", stdtSclGrp);
		selenium.click("id=search_button");
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Verify social group found");
			try { if (selenium.isElementPresent("link="+stdtSclGrp)) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Verify social group found");
			try { if (selenium.isElementPresent("link=Join Now")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		selenium.click("link=Join Now");
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Join social group popup");
			try { if (selenium.isTextPresent("exact:Are you sure you want to join the group \""+stdtSclGrp+"\"?")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}
		selenium.click("//table[@id='ext-comp-1008']/tbody/tr[2]/td[2]");	
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Sucessfully join social group popup");
			try { if (selenium.isTextPresent("You have successfully joined the group \""+stdtSclGrp+"\".")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}
		selenium.click("id=ext-gen69");
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Sucessfully joined social group screen");
			try { if (selenium.isTextPresent("Joined")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}
		
		actionBuilder("Groups","//ul[@id='nav']/li[3]/ul/li/a");
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Soical group screen");
			try { if (selenium.isElementPresent("link=Leave Group")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}
		selenium.click("link=Leave Group");
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Leave social group popup");
			try { if (selenium.isTextPresent("exact:Are you sure you want to remove yourself from the group "+stdtSclGrp+"?")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		selenium.click("//table[@id='ext-comp-1008']/tbody/tr[2]/td[2]");
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Sucessfully left social group popup");
			try { if (selenium.isTextPresent("You have successfully left the group "+stdtSclGrp)) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}
		selenium.click("id=ext-gen69");
		assertTrue(!selenium.isElementPresent(stdtSclGrp));
		
		//Teacher verifies Student's URL post on SocialGroup		
		selenium.click("link=Home");
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Top News - Wall");
			try { if (selenium.isTextPresent("Top News - Wall")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}
		
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Verify teacher's post on stdt wall");
			try { if (selenium.isElementPresent("link=exact:http://"+tchrUrlPostStdtWall+".com")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Verify teacher's course post");
			try { if (selenium.isElementPresent("link=exact:http://"+tchrUrlPostCrsWall+".com")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}
		
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Student's post on teacher's social group");
			try { if (selenium.isTextPresent(stdtFullName+" shared a url on "+tchrSclGrp+" wall")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Student's url post on teacher's social group");
			try { if (selenium.isElementPresent("link=exact:http://"+stdtUrlPostTchrSclGrp+".com")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		selenium.click("link=Recent News");
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Student's post on teacher's social group in recent news");
			try { if (selenium.isTextPresent(stdtFullName+" shared a url on "+tchrSclGrp+" wall")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Student's url post on teacher's social group in recent news");
			try { if (selenium.isElementPresent("link=exact:http://"+stdtUrlPostTchrSclGrp+".com")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}
		
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Student's url post on teacher's social group in recent news");
			try { if (selenium.isElementPresent("link=exact:http://"+tchrUrlPostStdtWall+".com")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Verify teacher's course post in recent news");
			try { if (selenium.isElementPresent("link=exact:http://"+tchrUrlPostCrsWall+".com")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		actionBuilder("Groups","//ul[@id='nav']/li[3]/ul/li/a");
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Social group screen");
			try { if (selenium.isElementPresent("link="+tchrSclGrp)) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		selenium.click("link="+tchrSclGrp);
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Delete social group button");
			try { if (selenium.isElementPresent("id=sgroup_delete")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}
		
		selenium.chooseOkOnNextConfirmation();
		selenium.click("id=sgroup_delete");
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Social group screen after deletion");
			try { if (selenium.isTextPresent("My Social Groups")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}
		
		assertTrue(!selenium.isElementPresent(tchrSclGrp));
		
		actionBuilderID("header_profile_firstname","//ul[@id='navright']/li[3]/ul/li[2]/a");			
		for (int second = 0;; second++) {
			if (second >= 60) fail("Timeout: Final logout");
			try { if (selenium.isElementPresent("id=loginpage_compatibility_btn")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}
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

	private void actionBuilder(String menu, String menuOption) {
		// TODO Auto-generated method stub
		
		//get the element that shows menu with the mouseOver event
		WebElement visibleElememt = driver.findElement(By.linkText(menu));

		//the element that I want to click (hidden)
		WebElement hiddenElement = driver.findElement(By.xpath(menuOption));

		//build and perform the mouseOver with Advanced User Interactions API
		Actions builder2 = new Actions(driver);    
		builder2.moveToElement(visibleElememt).build().perform();

		//then click when menu option is visible
		hiddenElement.click();
		
	}
	
	public String[][] getTableArray(String xlFilePath, String sheetName, String tableName) throws Exception{
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
