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
public class TransformCritical implements IAnnotationTransformer {

    String DependentMethods[] = null;

    @Override
    public void transform(ITestAnnotation annotation, Class testClass,
            Constructor testConstructor, Method testMethod) {

        if ("testStdtJoinsTchrSclGrp".equals(testMethod.getName())) {
            System.out.println("Inside testStdtJoinsTchrSclGrp");
            DependentMethods = new String[1];
            DependentMethods[0] = "runThrghTestNG.TchrPosts_SclGrp.testTchrCrtSclGrp";
            annotation.setDependsOnMethods(DependentMethods);
        }

        if ("testStdtPostURLOnTchrSclGrp".equals(testMethod.getName())) {
            System.out.println("Inside testStdtPostURLOnTchrSclGrp");
            DependentMethods = new String[1];
            DependentMethods[0] = "testStdtJoinsTchrSclGrp";
            annotation.setDependsOnMethods(DependentMethods);
        }

        if ("testStdtCmntOnTchrCrsPost".equals(testMethod.getName())) {
            System.out.println("Inside testStdtCmntOnTchrCrsPost");
            DependentMethods = new String[1];
            DependentMethods[0] = "runThrghTestNG.TchrPosts_SclGrp.testTchrPostsOn_Wall_CrsWall";
            annotation.setDependsOnMethods(DependentMethods);
        }

        if ("testTchrCrtLvSsn".equals(testMethod.getName())) {
            System.out.println("Inside testTchrCrtLvSsn");
            DependentMethods = new String[1];
            DependentMethods[0] = "runThrghTestNG.TchrPosts_SclGrp.testTchrCrtSclGrp";
            annotation.setDependsOnMethods(DependentMethods);
        }

        if ("testStdtCrtLvSsn".equals(testMethod.getName())) {
            System.out.println("Inside testStdtCrtLvSsn");
            DependentMethods = new String[1];
            DependentMethods[0] = "runThrghTestNG.TchrLvSsn_GglDoc.testTchrCrtLvSsn";
            annotation.setDependsOnMethods(DependentMethods);
        }

        if ("testTchrJoinsStdtSclGrp".equals(testMethod.getName())) {
            System.out.println("Inside testTchrJoinsStdtSclGrp");
            DependentMethods = new String[1];
            DependentMethods[0] = "runThrghTestNG.StdtLvSsn_SclGrp_GglDoc.testStdtCrtSclGrp";
            annotation.setDependsOnMethods(DependentMethods);
        }

        if ("testTchrLeavesStdtSclGrp".equals(testMethod.getName())) {
            System.out.println("Inside testTchrLeavesStdtSclGrp");
            DependentMethods = new String[1];
            DependentMethods[0] = "testTchrJoinsStdtSclGrp";
            annotation.setDependsOnMethods(DependentMethods);
        }

        if ("testTchrDeleteSclGrp".equals(testMethod.getName())) {
            System.out.println("Inside testTchrDeleteSclGrp");
            DependentMethods = new String[1];
            DependentMethods[0] = "runThrghTestNG.TchrPosts_SclGrp.testTchrCrtSclGrp";
            annotation.setDependsOnMethods(DependentMethods);
        }

        if ("testStdtDeleteSclGrp".equals(testMethod.getName())) {
            System.out.println("Inside testStdtDeleteSclGrp");
            DependentMethods = new String[1];
            DependentMethods[0] = "runThrghTestNG.StdtLvSsn_SclGrp_GglDoc.testStdtCrtSclGrp";
            annotation.setDependsOnMethods(DependentMethods);
        }
    }
}
