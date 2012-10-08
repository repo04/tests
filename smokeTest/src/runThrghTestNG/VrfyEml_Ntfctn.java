/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package runThrghTestNG;

import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
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
 * @author somesh.bansal
 */
public class VrfyEml_Ntfctn extends BaseClass {

    Actions a = new Actions();
    String vrfy1, vrfy2, vrfy3, vrfy4, vrfy5, vrfy6;
    String stdtFllNm;

    /**
     * The annotated method will be run before the first test method in the
     * current class is invoked, Content Admin logs in
     *
     * @throws Exception
     */
    @BeforeClass
    public void testTchrEmailLgn() throws Exception {
        driver.get("https://mail.google.com/");
        ip.isTitlePresent(driver, "Gmail: Email from Google");
        WebElement gglUsrNm = driver.findElement(By.xpath(av.getTokenValue("fieldGglDocUsrIdXPATH")));
        WebElement gglPswd = driver.findElement(By.xpath(av.getTokenValue("fieldGglDocPswdXPATH")));
        value:
        while (true) {
            gglUsrNm.clear();
            gglPswd.clear();
            gglUsrNm.sendKeys("2torteacher");
            gglPswd.sendKeys("Newuser321");
            try {
                new WebDriverWait(driver, 60).until(ExpectedConditions.textToBePresentInElementValue(By.xpath(av.getTokenValue("fieldGglDocUsrIdXPATH")), "2torteacher"));
                break value;
            } catch (TimeoutException e) {
            }
        }
        driver.findElement(By.xpath(av.getTokenValue("fieldGglDocSignInXPATH"))).click();
        ip.isTitlePresent(driver, "Gmail");
    }

    /**
     *
     * @throws Exception
     */
    @Test(dependsOnMethods = {"runThrghTestNG.UsrCrtn_AsgnRole_WrkngGrp.testAddMbrsToWrkngGrp", "runThrghTestNG.StdtJnSclGrp_Post.testStdtJoinsTchrSclGrp",
        "runThrghTestNG.StdtJnSclGrp_Post.testStdtCmntOnTchrCrsPost", "runThrghTestNG.TchrJoin_Delete_SclGrp.testTchrJoinsStdtSclGrp"})
    public void testVerifyEmail() throws Exception {

        stdtFllNm = UsrCrtn_AsgnRole_WrkngGrp.usrsArray[0][1].substring(0, 1).toUpperCase() + UsrCrtn_AsgnRole_WrkngGrp.usrsArray[0][1].substring(1);
        vrfy1 = "You are now a member of " + StdtLvSsn_SclGrp_GglDoc.stdtSclGrpArray[0][0];
        vrfy2 = stdtFllNm + "fstNm " + stdtFllNm + "sndNm has joined the group " + TchrPosts_SclGrp.tchrSclGrpArray[0][0] + ".";
        vrfy3 = stdtFllNm + "fstNm " + stdtFllNm + "sndNm commented on your post.";
        vrfy4 = "You are now a member of " + TchrPosts_SclGrp.tchrSclGrpArray[0][0];
        vrfy5 = stdtFllNm + "fstNm " + stdtFllNm + "sndNm has joined the group " + UsrCrtn_AsgnRole_WrkngGrp.wrkngGrpArray[0][0] + ".";
        vrfy6 = "You are now a member of " + UsrCrtn_AsgnRole_WrkngGrp.wrkngGrpArray[0][0];

        String[] words = {vrfy1, vrfy2, vrfy3, vrfy4, vrfy5, vrfy6};
        List<String> wordList = Arrays.asList(words);
        int x = wordList.size();

        List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
        String name = iframes.get(iframes.size() - 1).getAttribute("name");
        driver.switchTo().frame(name);
        ip.isElementPresentStartsWithTextByXPATH(driver, "Inbox");
        driver.findElement(By.xpath("//*[starts-with(text(),'Inbox')]")).click();

        for (int i = 1; i < 7; i++) {
            new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//tr[1]/td[5]/div/span")));
            Utility.actionBuilderClick(driver, "//tr[1]/td[5]/div/span");

            int j = 1;
            verify:
            for (String a : wordList) {
                System.out.println("j:" + j);
                if (j < x) {
                    try {
                        ip.isTextPresentByXPATH(driver, "//h1/span", a, 15);
                        System.out.println("Email verified for Text: '" + a + "'");
                        Reporter.log("Email verified for Text: '" + a + "'");
                        Reporter.log("<br />");
                        Utility.actionBuilderClick(driver, "//div[2]/div/div/div[2]/div[3]/div/div");
                        break verify;
                    } catch (TimeoutException e) {
                        System.out.println("catch:" + a);
                    }
                } else {
                    ip.isTextPresentByXPATH(driver, "//h1/span", a);
                    System.out.println("Email verified for Text: '" + a + "'");
                    Reporter.log("Email verified for Text: '" + a + "'");
                    Reporter.log("<br />");
                    Utility.actionBuilderClick(driver, "//div[2]/div/div/div[2]/div[3]/div/div");
                }
                j++;
            }
        }
        driver.switchTo().defaultContent();
    }

    /**
     * The annotated method will be run after all the test methods in the
     * current class have been run, User logsOut
     *
     * @throws Exception
     */
    @AfterClass
    public void testCntntAdminLogOut() throws Exception {
        Utility.navigateToSubMenu(driver, "//td[2]/a");
        ip.isTitlePresent(driver, "Gmail: Email from Google");
    }
}
