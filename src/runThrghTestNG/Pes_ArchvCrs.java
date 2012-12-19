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
public class Pes_ArchvCrs {
    
    Actions a = new Actions();
    
    /**
     * The annotated method will be run before the first test method in the
     * current class is invoked, Student logs in, PES Admin Logs in
     *
     * @throws Exception
     */
    @BeforeClass(groups = {"prerequisite"})
    public void testPESAdminLgn() throws Exception {
        a.login("pesAdmin");
    }
    
    /**
     * Archive Course
     * 
     * @throws Exception 
     */
    @Test(groups = {"fullsmoke", "course.archive"})
    public void testArchvCrs() throws Exception {
        a.archiveCrs(Crs_GrpCrsCreation.crsName);
    }
    
     /**
     * The annotated method will be run after all the test methods in the
     * current class have been run
     *
     * @throws Exception
     */
    @AfterClass(groups = {"prerequisite"})
    public void testPESAdminLogOut() throws Exception {
        a.logOut();
    }
    
}