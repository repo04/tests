package smoketest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SocialGroup extends Page{

	public SocialGroup(WebDriver driver, AccountValues av) {
		super(driver, av);
	}

	public void createSocialGroup(String sclGrpName) throws Exception {
		
		IsPresent is = new IsPresent();
		      
		Utility.myButtonClick(driver.findElement(By.linkText(av.getTokenValue("strtSclGrpBtn"))));
		
		//Verify Navigated to Social Group creation page
		is.isElementPresentByID(driver, av.getTokenValue("sclGrpNameID"));
		
		//Create New Social Group
		Utility.mySendKeys(driver.findElement(By.id(av.getTokenValue("sclGrpNameID"))), av.getTokenValue(sclGrpName));
		DateFormat dateFormat;
                dateFormat = new SimpleDateFormat("mmss");	
                Date date = new Date();
                String sclGrpShrtName = av.getTokenValue(sclGrpName)+dateFormat.format(date);
                Utility.mySendKeys(driver.findElement(By.id(av.getTokenValue("sclGrpShrtNameID"))), sclGrpShrtName);
                Utility.mySendKeys(driver.findElement(By.id(av.getTokenValue("sclGrpAbtGrpID"))), "About the group");
                Utility.mySendKeys(driver.findElement(By.id(av.getTokenValue("sclGrpWhoShudJoinID"))), "other student should join");
                Utility.mySendKeys(driver.findElement(By.id(av.getTokenValue("sclGrpTpc1ID"))), "Global Warming");
                Utility.mySendKeys(driver.findElement(By.id(av.getTokenValue("sclGrpTpc2ID"))), "Social N/W sites");

                Utility.myButtonClick(driver.findElement(By.id(av.getTokenValue("sclGrpSbmtID"))));

                is.isElementPresentByLink(driver,av.getTokenValue(sclGrpName));
    	
	}

	
}
