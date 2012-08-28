package smoketest;

import org.junit.Test;


public class SmokeTest {
    
    static Actions a = new Actions();
    static String crsName = null;
    static String grpCrsName = "SmkTstGrpCrs Aug 27, 2012 4:09 PM";
    static String tchrUsrName = "smktsttchr2708-1651";
    static String stdtUsrName = null;
    static String tchrSclGrpName = null;
    static String stdtSclGrpName = null;
    static String frmActvyName = "SmkTstForum Aug 27, 2012 7:03 PM";
    static String quizActvtyName = "SmkTstQuiz Aug 27, 2012 7:04 PM";
    static String allInOneAsgnmntAvtvtyName = "SmkTstAllInOneAsgnmnt Aug 27, 2012 7:04 PM";
    static String pageActvtyName = "SmkTstPage Aug 27, 2012 7:05 PM";
    static String tchrTxtWallPost = "txtwallpostbysmktsttchr2708-1651 Aug 27, 2012 6:34 PM";
    static String tchrUrlWallPost = "http://urlwallpostbysmktsttchr2708-165127Aug121834.com";
    static String tchrUrlPostOnStdtWall = null;
    static String tchrUrlCrsPost = "http://urlcrspostbysmktsttchr2708-165127Aug121834.com";
    static String stdtUrlPostOnTchrSclGrp = null;
    static String wrkngGrp = "SmkeTstWrkngGrp Aug 27, 2012 4:59 PM";
    
    /*public static void main(String[] args) throws Exception {

        a.setUp("guAccountProperty");

        //testCourse_ActivityCreation();
        //testUserCreation_AsgnRoleGrpCourse();
        testTchrPost_SclGrpLvSsnCreation();
        //testStdtPost_SclGrpLvSSnCreation();
        //testJoin_LeaveDeleteSclGrp();

        a.tearDown();
    }*/
    
    @Test
    public void testMain() throws Exception {

        a.setUp("guAccountProperty");

        //testCourse_ActivityCreation();
        //testUserCreation_AsgnRoleGrpCourse();
        testTchrPost_SclGrpLvSsnCreation();
        //testStdtPost_SclGrpLvSSnCreation();
        //testJoin_LeaveDeleteSclGrp();

        a.tearDown();
    }

    /* COMPLETED
     * Content Admin logs in 
     * Create - Course ,GrpCourse, Activities like Forum, Quiz, All In One Assignment & Page 
     * Verify Activity & resource items will appear in activity report
     * Logs out
     */
    private static void testCourse_ActivityCreation() {
        a.login("contentAdmin");

        a.navigateToMyCourse();
        crsName = a.createCourse();
        System.out.println("crsName: " + crsName);

        a.navigateToMyCourse();
        grpCrsName = a.createGrpCourse(crsName);
        System.out.println("grpCrsName: " + grpCrsName);

        a.navigateToMyCourse();
        a.selectGrpCourse(grpCrsName);
        frmActvyName = a.createForumActivity();
        System.out.println("frmActvyName: " + frmActvyName);

        a.navigateToMyCourse();
        a.selectGrpCourse(grpCrsName);
        quizActvtyName = a.createQuizActivity();
        System.out.println("quizActvtyName: " + quizActvtyName);

        a.navigateToMyCourse();
        a.selectGrpCourse(grpCrsName);
        allInOneAsgnmntAvtvtyName = a.createAllInOneAsgnmntActivity();
        System.out.println("allInOneAsgnmntAvtvtyName: " + allInOneAsgnmntAvtvtyName);

        a.navigateToMyCourse();
        a.selectGrpCourse(grpCrsName);
        pageActvtyName = a.createPageResource();
        System.out.println("pageActvtyName: " + pageActvtyName);

        //Verify that activity & resource items will appear in activity report (Content) Admin 
        a.navigateToMyCourse();
        a.selectGrpCourse(grpCrsName);
        a.navigateToActvtyRprt();
        a.verifyActivities(frmActvyName, quizActvtyName, allInOneAsgnmntAvtvtyName, pageActvtyName);

        a.logOut();
    }

    /* COMPLETED
     * Not included in Smoke TestRail but as a prerequisite, the same is automated
     * Create Two Users
     * Assign / Enroll users to newly created GrpCourse as Teacher / Student roles
     * Create & Add members to Working Group
     * Logs out 
     */
    private static void testUserCreation_AsgnRoleGrpCourse() {
        a.login("pesAdmin");

        a.navigateToMyContacts();
        tchrUsrName = a.createUser("teacher");
        System.out.println("tchrUsrName: " + tchrUsrName);

        a.navigateToMyCourse();
        a.selectGrpCourse(grpCrsName);
        a.enrollUsrToRole_GrpCrs(tchrUsrName, grpCrsName);

        a.navigateToMyContacts();
        stdtUsrName = a.createUser("student");
        System.out.println("stdtUsrName: " + stdtUsrName);

        a.navigateToMyCourse();
        a.selectGrpCourse(grpCrsName);
        a.enrollUsrToRole_GrpCrs(stdtUsrName, grpCrsName);

        a.navigateToWorkingGroups();
        wrkngGrp = a.createWorkingGroup();
        System.out.println("wrkngGrp: " + wrkngGrp);

        /* 
         * Dynamically adding variable number of users to WrkngGrp
         * Parameters: Users to be added to WrkngGrp
         */
        a.navigateToWorkingGroups();
        a.accessWrknGrp(wrkngGrp);
        a.addMbrsToWrkngGrp(tchrUsrName, stdtUsrName);

        a.logOut();
    }

