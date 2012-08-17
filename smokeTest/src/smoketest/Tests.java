/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smoketest;

public class Tests
{
    /*
     * Test verifies successful login
     * University is the name of the properties file
     * User is the role; student, teacher, pesadmin
     */
    public void testLogin( String university, String user )
    {
        Login login = new Login( university );
        
        // Executes login attempt based on user type
        login.attemptLogin( user );
        
        Utility.myVerifyCurrentPage( login.driver, login.av.getTokenValue("homePageTitle") );

        login.driver.quit();
    }
    
    public void testTextWallPost(String university) throws Exception  {
        
        wallPosts wp = new wallPosts();
       // wp.textPost(university);   
        
    
    }
    
    public void testURLWallPost(String university) throws Exception  {
        wallPosts wp = new wallPosts();
        //wp.urlPost(university);     
    }
}
