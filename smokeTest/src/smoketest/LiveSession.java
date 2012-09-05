package smoketest;

import java.text.DateFormat;
import java.util.Date;
import org.openqa.selenium.By;
import runThrghTestNG.BaseClass;

public class LiveSession extends BaseClass {

    Date now = new Date();
    
    public void buildLiveSession(String sclGrpName) {

        String user = LoginPage.getUser();
        String liveSsnNm = null;

        switch (user.substring(0, 7)) {
            case "student":
                liveSsnNm = "SmkTstLvSsnInTchrSclGrpBYStdt " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
                break;
            default:
                liveSsnNm = "SmkTstLvSsn " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
                
        }

        String liveSsnDesc = "SmkTstLvSsnDesc " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
        
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

        switch (user.substring(0, 7)) {
            case "student":
                ip.isTextPresentByXPATH(driver, av.getTokenValue("stdtLvSsnInTchrSclGrpXPATH"), liveSsnNm);
                break;
            default:
                ip.isTextPresentByXPATH(driver, av.getTokenValue("tchrLvSsnXPATH"), liveSsnNm);                
        }
    }
}
