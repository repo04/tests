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
    private String courseName;
    private String groupCourseName;
    private String backupFileName;

    /**
     * Create & verify Course
     */
    public void createCourse() {
        String crsShrtName;
        if (test.equalsIgnoreCase("RegressionTests")) {
            this.courseName = "RgsnTstCrs " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
            crsShrtName = "RgsnShrtCrs " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
        } else if (test.equalsIgnoreCase("SmokeTests")) {
            this.courseName = "SmkTstCrs " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
            crsShrtName = "SmkShrtCrs " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
        } else {
            this.courseName = "DbgTstCrs " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
            crsShrtName = "DbgShrtCrs " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
        }
        ip.isElementClickableByXpath(driver, xpv.getTokenValue("btnAddNewCrs"), 60);
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("btnAddNewCrs"));

        //Navigate to Create Course Screen
        driver.findElement(By.xpath(xpv.getTokenValue("btnAddNewCrs"))).click();
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("slctCrsCtgryXPATH"));

        //Input Values
        new Select(driver.findElement(By.xpath(xpv.getTokenValue("slctCrsCtgryXPATH")))).selectByVisibleText("Active");
        driver.findElement(By.xpath(xpv.getTokenValue("fieldCrsFullNmXPATH"))).sendKeys(courseName);
        driver.findElement(By.xpath(xpv.getTokenValue("fieldCrsShrtNmXPATH"))).sendKeys(crsShrtName);
        new Select(driver.findElement(By.xpath(xpv.getTokenValue("slctCrsStrtDtDyXPATH")))).selectByValue("1");
        new Select(driver.findElement(By.xpath(xpv.getTokenValue("slctCrsStrtDtMnthXPATH")))).selectByValue("8");
        new Select(driver.findElement(By.xpath(xpv.getTokenValue("slctCrsStrtDtYrXPATH")))).selectByValue("2012");
        new Select(driver.findElement(By.xpath(xpv.getTokenValue("slctCrsEndDtDyXPATH")))).selectByValue("31");
        new Select(driver.findElement(By.xpath(xpv.getTokenValue("slctXrsEndDtMnthXPATH")))).selectByValue("7");
        new Select(driver.findElement(By.xpath(xpv.getTokenValue("slctCrsEndDtYrXPATH")))).selectByValue("2013");
        driver.findElement(By.xpath(xpv.getTokenValue("btnSbmt"))).click();

        //Verify Course is created or not
        ip.isElementPresentContainsTextByXPATH(driver, courseName);

    }

    /**
     * Create & verify GroupCourse
     *
     * @param courseName
     */
    public void createGroupCourse(String courseName) {

        if (test.equalsIgnoreCase("RegressionTests")) {
            this.groupCourseName = "RgsnTstGrpCrs " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
        } else if (test.equalsIgnoreCase("SmokeTests")) {
            this.groupCourseName = "SmkTstGrpCrs " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
        } else {
            this.groupCourseName = "DbgTstGrpCrs " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(now);
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
        driver.findElement(By.xpath(xpv.getTokenValue("fieldGrpCrsNameXPATH"))).sendKeys(groupCourseName);
        driver.findElement(By.xpath(xpv.getTokenValue("btnSbmt"))).click();

        //Verify GroupCourse is created or not
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("btnCrtGrpCrs"));

    }

    /**
     * Delete Group Course
     *
     * @param groupCourseName
     */
    public void deleteGroupCourse(String groupCourseName) {
        ip.isElementClickableByXpath(driver, xpv.getTokenValue("lftPnlUsrsLnkXPATH"), 60);
        driver.findElement(By.xpath(xpv.getTokenValue("lftPnlUsrsLnkXPATH"))).click();
        ip.isElementClickableByXpath(driver, xpv.getTokenValue("lftPnlGrpsLinkXPATH"), 60);
        driver.findElement(By.xpath(xpv.getTokenValue("lftPnlGrpsLinkXPATH"))).click();
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("btnDltGrpCrs"));
        Select select = new Select(driver.findElement(By.xpath(xpv.getTokenValue("slctGrpCrs"))));
        select.selectByVisibleText(groupCourseName + " (0)");
        driver.findElement(By.xpath(xpv.getTokenValue("btnDltGrpCrs"))).click();
        ip.isTextPresentByXPATH(driver, "//div[4]/div[4]/div/div/p", "Are you sure you want to delete group '" + groupCourseName + "'?");
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("btnCnfrmDltCrsXPATH"));
        driver.findElement(By.xpath(xpv.getTokenValue("btnCnfrmDltCrsXPATH"))).click();
        ip.isElementPresentByXPATH(driver, xpv.getTokenValue("slctGrpCrs"));
        Utility.clickByJavaScript(driver, xpv.getTokenValue("linkToCourseXPATH"));
        new WebDriverWait(driver, 60).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(text(),'" + groupCourseName + "')]")));
    }

    /**
     * Archive Course
     *
     * @param courseName
     */
    public void archiveCourse(String courseName) {
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
                ip.isTextPresentByXPATH(driver, "//tr[" + x + "]/td/a", courseName, 5);
                driver.findElement(By.xpath("//tr[" + x + "]/td[3]/input")).click();
                break loop;
            } catch (TimeoutException e) {
                System.out.println("Text not present at x: " + x);
                x++;
            }
        } while (x < 22);
        Select slctMvCrs = new Select(driver.findElement(By.xpath(xpv.getTokenValue("slctMvCrs"))));
        slctMvCrs.selectByVisibleText("Archive");
        new WebDriverWait(driver, 60).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(text(),'" + courseName + "')]")));
        Select slctCrsCtgry = new Select(driver.findElement(By.xpath(xpv.getTokenValue("slctCrsCtgry"))));
        slctCrsCtgry.selectByVisibleText("Archive");

        boolean status;
        int i = 2;

        try {
            ip.isElementPresentContainsTextByXPATH(driver, courseName);
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
        ip.isElementPresentContainsTextByXPATH(driver, courseName);
    }

    /**
     * Takes backup of course
     *
     * @param activities
     */
    public void backupCourse(String... activities) {
        ip.isElementClickableByXpath(driver, "//li[6]/p/a", 60);
        driver.findElement(By.linkText("Backup")).click();
        ip.isTextPresentByXPATH(driver, "//div[4]/div[4]/div/div", "Your settings have been altered due to unmet dependencies");
        new WebDriverWait(driver, 5).until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(By.xpath("//div[2]/span"))));
        driver.findElement(By.xpath("//fieldset/input")).click();
        ip.isTextPresentByXPATH(driver, "//legend", "Backup settings");
        ip.isTextPresentByXPATH(driver, "//fieldset[2]/legend", "Include:");

        validateActivities(activities, "//div[", "]/div/div/div/div/label", 3);

        driver.findElement(By.xpath("//fieldset/input")).click();
        new WebDriverWait(driver, 5).until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(By.xpath("//span[5]"))));
        backupFileName = driver.findElement(By.id("id_setting_root_filename")).getAttribute("value");
        driver.findElement(By.xpath("//fieldset/input")).click();
        ip.isTextPresentByXPATH(driver, "//div[4]/div[4]/div/div[2]/div", "The backup file was successfully created.");
        driver.findElement(By.xpath("//div/input")).click();
        ip.isTextPresentByXPATH(driver, "//div[3]/div/h2", "Import a backup file");
        driver.findElement(By.cssSelector("#region-main > div.region-content")).click();
        ip.isTextPresentByXPATH(driver, "//div[3]/table/tbody/tr/td", backupFileName);
    }

    /**
     * Restore course as new archive course
     *
     * @param activities
     */
    public void restoreAsNewArchiveCourse(String... activities) {
        driver.findElement(By.linkText("Restore")).click();
        int j = 1;
        for (String activity : activities) {
            if (j > 6) {
                ip.isTextPresentByXPATH(driver, "//div[3]/table/tbody/tr/td", activity);
            }
            j++;
        }
        driver.findElement(By.xpath("//tr[1]/td[5]/a")).click();
        ip.isTextPresentByXPATH(driver, "//div[2]/div/h2", "Backup details");
        ip.isTextPresentByXPATH(driver, "//div[2]/h2", "Backup settings");

        validateActivities(activities, "//tr[", "]/td[2]", 1);

        driver.findElement(By.xpath("//div/input")).click();
        ip.isTextPresentByXPATH(driver, "//form/div/h2", "Restore as a new course");
        driver.findElement(By.xpath("//tr[3]/td/input")).click();
        driver.findElement(By.xpath("//div[3]/div/input")).click();
        ip.isTextPresentByXPATH(driver, "//legend", "Restore settings");

        if (!driver.findElement(By.xpath("//span/input")).isSelected()) {
            driver.findElement(By.xpath("//span/input")).click();
        }
        driver.findElement(By.xpath("//fieldset/input")).click();
        ip.isTextPresentByXPATH(driver, "//legend", "Course settings");

        String fetchedCourseName = driver.findElement(By.id("id_setting_course_course_fullname")).getAttribute("value");
        String shortCourseName = driver.findElement(By.id("id_setting_course_course_shortname")).getAttribute("value");
        int whiteSpaceCourseName = fetchedCourseName.indexOf(" ");
        int whiteSpaceShortCourseName = shortCourseName.indexOf(" ");
        String restoredCourseName = fetchedCourseName.substring(0, whiteSpaceCourseName)
                + " Restore " + fetchedCourseName.substring(whiteSpaceCourseName + 1);
        String restoredShortCourseName = shortCourseName.substring(0, whiteSpaceShortCourseName)
                + " Restore " + shortCourseName.substring(whiteSpaceShortCourseName + 1);
        driver.findElement(By.id("id_setting_course_course_fullname")).clear();
        driver.findElement(By.id("id_setting_course_course_shortname")).clear();
        driver.findElement(By.id("id_setting_course_course_fullname")).sendKeys(restoredCourseName);
        driver.findElement(By.id("id_setting_course_course_shortname")).sendKeys(restoredShortCourseName);
        System.out.print(restoredCourseName + "\n" + restoredShortCourseName + "\n");
        
        //Verify Section checkboxes
        int a = 4;
        do {
            if (!driver.findElement(By.xpath("//div[2]/div[" + a + "]/div/div/div[2]/span/input")).isSelected()) {
                driver.findElement(By.xpath("//div[2]/div[" + a + "]/div/div/div[2]/span/input")).click();
            }
            a++;
        } while (a < 15);

        //Verify Activities checkboxes
        int b = 3;
        int c;
        if (!"gu-msn".equals(program)) {
            c = 8;
        } else {
            c = 9;
        }

        do {
            if (!driver.findElement(By.xpath("//div[5]/div[" + b + "]/div/div/div[2]/span/input")).isSelected()) {
                driver.findElement(By.xpath("//div[5]/div[" + b + "]/div/div/div[2]/span/input")).click();
            }
            b++;
        } while (b < c);

        driver.findElement(By.xpath("//fieldset/input")).click();
        ip.isTextPresentByXPATH(driver, "//legend", "Backup settings");
        ip.isTextPresentByXPATH(driver, "//fieldset[2]/legend", "Course settings");

        driver.findElement(By.xpath("//fieldset/input")).click();
        ip.isTextPresentByXPATH(driver, "//form/div/div", "The Student role in the backup file cannot "
                + "be mapped to any of the roles that you are allowed to assign.");
        driver.findElement(By.xpath("//div[3]/input")).click();
        ip.isTextPresentByXPATH(driver, "//div[4]/div[4]/div/div[2]/div", "The course was restored successfully, "
                + "clicking the continue button below will take you to view the course you restored.");
        driver.findElement(By.xpath("//div/input")).click();
        ip.isTextPresentByXPATH(driver, "//h1/a", restoredCourseName);

        int d = 1;
        for (String actvity : activities) {
            if (d < 7) {
                if (!"gu-msn".equals(program)) {
                    if (d > 1) {
                        ip.isElementPresentContainsTextByXPATH(driver, actvity);
                    }
                } else {
                    ip.isElementPresentContainsTextByXPATH(driver, actvity);
                }
                d++;
            }
        }

        ip.isTextPresentByXPATH(driver, "//div[3]/div/div[2]", "Instructors");
        ip.isTextPresentByXPATH(driver, "//div[3]/div/div[3]", "None");
        ip.isTextPresentByXPATH(driver, "//div[4]/div/div[2]", "Students");
        ip.isTextPresentByXPATH(driver, "//div[4]/div/div[3]", "None");

        driver.findElement(By.xpath("//li[4]/p/span")).click();
        driver.findElement(By.xpath("//li[4]/ul/li[3]/p/a")).click();
        ip.isElementClickableByXpath(driver, "//select", 60);

        Select select = new Select(driver.findElement(By.id("groups")));
        if (!" ".equalsIgnoreCase(select.getOptions().get(0).getText())) {
            Utility.illegalStateException(select.getOptions().get(0).getText()
                    + ": Group section should not get restored");
        }
    }

    /**
     * Validate activities while taking Backup & Restore then
     *
     * @param activities
     * @param activityxpath1
     * @param activityxpath2
     * @param i
     */
    private void validateActivities(String[] activities, String activityxpath1, String activityxpath2,
            int i) {
        int x = 1;
        for (String activity : activities) {
            if (x < 7) {
                if (!"gu-msn".equals(program)) {
                    if (x > 1) {
                        ip.isTextPresentByXPATH(driver, activityxpath1 + i + activityxpath2, activity, 60);
                        i++;
                    }
                } else {
                    ip.isTextPresentByXPATH(driver, activityxpath1 + i + activityxpath2, activity, 60);
                    i++;
                }
            }
            x++;
        }
    }

    /**
     * @return CourseName
     */
    public String getCourseName() {
        return this.courseName;
    }

    /**
     * @return GroupCourseName
     */
    public String getGroupCourseName() {
        return this.groupCourseName;
    }

    /**
     * @return backupFileName
     */
    public String getBackupFileName() {
        return this.backupFileName;
    }
}
