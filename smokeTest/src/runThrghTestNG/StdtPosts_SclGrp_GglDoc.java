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
public class StdtPosts_SclGrp_GglDoc extends BaseClass {

    static String stdtSclGrpName = null;
    static String stdtUrlPostOnTchrSclGrp = null;

    @Test
    public void testStdtLgn() throws Exception {
        a.login(UsrCrtn_AsgnRole_WrkngGrp.stdtUsrName);
    }

    @Test(dependsOnMethods = {"testStdtLgn"})
    public void testStdtCrtSclGrp() throws Exception {
        a.navigateToMySocialGroups();
        stdtSclGrpName = a.createSocialGroups();
        System.out.println("stdtSclGrpName: " + stdtSclGrpName);
    }

    @Test(dependsOnMethods = {"testStdtLgn"})
    public void testStdtJoinsTchrSclGrp() throws Exception {
        a.navigateToMySocialGroups();
        a.findSocialGroup(TchrPosts_SclGrp_GglDoc.tchrSclGrpName);
        a.joinSocialGroup(TchrPosts_SclGrp_GglDoc.tchrSclGrpName);
    }

    @Test(dependsOnMethods = {"testStdtLgn"})
    public void testStdtVrfyWrkGrp() throws Exception {
        a.navigateToWorkingGroups();
        a.vrfyWrkngGrp_GglDoc(UsrCrtn_AsgnRole_WrkngGrp.wrkngGrpName, TchrPosts_SclGrp_GglDoc.gglDocName);
    }

    @Test(dependsOnMethods = {"testStdtJoinsTchrSclGrp"})
    public void testStdtPostURLOnTchrSclGrp() throws Exception {
        a.navigateToMySocialGroups();
        a.acessSclGrpWall(TchrPosts_SclGrp_GglDoc.tchrSclGrpName);
        stdtUrlPostOnTchrSclGrp = a.urlPost("urlSclGrpPost");
        System.out.println("stdtUrlPostOnTchrSclGrp: " + stdtUrlPostOnTchrSclGrp);
    }

    @Test(dependsOnMethods = {"testStdtJoinsTchrSclGrp"})
    public void testStdtCrtLvSsn() throws Exception {
        a.navigateToMySocialGroups();
        a.acessSclGrpWall(TchrPosts_SclGrp_GglDoc.tchrSclGrpName);
        a.acessLvSsnWall();
        a.createLiveSsn(TchrPosts_SclGrp_GglDoc.tchrSclGrpName);
    }

    @Test(dependsOnMethods = {"testStdtPostURLOnTchrSclGrp"})
    public void testStdtVrfyURLPsts_TopNews_RcntNews() throws Exception {
        a.navigateToMyHome();
        a.vrfyURLPstsAsTopNews_RcntNews(stdtUrlPostOnTchrSclGrp, TchrPosts_SclGrp_GglDoc.tchrUrlCrsPost, TchrPosts_SclGrp_GglDoc.tchrUrlPostOnStdtWall, TchrPosts_SclGrp_GglDoc.tchrUrlWallPost);
    }

    @Test(dependsOnMethods = {"testStdtLgn"})
    public void testStdtVrfyActivities() throws Exception {
        a.navigateToMyCourse();
        a.selectGrpCourse(Crs_GrpCrsCreation.grpCrsName);
        a.navigateToActvtyRprt();
        a.verifyActivities(Crs_GrpCrsCreation.frmActvyName, Crs_GrpCrsCreation.quizActvtyName, Crs_GrpCrsCreation.allInOneAsgnmntAvtvtyName, Crs_GrpCrsCreation.pageActvtyName);
    }
}
