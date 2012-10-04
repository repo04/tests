/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package runThrghTestNG;

import java.util.Iterator;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import smoketest.Actions;

/**
 * Teacher Login, Posts on Wall & Course Wall, Creates Social Group
 *
 */
public class TchrPosts_SclGrp extends BaseClass {

    String tchrTxtWallPost;
    String tchrUrlWallPost;
    static String[][] tchrUrlCrsPostArray = new String[1][1];
    static String[][] tchrSclGrpArray = new String[1][1];
    Actions a = new Actions();

    @DataProvider(name = "tchrUrlCrsPost")
    public static Object[][] tchrUrlCrsPost(ITestContext context) throws Exception {

        System.out.println("init tchrUrlCrsPost");

        if (test.equalsIgnoreCase("SmokeTests") || test.equalsIgnoreCase("CriticalTests")) {
            System.out.println("if tchrUrlCrsPost: " + test);
            return (tchrUrlCrsPostArray);
        } else {
            System.out.println("else tchrUrlCrsPost: " + test);
            return new Object[][]{{context.getCurrentXmlTest().getParameter("tchrUrlCrsPost")}};
        }
    }

    @DataProvider(name = "TchrSclGrp")
    public static Object[][] TchrSclGrp(ITestContext context) throws Exception {

        System.out.println("init TchrSclGrp");


        if (test.equalsIgnoreCase("SmokeTests") || test.equalsIgnoreCase("CriticalTests")) {
            System.out.println("if TchrSclGrp: " + test);
            return (tchrSclGrpArray);
        } else {
            System.out.println("else TchrSclGrp: " + test);
            return new Object[][]{{context.getCurrentXmlTest().getParameter("tchrSclGrpName")}};
        }
    }

    @DataProvider(name = "GrpCrsTchrUrlCrsPst")
    public static Iterator<Object[]> GrpCrsTchrUrlCrsPst(ITestContext context) throws Exception {
        System.out.println("init GrpCrsTchrUrlCrsPst");
        return DataProviderUtil.cartesianProviderFrom(Crs_GrpCrsCreation.Course(context), tchrUrlCrsPost(context));
    }

    /**
     * The annotated method will be run before the first test method in the
     * current class is invoked, Student logs in, Teacher Logs in
     *
     * @throws Exception
     */
    @BeforeClass
    public void testTchrLgn(ITestContext context) throws Exception {
        if (test.equalsIgnoreCase("SmokeTests")) {
            a.login(UsrCrtn_AsgnRole_WrkngGrp.usrsArray[0][0]);
        } else {
            a.login(context.getCurrentXmlTest().getParameter("tchrUsrName"));
        }
    }

    /**
     * Post & verify Text,URL on own & Course Wall
     *
     * @throws Exception
     */
    @Test(dataProvider = "Course", dataProviderClass = Crs_GrpCrsCreation.class)
    public void testTchrPostsOn_Wall_CrsWall(String grpCrsName) throws Exception {
        a.navigateToMyWall();
        tchrTxtWallPost = a.textPost("txtWallPost");
        System.out.println("tchrTxtWallPost: " + tchrTxtWallPost);
        Reporter.log("tchrTxtWallPost: " + tchrTxtWallPost);

        a.navigateToMyWall();
        tchrUrlWallPost = a.urlPost("urlWallPost");
        System.out.println("tchrUrlWallPost: " + tchrUrlWallPost);
        Reporter.log("tchrUrlWallPost: " + tchrUrlWallPost);

        a.selectGrpCourse(grpCrsName);
        tchrUrlCrsPostArray[0][0] = a.urlPost("urlCrsPost");
        System.out.println("tchrUrlCrsPost: " + tchrUrlCrsPostArray[0][0]);
        Reporter.log("tchrUrlCrsPost: " + tchrUrlCrsPostArray[0][0]);
    }

    /**
     * Create SocialGroup
     *
     * @throws Exception
     */
    @Test
    public void testTchrCrtSclGrp() throws Exception {
        a.navigateToMySocialGroups();
        tchrSclGrpArray[0][0] = a.createSocialGroup();
        System.out.println("tchrSclGrpName: " + tchrSclGrpArray[0][0]);
        Reporter.log("tchrSclGrpName: " + tchrSclGrpArray[0][0]);
    }

    /**
     * The annotated method will be run after all the test methods in the
     * current class have been run
     *
     * @throws Exception
     */
    @AfterClass
    public void testTchrLogOut() throws Exception {
        a.logOut();
    }
}
