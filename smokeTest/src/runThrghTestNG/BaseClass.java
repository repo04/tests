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
    
    @BeforeClass
    public void setUp() throws Exception {
        a.setUp("guAccountProperty");
    }
    
    @AfterClass
    public void tearDown() throws Exception {
        a.logOut();
        a.tearDown();
    }    
}