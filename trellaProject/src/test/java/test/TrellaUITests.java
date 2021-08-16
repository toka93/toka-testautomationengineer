package test;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.ThreadLocalRandom;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Actions.Helpers;
import Actions.WindowActions;
import UIPages.CustomerPage;
import UIPages.BankingPage;
import UIPages.HomePage;
import UIPages.OpenAccountsPage;
import configuration.ConfigProperties;
import configuration.Log4j2Configurations;
import excel.ExcelTestParser;

@Listeners(test.ListenerTest.class)
public class TrellaUITests extends BaseConfiguration {
	Logger log = LogManager.getLogger(Log4j2Configurations.class);
	static ConfigProperties config = new ConfigProperties();
	static String url = config.getApplicationUrl();
	String excel = config.getTestDataFile();
	String browser = config.getBrowser();
	private ExcelTestParser excelTestParser;
	ExtentSparkReporter htmlReporter;
	ExtentTest child;
	String email = config.getAppuser();

	WindowActions wiActions = new WindowActions();
	// Excel full path
	String excelpath = System.getProperty("user.dir") + File.separator + excel + File.separator + excel + ".xlsx";

	String screenshotPath = "path";
	String className = this.getClass().getSimpleName();
	String methodname;
	HomePage homePage;
	BankingPage bankingPage;
	CustomerPage CustomerPage;
	OpenAccountsPage openAccountPage;

	WindowActions winAc = new WindowActions();
	Helpers helpers = new Helpers();
	

	String fname = null;
	String lname = null;
	String pcode = null;
	String custID = null;
	int randomNum = ThreadLocalRandom.current().nextInt(1, 3 + 1);
	String accID = null;

	@BeforeMethod(alwaysRun = true)
	public void setup(Method method) throws IOException, InterruptedException {
		// startDriver(browser);
		log.info("send browser name as a parameter ");
		test.set(child);
		log.info("start driver browser in before method");
		excelTestParser = new ExcelTestParser(excelpath);
		homePage = new HomePage(driver);
		bankingPage = new BankingPage(driver);
		CustomerPage = new CustomerPage(driver);
		openAccountPage = new OpenAccountsPage(driver);
	}

	@Test(priority = 1)
	public void AddCustomerTest(Method method) throws AWTException, InterruptedException, IOException {
		try {
			methodname = method.getName();
			log.info("get class name to use in Excel reader/ sheet name is  : " + className);
			child = ((ExtentTest) parentTest.get()).createNode(methodname);
			log.info("Add Customer Test ");
			child.info("Add Customer Test ");
			String name = helpers.getAlphaNumericString(3);
			log.info("Value Added For Fields : " + name);

			fname = excelTestParser.getCellValue(className, methodname, "First Name").toString() + "_" + name;
			log.info("First Name is : " + fname);

			lname = excelTestParser.getCellValue(className, methodname, "Last Name").toString() + "_" + name;
			log.info("Last Name is : " + lname);

			pcode = excelTestParser.getCellValue(className, methodname, "Postal Code").toString();
			log.info("Postal Code : " + pcode);

			homePage.OpenBanking(driver);
			log.info("Open Banking Link ");
			child.info("Open Banking Link ");
			homePage.switchToNewTab(driver);
			log.info("switch To Banking Tab ");
			child.info("switch To Banking Tab ");
			bankingPage.OpenBankManagerLogin(driver);
			log.info("Open Bank Manager Login ");
			child.info("Open Bank Manager Login ");
			bankingPage.OpenAddCustomer(driver);
			log.info("Open Add Customer");
			child.info("Open Add Customer");
			CustomerPage.addCustomer(fname, lname, pcode, driver);
			log.info("Add Customer First Name : " + fname);
			child.info("Add Customer First Name : " + fname);

			custID = CustomerPage.getCustomerIDandAcceptAlert(driver);
			log.info("Customer ID : " + custID);
			child.info("Customer ID : " + custID);

			CustomerPage.clickOnCustomersTab();
			boolean flag = CustomerPage.GetCustomerValues(custID, driver, fname, lname, pcode);
			Assert.assertTrue(flag);

			child.pass("Customer Appear in the right order and all data appear as eneterd");
			log.info("Customer Appear in the right order and all data appear as eneterd");

		} catch (Exception e) {

			child.fail("test failed");
			log.error("test failed and exception is :" + e);
		}
	}

