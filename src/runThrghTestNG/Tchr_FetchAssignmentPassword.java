/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package runThrghTestNG;

import java.util.Iterator;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import smoketest.Actions;
import smoketest.Utility;

/**
 *
 *
 */
public class Tchr_FetchAssignmentPassword extends BaseClass {

    Actions a = new Actions();
    String vrfy1, vrfy2, vrfy3, vrfy4, vrfy5, vrfy6;
    String stdtFllNm;
    static String[][] quizPasswordArray = new String[1][1];

    public static Object[][] quizPassword(ITestContext context) throws Exception {
        System.out.println("init quizPassword");
        return (quizPasswordArray);
    }

    @DataProvider(name = "GrpCrsPswdQzNamePassword")
    public static Iterator<Object[]> GrpCrsPswdQzNamePassword(ITestContext context) throws Exception {
        System.out.println("init GrpCrsPswdQzNamePassword");
        return DataProviderUtility.cartesianProviderFrom(ContentAdmin_Course_GroupCourseCreation.Course(context),
                ContentAdmin_Course_GroupCourseCreation.PswdQzName(context), quizPassword(context));
    }

    /**
     * The annotated method will be run before the first test method in the
     * current class is invoked, Content Admin logs in
     *
     * @throws Exception
     */
    @BeforeClass(groups = {"prerequisite"})
    public void testTeacherEmailLogin() throws Exception {
        Utility.usrEmailLogin(driver, xpv, "2torteacher");
    }

    /**
     *
     * @param pswdQuizName
     * @throws Exception
     */
    @Test(dataProvider = "PswdQzName", dataProviderClass = ContentAdmin_Course_GroupCourseCreation.class,
          groups = {"regressionSmoke", "fullSmoke", "pswdQuiz.readMail"})
    public void testTeacherFetchQuizPassword(String pswdQuizName) throws Exception {
        int x = 1;
        Boolean mailresult = null;
        loop:
        do {
            ip.isElementPresentByXPATH(driver, "//div[2]/div/div/div[2]/div/div/div/div/div/div/div/div");
            driver.findElement(By.xpath("//div[2]/div/div/div[2]/div/div/div/div/div/div/div/div")).click();
            try {
                ip.isTextPresentByXPATH(driver, "//tr[" + x + "]/td[6]/div/div/div/span/b", "Passwords for " + pswdQuizName, 10);
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

        ip.isTextPresentByXPATH(driver, "//h1/span", "Passwords for " + pswdQuizName, 15);
        String mailContent = driver.findElement(By.xpath("//div[2]/div[6]/div")).getText();

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
    public void testTeacherEmailLogOut() throws Exception {
        Utility.usrEmailLogout(driver);
        driver.get(url);
        ip.isTitlePresent(driver, xpv.getTokenValue(program + "loginPageTitle"));
    }
}