    /* COMPLETED
     * Teacher LOGS in
     * Post/Verify Text ,URL on its wall
     * Post/Verify URL on Course Wall
     * Post/Verify URL on Stdt's wall
     * Create SocialGroup
     * Find SclGroup & Create LiveSession within SclGroup
     * Verify All Posts on TopNews / RecentNews Section
     * Verify Activity & resource items will appear in activity report
     */
    private static void testTchrPost_SclGrpLvSsnCreation() {
        a.login(tchrUsrName);

        a.navigateToMyWall();
        tchrTxtWallPost = a.textPost("txtWallPost");
        System.out.println("tchrTxtWallPost: " + tchrTxtWallPost);
        
        a.navigateToMyWall();
        tchrUrlWallPost = a.urlPost("urlWallPost");
        System.out.println("tchrUrlWallPost: " + tchrUrlWallPost);

        a.selectGrpCourse(grpCrsName);
        tchrUrlCrsPost = a.urlPost("urlCrsPost");
        System.out.println("tchrUrlCrsPost: " + tchrUrlCrsPost);

        //This will fail as User are not in each other MyContacts list
        /*a.navigateToMyContacts();
        a.findContact(stdtUsrName);
        a.navigateToContactsWall(stdtUsrName);
        tchrUrlPostOnStdtWall = a.urlPost("urlPostOnStdtWall");
        System.out.println("tchrUrlPostOnStdtWall: " + tchrUrlPostOnStdtWall);*/

        a.navigateToMySocialGroups();
        tchrSclGrpName = a.createSocialGroups();
        System.out.println("tchrSclGrpName: " + tchrSclGrpName);

        a.navigateToMySocialGroups();
        a.acessSclGrpWall(tchrSclGrpName);
        a.acessLvSsnWall();
        a.createLiveSsn(tchrSclGrpName);
        
        a.navigateToWorkingGroups();
        a.verifyWrkngGrp(wrkngGrp);        
        //Pending Create Google Doc 

        //Verify the activity report view for Teacher
        a.navigateToMyCourse();
        a.selectGrpCourse(grpCrsName);
        a.navigateToActvtyRprt();
        a.verifyActivities(frmActvyName, quizActvtyName, allInOneAsgnmntAvtvtyName, pageActvtyName);

        //Verify n number of URL Posts on TopNews/RecentNews Page
        //Limitation - Texts cannot be verified as position is not known on Page
        a.navigateToMyHome();
        a.VrfyURLPstsAsTopNews_RcntNews(tchrUrlWallPost, tchrUrlCrsPost, tchrUrlPostOnStdtWall);

        a.logOut();
    }

    /*
     * Student logs in
     * Find & Join Teacher's Scl Grp
     * Post/Verify URL on Tchr's Scl Grp
     * Create Live Session in Teacher's Social Group
     * Creates his/her Social Group
     * Verify All Posts on TopNews / RecentNews Section
     * Verify Activity & resource items will appear in activity report
     */
    private static void testStdtPost_SclGrpLvSSnCreation() {

        a.login(stdtUsrName);

        a.navigateToMySocialGroups();
        a.findSocialGroup(tchrSclGrpName);
        a.joinSocialGroup(tchrSclGrpName);

        //Pre-requisite User already have joined the social group
        //Post URL on Teacher's Social Group
        a.navigateToMySocialGroups();
        a.acessSclGrpWall(tchrSclGrpName);
        stdtUrlPostOnTchrSclGrp = a.urlPost("urlSclGrpPost");
        System.out.println("stdtUrlPostOnTchrSclGrp: " + stdtUrlPostOnTchrSclGrp);

        //Stdt creates live Ssn in Tchr's Scl Grp
        a.navigateToMySocialGroups();
        a.acessSclGrpWall(tchrSclGrpName);
        a.acessLvSsnWall();
        a.createLiveSsn(tchrSclGrpName);

        //Stdt creates Scl Grp
        a.navigateToMySocialGroups();
        stdtSclGrpName = a.createSocialGroups();
        System.out.println("stdtSclGrpName: " + stdtSclGrpName);
        
        a.navigateToWorkingGroups();
        a.verifyWrkngGrp(wrkngGrp);
        //Verify Google Doc is pending

        //Verify the activity report view for Student
        a.navigateToMyCourse();
        a.selectGrpCourse(grpCrsName);
        a.navigateToActvtyRprt();
        a.verifyActivities(frmActvyName, quizActvtyName, allInOneAsgnmntAvtvtyName, pageActvtyName);

        //Verify n number of URL Posts on TopNews/RecentNews Page
        a.navigateToMyHome();
        a.VrfyURLPstsAsTopNews_RcntNews(tchrUrlWallPost, tchrUrlCrsPost, tchrUrlPostOnStdtWall, stdtUrlPostOnTchrSclGrp);

        a.logOut();
    }

    /*
     * Teacher Login
     * Find, Join, Leave Stdt Social Group
     * Verify All Posts on TopNews / RecentNews Section
     */
    private static void testJoin_LeaveDeleteSclGrp() {

        a.login(tchrUsrName);
        a.navigateToMySocialGroups();
        a.findSocialGroup(stdtSclGrpName);
        a.joinSocialGroup(stdtSclGrpName);

        //Leave Stdt Social Group 
        //Limitation - Student must have only one group to leave on his/her Social Group page
        //Otherwise script will click on first 'Leave Group' Link & will fail thereafter
        a.navigateToMySocialGroups();
        a.leaveSocialGroup(stdtSclGrpName);

        a.navigateToMySocialGroups();
        a.deleteSocialGroup(tchrSclGrpName);

        //Verify n number of URL Posts on TopNews/RecentNews Page
        a.navigateToMyHome();
        a.VrfyURLPstsAsTopNews_RcntNews(tchrUrlWallPost, tchrUrlCrsPost, tchrUrlPostOnStdtWall, stdtUrlPostOnTchrSclGrp);
    }
}
