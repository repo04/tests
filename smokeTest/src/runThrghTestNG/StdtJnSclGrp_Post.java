/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package runThrghTestNG;

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
    public void testStdtLgn() throws Exception {
        a.login(UsrCrtn_AsgnRole_WrkngGrp.stdtUsrName);
    }

    /**
     * Find & Join Teacher's Social Group
     *
     * @throws Exception
     */
    @Test(dependsOnMethods = {"runThrghTestNG.TchrPosts_SclGrp.testTchrCrtSclGrp"})
    public void testStdtJoinsTchrSclGrp() throws Exception {
        a.navigateToMySocialGroups();
        a.findSocialGroup(TchrPosts_SclGrp.tchrSclGrpName);
        a.joinSocialGroup(TchrPosts_SclGrp.tchrSclGrpName);
    }

    /**
     * Post/Verify URL on Teacher's SocialGroup
     *
     * @throws Exception
     */
    @Test(dependsOnMethods = {"testStdtJoinsTchrSclGrp"})
    public void testStdtPostURLOnTchrSclGrp() throws Exception {
        a.navigateToMySocialGroups();
        a.accessSclGrpWall(TchrPosts_SclGrp.tchrSclGrpName);
        stdtUrlPostOnTchrSclGrp = a.urlPost("urlSclGrpPost");
        System.out.println("stdtUrlPostOnTchrSclGrp: " + stdtUrlPostOnTchrSclGrp);
        Reporter.log("stdtUrlPostOnTchrSclGrp: " + stdtUrlPostOnTchrSclGrp);        
    }

    /**
     * Add Comment on Teacher's CoursePost
     *
     * @throws Exception
     */
    @Test(dependsOnMethods = {"runThrghTestNG.TchrPosts_SclGrp.testTchrPostsOn_Wall_CrsWall"})
    public void testStdtCmntOnTchrCrsPost() throws Exception {
        a.selectGrpCourse(Crs_GrpCrsCreation.grpCrsName);
        stdtTxtCmntOnTchrCrsPost = a.textCmntPost(TchrPosts_SclGrp.tchrUrlCrsPost, "txtCmntOnTchrCrsPst");
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
