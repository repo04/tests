/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package runThrghTestNG;

import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


/**
 *
 * Teacher LOGS in 
 * Post/Verify Text ,URL on own wall 
 * Post/Verify URL on Course Wall 
 * Post/Verify URL on Stdt's wall - PENDING
 * Create SocialGroup 
 * Find SclGroup & Create LiveSession in SclGroup
 * Create GoogleDoc in Working Group
 * Verify All Posts on Top & RecentNews
 * Verify Activities & resource appear items on activity report
 *
 * @author somesh.bansal
 */
@Listeners({runThrghTestNG.TestNGCustomReport.class})
public class TchrPosts_SclGrp_GglDoc extends BaseClass {

    static String tchrTxtWallPost;
    static String tchrUrlWallPost;
    static String tchrUrlCrsPost;
    static String tchrUrlPostOnStdtWall;
    static String tchrSclGrpName;
    static String gglDocName;

    //Teacher LOGS in
    @Test
    public void testTchrLgn() throws Exception {
        a.login(UsrCrtn_AsgnRole_WrkngGrp.tchrUsrName);
    }

    //Post/Verify Text ,URL on own wall 
    //Post/Verify URL on Course Wall 
    @Test(dependsOnMethods = {"testTchrLgn"})
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

    //Verify All URL Posts on Top & RecentNews
    @Test(dependsOnMethods = {"testTchrPostsOn_Wall_CrsWall"})
    public void testTchrVrfyURLPsts_Top_RcntNews() throws Exception {
        a.navigateToMyHome();
        a.vrfyURLPstsAsTopNews_RcntNews(tchrUrlWallPost, tchrUrlCrsPost, tchrUrlPostOnStdtWall);
    }

    //Create SocialGroup
    @Test(dependsOnMethods = {"testTchrLgn"})
    public void testTchrCrtSclGrp() throws Exception {
        a.navigateToMySocialGroups();
        tchrSclGrpName = a.createSocialGroups();
        System.out.println("tchrSclGrpName: " + tchrSclGrpName);
        Reporter.log("tchrSclGrpName: " + tchrSclGrpName);
    }

    //Find SclGroup & Create LiveSession in SclGroup
    @Test(dependsOnMethods = {"testTchrCrtSclGrp"})
    public void testTchrCrtLvSsn() throws Exception {
        a.navigateToMySocialGroups();
        a.acessSclGrpWall(tchrSclGrpName);
        a.acessLvSsnWall();
        a.createLiveSsn(tchrSclGrpName);
    }

    //Create GoogleDoc in Working Group
    @Test(dependsOnMethods = {"testTchrLgn"})
    public void testTchrCrtGglDoc() throws Exception {
        a.navigateToWorkingGroups();
        gglDocName = a.createGoogleDoc(UsrCrtn_AsgnRole_WrkngGrp.wrkngGrpName);
        System.out.println("gglDocName: " + gglDocName);
        Reporter.log("gglDocName: " + gglDocName);
    }

    //Verify Activities & resource appear items on activity report
    @Test(dependsOnMethods = {"testTchrLgn"})
    public void testTchrVrfyActivities() throws Exception {
        a.navigateToMyCourse();
        a.selectGrpCourse(Crs_GrpCrsCreation.grpCrsName);
        a.navigateToActvtyRprt();
        a.verifyActivities(Crs_GrpCrsCreation.frmActvyName, Crs_GrpCrsCreation.quizActvtyName, Crs_GrpCrsCreation.allInOneAsgnmntAvtvtyName, Crs_GrpCrsCreation.pageActvtyName);
    }
}
