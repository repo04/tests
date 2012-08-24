
package smoketest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class SmokeTest 
{ 
    public static void main(String[] args) {
        
        Actions a = new Actions();

        // Test Login as student
//        try {
//            t.setUp( "guAccountProperty" );
//            t.login( "student" );
//            t.tearDown();
//        }
//        catch( Exception ex ) {
//            System.out.println( ex );
//        }
        
        // Test navigation to wall

            a.setUp( "guAccountProperty" );
            a.login( "pesAdmin" );
            a.navigateToWorkingGroups();
           a.addWorkingGroup();
            //a.navigateToMyWall("student");    
            //a.urlToWall();\
//            a.navigateToCourse();
//            String crsName = a.createCourse();
//        a.navigateToCourse();
//        String grpCrsName = a.createGrpCourse(crsName);
//        
//        a.navigateToCourse();
//            String sclGrpName;
//            sclGrpName = a.createSocialGroups();
//            a.logOut();
//            a.login( "teacher" );
//            a.navigateToSocialGroups();
//            a.findSocialGroup(sclGrpName);
            

            //t.tearDown();
    }
}
