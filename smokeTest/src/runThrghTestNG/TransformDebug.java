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

    String DependentMethods[] = null;

    @Override
    public void transform(ITestAnnotation annotation, Class testClass,
            Constructor testConstructor, Method testMethod) {

        //GroupName = UsrsCrtn_Role_Dltn
        if ("testAsgnRole".equals(testMethod.getName())) {
            System.out.println("Inside testAsgnRole");
            DependentMethods = new String[1];
            DependentMethods[0] = "runThrghTestNG.UsrCrtn_AsgnRole_WrkngGrp.testUsrCrtn";
            annotation.setDependsOnMethods(DependentMethods);
            annotation.setDataProvider("GrpCrsUsersDebug");
            annotation.setDataProviderClass(UsrCrtn_AsgnRole_WrkngGrp.class);
        }
        
        if ("testUnerolUsers".equals(testMethod.getName())) {
            System.out.println("Inside testUnerolUsers");
            DependentMethods = new String[1];
            DependentMethods[0] = "runThrghTestNG.UsrCrtn_AsgnRole_WrkngGrp.testAsgnRole";            
            annotation.setDependsOnMethods(DependentMethods);
            annotation.setDataProvider("GrpCrsUsersDebug");
            annotation.setDataProviderClass(UsrCrtn_AsgnRole_WrkngGrp.class);
        }
        
        if ("testDeleteUsers".equals(testMethod.getName())) {
            System.out.println("Inside testDeleteUsers");
            DependentMethods = new String[1];
            DependentMethods[0] = "runThrghTestNG.PES_CleanTestData.testUnerolUsers";            
            annotation.setDependsOnMethods(DependentMethods);
            annotation.setAlwaysRun(true);
            annotation.setDataProvider("UsersDebug");
            annotation.setDataProviderClass(UsrCrtn_AsgnRole_WrkngGrp.class);
        }
        
        //GroupName = WrkngGrpCrtn_AddMbrs_Dltn
        if ("testAddMbrsToWrkngGrp".equals(testMethod.getName())) {
            System.out.println("Inside testAddMbrsToWrkngGrp");
            DependentMethods = new String[1];
            DependentMethods[0] = "testCrtWrkgnGrp";
            annotation.setDependsOnMethods(DependentMethods);
            annotation.setDataProvider("WrkngGrpDebugUsrs");
            annotation.setDataProviderClass(UsrCrtn_AsgnRole_WrkngGrp.class);
        }
        
        if ("testRemoveMbrsFrmWrkngGrp".equals(testMethod.getName())) {
            System.out.println("Inside testRemoveMbrsFrmWrkngGrp");
            DependentMethods = new String[1];
            DependentMethods[0] = "runThrghTestNG.UsrCrtn_AsgnRole_WrkngGrp.testAddMbrsToWrkngGrp";
            annotation.setDependsOnMethods(DependentMethods);
            annotation.setDataProvider("GrpCrsWrkngGrpDebugUsers");
            annotation.setDataProviderClass(UsrCrtn_AsgnRole_WrkngGrp.class);
        }
        
        if ("testDeleteWrkngGrp".equals(testMethod.getName())) {
            System.out.println("Inside testDeleteWrkngGrp");
            DependentMethods = new String[1];
            DependentMethods[0] = "runThrghTestNG.PES_CleanTestData.testRemoveMbrsFrmWrkngGrp";            
            annotation.setDependsOnMethods(DependentMethods);
            annotation.setAlwaysRun(true);
            annotation.setDataProvider("WrkngGrpDebug");
            annotation.setDataProviderClass(UsrCrtn_AsgnRole_WrkngGrp.class);
        }
        
        //GroupName = GglDoc_MbrsCrtVrfy
        if ("testStdtVrfyWrkGrp_GglDoc".equals(testMethod.getName())) {
            System.out.println("Inside testStdtVrfyWrkGrp_GglDoc");
            DependentMethods = new String[1];
            DependentMethods[0] = "runThrghTestNG.TchrLvSsn_GglDoc.testTchrCrtGglDoc";
            annotation.setDependsOnMethods(DependentMethods);
        }
        
        //GroupName = TchrWallCrs_StdtCmntOnPsts
        if ("testStdtCmntOnTchrCrsPost".equals(testMethod.getName())) {
            System.out.println("Inside testStdtCmntOnTchrCrsPost");
            DependentMethods = new String[1];
            DependentMethods[0] = "runThrghTestNG.TchrPosts_SclGrp.testTchrPostsOn_Wall_CrsWall";
            annotation.setDependsOnMethods(DependentMethods);
        }
        
        //GroupName = TchrStdt_CrtDlt_SclGrpLvSsn
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
        
        if ("testTchrCrtLvSsn".equals(testMethod.getName())) {
            System.out.println("Inside testTchrCrtLvSsn");
            DependentMethods = new String[1];
            DependentMethods[0] = "runThrghTestNG.TchrPosts_SclGrp.testTchrCrtSclGrp";
            annotation.setDependsOnMethods(DependentMethods);
        }
        
        if ("testStdtCrtLvSsn".equals(testMethod.getName())) {
            System.out.println("Inside testStdtCrtLvSsn");
            DependentMethods = new String[2];
            DependentMethods[0] = "runThrghTestNG.StdtJnSclGrp_Post.testStdtJoinsTchrSclGrp";
            DependentMethods[1] = "runThrghTestNG.TchrLvSsn_GglDoc.testTchrCrtLvSsn";
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
            annotation.setDataProvider("StdtSclGrpDebug");
            annotation.setDataProviderClass(StdtLvSsn_SclGrp_GglDoc.class);
        }
        
        //GroupName = Stdt_JnLvsTchrGrp
        if ("testTchrLeavesStdtSclGrp".equals(testMethod.getName())) {
            System.out.println("Inside testTchrLeavesStdtSclGrp");
            DependentMethods = new String[1];
            DependentMethods[0] = "testTchrJoinsStdtSclGrp";
            annotation.setDependsOnMethods(DependentMethods);            
        }        
    }
}