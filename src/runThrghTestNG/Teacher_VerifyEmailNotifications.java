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
public class Teacher_VerifyEmailNotifications extends BaseClass {

    Actions a = new Actions();
    String vrfy1, vrfy2, vrfy3, vrfy4, vrfy5, vrfy6;
    String stdtFllNm;
    
    /**
     * The annotated method will be run before the first test method in the
     * current class is invoked, Content Admin logs in
     *
     * @throws Exception
     */
    @BeforeClass(groups = {"prerequisite"})
    public void testTeacherEmailLogIn() throws Exception {
        Utility.usrEmailLogin(driver, xpv, "2torteacher");
    }

    /**
     * Teacher verifies Critical Smoke Test Email Notifications & delete
     * subsequently *
     *
     * @param tchrSclGrpName
     * @param stdtSclGrpName
     * @throws Exception
     */
    @Test(dataProvider = "TchrStdtSclGrps", dataProviderClass = Student_LiveSession_SocialGroup_GoogleDoc.class,
          groups = {"tchrVrfyCriticalTestEmails"})
    public void testTeacherVerifyCriticalSmokeTestEmails(String tchrSclGrpName, String stdtSclGrpName) throws Exception {

        stdtFllNm = "Auto Student1";

        vrfy1 = "You are now a member of " + stdtSclGrpName;
        vrfy2 = stdtFllNm + " commented on your post.";
        vrfy3 = stdtFllNm + " has joined the group " + tchrSclGrpName + ".";
        vrfy4 = "You are now a member of " + tchrSclGrpName;

        ArrayList<String> wordList = new ArrayList<>();
        wordList.add(vrfy1);
        wordList.add(vrfy2);
        wordList.add(vrfy3);
        wordList.add(vrfy4);

        ip.isElementPresentByXPATH(driver, "//div[2]/div/div/div[2]/div/div/div/div/div/div/div/div");
        driver.findElement(By.xpath("//div[2]/div/div/div[2]/div/div/div/div/div/div/div/div")).click();

        for (int i = 0; i < 4; i++) {
            ip.isElementClickableByXpath(driver, "//tr[1]/td[5]/div/span", 60);
            Utility.actionBuilderClick(driver, "//tr[1]/td[5]/div/span");

            int j = 0;

            verify:
            for (String a : wordList) {
                System.out.println("j:" + j);
                if (j < wordList.size()) {
                    try {
                        ip.isTextPresentByXPATH(driver, "//h1/span", a, 15);
                        String env;
                        if (BaseClass.program.substring(0, 2).contains("gu")) {
                            env = "2GU";
                        } else if (program.substring(0, 3).contains("vac")) {
                            env = "VAC";
                        } else if (program.substring(0, 3).contains("mpa")) {
                            env = "2SG";
                        } else if (program.substring(0, 3).contains("llm")) {
                            env = "@WashuLaw";
                        } else {
                            env = "2" + program.substring(1, 3).toUpperCase();
                        }

                        if (a.contentEquals(vrfy2)) {
                            ip.isTextPresentByXPATH(driver, "//div[6]/div/div[4]", "Thanks\n" + env, 15);
                        } else {
                            ip.isTextPresentByXPATH(driver, "//div[6]/div/div[3]", "Thanks\n" + env, 15);
                        }
                        System.out.println("EmailNotification verified: '" + a + "'");
                        Reporter.log("EmailNotification verified: '" + a + "'");
                        Reporter.log("<br/>");
                        Utility.actionBuilderClick(driver, "//div[2]/div/div/div[2]/div[3]/div/div");
                        new WebDriverWait(driver, 60).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[2]/div/div/div[2]/div[3]/div/div")));
                        wordList.remove(j);
                        System.out.println("WordList ka size in b/w: " + wordList.size());
                        break verify;
                    } catch (TimeoutException e) {
                        System.out.println("catch:" + a);
                        if (wordList.size() == 1 || e.getMessage().contains("//div[2]/div/div/div[2]/div[3]/div/div")) {
                            throw e;
                        } else if (e.getMessage().contains("//div[6]/div/div")) {
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
     * Teacher verifies Full Smoke Test Email Notifications & delete
     * subsequently
     *
     * @param tchrUsrName
     * @param stdtUsrName
     * @param wrkngGrpName
     * @param tchrSclGrpName
     * @param stdtSclGrpName
     * @throws Exception
     */
    @Test(dataProvider = "UsrsWrkngGrpTchrStdtSclGrps", dataProviderClass = Student_LiveSession_SocialGroup_GoogleDoc.class,
          groups = {"tchrVrfySmokeTestEmails"})
    public void testTeacherVerifyFullSmokeTestEmails(String tchrUsrName, String stdtUsrName, String wrkngGrpName,
            String tchrSclGrpName, String stdtSclGrpName) throws Exception {

        stdtFllNm = stdtUsrName.substring(0, 1).toUpperCase() + stdtUsrName.substring(1);
        vrfy1 = "You are now a member of " + stdtSclGrpName;
        vrfy2 = stdtFllNm + "fstNm " + stdtFllNm + "sndNm has joined the group " + tchrSclGrpName + ".";
        vrfy3 = stdtFllNm + "fstNm " + stdtFllNm + "sndNm commented on your post.";
        vrfy4 = "You are now a member of " + tchrSclGrpName;
        vrfy5 = stdtFllNm + "fstNm " + stdtFllNm + "sndNm has joined the group " + wrkngGrpName + ".";
        vrfy6 = "You are now a member of " + wrkngGrpName;

        ArrayList<String> wordList = new ArrayList<>();
        wordList.add(vrfy1);
        wordList.add(vrfy2);
        wordList.add(vrfy3);
        wordList.add(vrfy4);
        wordList.add(vrfy5);
        wordList.add(vrfy6);

        ip.isElementPresentByXPATH(driver, "//div[2]/div/div/div[2]/div/div/div/div/div/div/div/div");
        driver.findElement(By.xpath("//div[2]/div/div/div[2]/div/div/div/div/div/div/div/div")).click();

        for (int i = 0; i < 6; i++) {
            ip.isElementClickableByXpath(driver, "//tr[1]/td[5]/div/span", 60);
            Utility.actionBuilderClick(driver, "//tr[1]/td[5]/div/span");

            int j = 0;

            verify:
            for (String a : wordList) {
                System.out.println("j:" + j);
                if (j < wordList.size()) {
                    try {
                        ip.isTextPresentByXPATH(driver, "//h1/span", a, 15);
                        String env = null;
                        /**
                         * if (program.contains("gu-msn")) { env = "2GU"; } else
                         * if (program.substring(0, 3).contains("vac")) { env =
                         * "VAC"; } else { env = "2" + program.substring(1,
                         * 3).toUpperCase(); }
                         */
                        switch (program) {
                            case "unc-mba":
                                env = "2NC";
                                break;
                            case "gu-msn":
                                env = "2GU";
                                break;
                            case "usc-msw":
                                env = "VAC";
                                break;
                            case "usc-mat":
                                env = "2SC";
                                break;
                            case "wu-llm":
                                env = "LLM";
                                break;
                            case "unc-mpa":
                                env = "MPA";
                        }

                        if (a.contentEquals(vrfy3)) {
                            ip.isTextPresentByXPATH(driver, "//div[6]/div/div[4]", "Thanks\n" + env, 15);
                        } else {
                            ip.isTextPresentByXPATH(driver, "//div[6]/div/div[3]", "Thanks\n" + env, 15);
                        }
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
                        } else if (e.getMessage().contains("//div[6]/div/div")) {
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
    public void testTeacherEmailLogOut() throws Exception {
        Utility.usrEmailLogout(driver);        
    }
}
