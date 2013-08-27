/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lms.tests.runThrghTestNG;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

/**
 * This method will be invoked by TestNG to give you a chance to modify a TestNG
 * annotation read from your test classes. You can change the values you need by
 * calling any of the setters on the ITest interface.
 *
 * Note that only one of the three parameters testClass, testConstructor and
 * testMethod will be non-null.
 *
 * @param annotation The annotation that was read from your test class.
 * @param testClass If the annotation was found on a class, this parameter
 * represents this class (null otherwise).
 * @param testConstructor If the annotation was found on a constructor, this
 * parameter represents this constructor (null otherwise).
 * @param testMethod If the annotation was found on a method, this parameter
 * represents this method (null otherwise).
 */
public class TransformCriticalData implements IAnnotationTransformer {

    String DependentMethods[] = null;

    @Override
    public void transform(ITestAnnotation annotation, Class testClass,
            Constructor testConstructor, Method testMethod) {
        
        if ("testContentAdminQuizCreation".equals(testMethod.getName())) {
            System.out.println("Inside testContentAdminQuizCreation");
            DependentMethods = new String[1];
            DependentMethods[0] = "testContentAdminCourseGroupCourseCreation";
            annotation.setDependsOnMethods(DependentMethods);
        }

        if ("testContentAdminAddQuizQuestion".equals(testMethod.getName())) {
            System.out.println("Inside " + testMethod.getName());
            DependentMethods = new String[1];
            DependentMethods[0] = "testContentAdminQuizCreation";
            annotation.setDependsOnMethods(DependentMethods);
        }
        
        if ("testPESAdminUserCreation".equals(testMethod.getName())) {
            System.out.println("Inside " + testMethod.getName());
            DependentMethods = new String[1];
            DependentMethods[0] = "com.lms.tests.runThrghTestNG.ContentAdmin_Course_GroupCourseCreation.testContentAdminAddQuizQuestion";
            annotation.setDependsOnMethods(DependentMethods);
        }

        if ("testPESAdminAssignRole".equals(testMethod.getName())) {
            System.out.println("Inside " + testMethod.getName());
            DependentMethods = new String[1];
            DependentMethods[0] = "testPESAdminUserCreation";
            annotation.setDependsOnMethods(DependentMethods);
        }

        if ("testTeacherAddStudentAsContactfromCourse".equals(testMethod.getName())) {
            System.out.println("Inside " + testMethod.getName());
            DependentMethods = new String[1];
            DependentMethods[0] = "com.lms.tests.runThrghTestNG.Pes_UserCreation_AssignRole_WorkingGroup.testPESAdminAssignRole";
            annotation.setDependsOnMethods(DependentMethods);
        }

        if ("testStudentConfirmContactRequest".equals(testMethod.getName())) {
            System.out.println("Inside " + testMethod.getName());
            DependentMethods = new String[1];
            DependentMethods[0] = "com.lms.tests.runThrghTestNG.Teacher_Posts_SocialGroup.testTeacherAddStudentAsContactfromCourse";
            annotation.setDependsOnMethods(DependentMethods);
        }
    }
}