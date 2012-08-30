package runThrghTestNG;

import org.testng.annotations.Test;

/**
 *
 * @author somesh.bansal
 */
public class UsrCrtn_AsgnRole_WrkngGrp extends BaseClass {

    static String tchrUsrName = null;
    static String stdtUsrName = null;
    static String wrkngGrpName = null;

    @Test
    public void testPESAdminLgn() throws Exception {
        a.login("pesAdmin");
    }

    @Test(dependsOnMethods = {"testPESAdminLgn"})
    public void testUsrCrtn() throws Exception{
        a.navigateToMyContacts();
        tchrUsrName = a.createUser("teacher");
        System.out.println("tchrUsrName: " + tchrUsrName);

        a.navigateToMyContacts();
        stdtUsrName = a.createUser("student");
        System.out.println("stdtUsrName: " + stdtUsrName);
    }

    @Test(dependsOnMethods = {"testUsrCrtn"})
    public void testAsgnRole() throws Exception{
        a.navigateToMyCourse();
        a.selectGrpCourse(Crs_GrpCrsCreation.grpCrsName);
        a.enrollUsrToRole_GrpCrs(tchrUsrName, Crs_GrpCrsCreation.grpCrsName);

        a.navigateToMyCourse();
        a.selectGrpCourse(Crs_GrpCrsCreation.grpCrsName);
        a.enrollUsrToRole_GrpCrs(stdtUsrName, Crs_GrpCrsCreation.grpCrsName);
    }

    @Test(dependsOnMethods = {"testPESAdminLgn"})
    public void testCrtWrkgnGrp() throws Exception{
        a.navigateToWorkingGroups();
        wrkngGrpName = a.createWorkingGroup();
        System.out.println("wrkngGrp: " + wrkngGrpName);
    }

    @Test(dependsOnMethods = {"testCrtWrkgnGrp","testAsgnRole"})
    public void testAddMbrsToWrkngGrp() throws Exception{
        a.navigateToWorkingGroups();
        a.accessWrknGrp(wrkngGrpName);
        a.addMbrsToWrkngGrp(tchrUsrName, stdtUsrName);
    }
}