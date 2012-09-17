package smoketest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import runThrghTestNG.BaseClass;

public class LiveSession extends BaseClass {

    Date now = new Date();

    /**
     * Create & verify Live Session in
     * 
     * @param sclGrpName 
     */
    public void buildLiveSession(String sclGrpName) {

        String user = LoginPage.getUser();
        String liveSsnNm = null;  
        DateFormat dateFormat;
        
        //Split username
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
        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(av.getTokenValue("btnCrtSsn"))));
        driver.findElement(By.xpath(av.getTokenValue("btnCrtSsn"))).click();
        WebElement lvSsnNm = new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(av.getTokenValue("fieldLvSsnXPATH"))));
        WebElement lvSsnNmDesc = new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(av.getTokenValue("fieldLvSsnDescXPATH"))));
        dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        System.out.println(dateFormat.format(now));
        
        //This is to verify lvSsnName field passes correct value 
        value:
        while (true) {
            lvSsnNm.clear();
            lvSsnNmDesc.clear();
            lvSsnNm.sendKeys(liveSsnNm);
            lvSsnNmDesc.sendKeys(liveSsnDesc);
            try {
                new WebDriverWait(driver, 60).until(ExpectedConditions.textToBePresentInElementValue(By.id("startdate"), dateFormat.format(now)));
                new WebDriverWait(driver, 15).until(ExpectedConditions.textToBePresentInElementValue(By.xpath(av.getTokenValue("fieldLvSsnXPATH")), liveSsnNm));
                break value;
            } catch (TimeoutException e) {
            }
        }
        driver.findElement(By.xpath(av.getTokenValue("btnLvnSsnSbmt")))
                .click();

        //Verify LiveSession created or not
        switch (user.substring(0, 7)) {
            case "student":
                ip.isTextPresentByXPATH(driver, av.getTokenValue("stdtLvSsnInTchrSclGrpXPATH"), liveSsnNm);
                break;
            default:
                ip.isTextPresentByXPATH(driver, av.getTokenValue("tchrLvSsnXPATH"), liveSsnNm);
        }
    }
}
