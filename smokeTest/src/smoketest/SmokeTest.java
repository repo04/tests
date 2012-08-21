package smoketest;

public class SmokeTest {
	public static void main(String[] args) throws Exception {

		Tests t = new Tests();

		// Test Login as student
		// try {
		// t.setUp( "guAccountProperty" );
		// t.login( "student" );
		// t.tearDown();
		// }
		// catch( Exception ex ) {
		// System.out.println( ex );
		// }

		// Test navigation to wall

		t.setUp("guAccountProperty");
		t.login("contentAdmin");
		t.navigateToMyWall( "contentAdmin" );
		// t.textToWall();
		// t.urlToWall();
		t.createSclGrp("sclGrpName");
		t.createLiveSsn("sclGrpName", "liveSessionName");
		t.createCourse("courseName");
		t.createGrpToCourse("courseName", "grpCrsName");
		t.tearDown();
	}
}
