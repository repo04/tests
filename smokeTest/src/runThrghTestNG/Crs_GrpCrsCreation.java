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
public class Crs_GrpCrsCreation extends BaseClass {

    static String crsName = null;
    static String grpCrsName = null;
    static String frmActvyName = null;
    static String quizActvtyName = null;
    static String allInOneAsgnmntAvtvtyName = null;
    static String pageActvtyName = null;

    @Test
    public void testCntntAdminLgn() throws Exception{
        a.login("contentAdmin");
    }

    @Test(dependsOnMethods = {"testCntntAdminLgn"})
    public void testCrsGrpCrs_Creation() throws Exception{

        a.navigateToMyCourse();
        crsName = a.createCourse();
        System.out.println("crsName: " + crsName);

        a.navigateToMyCourse();
        grpCrsName = a.createGrpCourse(crsName);
        System.out.println("grpCrsName: " + grpCrsName);
    }

    @Test(dependsOnMethods = {"testCrsGrpCrs_Creation"})
    public void testActivities_Creation() throws Exception{
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
    }
}
