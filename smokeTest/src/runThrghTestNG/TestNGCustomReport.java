package runThrghTestNG;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class TestNGCustomReport extends TestListenerAdapter {

    @Override
    public void onTestFailure(ITestResult tr) {

        ScreenShot(tr);
    }

    public void ScreenShot(ITestResult result) {

        try {

            String NewFileNamePath;
            String methodName = result.getName();

            // Get the dir path
            File directory = new File(".");
            //System.out.println(directory.getCanonicalPath());

            //Get current date time with Date() to create unique file name
            SimpleDateFormat dateFormat = new SimpleDateFormat(
                    "ddMMMyy__hhmmaa");
            // get current date time with Date()
            Date date = new Date();
            //System.out.println(dateFormat.format(date));


            if (!(new File(directory.getCanonicalPath() + File.separator + "reports"+ File.separator + "screenshots")).exists()) {
                //System.out.println("make dir");
                new File(directory.getCanonicalPath() + File.separator + "reports"+ File.separator + "screenshots").mkdir();
            }

            NewFileNamePath = directory.getCanonicalPath() + File.separator + "reports"+ File.separator + "screenshots"
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