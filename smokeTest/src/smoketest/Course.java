package smoketest;

import java.text.DateFormat;
import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class Course extends Page {

    Date now = new Date();
    IsPresent ip = new IsPresent();
    private String crsName;
    private String grpCrsName;

    public Course(WebDriver driver, AccountValues av) {
        super(driver, av);
    }

    public void createCourse() {

        //Navigate to course page
        setUpCrsPage();

        this.crsName = "SmkTstCrs " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
        String crsShrtName = "ShrtCrs " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);

        ip.isElementPresentByXPATH(driver, av.getTokenValue("btnAddNewCrs"));

        //Navigate to Create Course Screen
        driver.findElement(By.xpath(av.getTokenValue("btnAddNewCrs"))).click();

        ip.isElementPresentByXPATH(driver, av.getTokenValue("slctCrsCtgryXPATH"));

        //Create Course
        new Select(driver.findElement(By.xpath(av.getTokenValue("slctCrsCtgryXPATH")))).selectByVisibleText("Active");

        driver.findElement(By.xpath(av.getTokenValue("fieldCrsFullNmXPATH"))).sendKeys(crsName);

        //Date need to be in specific format as getinstance include special characters
        driver.findElement(By.xpath(av.getTokenValue("fieldCrsShrtNmXPATH"))).sendKeys(crsShrtName);
        new Select(driver.findElement(By.xpath(av.getTokenValue("slctCrsStrtDtDyXPATH")))).selectByValue("1");
        new Select(driver.findElement(By.xpath(av.getTokenValue("slctCrsStrtDtMnthXPATH")))).selectByValue("8");
        new Select(driver.findElement(By.xpath(av.getTokenValue("slctCrsStrtDtYrXPATH")))).selectByValue("2012");
        new Select(driver.findElement(By.xpath(av.getTokenValue("slctCrsEndDtDyXPATH")))).selectByValue("31");
        new Select(driver.findElement(By.xpath(av.getTokenValue("slctXrsEndDtMnthXPATH")))).selectByValue("7");
        new Select(driver.findElement(By.xpath(av.getTokenValue("slctCrsEndDtYrXPATH")))).selectByValue("2013");
        driver.findElement(By.xpath(av.getTokenValue("btnSbmt"))).click();

        //Verify Course is created or not
        ip.isElementPresentContainsTextByXPATH(driver, av.getTokenValue(crsName));

    }

    public void createGrpCourse(String courseName) {

        //Navigate to course page
        setUpCrsPage();

        this.grpCrsName = "SmkTstGrpCrs " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);

        ip.isElementPresentStartsWithTextByXPATH(driver, courseName);

        //Click on newly created Course
        driver.findElement(By.xpath("//*[starts-with(text(),'" + courseName + "')]")).click();

        ip.isElementPresentContainsTextByXPATH(driver, courseName);

        //Naviagte to Group Course page
        driver.findElement(By.xpath(av.getTokenValue("lftPnlUsrsLnkXPATH"))).click();
        driver.findElement(By.xpath(av.getTokenValue("lftPnlGrpsLinkXPATH"))).click();

        ip.isElementPresentByXPATH(driver, av.getTokenValue("btnCrtGrpCrs"));

        //Create group course
        driver.findElement(By.xpath(av.getTokenValue("btnCrtGrpCrs"))).click();
        ip.isElementPresentByXPATH(driver, av.getTokenValue("fieldGrpCrsNameXPATH"));
        driver.findElement(By.xpath(av.getTokenValue("fieldGrpCrsNameXPATH"))).sendKeys(grpCrsName);
        driver.findElement(By.xpath(av.getTokenValue("btnSbmt"))).click();

        //Verify group course is created or not
        ip.isElementPresentByXPATH(driver, av.getTokenValue("btnCrtGrpCrs"));

    }

    public void setUpCrsPage() {

        //Navigate to Add/Edit Course Page
        driver.findElement(By.xpath(av.getTokenValue("lftPnlCrsLinkXPATH"))).click();
        driver.findElement(By.xpath(av.getTokenValue("lftPnlAddEditCrsXPATH"))).click();
    }

    public String getCrsName() {
        return this.crsName;
    }

    public String getGrpCrsName() {
        return this.grpCrsName;
    }
}
