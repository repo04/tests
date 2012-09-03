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

            //java.awt.Dimension resolution = Toolkit.getDefaultToolkit()
            //   .getScreenSize();
            //Rectangle rectangle = new Rectangle(resolution);

            // Get the dir path
            File directory = new File(".");
            //System.out.println(directory.getCanonicalPath());

            // get current date time with Date() to create unique file name
            SimpleDateFormat dateFormat = new SimpleDateFormat(
                    "dd_MMM_yyyy__hh_mm_ssaa");
            // get current date time with Date()
            Date date = new Date();
            //System.out.println(dateFormat.format(date));


            if (!(new File(directory.getCanonicalPath() + File.separator + "ScreenShots")).exists()) {
                //System.out.println("make dir");
                new File(directory.getCanonicalPath() + File.separator + "ScreenShots").mkdir();
            }

            NewFileNamePath = directory.getCanonicalPath() + File.separator + "ScreenShots"
                    + File.separator + methodName + "_" + dateFormat.format(date) + ".png";

            System.out.println(NewFileNamePath);

            // Capture the screen shot of the area of the screen defined by the rectangle
            //Robot robot = new Robot();
            //BufferedImage bi = robot.createScreenCapture(new Rectangle(rectangle));
            //ImageIO.write(bi, "png", new File(NewFileNamePath));
            //Reporter.log(NewFileNamePath);
            //File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            //WebDriver augmentedDriver = new Augmenter().augment(driver);

            File screenshot = ((TakesScreenshot) smoketest.Page.driver).
                    getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File(NewFileNamePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
