
package simpleTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;


public class Login 
{
    protected WebDriver driver = new FirefoxDriver();

    protected WebElement userName = driver.findElement( By.xpath("//*[@id='username']") );
    protected WebElement passWord = driver.findElement( By.xpath("//*[@id='password']") );
    
    // Verifies successful login based on university and account type
    public void Login( String university, String user )
    {
        //WebDriver driver = new FirefoxDriver();
        this.driver = driver;
        // Passes in the name of the university account properties file
        // This allows us to now grab the token values
        AccountValues collegeProp = new AccountValues( university );
        
        // Launches browser to login page found in property file
        driver.get( collegeProp.getTokenValue("programURL") );
        
        /* LoginPage verifies the correct page has loaded based on the pageTitle
         * token value from the properties file
         */
        LoginPage uncMBA = new LoginPage( driver, collegeProp.getTokenValue("pageTitle") );
        
        //WebElement userName = driver.findElement( By.xpath("//*[@id='username']") );
        //WebElement passWord = driver.findElement( By.xpath("//*[@id='password']") );
        
        userName.sendKeys( collegeProp.getTokenValue("stdntUserName") );

        //enterUserCredentials( user );
        
        driver.quit();
    }
    
    // This function enters 
    public void enterUserCredentials( String userType )
    {
       userName.sendKeys(guProp.getTokenValue("stdntUserName") );
    }

}
