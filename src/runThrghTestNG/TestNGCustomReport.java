package runThrghTestNG;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class TestNGCustomReport extends TestListenerAdapter {

    @Override
    public void onTestFailure(ITestResult tr) {
        ScreenShot(tr);
    }

    @Override
    public void onStart(ITestContext testContext) {
        System.out.print("Class: " + testContext.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.print("TestInvoking: " + result.getName());
    }

    //Capture screenshot on TestFailure
    public void ScreenShot(ITestResult result) {

        try {

            String NewFileNamePath;
            String methodName = result.getName();

            // Get the dir path
            File directory = new File(".");

            //Get current date time with Date() to create unique file name
            SimpleDateFormat dateFormat = new SimpleDateFormat(
                    "ddMMMyy__hhmmaa");
            // get current date time with Date()
            Date date = new Date();
            String rprtPrgm;
            String rprtEnv;

            if (BaseClass.program.substring(0, 2).contains("gu")) {
                rprtPrgm = "gu";
                rprtEnv = BaseClass.program.substring(2);
            } else {
                rprtPrgm = BaseClass.program.substring(0, 3);
                rprtEnv = BaseClass.program.substring(3);
            }

            if (!(new File(directory.getCanonicalPath() + File.separator + "reports" + File.separator + rprtPrgm + "_" + rprtEnv + "_" + BaseClass.brwsr + File.separator + "screenshots")).exists()) {
                new File(directory.getCanonicalPath() + File.separator + "reports" + File.separator + rprtPrgm + "_" + rprtEnv + "_" + BaseClass.brwsr + File.separator + "screenshots").mkdir();
            }

            NewFileNamePath = directory.getCanonicalPath() + File.separator + "reports" + File.separator + rprtPrgm + "_" + rprtEnv + "_" + BaseClass.brwsr + File.separator + "screenshots"
                    + File.separator + methodName + "_" + dateFormat.format(date) + ".png";

            System.out.println(NewFileNamePath);

            File screenshot = ((TakesScreenshot) BaseClass.driver).
                    getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File(NewFileNamePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
