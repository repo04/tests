package smoketest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LiveSession extends Page {

	DateFormat dateFormat;
    Date now;
	IsPresent ip = new IsPresent();

	public LiveSession(WebDriver driver, AccountValues av) {
		super(driver, av);
	}

	public void createLiveSession(String sclGrpName, String liveSessionName) {
		
		String sclGrpNm = av.getTokenValue(sclGrpName);
		String liveSsnNm = av.getTokenValue(liveSessionName);

		//Navigate to SocialGroup Page
		Utility.navigateToSubMenu(driver, av.getTokenValue("linkToSclGrpXPATH"));

		//Verify My Social Group Page
		ip.isElementPresentByID(driver, av.getTokenValue("fndSclGrpID"));

		//Click on Find a Social Group Link
		driver.findElement(By.id(av.getTokenValue("fndSclGrpID"))).click();

		//Verify Search for groups element is present or not
		ip.isElementPresentByID(driver, av.getTokenValue("srchForGrpsID"));

		//Enter Group Name
		driver.findElement(By.id(av.getTokenValue("srchForGrpsID"))).sendKeys(
				sclGrpNm);
		driver.findElement(By.id(av.getTokenValue("srchForGrpsBtnID"))).click();

		// Verify Social Group Present or not
		ip.isElementPresentByLink(driver, sclGrpNm);

		driver.findElement(By.linkText(sclGrpNm)).click();

		// Verify Social Group Present or not
		ip.isElementPresentByID(driver, av.getTokenValue("leftPaneLvMtngID"));

		driver.findElement(By.id(av.getTokenValue("leftPaneLvMtngID"))).click();

		// Verify Navigated to Live Meeting creation page
		ip.isElementPresentByID(driver, av.getTokenValue("crtSsnID"));

		driver.findElement(By.id(av.getTokenValue("crtSsnID"))).click();

		ip.isElementPresentByID(driver, av.getTokenValue("lvSsnNameID"));

		driver.findElement(By.id(av.getTokenValue("lvSsnNameID"))).sendKeys(
				liveSsnNm);

		//Date need to be in specific format as getinstance include special characters
		dateFormat = new SimpleDateFormat("ddMMMyy KKmma");
		now = new Date();
		driver.findElement(By.id(av.getTokenValue("lvSsnDescID"))).sendKeys(
				liveSsnNm + dateFormat.format(now));
		driver.findElement(By.xpath(av.getTokenValue("lvnSsnSbmtXPATH")))
				.click();

		ip.isTextPresentByCSS(driver, av.getTokenValue("lvSsnCSS"), liveSsnNm);

	}

}
