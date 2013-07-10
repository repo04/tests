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
public class Pes_ArchiveCourse {
    
    Actions a = new Actions();
    
    /**
     * The annotated method will be run before the first test method in the
     * current class is invoked, Student logs in, Pes Admin Logs in
     *
     * @throws Exception
     */
    @BeforeClass(groups = {"prerequisite"})
    public void testPesAdminLogIn() throws Exception {
        a.login("pesAdmin");
    }
    
    /**
     * Archive Course
     * 
     * @throws Exception 
     */
    @Test(dataProvider = "CourseName", dataProviderClass=ContentAdmin_Course_GroupCourseCreation.class,
          groups = {"regressionSmoke", "fullSmoke", "course.archive"})
    public void testPesAdminArchiveCourse(String courseName) throws Exception {
        a.archiveCourse(courseName);
    }
    
     /**
     * The annotated method will be run after all the test methods in the
     * current class have been run
     *
     * @throws Exception
     */
    @AfterClass(groups = {"prerequisite"})
    public void testPesAdminLogOut() throws Exception {
        a.logOut();
    }
    
}
