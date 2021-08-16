package test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestListener;
import org.testng.ITestResult;

import configuration.Log4j2Configurations;

public class ListenerTest implements ITestListener {
	Logger log = LogManager.getLogger(Log4j2Configurations.class);

	
	@Override		
    public void onTestFailure(ITestResult result) {					
        // TODO Auto-generated method stub	
		log.info("The test "+ result.getName() +" failed and the exception thrown is : " + result.getThrowable());
        		
    }	
	
	
	
}
