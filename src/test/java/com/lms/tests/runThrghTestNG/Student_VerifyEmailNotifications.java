/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lms.tests.runThrghTestNG;

import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.lms.tests.smoketest.Actions;
import com.lms.tests.smoketest.Utility;

/**
 *
 *
 */
public class Student_VerifyEmailNotifications extends BaseClass {

    Actions a = new Actions();
    String verify1, verify2, verify3, verify4;
    String teacherFullName;

    /**
     * The annotated method will be run before the first test method in the
     * current class is invoked, Content Admin logs in
     *
     * @throws Exception
     */
    @BeforeClass(groups = {"prerequisite"})
    public void testStudentEmailLogIn() throws Exception {
        Utility.userEmailLogIn(driver, xpv, ldv.getTokenValue("emailUserName"), ldv.getTokenValue("emailPassword"));
    }
    
    /**
     * Student verifies Critical Smoke Test Email Notifications & delete subsequently
     *
     * @param teacherSocialGroupName
     * @param studentSocialGroupName
     * @throws Exception
     */
    @Test(dataProvider = "TeacherStudentSocialGroups", dataProviderClass = Student_LiveSession_SocialGroup_GoogleDoc.class,
          groups = {"studentVerifyCriticalSmokeTestEmails"})
    public void testStudentVerifyCriticalSmokeTestEmails(String teacherSocialGroupName, String studentSocialGroupName) throws Exception {

        teacherFullName = "Auto Teacher1";

        verify1 = teacherFullName + " has joined the group " + studentSocialGroupName + ".";
        verify2 = "You are now a member of " + studentSocialGroupName;
        verify3 = "You are now a member of " + teacherSocialGroupName;
        verify4 = "Posted on your Wall.";

        ArrayList<String> wordList = new ArrayList<>();
        wordList.add(verify1);
        wordList.add(verify2);
        wordList.add(verify3);
        wordList.add(verify4);

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
                        String prod;
                        if (BaseClass.program.substring(0, 2).contains("gu")) {
                            prod = "2GU";
                        } else if (BaseClass.program.substring(0, 3).contains("vac")) {
                            prod = "VAC";
                        } else if (BaseClass.program.substring(0, 3).contains("mpa")) {
                            prod = "2SG";
                        } else if (BaseClass.program.substring(0, 3).contains("llm")) {
                            prod = "@WashuLaw";
                        } else {
                            prod = "2" + program.substring(1, 3).toUpperCase();
                        }

                        if (a.contentEquals(verify4)) {
                            ip.isTextPresentByXPATH(driver, "//div[6]/div/div[4]", "Thanks\n" + prod, 15);
                        } else {
                            ip.isTextPresentByXPATH(driver, "//div[6]/div/div[3]", "Thanks\n" + prod, 15);
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
     * Student verifies Full Smoke Test Email Notifications & delete subsequently
     * 
     * @param teacherUserName
     * @param studentUserName
     * @param workingGroupName
     * @param teacherSocialGroupName
     * @param studentSocialGroupName
     * @throws Exception
     */
    @Test(dataProvider = "UsersWorkingGroupTeacherStudentSocialGroups", dataProviderClass = Student_LiveSession_SocialGroup_GoogleDoc.class,
          groups = {"studentVrfyFullSmokeTestEmails"})
    public void testStudentVerifyFullSmokeTestEmails(String teacherUserName, String studentUserName, String workingGroupName,
            String teacherSocialGroupName, String studentSocialGroupName) throws Exception {

        teacherFullName = teacherUserName.substring(0, 1).toUpperCase() + teacherUserName.substring(1);
        verify1 = teacherFullName + "fstNm " + teacherFullName + "sndNm has joined the group " + studentSocialGroupName + ".";
        verify2 = "You are now a member of " + studentSocialGroupName;
        verify3 = "You are now a member of " + teacherSocialGroupName;
        verify4 = "You are now a member of " + workingGroupName;

        ArrayList<String> wordList = new ArrayList<>();
        wordList.add(verify1);
        wordList.add(verify2);
        wordList.add(verify3);
        wordList.add(verify4);

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
    public void testStudentEmailLogOut() throws Exception {
        Utility.userEmailLogOut(driver);
    }
}
