/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smoketest;

public class Tests
{
    
    public void testLogin( String university, String user ) throws Exception
    {
        Login l = new Login( university );
        
        l.attemptLogin(user);
        
        
        
        
//        WebDriver driver = new FirefoxDriver();
//        AccountValues v = new AccountValues( university );
//        HomePage h = new HomePage( driver, v.getTokenValue("homePageTitle") );
//        
//        try 
//        {
//            Login l = new Login( university, user );
//            
//            
//        } 
//        catch (Exception ex) 
//        {
//            Logger.getLogger(Tests.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
    
    public void testTextWallPost(String university) throws Exception  {
        
        wallPosts wp = new wallPosts();
        wp.textPost(university);   
        
    
    }
    
    public void testURLWallPost(String university) throws Exception  {
        wallPosts wp = new wallPosts();
        wp.urlPost(university);     
    }
}
