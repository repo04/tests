/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package runThrghTestNG;

import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import smoketest.Actions;
import smoketest.Utility;

/**
 *
 * 
 */
public class StdtEmlNtfctn_CrtclTests extends BaseClass {

    Actions a = new Actions();
    String vrfy1, vrfy2, vrfy3, vrfy4;
    String tchrFllNm;

    /**
     * The annotated method will be run before the first test method in the
     * current class is invoked, Content Admin logs in
     *
     * @throws Exception
     */
    @BeforeClass
    public void testStdtEmailLgn() throws Exception {
        driver.get("https://mail.google.com/");
        ip.isTitlePresent(driver, "Gmail: Email from Google");
        WebElement gglUsrNm = driver.findElement(By.xpath(xpv.getTokenValue("fieldGglDocUsrIdXPATH")));
        WebElement gglPswd = driver.findElement(By.xpath(xpv.getTokenValue("fieldGglDocPswdXPATH")));
        value:
        while (true) {
            gglUsrNm.clear();
            gglPswd.clear();
            gglUsrNm.sendKeys("2torstudent");
            gglPswd.sendKeys("Newuser321");
            try {
                new WebDriverWait(driver, 60).until(ExpectedConditions.textToBePresentInElementValue(By.xpath(xpv.getTokenValue("fieldGglDocUsrIdXPATH")), "2torstudent"));
                break value;
            } catch (TimeoutException e) {
            }
        }
        driver.findElement(By.xpath(xpv.getTokenValue("fieldGglDocSignInXPATH"))).click();
        ip.isTitleContains(driver, "2torstudent@gmail.com - Gmail");
    }

    /**
     * 
     * @param tchrSclGrpName
     * @param stdtSclGrpName
     * @throws Exception 
     */
    @Test(dataProvider = "TchrStdtSclGrps", dataProviderClass = StdtLvSsn_SclGrp_GglDoc.class)
    public void testStdtVerifyEmails(String tchrSclGrpName, String stdtSclGrpName) throws Exception {

        tchrFllNm = "Auto Teacher1";

        vrfy1 = tchrFllNm + " has joined the group " + stdtSclGrpName + ".";
        vrfy2 = "You are now a member of " + stdtSclGrpName;
        vrfy3 = "You are now a member of " + tchrSclGrpName;  
        vrfy4 = "Posted on your Wall.";              

        ArrayList<String> wordList = new ArrayList<>();
        wordList.add(vrfy1);
        wordList.add(vrfy2);
        wordList.add(vrfy3);
        wordList.add(vrfy4);

        ip.isElementPresentByXPATH(driver, "//div[2]/div/div/div[2]/div/div/div/div/div/div/div/div");
        driver.findElement(By.xpath("//div[2]/div/div/div[2]/div/div/div/div/div/div/div/div")).click();

        for (int i = 0; i < 4; i++) {
            new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//tr[1]/td[5]/div/span")));
            Utility.actionBuilderClick(driver, "//tr[1]/td[5]/div/span");

            int j = 0;

            verify:
            for (String a : wordList) {
                System.out.println("j:" + j);
                if (j < wordList.size()) {
                    try {
                        ip.isTextPresentByXPATH(driver, "//h1/span", a, 15);
                        System.out.println("EmailNotification verified: '" + a + "'");
                        Reporter.log("EmailNotification verified: '" + a + "'");
                        Reporter.log("<br />");
                        Utility.actionBuilderClick(driver, "//div[2]/div/div/div[2]/div[3]/div/div");
                        new WebDriverWait(driver, 60).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[2]/div/div/div[2]/div[3]/div/div")));
                        wordList.remove(j);
                        System.out.println("WordList ka size in b/w: " + wordList.size());
                        break verify;
                    } catch (TimeoutException e) {
                        System.out.println("catch:" + a);
                        if (wordList.size() == 1) {
                            throw e;
                        }
                    }
                    j++;
                }
            }
        }
    }

    /**
     * The annotated method will be run after all the test methods in the
     * current class have been run, User logsOut
     *
     * @throws Exception
     */
    @AfterClass
    public void testStdtEmailLogOut() throws Exception {
        ip.isElementPresentByXPATH(driver, "//td[2]/a");
        try {
            Utility.navigateToSubMenu(driver, "//td[2]/a");
        } catch (UnhandledAlertException e) {
            driver.switchTo().alert().accept();
        }
        ip.isTitlePresent(driver, "Gmail: Email from Google");
    }
}
