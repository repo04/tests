/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lms.tests.runThrghTestNG;

import java.util.Iterator;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.lms.tests.smoketest.Utility;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 *
 *
 */
public class Teacher_FetchActivityPassword extends BaseClass {

    String verify1, verify2, verify3, verify4, verify5, verify6;
    String studentFullName;
    static String[][] quizPasswordArray = new String[1][1];

    public static Object[][] quizPassword(ITestContext context) throws Exception {
        System.out.println("init quizPassword");
        return (quizPasswordArray);
    }
                          
    @DataProvider(name = "GroupCoursePasswordQuizNamePassword")
    public static Iterator<Object[]> GroupCoursePasswordQuizNamePassword(ITestContext context) throws Exception {
        System.out.println("init GroupCoursePasswordQuizNamePassword");
        return DataProviderUtility.cartesianProviderFrom(ContentAdmin_Course_GroupCourseCreation.GroupCourse(context),
                ContentAdmin_Course_GroupCourseCreation.PasswordQuizName(context), quizPassword(context));
    }

    /**
     * The annotated method will be run before the first test method in the
     * current class is invoked, Content Admin logs in
     *
     * @throws Exception
     */
    @BeforeClass(groups = {"prerequisite"})
    public void testTeacherEmailFetchActivityPasswordLogIn() throws Exception {
        Utility.userEmailLogIn(getWebdriver(), xpv, ldv.getTokenValue("emailUserName"), ldv.getTokenValue("emailPassword"));
    }

    /**
     *
     * @param passwordQuizName
     * @throws Exception
     */
    @Test(dataProvider = "PasswordQuizName", dataProviderClass = ContentAdmin_Course_GroupCourseCreation.class,
          groups = {"regressionSmoke", "fullSmoke", "pswdQuiz.readMail"})
    public void testTeacherFetchQuizPassword(String passwordQuizName) throws Exception {
        
        RemoteWebDriver driver = getWebdriver();
        int x = 1;
        Boolean mailresult = null;
        loop:
        do {
            ip.isElementPresentByXPATH(driver, "//div[2]/div/div/div[2]/div/div/div/div/div/div/div/div");
            driver.findElement(By.xpath("//div[2]/div/div/div[2]/div/div/div/div/div/div/div/div")).click();
            try {
                ip.isTextPresentByXPATH(driver, "//tr[" + x + "]/td[6]/div/div/div/span/b", "Passwords for " + passwordQuizName, 10);
                Utility.actionBuilderClick(driver, "//tr[" + x + "]/td[6]/div/div/div/span/b");
                mailresult = false;
                break loop;
            } catch (TimeoutException e) {
                System.out.println("Password Email Notification not present at " + x + " position");
                mailresult = true;
                x++;
            }
        } while (x < 10);

        if (mailresult) {
            Utility.illegalStateException("Quiz Password Email Notification not received");
        }

        ip.isTextPresentByXPATH(driver, "//h1/span", "Passwords for " + passwordQuizName, 15);
        String mailContent = driver.findElement(By.xpath("//div/div/div/div/div[2]/div[6]/div")).getText();

        System.out.println("Mail: " + mailContent);
        int z = mailContent.lastIndexOf(":");

        System.out.println("z value: " + z);
        String pswd = mailContent.substring(z);
        quizPasswordArray[0][0] = pswd.substring(2);
        System.out.println("print:" + "**" + quizPasswordArray[0][0] + "**");
    }

    /**
     * The annotated method will be run after all the test methods in the
     * current class have been run, User logsOut
     *
     * @throws Exception
     */
    @AfterClass(groups = {"prerequisite"})
    public void testTeacherEmailFetchActivityPasswordLogOut() throws Exception {
        RemoteWebDriver driver = getWebdriver();
        Utility.userEmailLogOut(driver);
        driver.get(getURL());
        Utility.verifyCurrentUrl(driver, xpv.getTokenValue("loginPageURL"));
    }
}
