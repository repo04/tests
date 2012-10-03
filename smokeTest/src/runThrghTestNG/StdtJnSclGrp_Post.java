/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package runThrghTestNG;

import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import smoketest.Actions;

/**
 * Student logs in, Find & Join Teacher's Social Group, Post/Verify URL on
 * Teacher's SocialGroup,
 *
 */
public class StdtJnSclGrp_Post extends BaseClass {

    static String stdtUrlPostOnTchrSclGrp;
    static String stdtTxtCmntOnTchrCrsPost;
    Actions a = new Actions();

    /**
     * The annotated method will be run before the first test method in the
     * current class is invoked, Student logs in
     *
     * @throws Exception
     */
    @BeforeClass
    public void testStdtLgn(ITestContext context) throws Exception {
        if (test.equalsIgnoreCase("SmokeTests")) {
            a.login(UsrCrtn_AsgnRole_WrkngGrp.usrsArray[0][1]);
        } else {
            a.login(context.getCurrentXmlTest().getParameter("stdtUsrName"));
        }
    }

    /**
     * Find & Join Teacher's Social Group
     *
     * @throws Exception
     */
    //@Test(dataProvider = "TchrSclGrp", dataProviderClass = TchrPosts_SclGrp.class, dependsOnMethods = {"runThrghTestNG.TchrPosts_SclGrp.testTchrCrtSclGrp"})
    @Test
    public void testStdtJoinsTchrSclGrp(String tchrSclGrpName) throws Exception {
        a.navigateToMySocialGroups();
        a.findSocialGroup(tchrSclGrpName);
        a.joinSocialGroup(tchrSclGrpName);
    }

    /**
     * Post/Verify URL on Teacher's SocialGroup
     *
     * @throws Exception
     */
    //@Test(dataProvider = "TchrSclGrp", dataProviderClass = TchrPosts_SclGrp.class, dependsOnMethods = {"testStdtJoinsTchrSclGrp"})
    @Test
    public void testStdtPostURLOnTchrSclGrp(String tchrSclGrpName) throws Exception {
        a.navigateToMySocialGroups();
        a.accessSclGrpWall(tchrSclGrpName);
        stdtUrlPostOnTchrSclGrp = a.urlPost("urlSclGrpPost");
        System.out.println("stdtUrlPostOnTchrSclGrp: " + stdtUrlPostOnTchrSclGrp);
        Reporter.log("stdtUrlPostOnTchrSclGrp: " + stdtUrlPostOnTchrSclGrp);
    }

    /**
     * Add Comment on Teacher's CoursePost
     *
     * @throws Exception
     */
    //@Test(dataProvider = "GrpCrsTchrUrlCrsPst", dataProviderClass = TchrPosts_SclGrp.class, dependsOnMethods = {"runThrghTestNG.TchrPosts_SclGrp.testTchrPostsOn_Wall_CrsWall"})
    @Test
    public void testStdtCmntOnTchrCrsPost(String grpCrsName, String tchrUrlCrsPost) throws Exception {
        a.selectGrpCourse(grpCrsName);
        stdtTxtCmntOnTchrCrsPost = a.textCmntPost(tchrUrlCrsPost, "txtCmntOnTchrCrsPst");
        System.out.println("stdtTxtCmntOnTchrCrsPost: " + stdtTxtCmntOnTchrCrsPost);
        Reporter.log("stdtTxtCmntOnTchrCrsPost: " + stdtTxtCmntOnTchrCrsPost);
    }

    /**
     * The annotated method will be run after all the test methods in the
     * current class have been run
     *
     * @throws Exception
     */
    @AfterClass
    public void testStdtLogOut() throws Exception {
        a.logOut();
    }
}
