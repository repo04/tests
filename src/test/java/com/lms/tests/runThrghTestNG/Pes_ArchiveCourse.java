/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lms.tests.runThrghTestNG;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.lms.tests.smoketest.Actions;

/**
 *
 * 
 */
public class Pes_ArchiveCourse extends BaseClass {
    
    Actions a;
    
    /**
     * The annotated method will be run before the first test method in the
     * current class is invoked, Student logs in, PES Admin Logs in
     *
     * @throws Exception
     */
    @BeforeClass(groups = {"prerequisite"})
    public void testPESAdminLogIn() throws Exception {
        a = new Actions(driver);
        a.login("pesAdmin");
    }
    
    /**
     * Archive Course
     * 
     * @throws Exception 
     */
    @Test(groups = {"regressionSmoke", "fullSmoke", "course.archive"})
    public void testPESAdminArchiveCourse() throws Exception {
        a.archiveCourse(ContentAdmin_Course_GroupCourseCreation.courseName);
    }
    
     /**
     * The annotated method will be run after all the test methods in the
     * current class have been run
     *
     * @throws Exception
     */
    @AfterClass(groups = {"prerequisite"})
    public void testPESAdminLogOut() throws Exception {
        a = new Actions(driver);
        a.logOut();
    }
    
}
