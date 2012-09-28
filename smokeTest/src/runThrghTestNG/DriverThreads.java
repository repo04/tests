/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package runThrghTestNG;

import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import smoketest.AccountValues;
import smoketest.IsPresent;

/**
 *
 * @author somesh.bansal
 */
public class DriverThreads implements Runnable {

    public AccountValues av;
    public IsPresent ip = new IsPresent();
    String program;
    private WebDriver driverN;
    Map<String, WebDriver> driverMap;

    public DriverThreads(String program, Map<String, WebDriver> driverMap, AccountValues av) {
        this.program = program;
        this.driverMap = driverMap;
        this.av = av;
        new Thread(this).start();
    }

    @Override
    public void run() {
        System.out.println("RUNNNN");
        System.out.println(".......instantiate");
        try {
            driverN = new FirefoxDriver();
        } catch (Exception e) {
            e.printStackTrace();
        }
        driverMap.put(program, driverN);
        System.out.println(driverN + ".......");
        driverMap.get(program).manage().window().maximize();
        driverMap.get(program).get(av.getTokenValue("programURL"));
        ip.isTitlePresent(driverMap.get(program), av.getTokenValue("loginPageTitle"));
    }
}
