/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package runThrghTestNG;

import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import smoketest.Actions;

/**
 * Teacher Login, 
 * Posts on Wall & Course Wall,
 * Creates Social Group
 * 
 */
@Listeners({runThrghTestNG.TestNGCustomReport.class})
public class TchrPosts_SclGrp extends BaseClass{

    static String tchrTxtWallPost;
    static String tchrUrlWallPost;
    static String tchrUrlCrsPost;
    static String tchrSclGrpName;
    Actions a = new Actions();

    /**
     * The annotated method will be run before the first test method in the
     * current class is invoked, Student logs in, Teacher Logs in
     *
     * @throws Exception
     */
    @BeforeClass
    public void testTchrLgn() throws Exception {
        a.login(UsrCrtn_AsgnRole_WrkngGrp.tchrUsrName);
    }

    /**
     * Post & verify Text,URL on own & Course Wall
     *
     * @throws Exception
     */
    @Test
    public void testTchrPostsOn_Wall_CrsWall() throws Exception {
        a.navigateToMyWall();
        tchrTxtWallPost = a.textPost("txtWallPost");
        System.out.println("tchrTxtWallPost: " + tchrTxtWallPost);
        Reporter.log("tchrTxtWallPost: " + tchrTxtWallPost);

        a.navigateToMyWall();
        tchrUrlWallPost = a.urlPost("urlWallPost");
        System.out.println("tchrUrlWallPost: " + tchrUrlWallPost);
        Reporter.log("tchrUrlWallPost: " + tchrUrlWallPost);

        a.selectGrpCourse(Crs_GrpCrsCreation.grpCrsName);
        tchrUrlCrsPost = a.urlPost("urlCrsPost");
        System.out.println("tchrUrlCrsPost: " + tchrUrlCrsPost);
        Reporter.log("tchrUrlCrsPost: " + tchrUrlCrsPost);
    }

    /**
     * Create SocialGroup
     *
     * @throws Exception
     */
    @Test(dependsOnMethods = {"testTchrPostsOn_Wall_CrsWall"}, alwaysRun = true)
    public void testTchrCrtSclGrp() throws Exception {
        a.navigateToMySocialGroups();
        tchrSclGrpName = a.createSocialGroup();
        System.out.println("tchrSclGrpName: " + tchrSclGrpName);
        Reporter.log("tchrSclGrpName: " + tchrSclGrpName);
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
