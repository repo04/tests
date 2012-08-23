package smoketest;

public class SmokeTest {

    public static void main(String[] args) throws Exception {

        Actions a = new Actions();
        String crsName;
        String grpCrsName;
        String stdtUsrName;
        String tchrUsrName;
        String sclGrpName;

        a.setUp("guAccountProperty");

        /* COMPLETED
         * a> Content Admin logs in - Create as follows
         * b> Course ,GrpCourse, Activities like Forum, Quiz, All In One Assignment & Page 
         * c> Logs out
         */
        a.login("contentAdmin");
        a.navigateToCourse();
        crsName = a.createCourse();
        a.navigateToCourse();
        grpCrsName = a.createGrpCourse(crsName);
        
        a.navigateToCourse();
        a.selectGrpCourse(grpCrsName);
        a.createForumActivity();
        
        a.navigateToCourse();
        a.selectGrpCourse(grpCrsName);
        a.createQuizActivity();
        
        a.navigateToCourse();
        a.selectGrpCourse(grpCrsName);
        a.createAllInOneAsgnmntActivity();
        
        a.navigateToCourse();
        a.selectGrpCourse(grpCrsName);
        a.createPageResource();
        
        a.logOut();

        /* COMPLETED
         * Not included in Smoke TestRail but as a prerequisite, the same is automated
         * a> Create Two Users
         * b> Assign / Enroll users to newly created GrpCourse as Teacher / Student roles
         * c> Logs out 
         */
        a.login("pesAdmin");
        
        a.navigateToContact();
        tchrUsrName = a.createUser("teacher");
        
        a.navigateToCourse();
        a.selectGrpCourse(grpCrsName);
        a.enrollUsrToRole_GrpCrs(tchrUsrName,grpCrsName);
        
        a.navigateToContact();
        stdtUsrName = a.createUser("student");
                        
        a.navigateToCourse();
        a.selectGrpCourse(grpCrsName);
        a.enrollUsrToRole_GrpCrs(stdtUsrName,grpCrsName);
        
        a.logOut();

        /* COMPLETED
         * Teacher logs in
         * Post Text , URL on wall
         * Create SocialGroup 
         * Find & Create Live Session within SclGroup
         */
        /*a.login("teacher");
        a.navigateToMyWall();
        a.textToWall();
        a.urlToWall();
        a.navigateToSocialGroups();
        sclGrpName = a.createSocialGroups();
        a.navigateToSocialGroups();
        a.findSocialGroup(sclGrpName);
        a.createLiveSsn(sclGrpName);
        a.logOut();*/

        //Student logs in
        //Find & join Teacher's social group
        /*a.login("student");
         a.navigateToSocialGroups();  
         a.findSocialGroup(sclGrpName);
         a.logOut();*/

        a.tearDown();
    }
}
