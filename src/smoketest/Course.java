package smoketest;

import java.text.DateFormat;
import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
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
        String crsShrtName;
        if (test.equalsIgnoreCase("RegressionTests")) {
            this.crsName = "RgsnTstCrs " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
            crsShrtName = "RgsnShrtCrs " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
        } else if (test.equalsIgnoreCase("SmokeTests")) {
            this.crsName = "SmkTstCrs " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
            crsShrtName = "SmkShrtCrs " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
        } else {
            this.crsName = "DbgTstCrs " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
            crsShrtName = "DbgShrtCrs " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
        }
        ip.isElementClickableByXpath(driver, xpv.getTokenValue("btnAddNewCrs"), 60);
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("btnAddNewCrs"));

        //Navigate to Create Course Screen
        driver.findElement(By.xpath(xpv.getTokenValue("btnAddNewCrs"))).click();
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("slctCrsCtgryXPATH"));

        //Input Values
        new Select(driver.findElement(By.xpath(xpv.getTokenValue("slctCrsCtgryXPATH")))).selectByVisibleText("Active");
        driver.findElement(By.xpath(xpv.getTokenValue("fieldCrsFullNmXPATH"))).sendKeys(crsName);
        driver.findElement(By.xpath(xpv.getTokenValue("fieldCrsShrtNmXPATH"))).sendKeys(crsShrtName);
        new Select(driver.findElement(By.xpath(xpv.getTokenValue("slctCrsStrtDtDyXPATH")))).selectByValue("1");
        new Select(driver.findElement(By.xpath(xpv.getTokenValue("slctCrsStrtDtMnthXPATH")))).selectByValue("8");
        new Select(driver.findElement(By.xpath(xpv.getTokenValue("slctCrsStrtDtYrXPATH")))).selectByValue("2012");
        new Select(driver.findElement(By.xpath(xpv.getTokenValue("slctCrsEndDtDyXPATH")))).selectByValue("31");
        new Select(driver.findElement(By.xpath(xpv.getTokenValue("slctXrsEndDtMnthXPATH")))).selectByValue("7");
        new Select(driver.findElement(By.xpath(xpv.getTokenValue("slctCrsEndDtYrXPATH")))).selectByValue("2013");
        driver.findElement(By.xpath(xpv.getTokenValue("btnSbmt"))).click();

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

        if (test.equalsIgnoreCase("RegressionTests")) {
            this.grpCrsName = "RgsnTstGrpCrs " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
        } else if (test.equalsIgnoreCase("SmokeTests")) {
            this.grpCrsName = "SmkTstGrpCrs " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
        } else {
            this.grpCrsName = "DbgTstGrpCrs " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
        }
        ip.isElementPresentStartsWithTextByXPATH(driver, courseName);

        //Click on newly created Course
        driver.findElement(By.xpath("//*[starts-with(text(),'" + courseName + "')]")).click();
        ip.isElementPresentContainsTextByXPATH(driver, courseName);

        //Naviagte to Group Course page
        ip.isElementClickableByXpath(driver, xpv.getTokenValue("lftPnlUsrsLnkXPATH"), 60);
        driver.findElement(By.xpath(xpv.getTokenValue("lftPnlUsrsLnkXPATH"))).click();
        ip.isElementClickableByXpath(driver, xpv.getTokenValue("lftPnlGrpsLinkXPATH"), 60);
        driver.findElement(By.xpath(xpv.getTokenValue("lftPnlGrpsLinkXPATH"))).click();
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("btnCrtGrpCrs"));
        driver.findElement(By.xpath(xpv.getTokenValue("btnCrtGrpCrs"))).click();
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("fieldGrpCrsNameXPATH"));

        //Input Values
        driver.findElement(By.xpath(xpv.getTokenValue("fieldGrpCrsNameXPATH"))).sendKeys(grpCrsName);
        driver.findElement(By.xpath(xpv.getTokenValue("btnSbmt"))).click();

        //Verify GroupCourse is created or not
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("btnCrtGrpCrs"));

    }

    /**
     * Delete Group Course
     *
     * @param grpCrsName
     */
    public void deleteGroupCourse(String grpCrsName) {
        ip.isElementClickableByXpath(driver, xpv.getTokenValue("lftPnlUsrsLnkXPATH"), 60);
        driver.findElement(By.xpath(xpv.getTokenValue("lftPnlUsrsLnkXPATH"))).click();
        ip.isElementClickableByXpath(driver, xpv.getTokenValue("lftPnlGrpsLinkXPATH"), 60);
        driver.findElement(By.xpath(xpv.getTokenValue("lftPnlGrpsLinkXPATH"))).click();
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("btnDltGrpCrs"));
        Select select = new Select(driver.findElement(By.xpath(xpv.getTokenValue("slctGrpCrs"))));
        select.selectByVisibleText(grpCrsName + " (0)");
        driver.findElement(By.xpath(xpv.getTokenValue("btnDltGrpCrs"))).click();
        ip.isTextPresentByXPATH(driver, "//div[4]/div[4]/div/div/p", "Are you sure you want to delete group '" + grpCrsName + "'?");
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("btnCnfrmDltCrsXPATH"));
        driver.findElement(By.xpath(xpv.getTokenValue("btnCnfrmDltCrsXPATH"))).click();
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("slctGrpCrs"));
        Utility.navigateToSubMenu(driver, xpv.getTokenValue("linkToCourseXPATH"));
        new WebDriverWait(driver, 60).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(text(),'" + grpCrsName + "')]")));
    }

    /**
     * Archive Course
     *
     * @param crsName
     */
    public void archiveCourse(String crsName) {
        ip.isElementClickableByXpath(driver, xpv.getTokenValue("lftPnlArchvCrsLnkXPATH"), 60);
        driver.findElement(By.xpath(xpv.getTokenValue("lftPnlArchvCrsLnkXPATH"))).click();
        ip.isElementClickableByXpath(driver, xpv.getTokenValue("lftPnlArchvAddEditCrsLnkXPATH"), 60);
        driver.findElement(By.xpath(xpv.getTokenValue("lftPnlArchvAddEditCrsLnkXPATH"))).click();
        //new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(xpv.getTokenValue("lnkActive"))));
        //driver.findElement(By.xpath(xpv.getTokenValue("lnkActive"))).click();
        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.linkText("Active")));
        driver.findElement(By.linkText("Active")).click();


        int x = 2;
        loop:
        do {
            try {
                ip.isTextPresentByXPATH(driver, "//tr[" + x + "]/td/a", crsName, 5);
                driver.findElement(By.xpath("//tr[" + x + "]/td[3]/input")).click();
                break loop;
            } catch (TimeoutException e) {
                System.out.println("Text not present at x: " + x);
                x++;
            }
        } while (x < 22);
        Select slctMvCrs = new Select(driver.findElement(By.xpath(xpv.getTokenValue("slctMvCrs"))));
        slctMvCrs.selectByVisibleText("Archive");
        new WebDriverWait(driver, 60).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(text(),'" + crsName + "')]")));
        Select slctCrsCtgry = new Select(driver.findElement(By.xpath(xpv.getTokenValue("slctCrsCtgry"))));
        slctCrsCtgry.selectByVisibleText("Archive");

        boolean status;
        int i = 2;

        try {
            ip.isElementPresentContainsTextByXPATH(driver, crsName);
            status = false;
        } catch (TimeoutException e) {
            status = true;
        }

        fndElmt:
        while (status) {
            try {
                driver.findElement(By.xpath("//div[2]/a[" + i + "]"));
            } catch (NoSuchElementException e) {
                int y = i - 2;
                System.out.println("Total Elements i: " + y);
                driver.findElement(By.xpath("//div[2]/a[" + y + "]")).click();
                break fndElmt;
            }
            i++;
        }
        ip.isElementPresentContainsTextByXPATH(driver, crsName);
    }

    /**
     * Navigate to Add/Edit Course Page
     */
    public void setUpCrsPage() {
        ip.isElementClickableByXpath(driver, xpv.getTokenValue("lftPnlCrsLinkXPATH"), 60);
        driver.findElement(By.xpath(xpv.getTokenValue("lftPnlCrsLinkXPATH"))).click();
        ip.isElementClickableByXpath(driver, xpv.getTokenValue("lftPnlAddEditCrsXPATH"), 60);
        driver.findElement(By.xpath(xpv.getTokenValue("lftPnlAddEditCrsXPATH"))).click();
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
