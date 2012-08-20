package smoketest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LiveSession extends Page{

	public LiveSession(WebDriver driver, AccountValues av) {
		super(driver, av);
	}
	
public void createLiveSession(String liveSsnNm) throws Exception {
		
		IsPresent ip = new IsPresent();
		      
		Utility.myButtonClick(driver.findElement(By.id(av.getTokenValue("leftPaneLvMtngID"))));
		
		//Verify Navigated to Live Meeting creation page
		ip.isElementPresentByID(driver, av.getTokenValue("crtSsnID"));
		
		Utility.myButtonClick(driver.findElement(By.id(av.getTokenValue("crtSsnID"))));
		
		ip.isElementPresentByID(driver, av.getTokenValue("lvSsnName"));
		
		Utility.mySendKeys(driver.findElement(By.id(av.getTokenValue("lvSsnName"))), liveSsnNm);
		DateFormat dateFormat;
                dateFormat = new SimpleDateFormat("mmss");	
                Date date = new Date();
		Utility.mySendKeys(driver.findElement(By.id(av.getTokenValue("lvSsnDesc"))), liveSsnNm+dateFormat.format(date));
		Utility.myButtonClick(driver.findElement(By.xpath(av.getTokenValue("lvnSsnSbmtXPath"))));  	    	
    	
		ip.isTextPresentByCSS(driver, av.getTokenValue("lvSsnCSS"), liveSsnNm);
    	
	}

}
