package smoketest;

import org.openqa.selenium.WebDriver;

// Page parent class
public class Page {
    
    AccountValues av;
    WebDriver driver;
    
    // Page constructor
    public Page( WebDriver driver, AccountValues av ) {
        
        this.driver = driver;
        this.av = av;
    }           
}
