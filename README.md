# README #

This README would normally document whatever steps are necessary to get your application up and running.

## Setup ##
- Import all the jar files in the project
- Gekodriver/Chromedriver is to be picked up from local path instead of the repository. Hence replace the path in this statement with path to your local machine: System.setProperty("webdriver.gecko.driver","C:\\selenium-java-3.4.0/geckodriver.exe");

## Test Execution Prequisits ##
- User should be already logged in to https://ashishtestspartez.atlassian.net/wiki

## Test 1: Creating a wiki page ##
- This test fails if message appears "Something went wrong. Please save your changes..". Possible cause is poor network connection or suddent interuption in network.