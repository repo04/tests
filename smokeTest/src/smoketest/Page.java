package smoketest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Page {
    
    AccountValues av;
    WebDriver driver;
    
    public Page( WebDriver driver, AccountValues av ) {
        
        this.driver = driver;
        this.av = av;
    }
            
}
