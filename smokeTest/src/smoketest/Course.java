package smoketest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class Course extends Page {

	DateFormat dateFormat;
    Date now;
	IsPresent ip = new IsPresent();    
	
	public Course(WebDriver driver, AccountValues av) {
		super(driver, av);
	}

	public void createCourse(String courseName) {
		
		//Navigate to course page
		setUpCrsPage();
		
		ip.isElementPresentByXPATH(driver, av.getTokenValue("addNewCrsBtnXPATH"));
		
		//Navigate to Create Course Screen
		driver.findElement(By.xpath(av.getTokenValue("addNewCrsBtnXPATH"))).click();
		
		ip.isElementPresentByID(driver, av.getTokenValue("crsCtgryID"));		
		
		//Create Course
		new Select(driver.findElement(By.id(av.getTokenValue("crsCtgryID")))).selectByVisibleText("Active");
		
		driver.findElement(By.id(av.getTokenValue("crsFullNmID"))).sendKeys(av.getTokenValue(courseName));
		
		//Date need to be in specific format as getinstance include special characters
		dateFormat = new SimpleDateFormat("ddMMMyy KKmma");
		now = new Date();
		driver.findElement(By.id(av.getTokenValue("crsShrtNmID"))).sendKeys(av.getTokenValue(courseName) + dateFormat.format(now));
		new Select(driver.findElement(By.name(av.getTokenValue("crsStrtDtDyNAME")))).selectByVisibleText("1");
		new Select(driver.findElement(By.name(av.getTokenValue("crsStrtDtMnthNAME")))).selectByVisibleText("August");
		new Select(driver.findElement(By.name(av.getTokenValue("crsStrtDtYrNAME")))).selectByVisibleText("2012");
		new Select(driver.findElement(By.name(av.getTokenValue("crsEndDtDyNAME")))).selectByVisibleText("31");
		new Select(driver.findElement(By.name(av.getTokenValue("crsEndDtMnthNAME")))).selectByVisibleText("July");
		new Select(driver.findElement(By.name(av.getTokenValue("crsEndDtYrNAME")))).selectByVisibleText("2013");
		driver.findElement(By.id(av.getTokenValue("crsSbmtBtnID"))).click();		
		
		//Verify Course is created or not
		ip.isElementPresentContainsTextByXPATH(driver, av.getTokenValue(courseName));
		
	}

	public void createGrpCourse(String courseName, String grpCrsName) {
		
		//Navigate to course page
		setUpCrsPage();
		
		ip.isElementPresentStartsWithTextByXPATH(driver, av.getTokenValue(courseName));
		
		//Click on newly created Course
		driver.findElement(By.xpath("//*[starts-with(text(),'"+av.getTokenValue(courseName)+"')]")).click();
		
		ip.isElementPresentContainsTextByXPATH(driver, av.getTokenValue(courseName));
		
		//Naviagte to Group Course page
		driver.findElement(By.xpath(av.getTokenValue("lftPnlUsrsLnkXPATH"))).click();
		driver.findElement(By.xpath(av.getTokenValue("lftPnlGrpsLinkXPATH"))).click();
		
		ip.isElementPresentByID(driver, av.getTokenValue("crtGrpCrsBtnID"));
		
		//Create group course
		driver.findElement(By.id(av.getTokenValue("crtGrpCrsBtnID"))).click();		
		ip.isElementPresentByID(driver, av.getTokenValue("grpCrsNameID"));
		driver.findElement(By.id(av.getTokenValue("grpCrsNameID"))).sendKeys(av.getTokenValue(grpCrsName));
		driver.findElement(By.id(av.getTokenValue("grpCrsSbmtBtnID"))).click();	
		
		//Verify group course is created or not
		ip.isElementPresentByID(driver, av.getTokenValue("crtGrpCrsBtnID"));
		
	}
	
	public void setUpCrsPage() {
		
		//Navigate to Home Page
		Utility.navigateToSubMenu(driver, av.getTokenValue("linkToHomeXPATH"));

		//Verify Home Page
		Utility.myVerifyCurrentPage(driver, av.getTokenValue("homePageTitle"));
		
		//Navigate to Add/Edit Course Page
		driver.findElement(By.xpath(av.getTokenValue("lftPnlSiteAdminLinkXPATH"))).click();
		driver.findElement(By.xpath(av.getTokenValue("lftPnlCrsLinkXPATH"))).click();
		driver.findElement(By.linkText(av.getTokenValue("lftPnlAddEditCrsLINK"))).click();		
	}

}
