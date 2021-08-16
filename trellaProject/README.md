#Running Configuration :
##Config.properties
 contains all the environment variables which can be changed in it :
####-Application URl 
####-user_name 
####-Browser 
####-ChromeVersion 
####-Type : local 

Steps to run :
Prerequisite:  
-Install Java JVM 1.8  
-Install Chrome Browser
using the chrome browser installed and in case browser version is updated : you can change it from Config file (Version) ,
 just make sure it exists here or use the nearest version 
https://chromedriver.storage.googleapis.com/

##Steps:
####1.Open CMD
####2.CD to the directory contains the project
####3.java -jar Trella.jar
-------------------------------------------------------------------------------------------------------------------------------------------------------
##After Every Run : 
####Report ,Log and Screen-shots for every test will be generated.
-------------------------------------------------------------------------------------------------------------------------------------------------------
##Data File:

####The project depends on Data from Excel file exists in the TestData Folder.
--------------------------------------------------------------------------------------------------------------------------------------------------------
you can also import the project as maven project on the IDE and add java to the project build path then build the project and run each class on its own 
(TrellaUITests and TrellaAPITests)
--TrellaUITests class contsins 3 cases that as mentioned in the task run sequentially.

 

