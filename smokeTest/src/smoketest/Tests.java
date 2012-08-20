/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smoketest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Tests {

	AccountValues av;
	WebDriver driver;
	
	
	public void setUp(String university) {

		driver = new FirefoxDriver();
		av = new AccountValues(university);

	}

	public void login(String user) {

		LoginPage lp = new LoginPage(driver, av);

		lp.attemptLogin(user);
	}

	public void navigateToMyWall(String user) {

		LoginPage lp = new LoginPage(driver, av);

		lp.attemptLogin(user);

		Utility.myVerifyCurrentPage(driver, av.getTokenValue("homePageTitle"));

		// We will be using JavaScript execute command to navigate to subMenu screen
		// MyWallPage wall = new MyWallPage( driver, av, myWall );
		Utility.navigateToSubMenu(driver, av.getTokenValue("linkToWallXPATH"));

		//Verify My Wall Page
		Utility.myVerifyCurrentPage(driver, av.getTokenValue("wallPageTitle"));
	}
	
	public void testTextWallPost() throws Exception {
		WallPosts wp = new WallPosts(driver, av);
		wp.textPost();
	}

	public void testURLWallPost() throws Exception {
		WallPosts wp = new WallPosts(driver, av);
		wp.urlPost();
	}
	
	public void testCreateSclGrp(String sclGrpName) throws Exception {
		IsPresent ip = new IsPresent();
        
		//Navigate to SocialGroup Page
		Utility.navigateToSubMenu(driver, av.getTokenValue("linkToSclGrpXPath"));
		
		//Verify My Social Group Page
		ip.isElementPresentByLink(driver, av.getTokenValue("strtSclGrpBtn"));  
        
                SocialGroup sg = new SocialGroup(driver, av);

		sg.createSocialGroup(sclGrpName);
		
	}

	public void testCreateLiveSsn(String sclGrpName, String liveSessionName) throws Exception {
		IsPresent ip = new IsPresent();
		String sclGrpNm = av.getTokenValue(sclGrpName);
		String liveSsnNm = av.getTokenValue(liveSessionName);
        
		//Navigate to SocialGroup Page
		Utility.navigateToSubMenu(driver, av.getTokenValue("linkToSclGrpXPath"));
		
		//Verify My Social Group Page
		ip.isElementPresentByID(driver, av.getTokenValue("fndSclGrpID"));
		
		//Click on Find a Social Group Link
		Utility.myButtonClick(driver.findElement(By.id(av.getTokenValue("fndSclGrpID"))));

		//Verify Search for groups element is present or not
		ip.isElementPresentByID(driver, av.getTokenValue("srchForGrpsID"));
		
		//Enter Group Name
		Utility.mySendKeys(driver.findElement(By.id(av.getTokenValue("srchForGrpsID"))),sclGrpNm);
		Utility.myButtonClick(driver.findElement(By.id(av.getTokenValue("srchForGrpsBtnID"))));
		
		//Verify Social Group Present or not
		ip.isElementPresentByLink(driver,sclGrpNm);
		
		Utility.myButtonClick(driver.findElement(By.linkText(sclGrpNm)));
		
		//Verify Social Group Present or not
		ip.isElementPresentByID(driver, av.getTokenValue("leftPaneLvMtngID"));
				
		LiveSession ls = new LiveSession(driver, av);

		ls.createLiveSession(liveSsnNm);
					
	}
	
	public void tearDown() {
		driver.quit();
	}

	

	
}
