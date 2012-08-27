
package smoketest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class SmokeTest 
{ 
    IsPresent ip = new IsPresent();
 
    public static void main(String[] args) {
            
       Actions a = new Actions();
       String nameOfSclGrp; 
      /* Completed
       * Login - Student;
       * Submit Text on own Wall
       * Create Social Group
       * Login Teacher
       * Submit text on students wall
       * Login Student 
       * Verify Text
       */
        a.setUp( "guAccountProperty");
        a.login( "pesAdmin" );
        a.navigateToWorkingGroups();
        a.addWorkingGroup();
        
//        a.navigateToMyWall( "student" );
//        a.urlToWall();
//        a.navigateToSocialGroups();
//        nameOfSclGrp = a.createSocialGroups();
//        a.logOut();
//        
//        a.login( "teacher" );
//        a.navigateToContacts();
//        a.goToContactWall();
//        String temp = a.textToWall();
//        a.logOut();
//        
//        a.login("student");
//        a.navigateToMyWall("student");
//        a.verifyTopWallPost(temp);
//        a.tearDown();
        

    }
    
    /* Completed
     * Login - Student
     * Text on own Wall
     * Create Social Group
     */

}
