package smoketest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Tests {

	AccountValues av;
	WebDriver driver;
	
	// Login as user: student, teacher or PES
	public void login(String user) {

		LoginPage lp = new LoginPage(driver, av);
		lp.attemptLogin(user);
	}

	public void navigateToMyWall(String user) {
		
		// Uses js to click on hidden element by element XPATH
		Utility.navigateToSubMenu(driver, av.getTokenValue("linkToWallXPATH"));
		Utility.myVerifyCurrentPage(driver, av.getTokenValue("wallPageTitle"));
	}

	public void textToWall() {
		WallPage wp = new WallPage(driver, av);
		wp.textPost();
	}

	public void urlToWall() {
		WallPage wp = new WallPage(driver, av);
		wp.urlPost();
	}

	public void createSclGrp(String sclGrpName) {
		SocialGroup sg = new SocialGroup(driver, av);
		sg.createSocialGroup(sclGrpName);
	}

	public void createLiveSsn(String sclGrpName, String liveSessionName) {
		LiveSession ls = new LiveSession(driver, av);
		ls.createLiveSession(sclGrpName, liveSessionName);
	}

	public void setUp(String university) {
		driver = new FirefoxDriver();
		av = new AccountValues(university);
	}

	public void tearDown() {
		driver.quit();
	}

	//Login with ContentAdmin and create Course
	public void createCourse(String courseName) {
		Course cr = new Course(driver, av);
		cr.createCourse(courseName);
	}

	//Login with ContentAdmin and create Group to newly created course 
	public void createGrpToCourse(String courseName, String grpCrsName) {
		Course cr = new Course(driver, av);
		cr.createGrpCourse(courseName, grpCrsName);
	}

}
