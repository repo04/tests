/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package runThrghTestNG;

import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import smoketest.Actions;

/* 
 * Content Admin logs in 
 * Create - Course ,GrpCourse, Activities like Forum, Quiz, All In One Assignment & Page 
 * Logs out
 */
public class Crs_GrpCrsCreation extends BaseClass {

    static String crsName;
    static String grpCrsName;
    static String frmActvyName;
    static String quizActvtyName;
    static String allInOneAsgnmntAvtvtyName;
    static String pageActvtyName;
    Actions a = new Actions();
    public String xyz;
    String[][] tabArray=null;

    

    /**
     * The annotated method will be run before the first test method in the
     * current class is invoked, Content Admin logs in
     *
     * @throws Exception
     */
    /*@BeforeClass
    @Parameters({"abc"})
    public void testCntntAdminLgn(String xyz) throws Exception {

        this.xyz = xyz;
        a.login("contentAdmin");
    }*/
    
    @Parameters({"tstNm"})
    @BeforeClass
    public void testCntntAdminLgn(String TN) throws Exception {
            this.xyz = TN; 
            System.out.println("testName: " + TN);
            a.login("contentAdmin");
    }
    
    
    @DataProvider(name = "DP")
    public Object[][] createData(ITestContext context) throws Exception {        
        
        if(xyz.equalsIgnoreCase("SmokeTest"))
        {
            System.out.println("Inside testName: " + xyz);
            Object[][] abc = createData1();
            return(abc);   
        }else{
            System.out.println("Inside testName: " + xyz);
            Object[][] def = createData2(context);
            return(def);
        }       
    }
    
    /*@DataProvider(name = "DP1")
    public Object[][] createData() throws Exception {
        Object[][] retObjArr=getCrsArray();
        return(retObjArr);        
    }*/
    
    /*@DataProvider(name = "DP2")
    public Object[][] createData(ITestContext context) throws Exception {
        return new Object[][]{{context.getCurrentXmlTest().getParameter("crsNam"), context.getCurrentXmlTest().getParameter("grpCrsNam")}};
    }*/

    /**
     * Create - Course & GrpCourse
     *
     * @throws Exception
     */
    /*@Test
    public void testCrsGrpCrs_Creation() throws Exception {
        
        tabArray=new String[1][2];

        a.navigateToMyCourse();
        tabArray[0][0] = a.createCourse();
        System.out.println("crsName: " + tabArray[0][0]);
        Reporter.log("crsName: " + tabArray[0][0]);

        a.navigateToMyCourse();
        tabArray[0][1] = a.createGrpCourse(tabArray[0][0]);
        System.out.println("grpCrsName: " + tabArray[0][1]);
        Reporter.log("grpCrsName: " + tabArray[0][1]);
    }*/

    /**
     * Create - Activities like Forum, Quiz, All In One Assignment & Page
     *
     * @throws Exception
     */
    @Test(dataProvider = "DP", dependsOnMethods = {"testCrsGrpCrs_Creation"}, ignoreMissingDependencies=true)
    public void testActivities_Creation(String c, String d) throws Exception { 
        
        System.out.println("crsName: " + c);
        System.out.println("grpCrsName: " + d);
        
        /*a.navigateToMyCourse();
        a.selectGrpCourse(grpCrsName);
        frmActvyName = a.createForumActivity();
        System.out.println("frmActvyName: " + frmActvyName);
        Reporter.log("frmActvyName: " + frmActvyName);

        a.navigateToMyCourse();
        a.selectGrpCourse(grpCrsName);
        quizActvtyName = a.createQuizActivity();
        System.out.println("quizActvtyName: " + quizActvtyName);
        Reporter.log("quizActvtyName: " + quizActvtyName);

        a.navigateToMyCourse();
        a.selectGrpCourse(grpCrsName);
        allInOneAsgnmntAvtvtyName = a.createAllInOneAsgnmntActivity();
        System.out.println("allInOneAsgnmntAvtvtyName: " + allInOneAsgnmntAvtvtyName);
        Reporter.log("allInOneAsgnmntAvtvtyName: " + allInOneAsgnmntAvtvtyName);

        a.navigateToMyCourse();
        a.selectGrpCourse(grpCrsName);
        pageActvtyName = a.createPageResource();
        System.out.println("pageActvtyName: " + pageActvtyName);
        Reporter.log("pageActvtyName: " + pageActvtyName);*/
    }

    /**
     * The annotated method will be run after all the test methods in the
     * current class have been run, User logsOut
     *
     * @throws Exception
     */
    @AfterClass
    public void testCntntAdminLogOut() throws Exception {
        a.logOut();
    }

    private Object[][] getCrsArray() {
        return(tabArray);  
    }

    private Object[][] createData1() {
        Object[][] retObjArr=getCrsArray();
        return(retObjArr);
    }    

    private Object[][] createData2(ITestContext context) {
        return new Object[][]{{context.getCurrentXmlTest().getParameter("crsNam"), context.getCurrentXmlTest().getParameter("grpCrsNam")}};
    }
}
