/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smoketest;

import runThrghTestNG.BaseClass;

/**
 *
 * 
 */
public class Profile extends BaseClass {

    void verifyPersonalInfo() {
        ip.isTextPresentByXPATH(driver, "//div[5]/div/div/div/div[4]", "Basic Information");
        ip.isTextPresentByXPATH(driver, "//div[6]/div/div", "Name:");
        ip.isTextPresentByXPATH(driver, "//div[6]/div[3]/div", "Birthday:");
        ip.isTextPresentByXPATH(driver, "//div[6]/div[4]/div", "Gender:");

        ip.isTextPresentByXPATH(driver, "//div[10]/div/div", "Email:");
        ip.isTextPresentByXPATH(driver, "//div[10]/div[2]/div", "Website:");
        ip.isTextPresentByXPATH(driver, "//div[10]/div[3]/div", "Phone:");
        ip.isTextPresentByXPATH(driver, "//div[10]/div[4]/div", "Linkedin:");
        ip.isTextPresentByXPATH(driver, "//div[10]/div[5]/div", "Twitter:");
        ip.isTextPresentByXPATH(driver, "//div[10]/div[6]/div", "Skype:");
        ip.isTextPresentByXPATH(driver, "//div[10]/div[7]/div", "GTalk:");
        ip.isTextPresentByXPATH(driver, "//div[10]/div[8]/div", "Yahoo:");
        ip.isTextPresentByXPATH(driver, "//div[10]/div[9]/div", "MSN:");
        ip.isTextPresentByXPATH(driver, "//div[10]/div[10]/div", "AIM:");
        ip.isTextPresentByXPATH(driver, "//div[11]/div", "Availability:");
        ip.isTextPresentByXPATH(driver, "//div[12]", "Personal Information");

        switch (program) {
            case "usc-mat":
                ip.isTextPresentByXPATH(driver, "//div[5]/div/div/div/div[8]", "Contact Information");
                ip.isTextPresentByXPATH(driver, "//div[6]/div[2]/div", "City, State:");

                ip.isTextPresentByXPATH(driver, "//div[14]/div/div", "Favorite Books:");
                ip.isTextPresentByXPATH(driver, "//div[14]/div[2]/div", "Favorite Movies:");
                ip.isTextPresentByXPATH(driver, "//div[14]/div[3]/div", "Favorite Television shows:");
                ip.isTextPresentByXPATH(driver, "//div[14]/div[4]/div", "Favorite Music:");
                ip.isTextPresentByXPATH(driver, "//div[16]", "Educational Information");
                ip.isTextPresentByXPATH(driver, "//div[18]/div/div", "Courses:");
                ip.isTextPresentByXPATH(driver, "//div[18]/div[2]/div", "Course of Study:");
                ip.isTextPresentByXPATH(driver, "//div[18]/div[3]/div", "Teaching Experience:");
                ip.isTextPresentByXPATH(driver, "//div[18]/div[4]/div", "Why do you want to teach?");
                ip.isTextPresentByXPATH(driver, "//div[18]/div[5]/div", "What inspired you to teach?");
                ip.isTextPresentByXPATH(driver, "//div[18]/div[6]/div", "Interested in:");
                break;
            case "unc-mpa":
                //Problem
                ip.isTextPresentByXPATH(driver, "//div[5]/div/div/div/div[8]", "Contact Info");
                ip.isTextPresentByXPATH(driver, "//div[6]/div[2]/div", "City, State:");

                ip.isTextPresentByXPATH(driver, "//div[14]/div/div", "Favorite Books:");
                ip.isTextPresentByXPATH(driver, "//div[14]/div[2]/div", "Favorite Movies:");
                ip.isTextPresentByXPATH(driver, "//div[14]/div[3]/div", "Favorite Television shows:");
                ip.isTextPresentByXPATH(driver, "//div[14]/div[4]/div", "Favorite Music:");
                ip.isTextPresentByXPATH(driver, "//div[16]", "Work Information");
                ip.isTextPresentByXPATH(driver, "//div[18]/div/div", "Courses:");
                ip.isTextPresentByXPATH(driver, "//div[18]/div[2]/div", "Where do you work?");
                ip.isTextPresentByXPATH(driver, "//div[18]/div[3]/div", "What does your organization do?");
                ip.isTextPresentByXPATH(driver, "//div[18]/div[4]/div", "What role do you hold within the organization?");
                ip.isTextPresentByXPATH(driver, "//div[18]/div[5]/div", "What are your reasons for pursuing an MPA?");
                ip.isTextPresentByXPATH(driver, "//div[18]/div[6]/div", "In what languages are you proficient?");
                break;
            case "unc-mba":
                //Problem
                ip.isTextPresentByXPATH(driver, "//div[5]/div/div/div/div[8]", "Contact Info");
                ip.isTextPresentByXPATH(driver, "//div[14]/div[3]/div", "Favorite Television shows:");
                ip.isTextPresentByXPATH(driver, "//div[6]/div[2]/div", "City, State:");

                ip.isTextPresentByXPATH(driver, "//div[14]/div/div", "Favorite Books:");
                ip.isTextPresentByXPATH(driver, "//div[14]/div[2]/div", "Favorite Movies:");
                ip.isTextPresentByXPATH(driver, "//div[14]/div[4]/div", "Favorite Music:");
                ip.isTextPresentByXPATH(driver, "//div[16]", "Work Information");
                ip.isTextPresentByXPATH(driver, "//div[18]/div/div", "Courses:");
                ip.isTextPresentByXPATH(driver, "//div[18]/div[2]/div", "Current Employer:");
                ip.isTextPresentByXPATH(driver, "//div[18]/div[3]/div", "Position:");
                ip.isTextPresentByXPATH(driver, "//div[18]/div[4]/div", "Duration of Employment:");
                ip.isTextPresentByXPATH(driver, "//div[18]/div[5]/div", "Industry:");
                ip.isTextPresentByXPATH(driver, "//div[18]/div[6]/div", "Past Education:");
                ip.isTextPresentByXPATH(driver, "//div[18]/div[7]/div", "What languages are you proficient in?");
                ip.isTextPresentByXPATH(driver, "//div[18]/div[8]/div", "Reasons for pursuing an MBA:");
                ip.isTextPresentByXPATH(driver, "//div[18]/div[9]/div", "Fun Fact:");
                ip.isTextPresentByXPATH(driver, "//div[18]/div[10]/div", "Major accomplishment:");
                break;
            case "gu-msn":
                //Problem
                ip.isTextPresentByXPATH(driver, "//div[5]/div/div/div/div[8]", "Contact Info");
                ip.isTextPresentByXPATH(driver, "//div[14]/div[3]/div", "Favorite Television shows:");
                ip.isTextPresentByXPATH(driver, "//div[6]/div[2]/div", "City, State:");

                ip.isTextPresentByXPATH(driver, "//div[14]/div/div", "Favorite Books:");
                ip.isTextPresentByXPATH(driver, "//div[14]/div[2]/div", "Favorite Movies:");
                ip.isTextPresentByXPATH(driver, "//div[14]/div[4]/div", "Favorite Music:");
                ip.isTextPresentByXPATH(driver, "//div[16]", "Educational Information");
                ip.isTextPresentByXPATH(driver, "//div[18]/div/div", "Courses:");
                ip.isTextPresentByXPATH(driver, "//div[18]/div[2]/div", "Program:");
                ip.isTextPresentByXPATH(driver, "//div[18]/div[3]/div", "Nursing Experience:");
                ip.isTextPresentByXPATH(driver, "//div[18]/div[4]/div", "Why do you want to be an advanced practice nurse?");
                ip.isTextPresentByXPATH(driver, "//div[18]/div[5]/div", "What inspired you to be a nurse?");
                ip.isTextPresentByXPATH(driver, "//div[18]/div[6]/div", "Interested in:");
                break;
            case "usc-msw":
                ip.isTextPresentByXPATH(driver, "//div[5]/div/div/div/div[8]", "Contact Information");
                ip.isTextPresentByXPATH(driver, "//div[6]/div[2]/div", "City, State:");

                ip.isTextPresentByXPATH(driver, "//div[14]/div/div", "Favorite Books:");
                ip.isTextPresentByXPATH(driver, "//div[14]/div[2]/div", "Favorite Movies:");
                ip.isTextPresentByXPATH(driver, "//div[14]/div[3]/div", "Favorite Television shows:");
                ip.isTextPresentByXPATH(driver, "//div[14]/div[4]/div", "Favorite Music:");
                ip.isTextPresentByXPATH(driver, "//div[16]", "Educational Information");
                ip.isTextPresentByXPATH(driver, "//div[18]/div/div", "Courses:");
                ip.isTextPresentByXPATH(driver, "//div[18]/div[2]/div", "Course of Study:");
                ip.isTextPresentByXPATH(driver, "//div[18]/div[3]/div", "Social Work Experience:");
                ip.isTextPresentByXPATH(driver, "//div[18]/div[4]/div", "Why do you want to do social work?");
                ip.isTextPresentByXPATH(driver, "//div[18]/div[5]/div", "What inspired you to become a social worker?");
                ip.isTextPresentByXPATH(driver, "//div[18]/div[6]/div", "Interested in:");
                break;
            case "wu-llm":
                //Confusion of Country
                ip.isTextPresentByXPATH(driver, "//div[5]/div/div/div/div[8]", "Contact Information");
                ip.isTextPresentByXPATH(driver, "//div[6]/div[2]/div", "Country:");

                ip.isTextPresentByXPATH(driver, "//div[14]/div/div", "What professional goals will an LL.M. "
                        + "in U.S. Law help you to achieve?");
                ip.isTextPresentByXPATH(driver, "//div[14]/div[2]/div", "Have you received any professional "
                        + "awards or honors?");
                ip.isTextPresentByXPATH(driver, "//div[14]/div[3]/div", "Where did you receive your first law"
                        + " degree?");
                ip.isTextPresentByXPATH(driver, "//div[14]/div[4]/div", "Personal interests?");
                ip.isTextPresentByXPATH(driver, "//div[16]", "Work Information");
                ip.isTextPresentByXPATH(driver, "//div[18]/div/div", "Courses:");
                ip.isTextPresentByXPATH(driver, "//div[18]/div[2]/div", "Where do you work?");
                ip.isTextPresentByXPATH(driver, "//div[18]/div[3]/div", "What is your title?");
                ip.isTextPresentByXPATH(driver, "//div[18]/div[4]/div", "How many years have you been in the legal profession?");
                ip.isTextPresentByXPATH(driver, "//div[18]/div[5]/div", "Any professional memberships?");
                ip.isTextPresentByXPATH(driver, "//div[18]/div[6]/div", "Are you currently working with U.S. companies?");
                break;
            case "au-mir":
                //Confusion of Country
                ip.isTextPresentByXPATH(driver, "//div[5]/div/div/div/div[8]", "Contact Information");
                ip.isTextPresentByXPATH(driver, "//div[6]/div[2]/div", "Country:");

                ip.isTextPresentByXPATH(driver, "//div[14]/div/div", "Are you a member of any Professional Associations?");
                ip.isTextPresentByXPATH(driver, "//div[14]/div[2]/div", "Where do you currently reside?");
                ip.isTextPresentByXPATH(driver, "//div[14]/div[3]/div", "What languages are you proficient in?");
                ip.isTextPresentByXPATH(driver, "//div[14]/div[4]/div", "Please list a major accomplishments:");
                ip.isTextPresentByXPATH(driver, "//div[16]", "Work Information");
                ip.isTextPresentByXPATH(driver, "//div[18]/div/div", "Courses:");
                ip.isTextPresentByXPATH(driver, "//div[18]/div[2]/div", "Where did you previously attend University?");
                ip.isTextPresentByXPATH(driver, "//div[18]/div[3]/div", "What was your major?");
                ip.isTextPresentByXPATH(driver, "//div[18]/div[4]/div", "What employment sector are you currently or have recently worked in?");
                ip.isTextPresentByXPATH(driver, "//div[18]/div[5]/div", "Have you ever lived or worked Internationally? If so, where?");
                ip.isTextPresentByXPATH(driver, "//div[18]/div[6]/div", "What concentration are you interested in pursuing?");
                break;
            case "gwu-mph":
                //Confusion of Country
                ip.isTextPresentByXPATH(driver, "//div[5]/div/div/div/div[8]", "Contact Information");
                ip.isTextPresentByXPATH(driver, "//div[6]/div[2]/div", "Country:");

                ip.isTextPresentByXPATH(driver, "//div[14]/div/div", "Favorite Books:");
                ip.isTextPresentByXPATH(driver, "//div[14]/div[2]/div", "Favorite Movies:");
                ip.isTextPresentByXPATH(driver, "//div[14]/div[3]/div", "Favorite Television:");
                ip.isTextPresentByXPATH(driver, "//div[14]/div[4]/div", "Favorite Music:");
                ip.isTextPresentByXPATH(driver, "//div[16]", "Work Information");
                ip.isTextPresentByXPATH(driver, "//div[18]/div/div", "Courses:");
                ip.isTextPresentByXPATH(driver, "//div[18]/div[2]/div", "Current Employer:");
                ip.isTextPresentByXPATH(driver, "//div[18]/div[3]/div", "Title/Position:");
                ip.isTextPresentByXPATH(driver, "//div[18]/div[4]/div", "Do you have Public Health experience? What state or country?");
                ip.isTextPresentByXPATH(driver, "//div[18]/div[5]/div", "Past Education:");
                ip.isTextPresentByXPATH(driver, "//div[18]/div[6]/div", "Why are you pursuing an MPH?");
        }
    }
}
