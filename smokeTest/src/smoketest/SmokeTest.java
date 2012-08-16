/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smoketest;

/**
 *
 * @author bkang
 */
public class SmokeTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception 
    {
        Tests t = new Tests();
        t.testLogin("guAccountProperty", "student");
        t.testLogin("guAccountProperty", "teacher");
    }
}
