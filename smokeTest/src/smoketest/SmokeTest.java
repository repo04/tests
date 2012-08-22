package smoketest;

public class SmokeTest {

    public static void main(String[] args) throws Exception {

        Actions a = new Actions();
        String crsName;
        String grpCrsName;
        String sclGrpName;

        a.setUp("guAccountProperty");

        /* COMPLETED
         * Content Admin logs in - Create as follows
         * Course ,GrpCourse, Activities like Forum, Quiz, All In One Assignment & Page 
         * Logs out
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

        /* PENDING
         * Not included in Smoke TestRail but as a prerequisite, the same needs to be automated
         * a.login("pesAdmin");
         * a> Create two Users
         * b> Allocate users as Teacher & Student roles
         * c> Allocate Teacher & Student to newly created GrpCourse
         * a.logOut(); 
         */

        /* COMPLETED
         * Teacher logs in
         * Post Text , URL on wall
         * Create SocialGroup 
         * Find & Create Live Session within SclGroup
         */
        a.login("teacher");
        a.navigateToMyWall();
        //a.textToWall();
        //a.urlToWall();
        a.navigateToSocialGroups();
        sclGrpName = a.createSocialGroups();
        a.navigateToSocialGroups();
        a.findSocialGroup(sclGrpName);
        a.createLiveSsn(sclGrpName);
        a.logOut();

        //Student logs in
        //Find & join Teacher's social group
        /*a.login("student");
         a.navigateToSocialGroups();  
         a.findSocialGroup(sclGrpName);
         a.logOut();*/

        a.tearDown();
    }
}
