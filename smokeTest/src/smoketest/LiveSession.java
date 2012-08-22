
package smoketest;

import java.text.DateFormat;
import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class LiveSession extends Page {
    
    Date now = new Date();
    IsPresent ip = new IsPresent();
    
    public LiveSession( WebDriver driver, AccountValues av ) {
        
        super( driver, av );
    }
    
    public void buildLiveSession( String sclGrpName ) {
        
        String liveSsnNm = "SmkTstLvSsn " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
        String liveSsnDesc = "SmkTstLvSsnDesc " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
        
        driver.findElement(By.linkText(sclGrpName)).click();

        //Verify Live Session Panel present or not
        ip.isElementPresentByXPATH(driver, av.getTokenValue("btnLeftPnlLvMtng"));

        driver.findElement(By.xpath(av.getTokenValue("btnleftPnlLvMtng"))).click();

        //Verify Navigated to Live Meeting creation page
        ip.isElementPresentByXPATH(driver, av.getTokenValue("btnCrtSsn"));

        driver.findElement(By.xpath(av.getTokenValue("btnCrtSsn"))).click();

        ip.isElementPresentByXPATH(driver, av.getTokenValue("fieldLvSsnXPATH"));

        driver.findElement(By.xpath(av.getTokenValue("fieldLvSsnXPATH"))).sendKeys(
                liveSsnNm);

        driver.findElement(By.xpath(av.getTokenValue("fieldLvSsnDescXPATH"))).sendKeys(
                liveSsnDesc);
        driver.findElement(By.xpath(av.getTokenValue("btnLvnSsnSbmt")))
                .click();

        ip.isTextPresentByCSS(driver, av.getTokenValue("lvSsnCSS"), liveSsnNm);
    }
}
