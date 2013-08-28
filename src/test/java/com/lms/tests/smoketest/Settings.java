/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lms.tests.smoketest;

import com.lms.tests.runThrghTestNG.BaseClass;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 *
 *
 */
public class Settings extends BaseClass {

    String verify1, verify2, verify3, verify4, verify5, verify6, verify7,
            verify8, verify9, verify10, verify11, verify12, verify13, verify14, verify15;
    List<String> elementsText;
    List<WebElement> checkboxes;

    /**
     * Verify Settings page specific to user role
     */
    void verifySettings() {
        //General
        ip.isTextPresentByXPATH(driver, "//legend", "General");
        ip.isTextPresentByXPATH(driver, "//label", "Username");
        ip.isTextPresentByXPATH(driver, "//div[2]/div/label", "Current password");
        ip.isTextPresentByXPATH(driver, "//div[2]/div[3]/div[2]",
                "The password must have at least 8 characters, at least 1 digit(s), at least 1 lower case letter(s), at least 1 upper case letter(s), at least 1 non-alphanumeric character(s)");
        ip.isTextPresentByXPATH(driver, "//div[2]/div[4]/div", "New password");
        ip.isTextPresentByXPATH(driver, "//div[5]/div/label", "New password (again)");
        ip.isTextPresentByXPATH(driver, "//div[6]/div/label", "Timezone");
        if (program.contentEquals("gu-msn")) {
            ip.isTextPresentByXPATH(driver, "//div[7]/div/label", "Default Mail Client Separator");
        } else {
            ip.isTextPresentByXPATH(driver, "//div[7]/div/label", "Mail Preferences");
        }
        ip.isTextPresentByXPATH(driver, "//div[2]/div[4]/div", "New password");

        ip.isElementPresentByXPATH(driver, "//div[2]/input");
        ip.isElementPresentByXPATH(driver, "//div[4]/div[2]/input");
        ip.isElementPresentByXPATH(driver, "//div[5]/div[2]/input");
        ip.isElementPresentByXPATH(driver, "//select");
        ip.isElementPresentByXPATH(driver, "//div[7]/div[2]/select");
        ip.isElementPresentByXPATH(driver, "//img[@alt='Help with New password']");

        //Notification Settings
        ip.isTextPresentByXPATH(driver, "//fieldset[2]/legend", "Notification Settings");
        ip.isTextPresentByXPATH(driver, "//div[2]/h2", "To control which notifications you receive, "
                + "use the checkboxes below.\n\nSend a notification when:");

        verify1 = "Someone requests you as a contact";
        verify2 = "Someone confirms your contact request";
        verify3 = "Someone posts or comments on your profile wall";
        verify4 = "Someone comments on your post(s)";
        verify5 = "Someone joins a group you are in";
        verify6 = "Someone invites you to join a group";
        verify7 = "A live session will be held in the next 2 days";
        verify8 = "A professor or Student Support posts an announcement";
        verify9 = "Someone posts in a course I am teaching";
        verify10 = "A student submits an assignment";
        verify11 = "A recording for a live session I attended/should attend is available online";
        verify12 = "An assignment is due in the next 5 days";
        verify13 = "One of my assignments is graded";
        verify14 = "Remind me when assignment is past due";
        verify15 = "A professor posts in a course I am in";

        if (LoginPage.getUser().contains("student")) {
            elementsText = Arrays.asList(verify1, verify2, verify3, verify4, verify5, verify6,
                    verify12, verify7, verify13, verify8, verify14, verify15, verify11);
        } else {
            elementsText = Arrays.asList(verify1, verify2, verify3, verify4, verify5, verify6,
                    verify7, verify8, verify9, verify10, verify11);
        }

        int i = 2;
        for (String elementText : elementsText) {
            ip.isTextPresentByXPATH(driver, "//div[2]/table/tbody/tr[" + i + "]/td", elementText);
            i++;
        }

        checkboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
        ip.isTextPresentByXPATH(driver, "//th[2]", "Online");
        ip.isTextPresentByXPATH(driver, "//th[3]", "Email");
        switch (program) {
            case "wu-llm":
            case "unc-mpa":
            case "sc-msn":
            case "gwu-mph":
            case "au-mir":
                ip.invisibilityOfElementByXpathWithText(driver, "//th[4]", "Push");
                int x = 1;
                if (LoginPage.getUser().contains("pes") || LoginPage.getUser().contains("content")) {
                    for (WebElement checkbox : checkboxes) {
                        if (x == 1 || x == 24 || x == 29 || x == 30) {
                            if (checkbox.isSelected() || !checkbox.isEnabled()) {
                                Utility.illegalStateException("Checkbox: "
                                        + checkbox.getAttribute("name") + " should be: not selected & enabled");
                            }
                        } else if (x == 4 || x == 7 || x == 10 || x == 13 || x == 16 || x == 19
                                || x == 22 || x == 25 || x == 28 || x == 31 || x == 34) {
                            if (checkbox.isDisplayed()) {
                                Utility.illegalStateException("Checkbox: "
                                        + checkbox.getAttribute("name") + " should be hidden");
                            }
                        } else {
                            if (!checkbox.isSelected() || !checkbox.isEnabled()) {
                                Utility.illegalStateException("Checkbox: "
                                        + checkbox.getAttribute("name") + " should be: selected & enabled");
                            }
                        }
                        x++;
                    }
                } else if (LoginPage.getUser().contains("teacher")) {
                    for (WebElement checkbox : checkboxes) {
                        if (x == 8 || x == 11 || x == 23) {
                            if (!checkbox.isSelected() || checkbox.isEnabled()) {
                                Utility.illegalStateException("Checkbox: "
                                        + checkbox.getAttribute("name") + " should be: selected & not enabled");
                            }
                        } else if (x == 1 || x == 24 || x == 29 || x == 30) {
                            if (checkbox.isSelected() || !checkbox.isEnabled()) {
                                Utility.illegalStateException("Checkbox: "
                                        + checkbox.getAttribute("name") + " should be: not selected & enabled");
                            }
                        } else if (x == 4 || x == 7 || x == 10 || x == 13 || x == 16 || x == 19
                                || x == 22 || x == 25 || x == 28 || x == 31 || x == 34) {
                            if (checkbox.isDisplayed()) {
                                Utility.illegalStateException("Checkbox: "
                                        + checkbox.getAttribute("name") + " should be hidden");
                            }
                        } else {
                            if (!checkbox.isSelected() || !checkbox.isEnabled()) {
                                Utility.illegalStateException("Checkbox: "
                                        + checkbox.getAttribute("name") + " should be: selected & enabled");
                            }
                        }
                        x++;
                    }
                } else if (LoginPage.getUser().contains("coordinator")) {
                    for (WebElement checkbox : checkboxes) {
                        if (x == 17 || x == 20 || x == 21) {
                            checkbox.isEnabled();
                            if (checkbox.isSelected()) {
                                Utility.illegalStateException("Checkbox: "
                                        + checkbox.getAttribute("name") + " should not be selected");
                            }
                        } else {
                            if (!checkbox.isSelected() || !checkbox.isEnabled()) {
                                Utility.illegalStateException("Checkbox: "
                                        + checkbox.getAttribute("name") + " should be: selected & enabled");
                            }
                        }
                        x++;
                    }
                } else {
                    for (WebElement checkbox : checkboxes) {
                        if (x == 2 || x == 17 || x == 29) {
                            if (!checkbox.isSelected() || checkbox.isEnabled()) {
                                Utility.illegalStateException("Checkbox: "
                                        + checkbox.getAttribute("name") + " should be: selected & not enabled");
                            }
                        } else if (x == 1 || x == 30 || x == 33 || x == 35 || x == 36) {
                            if (checkbox.isSelected() || !checkbox.isEnabled()) {
                                Utility.illegalStateException("Checkbox: "
                                        + checkbox.getAttribute("name") + " should be: not selected & enabled");
                            }
                        } else if (x == 4 || x == 7 || x == 10 || x == 13 || x == 16 || x == 19
                                || x == 22 || x == 25 || x == 28 || x == 31 || x == 34 || x == 37 || x == 40) {
                            if (checkbox.isDisplayed()) {
                                Utility.illegalStateException("Checkbox: "
                                        + checkbox.getAttribute("name") + " should be hidden");
                            }
                        } else {
                            if (!checkbox.isSelected() || !checkbox.isEnabled()) {
                                Utility.illegalStateException("Checkbox: "
                                        + checkbox.getAttribute("name") + " should be: selected & enabled");
                            }
                        }
                        x++;
                    }
                }
                ip.isElementPresentByXPATH(driver, "//fieldset[3]/div/div/div[2]/input");
                break;

            default:
                ip.isTextPresentByXPATH(driver, "//th[4]", "Push");
                x = 1;
                if (LoginPage.getUser().contains("pes") || LoginPage.getUser().contains("content")) {
                    for (WebElement checkbox : checkboxes) {
                        if (x == 1 || x == 4 || x == 7 || x == 13 || x == 16 || x == 19 || x == 24
                                || x == 25 || x == 28 || x == 29 || x == 30 || x == 31 || x == 34) {
                            if (checkbox.isSelected() || !checkbox.isEnabled()) {
                                Utility.illegalStateException("Checkbox: "
                                        + checkbox.getAttribute("name") + " should be: not selected & enabled");
                            }
                        } else {
                            if (!checkbox.isSelected() || !checkbox.isEnabled()) {
                                Utility.illegalStateException("Checkbox: "
                                        + checkbox.getAttribute("name") + " should be: selected & enabled");
                            }
                        }
                        x++;
                    }
                } else if (LoginPage.getUser().contains("teacher")) {
                    for (WebElement checkbox : checkboxes) {
                        if (x == 8 || x == 11 || x == 23) {
                            if (!checkbox.isSelected() || checkbox.isEnabled()) {
                                Utility.illegalStateException("Checkbox: "
                                        + checkbox.getAttribute("name") + " should be: selected & not enabled");
                            }
                        } else if (x == 4 || x == 7 || x == 13 || x == 16 || x == 19
                                || x == 25 || x == 28 || x == 31 || x == 34) {
                            if (checkbox.isSelected() || checkbox.isEnabled()) {
                                Utility.illegalStateException("Checkbox: "
                                        + checkbox.getAttribute("name") + " should be: not selected & not enabled");
                            }
                        } else if (x == 1 || x == 24 || x == 29 || x == 30) {
                            if (checkbox.isSelected() || !checkbox.isEnabled()) {
                                Utility.illegalStateException("Checkbox: "
                                        + checkbox.getAttribute("name") + " should be: not selected & enabled");
                            }
                        } else {
                            if (!checkbox.isSelected() || !checkbox.isEnabled()) {
                                Utility.illegalStateException("Checkbox: "
                                        + checkbox.getAttribute("name") + " should be: selected & enabled");
                            }
                        }
                        x++;
                    }
                } else if (LoginPage.getUser().contains("coordinator")) {
                    for (WebElement checkbox : checkboxes) {
                        if (x == 1 || x == 17 || x == 20 || x == 21) {
                            checkbox.isEnabled();
                            if (checkbox.isSelected()) {
                                Utility.illegalStateException("Checkbox: "
                                        + checkbox.getAttribute("name") + " should not be selected");
                            }
                        } else {
                            if (!checkbox.isSelected() || !checkbox.isEnabled()) {
                                Utility.illegalStateException("Checkbox: "
                                        + checkbox.getAttribute("name") + " should be: selected & enabled");
                            }
                        }
                        x++;
                    }
                } else {
                    for (WebElement checkbox : checkboxes) {
                        if (x == 2 || x == 17 || x == 29) {
                            if (!checkbox.isSelected() || checkbox.isEnabled()) {
                                Utility.illegalStateException("Checkbox: "
                                        + checkbox.getAttribute("name") + " should be: selected & not enabled");
                            }
                        } else if (x == 4 || x == 7 || x == 13 || x == 16 || x == 19
                                || x == 28 || x == 31 || x == 37 || x == 40) {
                            if (checkbox.isSelected() || checkbox.isEnabled()) {
                                Utility.illegalStateException("Checkbox: "
                                        + checkbox.getAttribute("name") + " should be: not selected & not enabled");
                            }
                        } else if (x == 1 || x == 30 || x == 33 || x == 35 || x == 36) {
                            if (checkbox.isSelected() || !checkbox.isEnabled()) {
                                Utility.illegalStateException("Checkbox: "
                                        + checkbox.getAttribute("name") + " should be: not selected & enabled");
                            }
                        } else {
                            if (!checkbox.isSelected() || !checkbox.isEnabled()) {
                                Utility.illegalStateException("Checkbox: "
                                        + checkbox.getAttribute("name") + " should be: selected & enabled");
                            }
                        }
                        x++;
                    }
                }
                ip.isElementPresentByXPATH(driver, "//fieldset[3]/div/div/div[2]/input");
        }
    }
}
