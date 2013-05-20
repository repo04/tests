/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lms.tests.runThrghTestNG;

import com.saucelabs.common.SauceOnDemandAuthentication;
import com.saucelabs.common.SauceOnDemandSessionIdProvider;
import com.saucelabs.saucerest.SauceREST;
import com.saucelabs.common.Utils;
import com.saucelabs.testng.SauceOnDemandAuthenticationProvider;
import java.io.File;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.Augmenter;
import org.testng.Reporter;

/**
 *
 * @author somesh.bansal
 */
public class SauceOnDemandTestListener extends TestListenerAdapter {

    private static final String SELENIUM_BROWSER = "SELENIUM_BROWSER";
    private static final String SELENIUM_PLATFORM = "SELENIUM_PLATFORM";
    private static final String SELENIUM_VERSION = "SELENIUM_VERSION";
    
    /**
     * The underlying {@link com.saucelabs.common.SauceOnDemandSessionIdProvider} instance which contains the Selenium session id.  This is typically
     * the unit test being executed.
     */
    private SauceOnDemandSessionIdProvider sessionIdProvider;

    /**
     * The instance of the Sauce OnDemand Java REST API client.
     */
    private SauceREST sauceREST;

    /**
     * Check to see if environment variables that define the Selenium browser to be used have been set (typically by
     * a Sauce OnDemand CI plugin).  If so, then populate the appropriate system parameter, so that tests can use
     * these values.
     *
     * @param testContext
     */
    @Override
    public void onStart(ITestContext testContext) {
        super.onStart(testContext);
        String browser = System.getenv(SELENIUM_BROWSER);
        if (browser != null && !browser.equals("")) {
            System.setProperty("browser", browser);
        }
        String platform = System.getenv(SELENIUM_PLATFORM);
        if (platform != null && !platform.equals("")) {
            System.setProperty("os", platform);
        }
        String version = System.getenv(SELENIUM_VERSION);
        if (version != null && !version.equals("")) {
            System.setProperty("version", version);
        }
    }

    /**
     * @param result
     */
    @Override
    public void onTestStart(ITestResult result) {
        super.onTestStart(result);

        if (result.getInstance() instanceof SauceOnDemandSessionIdProvider) {
            this.sessionIdProvider = (SauceOnDemandSessionIdProvider) result.getInstance();
            //log the session id to the system out
            if (sessionIdProvider.getSessionId() != null) {
                System.out.println(String.format("SauceOnDemandSessionID=%1$s job-name=%2$s", sessionIdProvider.getSessionId(), result.getMethod().getMethodName()));
            }
        }
        SauceOnDemandAuthentication sauceOnDemandAuthentication;
        if (result.getInstance() instanceof SauceOnDemandAuthenticationProvider) {
            //use the authentication information provided by the test class
            SauceOnDemandAuthenticationProvider provider = (SauceOnDemandAuthenticationProvider) result.getInstance();
            sauceOnDemandAuthentication = provider.getAuthentication();
        } else {
            //otherwise use the default authentication
            sauceOnDemandAuthentication = new SauceOnDemandAuthentication();
        }
        this.sauceREST = new SauceREST(sauceOnDemandAuthentication.getUsername(), sauceOnDemandAuthentication.getAccessKey());
    }

    /**
     * @param tr
     */
    @Override
    public void onTestFailure(ITestResult tr) {
        super.onTestFailure(tr);
        //ScreenShot(tr);
        markJobAsFailed();
    }

    private void markJobAsFailed() {
        try {
            if (this.sauceREST != null && sessionIdProvider != null) {
                String sessionId = sessionIdProvider.getSessionId();
                if (sessionId != null) {
                    Map<String, Object> updates = new HashMap<String, Object>();
                    updates.put("passed", false);
                    Utils.addBuildNumberToUpdate(updates);
                    sauceREST.updateJobInfo(sessionId, updates);                    
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
            throw new RuntimeException(ioe);
        }
    }


    /**
     * @param tr
     */
    @Override
    public void onTestSuccess(ITestResult tr) {
        super.onTestSuccess(tr);
        markJobAsPassed();
    }

    private void markJobAsPassed() {
        try {
            if (this.sauceREST != null && sessionIdProvider != null) {
                String sessionId = sessionIdProvider.getSessionId();
                if (sessionId != null) {
                    Map<String, Object> updates = new HashMap<String, Object>();
                    updates.put("passed", true);
                    Utils.addBuildNumberToUpdate(updates);
                    sauceREST.updateJobInfo(sessionId, updates);
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
            throw new RuntimeException(ioe);
        }
    }
    
    //Capture screenshot on TestFailure
    private void ScreenShot(ITestResult result) {

        try {

            String NewFileNamePath;
            String methodName = result.getName();

            //Get current date time with Date() to create unique file name
            SimpleDateFormat dateFormat = new SimpleDateFormat(
                    "ddMMMyy__hhmmaa");
            // get current date time with Date()
            Date date = new Date();

            if (!(new File(BaseClass.directory.getCanonicalPath() + File.separator + "target" + File.separator + "surefire-reports" + File.separator + "screenshots")).exists()) {
                new File(BaseClass.directory.getCanonicalPath() + File.separator + "target" + File.separator + "surefire-reports" + File.separator + "screenshots").mkdir();
            }

            NewFileNamePath = BaseClass.directory.getCanonicalPath() + File.separator + "target" + File.separator + "surefire-reports" + File.separator + "screenshots"
                    + File.separator + methodName + "_" + dateFormat.format(date) + ".png";

            System.out.println(NewFileNamePath);
            /*System.out.println("//screenshot//: " + BaseClass.threadLocalDriver.get());
            File screenshot = ((TakesScreenshot) new Augmenter().augment(BaseClass.threadLocalDriver.get())).
                    getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File(NewFileNamePath));
            Reporter.log(methodName + " failed; Click on image to enlarge<br/>"
                    + "<a target=\"_blank\" href=\"" + NewFileNamePath + "\"><img src=\"file:///" + NewFileNamePath
                    + "\" alt=\"\"" + "height='100' width='100'/></a><br />");
            Reporter.setCurrentTestResult(null);
            System.out.println("//screenshot saved//");*/
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
