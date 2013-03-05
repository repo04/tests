
package smoketest;

//import org.testng.annotations.BeforeClass;
import runThrghTestNG.BaseClass;
import smoketest.Actions;

public class StudentSupport extends BaseClass {
    Actions a = new Actions();
    String programName = BaseClass.program;
    
    public void verifyStudentSupport() {
        testContactUS();
    }
    
    public void testContactUS() {
        
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("phoneNumberXPATH"), 
                                        xpv.getTokenValue(programName + "PhoneNumber"));
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("mondayXPATH"), 
                                        xpv.getTokenValue(programName + "MondayTimes"));
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("tuesdayXPATH"), 
                                        xpv.getTokenValue(programName + "TuesdayTimes"));
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("wednesdayXPATH"), 
                                        xpv.getTokenValue(programName + "WednesdayTimes"));
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("thursdayXPATH"), 
                                        xpv.getTokenValue(programName + "ThursdayTimes"));
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("fridayXPATH"), 
                                        xpv.getTokenValue(programName + "FridayTimes"));
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("saturdayXPATH"), 
                                        xpv.getTokenValue(programName + "SaturdayTimes"));
        ip.isTextPresentByXPATH(driver, xpv.getTokenValue("sundayXPATH"), 
                                        xpv.getTokenValue(programName + "SundayTimes"));
    }
}
