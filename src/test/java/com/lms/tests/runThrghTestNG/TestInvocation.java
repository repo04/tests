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
        if (!"setUp".equals(method.getName())) {            
            System.out.print("*****ProgamName: " + BaseClass.getProgram() + "*****" + "\n");
            
            if (!BaseClass.getProgram().contains("gu-msn")) {
                if (BaseClass.test.equalsIgnoreCase("RegressionTests")
                        || BaseClass.test.equalsIgnoreCase("SmokeTests")) {
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
            if (!BaseClass.test.equalsIgnoreCase("CriticalTests")
                    && (BaseClass.getProgram().contains("unc-mpa") || BaseClass.getProgram().contains("wu-llm")
                    || BaseClass.getProgram().contains("gwu-mph") || BaseClass.getProgram().contains("au-mir"))) {
                if ("testStudentSupportMobileURL".equals(method.getName())) {
                    System.out.println("Skipping Test Method");
                    throw new SkipException("Skipping Mobile App Test in Student Support: Not Available For UNC-MPA and WU-LLM");
                }
            }

            // Skip Backup/Restore TCs for Env other than Standalone
            if (!BaseClass.getURL().contains("standalone")) {
                if ("testContentAdminBackupCourse".equals(method.getName())) {
                    System.out.println("Skipping Test Method");
                    throw new SkipException("Skipping: Course Backup/Restore TC's as these are executable only on Standalone Env");
                }
            }
        }
    }

    @Override
    public void afterInvocation(IInvokedMethod iim, ITestResult itr) {
        //Do Nothing
    }
}