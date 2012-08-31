/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package runThrghTestNG;

import org.testng.annotations.Test;

/**
 * Student logs in 
 * Find & Join Teacher's Scl Grp 
 * Post/Verify URL on Tchr's Scl Grp 
 * Create Live Session in Teacher's Social Group 
 * Creates own Social Group 
 * Verify Google Doc
 * Verify All Posts on Top/Recent News  
 * Verify Activities & resource items appear in activity report
 *
 * @author somesh.bansal
 */
public class StdtPosts_SclGrp_GglDoc extends BaseClass {

    static String stdtSclGrpName;
    static String stdtUrlPostOnTchrSclGrp;

    //Student logs in
    @Test
    public void testStdtLgn() throws Exception {
        a.login(UsrCrtn_AsgnRole_WrkngGrp.stdtUsrName);
    }

    //Creates own Social Group
    @Test(dependsOnMethods = {"testStdtLgn"})
    public void testStdtCrtSclGrp() throws Exception {
        a.navigateToMySocialGroups();
        stdtSclGrpName = a.createSocialGroups();
        System.out.println("stdtSclGrpName: " + stdtSclGrpName);
    }

    //Find & Join Teacher's Social Group 
    @Test(dependsOnMethods = {"testStdtLgn"})
    public void testStdtJoinsTchrSclGrp() throws Exception {
        a.navigateToMySocialGroups();
        a.findSocialGroup(TchrPosts_SclGrp_GglDoc.tchrSclGrpName);
        a.joinSocialGroup(TchrPosts_SclGrp_GglDoc.tchrSclGrpName);
    }

    //Verify Google Doc
    @Test(dependsOnMethods = {"testStdtLgn"})
    public void testStdtVrfyWrkGrp() throws Exception {
        a.navigateToWorkingGroups();
        a.vrfyWrkngGrp_GglDoc(UsrCrtn_AsgnRole_WrkngGrp.wrkngGrpName, TchrPosts_SclGrp_GglDoc.gglDocName);
    }

    //Post/Verify URL on Tchr's Scl Grp
    @Test(dependsOnMethods = {"testStdtJoinsTchrSclGrp"})
    public void testStdtPostURLOnTchrSclGrp() throws Exception {
        a.navigateToMySocialGroups();
        a.acessSclGrpWall(TchrPosts_SclGrp_GglDoc.tchrSclGrpName);
        stdtUrlPostOnTchrSclGrp = a.urlPost("urlSclGrpPost");
        System.out.println("stdtUrlPostOnTchrSclGrp: " + stdtUrlPostOnTchrSclGrp);
    }

    //Create Live Session in Teacher's Social Group
    @Test(dependsOnMethods = {"testStdtJoinsTchrSclGrp"})
    public void testStdtCrtLvSsn() throws Exception {
        a.navigateToMySocialGroups();
        a.acessSclGrpWall(TchrPosts_SclGrp_GglDoc.tchrSclGrpName);
        a.acessLvSsnWall();
        a.createLiveSsn(TchrPosts_SclGrp_GglDoc.tchrSclGrpName);
    }

    //Verify All Posts on Top/Recent News  
    @Test(dependsOnMethods = {"testStdtPostURLOnTchrSclGrp"})
    public void testStdtVrfyURLPsts_Top_RcntNews() throws Exception {
        a.navigateToMyHome();
        a.vrfyURLPstsAsTopNews_RcntNews(stdtUrlPostOnTchrSclGrp, TchrPosts_SclGrp_GglDoc.tchrUrlCrsPost, TchrPosts_SclGrp_GglDoc.tchrUrlPostOnStdtWall, TchrPosts_SclGrp_GglDoc.tchrUrlWallPost);
    }

    //Verify Activities & resource items appear in activity report
    @Test(dependsOnMethods = {"testStdtLgn"})
    public void testStdtVrfyActivities() throws Exception {
        a.navigateToMyCourse();
        a.selectGrpCourse(Crs_GrpCrsCreation.grpCrsName);
        a.navigateToActvtyRprt();
        a.verifyActivities(Crs_GrpCrsCreation.frmActvyName, Crs_GrpCrsCreation.quizActvtyName, Crs_GrpCrsCreation.allInOneAsgnmntAvtvtyName, Crs_GrpCrsCreation.pageActvtyName);
    }
}
