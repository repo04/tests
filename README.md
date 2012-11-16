# Automation Environment Installation

Install Core Dependencies
----------------------------

* Download & Install JDK 1.7(Java SE Development Kit 7u9) or higher
*  Windows
 * [Win 32-Windows x86](http://www.oracle.com/technetwork/java/javase/downloads/jdk7u9-downloads-1859576.html)
 * [Win 64-Windows x64](http://www.oracle.com/technetwork/java/javase/downloads/jdk7u9-downloads-1859576.html)
 * [Set Path](http://java.com/en/download/help/path.xml)

*  Mac & Linux
 * [Mac OS X](http://www.oracle.com/technetwork/java/javase/downloads/jdk7u9-downloads-1859576.html)
 * [Linux 32-Linux x86](http://www.oracle.com/technetwork/java/javase/downloads/jdk7u9-downloads-1859576.html)
 * [Linux 64-Linux x64](http://www.oracle.com/technetwork/java/javase/downloads/jdk7u9-downloads-1859576.html) 
 * Path Auto configured		 

Verify JDK is configured correctly
 * java -version
 * javac -version	
	
	        
Install ANT 1.8 or higher
----------------------------
Requirement: ANT 1.8 or higher

	  i> Installing Ant on Windows
		* Enter the URL: http://ant.apache.org/bindownload.cgi.
		* On the Apache Ant Project page, find the heading Current Release of Ant.
		* Select apache-ant-1.8.4-bin.zip [PGP] [SHA1] [SHA512] [MD5]
		* Click Save to unzip and save it to your C:\ directory 
		* Set Environment Variable; follow from point 4(http://omrudi.wordpress.com/2008/11/08/how-to-install-ant-in-windows-xp)
     ii> Installing Ant on Linux
	    * Ant is already installed on Linux, and so it is not necessary to install it yourself.
		* If not, please follow (http://www.rndblog.com/how-to-install-ant-on-linux/) 
    iii> Installing Ant on Mac OS X
		* Ant is already installed on Mac OS X, and so it is not necessary to install it yourself.		 	
		* If you absolutely want to install it, though, the best way would be to install it through MacPorts (using sudo port install apache-ant)
		* However, to install it manually, you would need to follow 2nd Answers
		  http://stackoverflow.com/questions/3222804/how-can-i-install-apache-ant-on-mac-os-x
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

      i> Open Terminal
     ii> Navigate to <BASEDIR>
	iii> Run Automation
	       _______________________________________________________________________________________________
		a> ant runsmoke/runcritical -DantPrgrm=gu,usc -DantEnv=stgng,prod -DantBrwsr=chrome,ff -DantOS=win 
		
		b> debugSmoke takes 1 extra paramter i.e group name/s={The list of groups mentioned in ConfluencePage to run separated by comma}
		   ______________________________________________________________________________________________________________________	
		   ant rundebug -DantGrp=ActvtsVrfctn,ActvtsSbmtQz -DantPrgrm=gu,usc -DantEnv=stgng,prod -DantBrwsr=chrome,ff -DantOS=win 
	
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
		3> antEnv: 
			* qa
			* prod
			* stgng
			* Environment's delimiter: ","
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
     iv> Flow of TEST:
     	1> gu
            a> stgng 
			   a> chrome  b> ff
			b> prod
			   a> chrome  b> ff
        2> usc
            a> stgng 
			   a> chrome  b> ff
			b> prod
			   a> chrome  b> ff
	  v> Each run will have a separate "reports" folder {Basedir}\reports\{program}_{env}_{browser}
     vi> Test report (zip) folder is automatically mailed to recipients <mentioned in build.xml> once the execution is complete. 

	
	
	 
	 