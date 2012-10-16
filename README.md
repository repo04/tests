 README
--------------------------------------------------------------------------------------------------------------------------------------
Welcome to QA Automation Project README.<br />
The objective of the document is to describe the steps required to execute Automation vide multiple machines (OS's: WIN / LINUX / MAC)

1> Install JDK 1.7 or higher
----------------------------
In order to execute Automation Project, you need to install JDK 1.7 or higher

	  i> Download & install JDK 1.7 or higher specific to machine
	     http://www.oracle.com/technetwork/java/javase/downloads/java-se-jdk-7-download-432154.html
     ii> Set PATH & CLASSPATH
		 a> Windows <need to manually set>
		 b> Linux/Mac <automatically set>
    iii> Verify JDK is installed running 
         a> java -version, RESULT as 
		    java version "1.7.0_01"
		    Java(TM) SE Runtime Environment (build 1.7.0_01-b08)
		    Java HotSpot(TM) Client VM (build 21.1-b02, mixed mode, sharing)
	     b> javac -version, RESULT as
		    javac 1.7.0_01

2> Install ANT 1.8 or higher
----------------------------
In order to execute Automation Project, you need to install ANT 1.8 or higher

	  i> Download ANT 1.8 or higher specific to machine 
		 http://ant.apache.org/bindownload.cgi
     ii> After download ANT,extract zip package in (for example) C:\
    iii> Set ANT_HOME
         Ref: http://omrudi.wordpress.com/2008/11/08/how-to-install-ant-in-windows-xp/
		 a> Windows <need to manually set>
		 b> Linux/Mac <automatically set>
     iv> Verify, running 
	     a> ant -version, RESULT as 
		    Apache Ant(TM) version 1.8.4 compiled on May 22 2012
		   
3> Install GIT BASH
-------------------
In order to execute Automation Project, you need to install GitBash

	  i> Download & install GIT BASH specific to machine
	     https://help.github.com/articles/set-up-git#platform-all
     ii> Verify GIT BASH is installed
    iii> You must be the member of 2tor/tests project 
         Ref: https://github.com/2tor/tests
     iv> Navigate to directory using GIT BASH where you want to download the project. Once, at specific path execute following command
         git clone https://github.com/2tor/tests.git OR
         git clone git@github.com:2tor/tests.git <Enter your Login credentials>	   
      v> Verify, project <tests> is downloaded     
	
4> Setup ANT's project specific JARS
------------------------------------
In order to execute Automation project, you need to copy jars to your machine specific ANT's classpath. <br />
**PS:** Path where BUILD.XML is located is referred as **BASEDIR**

      i> Copy all files from <Basedir/lib/antLib> to your machine specific ANT's lib folder, eg:
		 a> Windows: C:\Program Files\apache-ant-1.8.4-bin\apache-ant-1.8.4
		 b> MAC: /usr/share/ant/lib
		
5> Setup to execute on Chrome Browser
-------------------------------------
In order to execute Automation project using CHROME Browser, you need to make file on unix server available to read, write, etc.

     i> Using GITBASH, navigate to project <Basedir/lib/chromedriver_*>. '*' refer to machine/os (linux32 / linux64 / mac / win)
    ii> Execute chmod +x filename or chmod 777 filename so as to make the file executable
        http://selftechy.com/2011/08/17/running-selenium-tests-with-chromedriver-on-linux
	   
6> Good to run Automation
-------------------------
After successfully executing above steps, you are good to run Automation

     i> Using GitBash, navigate to <Basedir>
    ii> Sample ANT Command to run Suite:
	
		*ant runsmoke -DantPrgrm=gu,usc -DantBrwsr=chrome,ff -DantOS=win* //with below mentioned parameters
	
	Applicable Parameters (Case Sensitive)
		1> run Target<Test type>: runsmoke / runcritical / rundebug  –> Accepts only one target name at a time
        2> antPrgrm<Target program>: gu / unc / usc / llm / vac / mpa {Program short name}  –> n number of program's delimited by ","
		3> antBrwsr<Target browser>: ff / chrome {browser name} –> n number of browser's delimited by ","
		4> antOS<Target OS>: win / linux32 / linux64 / mac {OS name} –> Only one OS name can be passed
     
    iii> Sequence of run would be as follows
     	1> Target program <gu>
                a> chrome  b> ff
        2> Target program <usc>
                a> chrome  b> ff
	 iv> Each run will have separate reports folder {Basedir}\reports\{program}_{browser}
      v> Test report (zip) folder is automatically mailed to recipients <mentioned in build.xml> once the execution is complete. 
	
-----------------------------------------------END--------------------------------------------------------------------------------------    
	
	
	 
	 