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
	
	
Install Maven 3.0.5 or higher
---------------------------
Requirement: Maven 3.0.5

	  i> Installing Maven on Windows
		* Enter the URL: http://maven.apache.org/download.cgi
		* Download latest Binary zip (apache-maven-3.0.5-bin.zip)
		* Unzip and save it to your C:\ directory
                * Create a JAVA_HOME System Variable
                * Create an M2_HOME System Variable
                * Add %JAVA_HOME%\bin;%M2_HOME%\bin; to your System Path
                    * Follow: Installation Instructions / Windows 2000/XP
                      http://maven.apache.org/download.cgi
     ii> Installing Maven on Linux / Mac OS X
		* Maven is already installed on Linux / Mac OS X, and so it is not necessary to install it yourself.		 	
		* If you absolutely want to install it, though, the best way would be to install it through MacPorts (using sudo port install apache-ant)
		* However, to install it manually, follow:
                    * Follow: Installation Instructions / Unix-based Operating Systems
                      http://maven.apache.org/download.cgi || http://www.mkyong.com/maven/how-to-install-maven-in-ubuntu/
    iii> Verify Maven installed correctly 
	     a> open terminal
		 b> mvn -version
		    ____________________________________________________
		    Apache Maven 3.0.5
			

Ubuntu (Linux) install instructions for Java and other dependencies
-------------------------------------------------------------------

    sudo add-apt-repository ppa:webupd8team/java
    sudo apt-get update
    sudo apt-get install oracle-java7-installer libgeronimo-activation-1.1-spec-java

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
	

Setup Property Files
--------------------
**Note:** 'loginDetails' property file needs to be placed at src/test/resources folder.  Please contact Benjamin/Somesh for a copy. This will eventually be on S3. <br />


Setup to execute on CHROME Browser
----------------------------------
Requirement: Chrome browser installed. <br />
**Note:** Ubuntu(Linux) installing instructions for Chrome and Firefox
         
	sudo apt-get install chromium-browser firefox

Run Automation
--------------
Automation can run when above steps are followed in order.

      i> Open Terminal
     ii> Navigate to *BASEDIR* (Path where pom.xml is located)
    iii> Run Automation
               ______________________________________________________________________________________________________
            a> mvn clean test -DmvnSuite=critical -DmvnUrl={gu-msn} -DmvnProgram=gu-msn -DmvnBrwsr=chrome -DmvnOS=win 
		
            b> debugSmoke takes 1 extra parameter i.e group name/s={The list of groups mentioned in Confluence Page to run separated by comma}
               ________________________________________________________________________________________________________________________________	
               mvn clean test -DmvnSuite=debug -DmvnGrp=SystemCompatibility -DmvnUrl={gu-msn} -DmvnProgram=gu-msn -DmvnBrwsr=chrome -DmvnOS=win
	
        Applicable Parameters (Case Sensitive)
        1> mvnSuite: critical / smoke / regression / debug / criticalData
                * Only one Suite to be passed
        2> mvnUrl:
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
        	* Only one PROGRAM URL to be passed
        3> mvnProgram: 
                * gu-msn || unc-mba || usc-mat || wu-llm || usc-msw || unc-mpa || au-mir || gwu-mph
                * Only one program domain name to be passed
        4> mvnBrwsr: 
                * ff || chrome
                * Only one browser to be passed
        5> mvnOS: 
                * win || mac || linux32 || linux64
                * Only one OS name to be passed
        6> mvnGrp:
                * n number of group/s mentioned in DebugSmoke confluence page can be passed delimited by ","
     iv> Reports are saved in '{Basedir}\target\surefire-reports\' folder
     	

## Installing Xvfb and dependencies

    sudo apt-get install xvfb xfonts-100dpi xfonts-75dpi xfonts-scalable xfonts-cyrillic xserver-xorg-core x11-xkb-utils

