 README
--------------------------------------------------------------------------------------------------------------------------------------
Welcome to QA Automation Project README.<br />
The objective of the document is to outline the steps required to configure and run the Automation Suite any system (OS's: WIN / LINUX / MAC)

1. Install JDK 1.7 or higher
----------------------------
Requirement: JDK 1.7 or higher

	  i> Download & install JDK 1.7 or higher (OS specific)
	     [JDK](http://www.oracle.com/technetwork/java/javase/downloads/java-se-jdk-7-download-432154.html)
     ii> Set Environment Variables: PATH & CLASSPATH
		 a> Windows <manually configured>
		 	*  [Path](http://java.com/en/download/help/path.xml)
		 	*  instuctions for classpath
		 b> Linux/Mac <auto configured>
    iii> Verify JDK is installed correctly
    	 a> open terminal 
         b> java -version
         	________________________________________________________________
		    java version "1.7.0_01"
		    Java(TM) SE Runtime Environment (build 1.7.0_01-b08)
		    Java HotSpot(TM) Client VM (build 21.1-b02, mixed mode, sharing)

	     c> javac -version
	        _______________
		    javac 1.7.0_01

2. Install ANT 1.8 or higher (Windows Only)
----------------------------
Requirement: ANT 1.8 or higher

	  i> Download ANT 1.8 or higher (OS specific) 
		 [Ant](http://ant.apache.org/bindownload.cgi)
     ii> Extract package
    iii> Set Environment Variable: ANT_HOME
		 a> Windows <manually configured>
		 	* [Tutorial](http://omrudi.wordpress.com/2008/11/08/how-to-install-ant-in-windows-xp)
		 b> Linux/Mac <auto configured>
     iv> Verify ANT installed correctly 
	     a> open terminal
		 b> ant -version
		    ____________________________________________________
		    Apache Ant(TM) version 1.8.4 compiled on May 22 2012
		   
3. Install Git
-------------------
Requirement: Git

	  i> Download & install Git (OS specific)
	  	* [Git](https://help.github.com/articles/set-up-git#platform-all)
     ii> Generate SSH Key
     	* [Tutorial](https://help.github.com/articles/generating-ssh-keys)

     	Is this section necessary?
    iii> You must be the member of 2tor/tests project 
         Ref: https://github.com/2tor/tests
     iv> Navigate to directory using GIT BASH where you want to download the project. Once, at specific path execute following command
         git clone https://github.com/2tor/tests.git OR
         git clone git@github.com:2tor/tests.git <Enter your Login credentials>	   
      v> Verify, project <tests> is downloaded     
	
4. Setup JARS
------------------------------------
Requirement: The appropriate JAR's will need to be copied to the machine specific ANT's classpath. <br />
**Note:** Path where BUILD.XML is located is referred as **BASEDIR**

      i> Copy all files from <Basedir/lib/antLib> to your machine specific ANT's lib folder, eg:
		 a> Windows: C:\Program Files\apache-ant-1.8.4-bin\apache-ant-1.8.4
		 b> MAC: /usr/share/ant/lib
		
5. Setup to execute on Chrome Browser
-------------------------------------
In order to execute Automation project using CHROME Browser, you need to make file on unix server available to read, write, etc.

     i> Using GIT, navigate to project <Basedir/lib/chromedriver_*>. '*' refers to machine/os (linux32 / linux64 / mac / win)
    ii> Execute chmod +x filename or chmod 777 filename so as to make the file executable
    * [Tutorial](http://selftechy.com/2011/08/17/running-selenium-tests-with-chromedriver-on-linux)
	   
6. Run Automation
-------------------------
Automation can run when above steps are followed in order.

     i> Open terminal
    ii> Checkout master
   iii> Navigate to folder containing build.xml
	iv> Run automation
	    _______________________________________________________________
		ant runsmoke -DantPrgrm=gu,usc -DantBrwsr=chrome,ff -DantOS=win 
	
	Applicable Parameters (Case Sensitive)
		1> Targets: runsmoke / runcritical / rundebug
			* Can accept only one target at a time
        2> antPrgrm: 
        	* gu
        	* unc
        	* usc 
        	* llm
        	* vac
        	* mpa 
        	* Program's delimiter: ","
		3> antBrwsr: 
			* ff
			* chrome
			* Browser's delimiter: ","
		4> antOS: 
			* win 
			* mac 
			* linux32
			* linux64
			* Only one OS name can be passed
    iii> Sequence of test run:
     	1> Target program <gu>
                a> chrome  b> ff
        2> Target program <usc>
                a> chrome  b> ff
	 iv> Each run will have a separate "reports" folder {Basedir}\reports\{program}_{browser}
      v> Test report (zip) folder is automatically mailed to recipients <mentioned in build.xml> once the execution is complete. 
	
-----------------------------------------------END--------------------------------------------------------------------------------------    
	
	
	 
	 