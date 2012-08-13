
package simpleTest;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class TestingSeleniumJars 
{
    public static void main(String[] args) 
    {
        Login t = new Login();
        
        // Test Login using Property file
        t.Login( "guAccountProperty", "student" );
    }
        /*
        WebDriver driver = new FirefoxDriver();
        
        // Time-out in 10 seconds
        WebDriverWait wait = new WebDriverWait(driver, 5);
          
        driver.get("https://unc-mba-lms-www-20-stg.2tor.com/local/login/index.php");
        
        WebElement userName = driver.findElement(By.xpath("//*[@id='username']"));
        WebElement passWord = driver.findElement(By.xpath("//*[@id='password']"));
      
        userName.sendKeys("2dmstudent1");       
        passWord.sendKeys("Moodle1!");
        
        // Login
        driver.findElement(By.xpath("//*[@id='loginpage_submit_btn']")).click();
                    
        driver.findElement(By.xpath("//*[@id='wall_publisher_textarea_noedit']")).click();
        
        driver.findElement(By.xpath("//*[@id='ext-gen260']/iframe")).click();
        
        
        
                try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(TestingSeleniumJars.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //WebElement textArea = driver.findElement(By.xpath("//*[@id='ext-gen260']/iframe"));
        //WebElement textArea = driver.findElement(By.xpath("//*[@id='ext-gen260']"));
        driver.switchTo().frame("ext-gen298");
        WebElement textArea = driver.switchTo().activeElement();
        textArea.sendKeys("asdlkfjaslkdf    ksajfkljsfjslkjflksajflksajdflkasjflksa  dlfkjsalkfjsalkjlkdfjlsa");
        //driver.findElement(By.xpath("//*[@id='wall_publisher_textarea_noedit']")).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(TestingSeleniumJars.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //textArea.sendKeys("aaaaa sjasfjdlksjafls fsajfiosj fiaj]aogosadijgsaigj aigjsoijgsogjsiojagksja gklasjsjskljfkls");
        driver.quit();
        
        
    }*/
}
