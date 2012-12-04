/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package runThrghTestNG;

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
public class TransformRegression implements IAnnotationTransformer {

    String DependentMethods[] = null;

    @Override
    public void transform(ITestAnnotation annotation, Class testClass,
            Constructor testConstructor, Method testMethod) {

        if ("testCreateNoteOnProfileWall".equals(testMethod.getName())) {
            System.out.println("Inside testCreateNoteOnProfileWall");
            DependentMethods = new String[1];
            DependentMethods[0] = "testCreateNoteOnCourseWall";
            annotation.setDependsOnMethods(DependentMethods);
        }
        
        if ("testVerifyNoteSorting".equals(testMethod.getName())) {
            System.out.println("Inside testVerifyNoteSorting");
            DependentMethods = new String[1];
            DependentMethods[0] = "testCreateNoteOnProfileWall";
            annotation.setDependsOnMethods(DependentMethods);
        }

        if ("testDeleteProfileNote".equals(testMethod.getName())) {
            System.out.println("Inside testDeleteProfileNote");
            DependentMethods = new String[1];
            DependentMethods[0] = "testVerifyNoteSorting";
            annotation.setDependsOnMethods(DependentMethods);
        }
        
        /*if ("testPesAdminPostAnnouncement".equals(testMethod.getName())) {
            System.out.println("Inside testPesAdminPostAnnouncement");
            DependentMethods = new String[1];
            DependentMethods[0] = "testPesAdminPostTextOnCourseSection";
            annotation.setDependsOnMethods(DependentMethods);
        }
        
        if ("testPesAdminPostTextOnCourseCommentsOn".equals(testMethod.getName())) {
            System.out.println("Inside testPesAdminPostAnnouncement");
            DependentMethods = new String[1];
            DependentMethods[0] = "testPesAdminPostAnnouncement";
            annotation.setDependsOnMethods(DependentMethods);
        }
        
        if ("testPesAdminPostTextOnCourseCommentsOff".equals(testMethod.getName())) {
            System.out.println("Inside testPesAdminPostAnnouncement");
            DependentMethods = new String[1];
            DependentMethods[0] = "testPesAdminPostTextOnCourseCommentsOn";
            annotation.setDependsOnMethods(DependentMethods);
        }*/
    }
}