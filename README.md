# Automation Environment Installation/Setup

Install Core Dependencies
-------------------------

* Download & Install JDK 1.7(Java SE Development Kit 7u9)
*  Windows
 * [Win 32-Windows x86](http://www.oracle.com/technetwork/java/javase/downloads/jdk7u9-downloads-1859576.html)
 * [Win 64-Windows x64](http://www.oracle.com/technetwork/java/javase/downloads/jdk7u9-downloads-1859576.html)
 * [Set Path](http://java.com/en/download/help/path.xml)

*  Mac & Linux
 * [Mac OS X](http://www.oracle.com/technetwork/java/javase/downloads/jdk7u9-downloads-1859576.html)
 * Path Auto configured		 

Verify JDK is configured correctly
 * java -version
 * javac -version	
	
	
Install ANT 1.8.4 or higher
---------------------------
Requirement: ANT 1.8.4

	  i> Installing Ant on Windows
		* Enter the URL: http://ant.apache.org/bindownload.cgi.
		* On the Apache Ant Project page, find the heading 'Current Release of Ant.'
		* Select apache-ant-1.8.4-bin.zip [PGP] [SHA1] [SHA512] [MD5]
		* Click Save to unzip and save it to your C:\ directory 
		* Set Environment Variable; follow 
		  http://www.daimi.au.dk/~hbc/technical/ant/setup.html
     ii> Installing Ant on Mac OS X
		* Ant is already installed on Mac OS X, and so it is not necessary to install it yourself.		 	
		* If you absolutely want to install it, though, the best way would be to install it through MacPorts (using sudo port install apache-ant)
		* However, to install it manually, you would need to follow 2nd Answers
		  http://stackoverflow.com/questions/3222804/how-can-i-install-apache-ant-on-mac-os-x
    iii> Verify ANT installed correctly 
	     a> open terminal
		 b> ant -version
		    ____________________________________________________
		    Apache Ant(TM) version 1.8.4 compiled on May 22 2012
			

Ubuntu (Linux) install instructions for Java, Ant, and other dependencies
-------------------------------------------------------------------------

    sudo add-apt-repository ppa:webupd8team/java
    sudo apt-get update
    sudo apt-get install oracle-java7-installer libgeronimo-activation-1.1-spec-java ant1.7 ant1.7-optional ant-contrib

Install Git
-----------
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
----------
Requirement: The appropriate JAR's need to be present in specific ANT's classpath. <br />
**Note:** Path where BUILD.XML is located is referred as **BASEDIR**

      i> Copy all files from <Basedir/lib/antLib> to your machine specific ANT's lib folder, eg:
		 a> Windows: C:/Program Files/apache-ant-1.8.4-bin/apache-ant-1.8.4/lib
		 b> MAC: /usr/share/ant/lib
		 c> Linux32/64: /usr/share/java/
		
Setup Property Files
--------------------
**Note:** 'loginDetails' property file needs to be placed at /tests/src folder.  Please contact Benjamin for a copy. This will eventually be on S3. <br />


Setup to execute on CHROME Browser
----------------------------------
Requirement: Chrome browser installed. <br />
**Note:** Ubuntu(Linux) installing instructions for Chrome and Firefox
         
	sudo apt-get install chromium-browser firefox

In order to execute Automation project using Chrome Browser, you need to make file on unix server available to read, write, etc.

      i> Using GIT, navigate to project <Basedir/lib/chromedriver_*>. '*' refers to machine/os (linux32 / linux64 / mac / win)
     ii> Execute chmod +x filename or chmod 777 filename so as to make the file executable
       * [Tutorial](http://selftechy.com/2011/08/17/running-selenium-tests-with-chromedriver-on-linux)
	   

Setup to execute on INTERNET EXPLORER
-------------------------------------
**WARNING:** This method of starting the IE driver is deprecated and will be removed in selenium 2.26. <br />
Please download the OS specific IEDriverServer.exe from http://code.google.com/p/selenium/downloads/list and ensure that it is in your PATH.
	   

Run Automation
--------------
Automation can run when above steps are followed in order.

      i> Open Terminal
     ii> Navigate to *BASEDIR*
	iii> Run Automation
	       _______________________________________________________________________________________________
		a> ant runsmoke/runcritical -DantUrl={gu-msn},{usc-mat} -DantBrwsr=chrome,ff -DantOS=win 
		
		b> debugSmoke takes 1 extra paramter i.e group name/s={The list of groups mentioned in ConfluencePage to run separated by comma}
		   ______________________________________________________________________________________________________________________	
		   ant rundebug -DantGrp=ActvtsVrfctn,ActvtsSbmtQz -DantUrl={gu-msn},{usc-mat} -DantBrwsr=chrome,ff -DantOS=win 
	
	Applicable Parameters (Case Sensitive)
		1> Targets: runsmoke / runcritical / rundebug
			* Can accept only one target at a time
        2> antUrl:
        	* gu-msn:  https://2gu.nursing.georgetown.edu || https://www-gu-msn-lms-stg.2u.com || 
			   https://www-gu-msn-lms-sb[01-10]-qa.2u.com || https://gu-msn-lms-standalone-prod.2u.com					  
        	* unc-mba: https://www.2nc.unc.edu || https://www-unc-mba-lms-stg.2u.com || 
			   https://www-unc-mba-lms-sb[01-10]-qa.2u.com || https://unc-mba-lms-standalone-prod.2u.com
        	* usc-mat: https://www.2sc.usc.edu || https://www-usc-mat-lms-stg.2u.com || 
			   https://www-usc-mat-lms-sb[01-10]-qa.2u.com || https://usc-mat-lms-standalone-prod.2u.com
        	* wu-llm:  https://2law.onlinelaw.wustl.edu || https://www-wu-llm-lms-stg.2u.com || 
			   https://www-wu-llm-lms-sb[01-10]-qa.2u.com || https://wu-llm-lms-standalone-prod.2u.com
        	* usc-msw: https://www.vac.usc.edu || https://www-usc-msw-lms-stg.2u.com || 
			   https://www-usc-msw-lms-sb[01-10]-qa.2u.com || https://usc-msw-lms-standalone-prod.2u.com
        	* unc-mpa: https://2sg.onlinempa.unc.edu || https://www-unc-mpa-lms-stg.2u.com || 
			   https://www-unc-mpa-lms-sb[01-10]-qa.2u.com || https://unc-mpa-lms-standalone-prod.2u.com
			* au-mir:  https://2ir.ironline.american.edu || https://www-au-mir-lms-stg.2u.com || 
			   https://www-au-mir-lms-sb[01-10]-qa.2u.com || https://au-mir-lms-standalone-prod.2u.com
        	* gwu-mph: https://2gw.publichealthonline.gwu.edu || https://www-gwu-mph-lms-stg.2u.com || 
			   https://www-gwu-mph-lms-sb[01-10]-qa.2u.com || https://gwu-mph-lms-standalone-prod.2u.com
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
		5> antGrp:
			* n number of group/s mentioned in DebugSmoke confluence page can be passed delimited by ","
     iv> Flow of TEST:
     	1> {gu-msn}            
			   a> chrome  b> ff			
        2> {usc-mat}            
			   a> chrome  b> ff			
	  v> Reports are saved in '{Basedir}\reports' folder


## Installing Xvfb and dependencies

    sudo apt-get install xvfb xfonts-100dpi xfonts-75dpi xfonts-scalable xfonts-cyrillic xserver-xorg-core x11-xkb-utils

