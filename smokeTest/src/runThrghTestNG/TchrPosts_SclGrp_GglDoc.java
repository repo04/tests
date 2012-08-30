/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package runThrghTestNG;

import org.testng.annotations.Test;

/**
 *
 * @author somesh.bansal
 */
public class TchrPosts_SclGrp_GglDoc extends BaseClass {

    static String tchrTxtWallPost = null;
    static String tchrUrlWallPost = null;
    static String tchrUrlCrsPost = null;
    static String tchrUrlPostOnStdtWall = null;
    static String tchrSclGrpName = null;
    static String gglDocName = null;

    @Test
    public void testTchrLgn() throws Exception {
        a.login(UsrCrtn_AsgnRole_WrkngGrp.tchrUsrName);
    }

    @Test(dependsOnMethods = {"testTchrLgn"})
    public void testTchrPostsOn_Wall_CrsWall() throws Exception{
        a.navigateToMyWall();
        tchrTxtWallPost = a.textPost("txtWallPost");
        System.out.println("tchrTxtWallPost: " + tchrTxtWallPost);

        a.navigateToMyWall();
        tchrUrlWallPost = a.urlPost("urlWallPost");
        System.out.println("tchrUrlWallPost: " + tchrUrlWallPost);

        a.selectGrpCourse(Crs_GrpCrsCreation.grpCrsName);
        tchrUrlCrsPost = a.urlPost("urlCrsPost");
        System.out.println("tchrUrlCrsPost: " + tchrUrlCrsPost);
    }

    @Test(dependsOnMethods = {"testTchrPostsOn_Wall_CrsWall"})
    public void testTchrVrfyURLPsts_TopNews_RcntNews() throws Exception{
        a.navigateToMyHome();
        a.vrfyURLPstsAsTopNews_RcntNews(tchrUrlWallPost, tchrUrlCrsPost, tchrUrlPostOnStdtWall);
    }

    @Test(dependsOnMethods = {"testTchrLgn"})
    public void testTchrCrtSclGrp() throws Exception{
        a.navigateToMySocialGroups();
        tchrSclGrpName = a.createSocialGroups();
        System.out.println("tchrSclGrpName: " + tchrSclGrpName);
    }

    @Test(dependsOnMethods = {"testTchrCrtSclGrp"})
    public void testTchrCrtLvSsn() throws Exception{
        a.navigateToMySocialGroups();
        a.acessSclGrpWall(tchrSclGrpName);
        a.acessLvSsnWall();
        a.createLiveSsn(tchrSclGrpName);
    }

    @Test(dependsOnMethods = {"testTchrLgn"})
    public void testTchrCrtGglDoc() throws Exception{
        a.navigateToWorkingGroups();
        gglDocName = a.createGoogleDoc(UsrCrtn_AsgnRole_WrkngGrp.wrkngGrpName);
        System.out.println("gglDocName: " + gglDocName);
    }

    @Test(dependsOnMethods = {"testTchrLgn"})
    public void testTchrVrfyActivities() throws Exception{
        a.navigateToMyCourse();
        a.selectGrpCourse(Crs_GrpCrsCreation.grpCrsName);
        a.navigateToActvtyRprt();
        a.verifyActivities(Crs_GrpCrsCreation.frmActvyName, Crs_GrpCrsCreation.quizActvtyName, Crs_GrpCrsCreation.allInOneAsgnmntAvtvtyName, Crs_GrpCrsCreation.pageActvtyName);
    }
}
