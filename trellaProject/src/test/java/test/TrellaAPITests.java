package test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Models.TrellaResponseModel;
import Models.TrellaResponseModel.base;
import configuration.ConfigProperties;
import configuration.Log4j2Configurations;
import excel.ExcelTestParser;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import net.minidev.json.JSONObject;

@Listeners(test.ListenerTest.class)
public class TrellaAPITests extends BaseConfiguration {
	Logger log = LogManager.getLogger(Log4j2Configurations.class);
	static ConfigProperties config = new ConfigProperties();
	String excel = config.getTestDataFile();
	private ExcelTestParser excelTestParser;
	ExtentSparkReporter htmlReporter;
	ExtentTest child;
	String email = config.getAppuser();

	// Excel full path
	String excelpath = System.getProperty("user.dir") + File.separator + excel + File.separator + excel + ".xlsx";

	String className = this.getClass().getSimpleName();
	String methodname;

	@BeforeClass
	public synchronized void beforeClass() throws MalformedURLException {

		ExtentTest parent = extent.createTest(getClass().getName());
		parentTest.set(parent);

	}

	@BeforeMethod(alwaysRun = true)
	public void setup(Method method) throws IOException, InterruptedException {
		// startDriver(browser);
		log.info("send browser name as a parameter ");
		test.set(child);
		log.info("start driver browser in before method");
		excelTestParser = new ExcelTestParser(excelpath);

	}

	@Test
	public void ViewListOfShipmentsFiltered(Method method) throws IOException {
		try {
			methodname = method.getName();

			log.info("get class name to use in Excel reader/ sheet name is  : " + className);
			child = ((ExtentTest) parentTest.get()).createNode(methodname);
			log.info("View List Of Shipments Filtered");
			child.info("View List Of Shipments Filtered");
			String APIURL = excelTestParser.getCellValue(className, methodname, "URL").toString();
			log.info("APIURL is : " + APIURL);

			String lngValue = excelTestParser.getCellValue(className, methodname, "lng").toString();
			log.info("lng is : " + lngValue);
			String latValue = excelTestParser.getCellValue(className, methodname, "lat").toString();
			log.info("lat is : " + latValue);

			RestAssured.baseURI = APIURL;
			RequestSpecification request = RestAssured.given();
			request.header("Authorization", config.getAppuser());

			request.queryParam("lng", lngValue).queryParam("lat", latValue);

			child.info("Add Paramter Lng with value : " + lngValue);

			child.info("Add Paramter Lat with value : " + latValue);
			Response response = request.get();
			ResponseBody body = response.getBody();

			int statusCode = response.getStatusCode();

			log.info("Response is back with status code : " + statusCode);

			log.info("Response body: " + response.body().asString());
			base[] responseBody = body.as(TrellaResponseModel.base[].class);
			Assert.assertTrue(statusCode == 200);
			child.pass("Response is back with status code : " + statusCode);
			Assert.assertTrue(responseBody.length == 3);
			log.info("Response length : " + responseBody.length);

			child.pass("request passed ::::: and response contains 3 shipments");
			log.info("request passed ::::: and response contains 3 shipments");

		} catch (Exception e) {

			child.fail("test failed");
			log.error("test failed and exception is :" + e);
		}
	}

	@Test
	public void ViewListOfShipments(Method method) throws IOException {
		try {
			methodname = method.getName();

			log.info("get class name to use in Excel reader/ sheet name is  : " + className);
			child = ((ExtentTest) parentTest.get()).createNode(methodname);
			log.info("View List Of Shipments");
			child.info("View List Of Shipments");
			String APIURL = excelTestParser.getCellValue(className, methodname, "URL").toString();
			log.info("APIURL is : " + APIURL);

			RestAssured.baseURI = APIURL;
			RequestSpecification request = RestAssured.given();

			JSONObject requestParams = new JSONObject();

			request.header("Authorization", config.getAppuser());
			request.body(requestParams.toJSONString());
			Response response = request.get();

			int statusCode = response.getStatusCode();
			log.info("request is back with status code : " + statusCode);

			log.info("Response body: " + response.body().asString());
			ResponseBody body = response.getBody();
			base[] responseBody = body.as(TrellaResponseModel.base[].class);
			Assert.assertTrue(statusCode == 200);
			child.pass("request is back with status code : " + statusCode);

			Assert.assertTrue(responseBody.length == 7);
			child.pass("Response passed ::::: and response contains 7 shipments");
			log.info("Response passed ::::: and response contains 7 shipments");

		} catch (Exception e) {

			child.fail("test failed");
			log.error("test failed and exception is :" + e);
		}

	}

	@AfterMethod
	public void afterMethod() {

		log.info("test finished");

	}

	@AfterMethod
	public void stopDriver(ITestResult res) throws IOException {

		log.info("get status of test just run");
		if (res.getStatus() == ITestResult.SUCCESS) {
			log.info("test passes ");
			child.pass(methodname + " Passed.");
		} else if (res.getStatus() == ITestResult.FAILURE) {
			log.info("test failed ");
			child.fail(methodname + " Failed.");
		} else if (res.getStatus() == ITestResult.SKIP) {
			log.info("test skipped ");
			child.skip(methodname + " Skipped.");
		} else {
			log.info("test finished with status other than (pass,fail,skip) ");
			child.log(Status.FAIL, "Test is Errored ,Check the Logs for More Info");
		}
		log.info("close driver browser in after method");
	}

}