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
        String liveSsnDesc = null;
        DateFormat dateFormat;

        //Split username
        switch (user.substring(0, 7)) {
            case "student":
            case "autostu":
                if (test.equalsIgnoreCase("SmokeTests")) {
                    liveSsnNm = "SmkTstLvSsnInTchrSclGrpBYStdt " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
                    liveSsnDesc = "SmkTstLvSsnDesc " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
                } else {
                    liveSsnNm = "CrtclTstLvSsnInTchrSclGrpBYStdt " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
                    liveSsnDesc = "CrtclTstLvSsnDesc " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
                }
                break;
            default:
                if (test.equalsIgnoreCase("SmokeTests")) {
                    liveSsnNm = "SmkTstLvSsn " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
                    liveSsnDesc = "SmkTstLvSsnDesc " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
                } else {
                    liveSsnNm = "CrtclTstLvSsn " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
                    liveSsnDesc = "CrtclTstLvSsnDesc " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
                }
        }

        //Verify Navigated to Live Meeting creation page
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("btnCrtSsn"));
        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(xpv.getTokenValue("btnCrtSsn"))));
        driver.findElement(By.xpath(xpv.getTokenValue("btnCrtSsn"))).click();
        WebElement lvSsnNm = new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(xpv.getTokenValue("fieldLvSsnXPATH"))));
        WebElement lvSsnNmDesc = new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(xpv.getTokenValue("fieldLvSsnDescXPATH"))));
        dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        System.out.println(dateFormat.format(now));
        driver.findElement(By.xpath(xpv.getTokenValue("fieldLvSsnDrtnXPATH"))).clear();
        driver.findElement(By.xpath(xpv.getTokenValue("fieldLvSsnDrtnXPATH"))).sendKeys(xpv.getTokenValue("lvSsnDuration"));

        //This is to verify lvSsnName field passes correct value 
        value:
        while (true) {
            lvSsnNm.clear();
            lvSsnNmDesc.clear();
            lvSsnNm.sendKeys(liveSsnNm);
            lvSsnNmDesc.sendKeys(liveSsnDesc);
            try {
                new WebDriverWait(driver, 60).until(ExpectedConditions.textToBePresentInElementValue(By.id("startdate"), dateFormat.format(now)));
                new WebDriverWait(driver, 15).until(ExpectedConditions.textToBePresentInElementValue(By.xpath(xpv.getTokenValue("fieldLvSsnXPATH")), liveSsnNm));
                break value;
            } catch (TimeoutException e) {
            }
        }
        driver.findElement(By.xpath(xpv.getTokenValue("btnLvnSsnSbmt")))
                .click();

        //Verify LiveSession created or not
        switch (user.substring(0, 7)) {
            case "student":
            case "autostu":
                ip.isTextPresentByXPATH(driver, xpv.getTokenValue("stdtLvSsnInTchrSclGrpXPATH"), liveSsnNm);
                break;
            default:
                ip.isTextPresentByXPATH(driver, xpv.getTokenValue("tchrLvSsnXPATH"), liveSsnNm);
        }
    }
}
