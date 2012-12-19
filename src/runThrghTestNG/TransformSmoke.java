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
public class TransformSmoke implements IAnnotationTransformer {

    String DependentMethods[] = null;

    @Override
    public void transform(ITestAnnotation annotation, Class testClass,
            Constructor testConstructor, Method testMethod) {

        if ("testActivities_Creation".equals(testMethod.getName())) {
            System.out.println("Inside testActivities_Creation");
            DependentMethods = new String[1];
            DependentMethods[0] = "testCrsGrpCrs_Creation";
            annotation.setDependsOnMethods(DependentMethods);
        }

        if ("testAddQuizQuestion".equals(testMethod.getName())) {
            System.out.println("Inside testAddQuizQuestion");
            DependentMethods = new String[1];
            DependentMethods[0] = "testActivities_Creation";
            annotation.setDependsOnMethods(DependentMethods);
        }

        if ("testAsgnRole".equals(testMethod.getName())) {
            System.out.println("Inside testAsgnRole");
            DependentMethods = new String[2];
            DependentMethods[0] = "runThrghTestNG.Crs_GrpCrsCreation.testCrsGrpCrs_Creation";
            DependentMethods[1] = "testUsrCrtn";
            annotation.setDependsOnMethods(DependentMethods);
        }

        if ("testAddMbrsToWrkngGrp".equals(testMethod.getName())) {
            System.out.println("Inside testAddMbrsToWrkngGrp");
            DependentMethods = new String[2];
            DependentMethods[0] = "testCrtWrkgnGrp";
            DependentMethods[1] = "testAsgnRole";
            annotation.setDependsOnMethods(DependentMethods);
        }

        if ("testTchrPostsOn_Wall_CrsWall".equals(testMethod.getName())
                || "testTchrCrtSclGrp".equals(testMethod.getName())
                || "testStdtCrtSclGrp".equals(testMethod.getName())) {
            System.out.println("Inside " + testMethod.getName());
            DependentMethods = new String[1];
            DependentMethods[0] = "runThrghTestNG.UsrCrtn_AsgnRole_WrkngGrp.testAsgnRole";
            annotation.setDependsOnMethods(DependentMethods);
        }

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

        if ("testTchrCrtGglDoc".equals(testMethod.getName())) {
            System.out.println("Inside testTchrCrtGglDoc");
            DependentMethods = new String[1];
            DependentMethods[0] = "runThrghTestNG.UsrCrtn_AsgnRole_WrkngGrp.testAddMbrsToWrkngGrp";
            annotation.setDependsOnMethods(DependentMethods);
        }

        if ("testTchrVrfyActivities".equals(testMethod.getName())
                || "testStdtVrfyActivities".equals(testMethod.getName())) {
            System.out.println("Inside " + testMethod.getName());
            DependentMethods = new String[2];
            DependentMethods[0] = "runThrghTestNG.Crs_GrpCrsCreation.testActivities_Creation";
            DependentMethods[1] = "runThrghTestNG.UsrCrtn_AsgnRole_WrkngGrp.testAsgnRole";
            annotation.setDependsOnMethods(DependentMethods);
        }
        
        if ("testTchrGradeAsgnmnt".equals(testMethod.getName())
                || "testTchrAllwResbmtAsgnmnt".equals(testMethod.getName())) {
            System.out.println("Inside testTchrGradeAsgnmnt");
            DependentMethods = new String[1];
            DependentMethods[0] = "runThrghTestNG.StdtJnSclGrp_Post.testSubmitAsgnmnt";
            annotation.setDependsOnMethods(DependentMethods);
        }

        if ("testVrfyAsgnmntGrade".equals(testMethod.getName())) {
            System.out.println("Inside " + testMethod.getName());
            DependentMethods = new String[1];
            DependentMethods[0] = "runThrghTestNG.TchrLvSsn_GglDoc.testTchrGradeAsgnmnt";
            annotation.setDependsOnMethods(DependentMethods);
        }

        if ("testSubmitQuiz".equals(testMethod.getName())) {
            System.out.println("Inside " + testMethod.getName());
            DependentMethods = new String[2];
            DependentMethods[0] = "runThrghTestNG.Crs_GrpCrsCreation.testAddQuizQuestion";
            DependentMethods[1] = "runThrghTestNG.UsrCrtn_AsgnRole_WrkngGrp.testAsgnRole";
            annotation.setDependsOnMethods(DependentMethods);
        }

        if ("testStdtCrtLvSsn".equals(testMethod.getName())) {
            System.out.println("Inside testStdtCrtLvSsn");
            DependentMethods = new String[2];
            DependentMethods[0] = "runThrghTestNG.StdtJnSclGrp_Post.testStdtJoinsTchrSclGrp";
            DependentMethods[1] = "runThrghTestNG.TchrLvSsn_GglDoc.testTchrCrtLvSsn";
            annotation.setDependsOnMethods(DependentMethods);
        }

        if ("testStdtVrfyWrkGrp_GglDoc".equals(testMethod.getName())) {
            System.out.println("Inside testStdtVrfyWrkGrp_GglDoc");
            DependentMethods = new String[1];
            DependentMethods[0] = "runThrghTestNG.TchrLvSsn_GglDoc.testTchrCrtGglDoc";
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
        
        if ("testDeleteWrkngGrp".equals(testMethod.getName())) {
            System.out.println("Inside testDeleteWrkngGrp");
            DependentMethods = new String[1];
            DependentMethods[0] = "runThrghTestNG.StdtLvSsn_SclGrp_GglDoc.testStdtVrfyWrkGrp_GglDoc";
            annotation.setDependsOnMethods(DependentMethods);
            annotation.setAlwaysRun(true);
        }

        if ("testDeleteUsers".equals(testMethod.getName())) {
            System.out.println("Inside testDeleteUsers");
            DependentMethods = new String[1];
            DependentMethods[0] = "runThrghTestNG.Stdt_DeleteSclGrp.testStdtDeleteSclGrp";
            annotation.setDependsOnMethods(DependentMethods);
            annotation.setAlwaysRun(true);
        }

        if ("testArchvCrs".equals(testMethod.getName())) {
            System.out.println("Inside testArchvCrs");
            DependentMethods = new String[1];
            DependentMethods[0] = "runThrghTestNG.PES_CleanTestData.testDeleteUsers";
            annotation.setDependsOnMethods(DependentMethods);
            annotation.setAlwaysRun(true);
        }

        if ("testTchrVerifyEmails".equals(testMethod.getName())) {
            System.out.println("Inside testTchrVerifyEmails");
            DependentMethods = new String[5];
            DependentMethods[0] = "runThrghTestNG.UsrCrtn_AsgnRole_WrkngGrp.testAddMbrsToWrkngGrp";
            DependentMethods[1] = "runThrghTestNG.StdtJnSclGrp_Post.testStdtJoinsTchrSclGrp";
            DependentMethods[2] = "runThrghTestNG.StdtJnSclGrp_Post.testStdtCmntOnTchrCrsPost";
            DependentMethods[3] = "runThrghTestNG.TchrJoin_Delete_SclGrp.testTchrJoinsStdtSclGrp";
            DependentMethods[4] = "runThrghTestNG.Pes_ArchvCrs.testArchvCrs";
            annotation.setDependsOnMethods(DependentMethods);
        }

        if ("testStdtVerifyEmails".equals(testMethod.getName())) {
            System.out.println("Inside testStdtVerifyEmails");
            DependentMethods = new String[4];
            DependentMethods[0] = "runThrghTestNG.UsrCrtn_AsgnRole_WrkngGrp.testAddMbrsToWrkngGrp";
            DependentMethods[1] = "runThrghTestNG.StdtJnSclGrp_Post.testStdtJoinsTchrSclGrp";
            DependentMethods[2] = "runThrghTestNG.TchrJoin_Delete_SclGrp.testTchrJoinsStdtSclGrp";
            DependentMethods[3] = "runThrghTestNG.Pes_ArchvCrs.testArchvCrs";
            annotation.setDependsOnMethods(DependentMethods);
        }
    }
}