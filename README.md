# Automation Environment Installation

Install Core Dependencies
----------------------------

* [Download](http://www.oracle.com/technetwork/java/javase/downloads/java-se-jdk-7-download-432154.html) & Install JDK 1.7 or higher
*  Windows 
 * [Set Path](http://java.com/en/download/help/path.xml)

*  Mac & Linux
 * Auto configured		 

Verify JDK is configured correctly
	java -version
	javac -version
	        
Install ANT 1.8 or higher (Windows Only)
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
		   
Install Git
-------------------
Requirement: Git

	  i> Download & install Git (OS specific)
	  	* [Git](https://help.github.com/articles/set-up-git#platform-all)
     ii> Generate SSH Key
     	* [Tutorial](https://help.github.com/articles/generating-ssh-keys)     	
    iii> Get Automation project locally
		a> You must be the member of 2tor/tests project
        b> Fork [Automation](https://github.com/2tor/tests) project using your login credentials
        c> Clone project to your local machine, run 
		   git clone https://github.com/2tor/tests.git OR
           git clone git@github.com:2tor/tests.git
     iv> Verify, project is downloaded     
	
Setup JARS
------------------------------------
Requirement: The appropriate JAR's are need to be copied to machine specific ANT's classpath. <br />
**Note:** Path where BUILD.XML is located is referred as **BASEDIR**

      i> Copy all files from <Basedir/lib/antLib> to your machine specific ANT's lib folder, eg:
		 a> Windows: C:\Program Files\apache-ant-1.8.4-bin\apache-ant-1.8.4
		 b> MAC: /usr/share/ant/lib
		
Setup to execute on Chrome Browser
-------------------------------------
In order to execute Automation project using CHROME Browser, you need to make file on unix server available to read, write, etc.

     i> Using GIT, navigate to project <Basedir/lib/chromedriver_*>. '*' refers to machine/os (linux32 / linux64 / mac / win)
    ii> Execute chmod +x filename or chmod 777 filename so as to make the file executable
    * [Tutorial](http://selftechy.com/2011/08/17/running-selenium-tests-with-chromedriver-on-linux)
	   
Run Automation
-------------------------
Automation can run when above steps are followed in order.

      i> Open terminal
     ii> Navigate to folder <BASEDIR>
	iii> Run automation
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

	
	
	 
	 