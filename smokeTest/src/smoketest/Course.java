package smoketest;

import java.text.DateFormat;
import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import runThrghTestNG.BaseClass;

public class Course extends BaseClass {

    Date now = new Date();
    private String crsName;
    private String grpCrsName;

    /**
     * Create & verify Course
     */
    public void createCourse() {
        //Navigate to Add/Edit Course Page
        setUpCrsPage();

        this.crsName = "SmkTstCrs " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
        String crsShrtName = "ShrtCrs " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(av.getTokenValue("btnAddNewCrs"))));
        ip.isElementPresentByXPATH(driver, av.getTokenValue("btnAddNewCrs"));

        //Navigate to Create Course Screen
        driver.findElement(By.xpath(av.getTokenValue("btnAddNewCrs"))).click();
        ip.isElementPresentByXPATH(driver, av.getTokenValue("slctCrsCtgryXPATH"));

        //Input Values
        new Select(driver.findElement(By.xpath(av.getTokenValue("slctCrsCtgryXPATH")))).selectByVisibleText("Active");
        driver.findElement(By.xpath(av.getTokenValue("fieldCrsFullNmXPATH"))).sendKeys(crsName);
        driver.findElement(By.xpath(av.getTokenValue("fieldCrsShrtNmXPATH"))).sendKeys(crsShrtName);
        new Select(driver.findElement(By.xpath(av.getTokenValue("slctCrsStrtDtDyXPATH")))).selectByValue("1");
        new Select(driver.findElement(By.xpath(av.getTokenValue("slctCrsStrtDtMnthXPATH")))).selectByValue("8");
        new Select(driver.findElement(By.xpath(av.getTokenValue("slctCrsStrtDtYrXPATH")))).selectByValue("2012");
        new Select(driver.findElement(By.xpath(av.getTokenValue("slctCrsEndDtDyXPATH")))).selectByValue("31");
        new Select(driver.findElement(By.xpath(av.getTokenValue("slctXrsEndDtMnthXPATH")))).selectByValue("7");
        new Select(driver.findElement(By.xpath(av.getTokenValue("slctCrsEndDtYrXPATH")))).selectByValue("2013");
        driver.findElement(By.xpath(av.getTokenValue("btnSbmt"))).click();

        //Verify Course is created or not
        ip.isElementPresentContainsTextByXPATH(driver, crsName);

    }

    /**
     * Create & verify GroupCourse
     *
     * @param courseName
     */
    public void createGrpCourse(String courseName) {
        //Navigate to Add/Edit Course Page
        setUpCrsPage();

        this.grpCrsName = "SmkTstGrpCrs " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
        ip.isElementPresentStartsWithTextByXPATH(driver, courseName);

        //Click on newly created Course
        driver.findElement(By.xpath("//*[starts-with(text(),'" + courseName + "')]")).click();
        ip.isElementPresentContainsTextByXPATH(driver, courseName);

        //Naviagte to Group Course page
        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(av.getTokenValue("lftPnlUsrsLnkXPATH"))));
        driver.findElement(By.xpath(av.getTokenValue("lftPnlUsrsLnkXPATH"))).click();
        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(av.getTokenValue("lftPnlGrpsLinkXPATH"))));
        driver.findElement(By.xpath(av.getTokenValue("lftPnlGrpsLinkXPATH"))).click();
        ip.isElementPresentByXPATH(driver, av.getTokenValue("btnCrtGrpCrs"));
        driver.findElement(By.xpath(av.getTokenValue("btnCrtGrpCrs"))).click();
        ip.isElementPresentByXPATH(driver, av.getTokenValue("fieldGrpCrsNameXPATH"));

        //Input Values
        driver.findElement(By.xpath(av.getTokenValue("fieldGrpCrsNameXPATH"))).sendKeys(grpCrsName);
        driver.findElement(By.xpath(av.getTokenValue("btnSbmt"))).click();

        //Verify GroupCourse is created or not
        ip.isElementPresentByXPATH(driver, av.getTokenValue("btnCrtGrpCrs"));

    }

    /**
     * Navigate to Add/Edit Course Page
     */
    public void setUpCrsPage() {
        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(av.getTokenValue("lftPnlCrsLinkXPATH"))));
        driver.findElement(By.xpath(av.getTokenValue("lftPnlCrsLinkXPATH"))).click();
        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(av.getTokenValue("lftPnlAddEditCrsXPATH"))));
        driver.findElement(By.xpath(av.getTokenValue("lftPnlAddEditCrsXPATH"))).click();
    }

    /**
     * @return CourseName
     */
    public String getCrsName() {
        return this.crsName;
    }

    /**
     * @return GroupCourseName
     */
    public String getGrpCrsName() {
        return this.grpCrsName;
    }
}
