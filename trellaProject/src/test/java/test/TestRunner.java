package test;

import org.testng.TestListenerAdapter;
import org.testng.TestNG;

public class TestRunner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		TestListenerAdapter tla = new TestListenerAdapter();
		TestNG testng = new TestNG();
		testng.setTestClasses(new Class[] { 
				
				TrellaAPITests.class ,
				TrellaUITests.class
				 
				
		});
		testng.addListener(tla);
		testng.run();

		if (testng.hasFailure()) {
			System.out.println("Test failed.");
			System.exit(1);
		} else {
			System.out.println("Test finished successfully.");
			System.exit(0);
		}

	}

}
