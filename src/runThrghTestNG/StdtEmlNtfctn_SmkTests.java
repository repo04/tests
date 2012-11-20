/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package runThrghTestNG;

import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
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
public class StdtEmlNtfctn_SmkTests extends BaseClass {

    Actions a = new Actions();
    String vrfy1, vrfy2, vrfy3, vrfy4;
    String tchrFllNm;

    /**
     * The annotated method will be run before the first test method in the
     * current class is invoked, Content Admin logs in
     *
     * @throws Exception
     */
    @BeforeClass(groups = {"prerequisite"})
    public void testStudentEmailLogin() throws Exception {
        Utility.usrEmailLogin(driver, xpv, "2torstudent");
    }

    /**
     * 
     * @param tchrUsrName
     * @param stdtUsrName
     * @param wrkngGrpName
     * @param tchrSclGrpName
     * @param stdtSclGrpName
     * @throws Exception 
     */
    @Test(dataProvider = "UsrsWrkngGrpTchrStdtSclGrps", dataProviderClass = StdtLvSsn_SclGrp_GglDoc.class,
          groups = {"fullsmoke", "stdtVrfyEmails"})
    public void testStudentVerifyEmails(String tchrUsrName, String stdtUsrName, String wrkngGrpName,
            String tchrSclGrpName, String stdtSclGrpName) throws Exception {

        tchrFllNm = tchrUsrName.substring(0, 1).toUpperCase() + tchrUsrName.substring(1);
        vrfy1 = tchrFllNm + "fstNm " + tchrFllNm + "sndNm has joined the group " + stdtSclGrpName + ".";
        vrfy2 = "You are now a member of " + stdtSclGrpName;
        vrfy3 = "You are now a member of " + tchrSclGrpName;
        vrfy4 = "You are now a member of " + wrkngGrpName;       

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
                        String prod;
                        if (BaseClass.program.substring(0, 2).contains("gu")) {
                            prod = "2GU";
                        } else if (BaseClass.program.substring(0, 3).contains("vac")) {
                            prod = "VAC";
                        } else {
                            prod = "2" + program.substring(1, 3).toUpperCase();
                        }
                        ip.isTextPresentByXPATH(driver, "//div[6]/div/div[3]", "Thanks\n" + prod, 15);    
                        
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
                        if (wordList.size() == 1 || e.getMessage().contains("//div[2]/div/div/div[2]/div[3]/div/div")) {
                            throw e;
                        }else if (e.getMessage().contains("//div[6]/div/div")) {
                            Utility.illegalStateException(e.getMessage().substring(0, 75) + " Notification: " + a);
                        }
                    }
                    j++;
                }
            }
        }
        ip.isElementPresentByXPATH(driver, "//div[2]/div/div/div[2]/div/div/div/div/div/div/div/div");
        driver.findElement(By.xpath("//div[2]/div/div/div[2]/div/div/div/div/div/div/div/div")).click();
   }

    /**
     * The annotated method will be run after all the test methods in the
     * current class have been run, User logsOut
     *
     * @throws Exception
     */
    @AfterClass(groups = {"prerequisite"})
    public void testStudentEmailLogOut() throws Exception {
        Utility.usrEmailLogout(driver);
    }
}
