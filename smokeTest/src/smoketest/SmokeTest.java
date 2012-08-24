package smoketest;

public class SmokeTest {

    static Actions a = new Actions();
    static String crsName = null;
    static String grpCrsName = null;
    static String stdtUsrName = null;
    static String tchrUsrName = null;
    static String tchrSclGrpName = null;
    static String stdtSclGrpName = null;    
    static String frmActvyName = null;
    static String quizActvtyName = null;
    static String allInOneAsgnmntAvtvtyName = null;
    static String pageActvtyName = null;
    
    
    public static void main(String[] args) throws Exception {

        a.setUp("guAccountProperty");
        
        testCourse_ActivityCreation();
        testUserCreation_AsgnRoleGrpCourse();
        testTchrPost_SclGrpLvSsnCreation();
        testStdtPost_SclGrpLvSSnCreation();
        testJoin_LeftSclGrp();       

        a.tearDown();
    }

    /* COMPLETED
     * a> Content Admin logs in 
     * b> Create - Course ,GrpCourse, Activities like Forum, Quiz, All In One Assignment & Page 
     * c> Logs out
     */
    private static void testCourse_ActivityCreation() {
        a.login("contentAdmin");

        a.navigateToCourse();
        crsName = a.createCourse();

        a.navigateToCourse();
        grpCrsName = a.createGrpCourse(crsName);

        a.navigateToCourse();
        a.selectGrpCourse(grpCrsName);
        frmActvyName = a.createForumActivity();

        a.navigateToCourse();
        a.selectGrpCourse(grpCrsName);
        quizActvtyName = a.createQuizActivity();

        a.navigateToCourse();
        a.selectGrpCourse(grpCrsName);
        allInOneAsgnmntAvtvtyName = a.createAllInOneAsgnmntActivity();

        a.navigateToCourse();
        a.selectGrpCourse(grpCrsName);
        pageActvtyName = a.createPageResource();

        a.logOut();
    }

    /* COMPLETED
     * Not included in Smoke TestRail but as a prerequisite, the same is automated
     * a> Create Two Users
     * b> Assign / Enroll users to newly created GrpCourse as Teacher / Student roles
     * c> Logs out 
     */
    private static void testUserCreation_AsgnRoleGrpCourse() {
        a.login("pesAdmin");

        a.navigateToMyContacts();
        tchrUsrName = a.createUser("teacher");

        a.navigateToCourse();
        a.selectGrpCourse(grpCrsName);
        a.enrollUsrToRole_GrpCrs(tchrUsrName, grpCrsName);

        a.navigateToMyContacts();
        stdtUsrName = a.createUser("student");

        a.navigateToCourse();
        a.selectGrpCourse(grpCrsName);
        a.enrollUsrToRole_GrpCrs(stdtUsrName, grpCrsName);

        a.logOut();
    }

    /* COMPLETED
     * Teacher LOGS in
     * Post Text , URL on its wall
     * Post Text , URL on STDT's wall
     * Create SocialGroup
     * Find SclGroup & Create Live Session within SclGroup
     */
    private static void testTchrPost_SclGrpLvSsnCreation() {
        a.login("teacher");
        a.navigateToMyWall();
        a.textPost("txtWallPost");
        a.urlPost("urlWallPost");

        a.navigateToMyContacts();
        a.findContact(stdtUsrName);
        a.navigateToContactsWall(stdtUsrName);
        a.textPost("txtPostOnStdtWall");
        a.urlPost("urlPostOnStdtWall");

        a.navigateToSocialGroups();
        tchrSclGrpName = a.createSocialGroups();

        a.navigateToSocialGroups();
        a.findSocialGroup(tchrSclGrpName);
        a.createLiveSsn(tchrSclGrpName);

        a.logOut();
    }

    /*
     * Student logs in
     * Find & Join Teacher's Scl Grp
     * Create his/her Scl Grp
     * URL on Tchr's Scl Grp
     * Creates his/her Social Grp & Live Ssn
     */
    private static void testStdtPost_SclGrpLvSSnCreation() {
        
        a.login("student");
        
        a.navigateToSocialGroups();
        a.findSocialGroup(tchrSclGrpName);
        a.joinSocialGroup(tchrSclGrpName);

        //Pre-requisite User already have joined the social group
        //Post URL on Teacher's Social Group
        a.navigateToSocialGroups();
        a.acessSclGrpWall(tchrSclGrpName);
        a.urlPost("urlSclGrpPost");

                
        a.navigateToSocialGroups();
        stdtSclGrpName = a.createSocialGroups();
        
        a.navigateToSocialGroups();
        a.findSocialGroup(stdtSclGrpName);
        a.createLiveSsn(stdtSclGrpName);
        
        //Verify POSTS is pending
        
        a.logOut();
    }

    /*
     * Teacher Login
     * Find & Join Stdt Social Group        
     */
    private static void testJoin_LeftSclGrp() {
        
        a.login("teacher");
        a.navigateToSocialGroups();
        a.findSocialGroup(stdtSclGrpName);
        a.joinSocialGroup(stdtSclGrpName);
        
        //Leave Stdt Social Group 
        //Limitation - Student must have only one group to leave on his/her Social Group page
        //Otherwise script will click on first 'Leave Group' found & will fail thereafter
        a.navigateToSocialGroups();
        a.leaveSocialGroup(stdtSclGrpName);
        
        //Verify POSTS is pending
    }
}
