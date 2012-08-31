/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package runThrghTestNG;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import smoketest.Actions;

/**
 *
 * @author somesh.bansal
 */
public class BaseClass {

    Actions a = new Actions();
    
    //The annotated method will be run before the first test method in the class is invoked
    @BeforeClass
    public void setUp() throws Exception {
        a.setUp("guAccountProperty");
    }
    
    //The annotated method will be run after all the test methods in the class have been run. 
    @AfterClass
    public void tearDown() throws Exception {
        a.logOut();
        a.tearDown();
    }    
}