	@Test(priority = 2)
	public void CheckCustomerAccount(Method method) throws AWTException, InterruptedException, IOException {
		try {
			methodname = method.getName();
			log.info("get class name to use in Excel reader/ sheet name is  : " + className);
			child = ((ExtentTest) parentTest.get()).createNode(methodname);
			log.info("Check Customer Account");
			child.info("Check Customer Account");

			openAccountPage.OpenOpenAccount(driver);
			log.info("Open Banking Link ");
			child.info("Open Banking Link ");
			String name = fname + " " + lname;
			openAccountPage.chooseUser(name);
			log.info("choose User : " + name);
			child.info("choose User : " + name);
			openAccountPage.chooseCurrency(randomNum,driver);
			log.info("choose Currency : " + randomNum);
			child.info("choose Currency : " + randomNum);
			openAccountPage.ClickOnProcess();
			log.info("Click On Process ");
			child.info("Click On Process ");
			accID = openAccountPage.getAccountIDandAcceptAlert(driver);
			log.info("Account is updated and Account ID is :  " + accID);
			child.info("Account is updated and Account ID is :  " + accID);
			CustomerPage.clickOnCustomersTab();
			log.info("Open Customers tab ");
			child.info("Open Customers tab");
			boolean flag = CustomerPage.checkAccountNumber(custID, accID, driver);
			Assert.assertTrue(flag);
			child.pass("Account number : " + accID + " appear in customer page in customer Id " + custID);
			log.info("Account number : " + accID + " appear in customer page in customer Id " + custID);

		} catch (Exception e) {

			child.fail("test failed");
			log.error("test failed and exception is :" + e);
		}
	}

	@Test(priority = 3)
	public void DeleteCustomer(Method method) throws AWTException, InterruptedException, IOException {
		try {
			methodname = method.getName();
			log.info("get class name to use in Excel reader/ sheet name is  : " + className);
			child = ((ExtentTest) parentTest.get()).createNode(methodname);
			log.info("Delete Customer");
			child.info("Delete Customer");
			CustomerPage.DeleteCustomer(custID, driver);
			child.info("Delete customer  ");
			log.info("Delete customer  ");
			int countOfRows = CustomerPage.getNumberOFRows(driver);
			Assert.assertTrue(countOfRows+1==Integer.parseInt(custID));
			child.pass("Customer deleted ");
			log.info("Customer deleted ");

			
		} catch (Exception e) {

			child.fail("test failed");
			log.error("test failed and exception is :" + e);
		}
	}

	@AfterMethod
	public void stopDriver(ITestResult res) throws IOException {

		screenshotPath = winAc.takeScreenshot(driver, methodname);
		log.info("get status of test just run");
		if (res.getStatus() == ITestResult.SUCCESS) {
			log.info("test passes and screenshot is taken before driver closes in path " + screenshotPath);
			child.pass(methodname + " Passed.");
		} else if (res.getStatus() == ITestResult.FAILURE) {
			log.info("test failed and screenshot is taken before driver closes in path " + screenshotPath);
			child.fail(methodname + " Failed.");
		} else if (res.getStatus() == ITestResult.SKIP) {
			log.info("test skipped and screenshot is taken before driver closes in path " + screenshotPath);
			child.skip(methodname + " Skipped.");
		} else {
			log.info(
					"test finished with status other than (pass,fail,skip) and screenshot is taken before driver closes in path "
							+ screenshotPath);
			child.log(Status.FAIL, "Test is Errored ,Check the Logs for More Info");
		}
		child.addScreenCaptureFromPath(screenshotPath);
		log.info("close driver browser in after method");
	}

}
