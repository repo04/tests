/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lms.tests.createXML;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

public class CreateXMLClass {

    public static void main(String[] args) throws IOException {

        System.out.print("****SIZE***: " + args.length + "\n");
        String[] programs = {"gu-msn", "unc-mba", "usc-mat", "wu-llm", "usc-msw",
            "unc-mpa", "gwu-mph", "au-mir", "corp-son", "sc-msn"};
        List<String> list;
        ArrayList<String> splitted = new ArrayList<>();
        String filename = null;
        int totalSize;
        String text = null;

        for (String arg : args) {
            list = Arrays.asList(arg.split(","));
            splitted.addAll(list);
            totalSize = list.size();
            filename = list.get(totalSize - 1);
            text = list.get(0);
            System.out.print("****totalSize***: " + totalSize + "\n");
            System.out.print("****filename***: " + filename + "\n");
            System.out.print("****text***: " + text + "\n");

            if (text.equalsIgnoreCase("All")) {
                splitted.clear();
                for (String prog : programs) {
                    splitted.add(prog);
                }
            } else {
                int dropLast = totalSize - 1;
                splitted.remove(dropLast);
            }
            System.out.print("****SPLIT***: " + splitted.size() + "\n");
        }

        String target = filename.substring(8);
        System.out.print("****target***: " + target + "\n");
        String shortUrl = null;
        String env = null;
        if (target.equalsIgnoreCase("Production")) {
            env = "Prod";
        } else if (target.equalsIgnoreCase("Staging")) {
            env = "Staging";
            shortUrl = "stg";
        } else {
            env = "Standalone";
            shortUrl = "standalone-prod";
        }

        Map<String, String> map = new HashMap<>();
        map.put("groupCourseName", "AutoGroupCourse");
        map.put("quizActivityName", "AutoQuiz");

        XmlSuite suite = new XmlSuite();

        suite.setName("CriticalSmoke" + env + " Suite");
        suite.setParallel("tests");
        suite.setThreadCount(10);
        suite.setVerbose(5);
        suite.addListener("com.lms.tests.runThrghTestNG.TransformCritical");
        suite.addListener("com.lms.tests.runThrghTestNG.TestInvocation");
        suite.setParameters(map);
        for (String split : splitted) {
            XmlTest test = new XmlTest();
            System.out.print(split + "\n");

            if (filename.equalsIgnoreCase("criticalProduction")) {
                switch (split) {
                    case "gu-msn":
                        test.addParameter("url", "https://2gu.nursing.georgetown.edu");
                        break;
                    case "unc-mba":
                        test.addParameter("url", "https://www.2nc.unc.edu");
                        break;
                    case "usc-mat":
                        test.addParameter("url", "https://www.2sc.usc.edu");
                        break;
                    case "usc-msw":
                        test.addParameter("url", "https://www.vac.usc.edu");
                        break;
                    case "wu-llm":
                        test.addParameter("url", "https://2law.onlinelaw.wustl.edu");
                        break;
                    case "unc-mpa":
                        test.addParameter("url", "https://2sg.onlinempa.unc.edu");
                        break;
                    case "au-mir":
                        test.addParameter("url", "https://2ir.ironline.american.edu");
                        break;
                    case "gwu-mph":
                        test.addParameter("url", "https://2gw.publichealthonline.gwu.edu");
                        break;
                    case "corp-son":
                        test.addParameter("url", "https://experience.semesteronline.org");
                        break;
                    case "sc-msn":
                        test.addParameter("url", "https://2sn.onlinenursing.simmons.edu");
                }
            } else {
                switch (split) {
                    case "gu-msn":
                        test.addParameter("url", "https://www-gu-msn-lms-" + shortUrl + ".2u.com");
                        break;
                    case "unc-mba":
                        test.addParameter("url", "https://www-unc-mba-lms-" + shortUrl + ".2u.com");
                        break;
                    case "usc-mat":
                        test.addParameter("url", "https://www-usc-mat-lms-" + shortUrl + ".2u.com");
                        break;
                    case "usc-msw":
                        test.addParameter("url", "https://www-usc-msw-lms-" + shortUrl + ".2u.com");
                        break;
                    case "wu-llm":
                        test.addParameter("url", "https://www-wu-llm-lms-" + shortUrl + ".2u.com");
                        break;
                    case "unc-mpa":
                        test.addParameter("url", "https://www-unc-mpa-lms-" + shortUrl + ".2u.com");
                        break;
                    case "au-mir":
                        test.addParameter("url", "https://www-au-mir-lms-" + shortUrl + ".2u.com");
                        break;
                    case "gwu-mph":
                        test.addParameter("url", "https://www-gwu-mph-lms-" + shortUrl + ".2u.com");
                        break;
                    case "corp-son":
                        test.addParameter("url", "https://www-corp-son-lms-" + shortUrl + ".2u.com");
                        break;
                    case "sc-msn":
                        test.addParameter("url", "https://www-sc-msn-lms-" + shortUrl + ".2u.com");
                }
            }

            test.setName("CriticalTests" + env + "_" + split.toUpperCase());
            test.setPreserveOrder("true");
            test.addParameter("program", split);
            test.addParameter("session", "CriticalTests" + env + "_" + split.toUpperCase());

            test.addIncludedGroup("prerequisite");
            test.addIncludedGroup("criticalSmoke");

            List<XmlClass> classes = new ArrayList<>();
            classes.add(new XmlClass("com.lms.tests.runThrghTestNG.Teacher_Posts_SocialGroup"));
            classes.add(new XmlClass("com.lms.tests.runThrghTestNG.Student_JoinSocialGroup_Post"));
            classes.add(new XmlClass("com.lms.tests.runThrghTestNG.Teacher_LiveSession_GoogleDoc"));
            classes.add(new XmlClass("com.lms.tests.runThrghTestNG.Student_LiveSession_SocialGroup_GoogleDoc"));
            classes.add(new XmlClass("com.lms.tests.runThrghTestNG.Teacher_JoinDelete_SocialGroup"));
            classes.add(new XmlClass("com.lms.tests.runThrghTestNG.Student_DeleteSocialGroup"));
            test.setXmlClasses(classes);
            suite.addTest(test);
        }
        File file = new File(filename + ".xml");
        System.out.println("file: " + file);
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(suite.toXml());
        }
    }
}
