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
public class TransformDebug implements IAnnotationTransformer {

    @Override
    public void transform(ITestAnnotation annotation, Class testClass,
            Constructor testConstructor, Method testMethod) {

        if ("testActivities_Creation".equals(testMethod.getName())) {
            System.out.println("Inside testActivities_Creation");
            annotation.setDataProvider("Course");
        }

        if ("testAsgnRole".equals(testMethod.getName())) {
            System.out.println("Inside testAsgnRole");
            annotation.setDataProvider("GrpCrsUsers");
        }

        if ("testAddMbrsToWrkngGrp".equals(testMethod.getName())) {
            System.out.println("Inside testAddMbrsToWrkngGrp");
            annotation.setDataProvider("WrkngGrpUsers");
        }

        if ("testTchrPostsOn_Wall_CrsWall".equals(testMethod.getName())) {
            System.out.println("Inside testTchrPostsOn_Wall_CrsWall");
            annotation.setDataProvider("Course");
            annotation.setDataProviderClass(Crs_GrpCrsCreation.class);
        }

        if ("testStdtJoinsTchrSclGrp".equals(testMethod.getName())) {
            System.out.println("Inside testStdtJoinsTchrSclGrp");
            annotation.setDataProvider("TchrSclGrp");
            annotation.setDataProviderClass(TchrPosts_SclGrp.class);
        }

        if ("testStdtPostURLOnTchrSclGrp".equals(testMethod.getName())) {
            System.out.println("Inside testStdtPostURLOnTchrSclGrp");
            annotation.setDataProvider("TchrSclGrp");
            annotation.setDataProviderClass(TchrPosts_SclGrp.class);
        }

        if ("testStdtCmntOnTchrCrsPost".equals(testMethod.getName())) {
            System.out.println("Inside testStdtCmntOnTchrCrsPost");
            annotation.setDataProvider("GrpCrsTchrUrlCrsPst");
            annotation.setDataProviderClass(TchrPosts_SclGrp.class);
        }

        if ("testTchrCrtLvSsn".equals(testMethod.getName())) {
            System.out.println("Inside testTchrCrtLvSsn");
            annotation.setDataProvider("TchrSclGrp");
            annotation.setDataProviderClass(TchrPosts_SclGrp.class);
        }

        if ("testTchrCrtGglDoc".equals(testMethod.getName())) {
            System.out.println("Inside testTchrCrtGglDoc");
            annotation.setDataProvider("WrkngGrp");
            annotation.setDataProviderClass(UsrCrtn_AsgnRole_WrkngGrp.class);
        }

        if ("testTchrVrfyActivities".equals(testMethod.getName()) || "testStdtVrfyActivities".equals(testMethod.getName())) {
            System.out.println("Inside " + testMethod.getName());
            annotation.setDataProvider("GrpCrsActivities");
            annotation.setDataProviderClass(Crs_GrpCrsCreation.class);
        }

        if ("testStdtCrtLvSsn".equals(testMethod.getName())) {
            System.out.println("Inside testStdtCrtLvSsn");
            annotation.setDataProvider("TchrSclGrp");
            annotation.setDataProviderClass(TchrPosts_SclGrp.class);
        }

        if ("testStdtVrfyWrkGrp_GglDoc".equals(testMethod.getName())) {
            System.out.println("Inside testStdtVrfyWrkGrp_GglDoc");
            annotation.setDataProvider("WrkngGrpGgleDoc");
            annotation.setDataProviderClass(TchrLvSsn_GglDoc.class);
        }

        if ("testTchrJoinsStdtSclGrp".equals(testMethod.getName())) {
            System.out.println("Inside testTchrJoinsStdtSclGrp");
            annotation.setDataProvider("StdtSclGrp");
            annotation.setDataProviderClass(StdtLvSsn_SclGrp_GglDoc.class);
        }

        if ("testTchrLeavesStdtSclGrp".equals(testMethod.getName())) {
            System.out.println("Inside testTchrLeavesStdtSclGrp");
            annotation.setDataProvider("StdtSclGrp");
            annotation.setDataProviderClass(StdtLvSsn_SclGrp_GglDoc.class);
        }

        if ("testTchrDeleteSclGrp".equals(testMethod.getName())) {
            System.out.println("Inside testTchrDeleteSclGrp");
            annotation.setDataProvider("TchrSclGrp");
            annotation.setDataProviderClass(TchrPosts_SclGrp.class);
        }
    }
}
