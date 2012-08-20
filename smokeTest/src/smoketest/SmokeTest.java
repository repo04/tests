
package smoketest;


public class SmokeTest 
{ 
    public static void main(String[] args) throws Exception {
        
        Tests t = new Tests();

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

            t.setUp("guAccountProperty");
            
            //2nd parameter not required anymore
            //t.navigateToMyWall("student", "stdntMyWallURL");
            t.navigateToMyWall("student");
            //t.testTextWallPost();
            //t.testURLWallPost();
            t.testCreateSclGrp("sclGrpName");
            t.testCreateLiveSsn("sclGrpName", "liveSessionName");
            t.tearDown();
            
    }
}
