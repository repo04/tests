/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lms.tests.runThrghTestNG;

import java.lang.reflect.Method;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.internal.ConstructorOrMethod;

/**
 * This method will be invoked by TestNG before and after a method is
 * invoked.This listener will only be invoked for configuration and test
 * methods.
 *
 */
public class TestInvocation implements IInvokedMethodListener {

    @Override
    public void beforeInvocation(IInvokedMethod invokedMethod, ITestResult result) {
        ITestNGMethod testNgMethod = result.getMethod();
        ConstructorOrMethod contructorOrMethod = testNgMethod.getConstructorOrMethod();
        Method method = contructorOrMethod.getMethod();
        System.out.print("*****MethodInvoking: " + method.getName() + "*****" + "\n");
        if (!"setUp".equals(method.getName()) && !BaseClass.program.contains("gu-msn")) {
            if (BaseClass.test.equalsIgnoreCase("RegressionTests") || BaseClass.test.equalsIgnoreCase("SmokeTests")) {
                if ("testContentAdminCreateQuizPasswordActivity".equals(method.getName())
                        || "testTeacherEmailFetchActivityPasswordLogIn".equals(method.getName())) {
                    System.out.println("Skipping Test Method");
                    throw new SkipException("Skipping Quiz Password TC: as it is only valid for GU MSN Program");
                }
            } else {
                if ("testTeacherGenerateQuizPassword".equals(method.getName())
                        || "testTeacherEmailFetchActivityPasswordLogIn".equals(method.getName())) {
                    System.out.println("Skipping Test Method");
                    throw new SkipException("Skipping Quiz Password TC: as it is only valid for GU MSN Program");
                }
            }
        }

        // Checks For UNC-MPA and WU-LLM 
        if (!"setUp".equals(method.getName()) && (BaseClass.program.contains("unc-mpa") 
                || BaseClass.program.contains("wu-llm") || BaseClass.program.contains("gwu-mph") 
                || BaseClass.program.contains("au-mir"))) {
            if ("testStudentSupportMobileURL".equals(method.getName()) || 
                    "testTeacherSupportMobileURL".equals(method.getName())) {
                System.out.println("Skipping Test Method");
                throw new SkipException("Skipping Mobile App Test in Student Support: Not available for '" + BaseClass.program + "'");
            }
        }        
        
        // Skip Backup/Restore TCs for Env other than Standalone
        if (!"setUp".equals(method.getName()) && !BaseClass.url.contains("standalone")
                && "criticalTests".equals(BaseClass.test)) {
            if ("testContentAdminLogIn".equals(method.getName())
                    || "testContentAdminBackupCourse".equals(method.getName())) {
                System.out.println("Skipping Test Method");
                throw new SkipException("Skipping: Course Backup/Restore TC's as these are to be executable only on Standalone Env");
            }
        }
    }

    @Override
    public void afterInvocation(IInvokedMethod iim, ITestResult itr) {
        //Do Nothing
    }
}