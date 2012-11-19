/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package runThrghTestNG;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import smoketest.Actions;

/**
 *
 * 
 */
public class CntAdmn_CleanTstData {

    Actions a = new Actions();

    /**
     * The annotated method will be run before the first test method in the
     * current class is invoked, Content Admin logs in
     *
     * @throws Exception
     */
    @BeforeClass(groups = {"prerequisite"})
    public void testContentAdminLogin() throws Exception {
        a.login("contentAdmin");
    }

    /**
     * Delete all Activities
     * 
     * @param grpCrsName
     * @param frmActvyName
     * @param quizActvtyName
     * @param allInOneAsgnmntAvtvtyName
     * @param pageActvtyName
     * @throws Exception 
     */
    @Test(dataProvider = "GrpCrsActivities", dataProviderClass = Crs_GrpCrsCreation.class,
          groups = {"activites.deletion"})
    public void testActivities_Deletion(String grpCrsName, String frmActvyName, String quizActvtyName,
            String allInOneAsgnmntAvtvtyName, String pageActvtyName) throws Exception {
        a.navigateToMyCourse();
        a.selectGrpCourse(grpCrsName);
        a.deleteActivites(frmActvyName, quizActvtyName, allInOneAsgnmntAvtvtyName, pageActvtyName);
    }
    
    /**
     * Delete Group Course
     * 
     * @param grpCrsName
     * @throws Exception 
     */
    @Test(dataProvider = "Course", dataProviderClass = Crs_GrpCrsCreation.class,
          groups = {"groupcourse.deletion"})
    public void testGroupCourse_Deletion(String grpCrsName) throws Exception {
        a.navigateToMyCourse();
        a.selectGrpCourse(grpCrsName);
        a.deleteGrpCrs(grpCrsName);
    }

    /**
     * The annotated method will be run after all the test methods in the
     * current class have been run, User logsOut
     *
     * @throws Exception
     */
    @AfterClass(groups = {"prerequisite"})
    public void testContentAdminLogOut() throws Exception {
        a.logOut();
    }
}
