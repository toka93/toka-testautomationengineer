package test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import configuration.ConfigProperties;
import configuration.ExtentManager;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseConfiguration {
	protected static ExtentReports extent;
	public static ThreadLocal parentTest = new ThreadLocal();
	protected static ThreadLocal test = new ThreadLocal();

	String ExtentReport = config.getExtentReport();
	ExtentSparkReporter htmlReporter;
	Logger log = LogManager.getLogger(BaseConfiguration.class);
	static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	static LocalDate localDate = LocalDate.now();
	LocalTime time = LocalTime.now(); 

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH-mm");

	String HtmlReportPath = System.getProperty("user.dir") + File.separator + ExtentReport + File.separator
		+	File.separator +  dtf.format(localDate)  + File.separator	+ ExtentReport  + time.format(formatter) + ".html";
	public static WebDriver driver;
	static ConfigProperties config = new ConfigProperties();
	ITestResult result = null;
	String HomeURL = config.getApplicationUrl();
	String chroVersion = config.GetChromeVersion();
	String browser = config.getBrowser();
	String type = config.GetType();
	static String excel = config.getTestDataFile();

	static String HeadlessState = config.GetHeadlessState();
	static String downloadFilepath = System.getProperty("user.dir") + File.separator + excel + File.separator
			+ "DownloadsFiles" +File.separator +  dtf.format(localDate) ;

	@BeforeSuite
	public void beforeSuite() {
		extent = ExtentManager.createInstance(HtmlReportPath);
		ExtentSparkReporter htmlReporter = new ExtentSparkReporter("test-output/" + ExtentReport + ".html");
		extent.attachReporter(htmlReporter);

	}

	@BeforeClass
	public synchronized void beforeClass() throws MalformedURLException {

		ExtentTest parent = extent.createTest(getClass().getName());
		parentTest.set(parent);
		startDriver(browser);

	}

	public void startDriver(String browserName) throws MalformedURLException {
		if (type.equalsIgnoreCase("local")) {
			if (browserName.equalsIgnoreCase("chrome")) {
				WebDriverManager.chromedriver().version(chroVersion).setup();

				driver = new ChromeDriver(chromeOption());

			}

			else if (browserName.equalsIgnoreCase("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver(firefoxOption());

			}

			else if (browserName.equalsIgnoreCase("ie")) {
				WebDriverManager.iedriver().setup();
				driver = new InternetExplorerDriver();

			} else if (browserName.equalsIgnoreCase("safari")) {

				System.setProperty("webdriver.safari.noinstall", "true"); 
				driver = new SafariDriver();

			}

		} else if (type.equalsIgnoreCase("remote")) {

			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), chromeOption());

		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		driver.navigate().to(HomeURL);
	}

	public static FirefoxOptions firefoxOption() {
		FirefoxOptions option = new FirefoxOptions();
		option.addPreference("browser.tabs.remote.autostart", false);
		option.addPreference("browser.tabs.remote.autostart.1", false);
		option.addPreference("browser.tabs.remote.autostart.2", false);
		option.addPreference("browser.tabs.remote.force-enable", "false");

		return option;
	}

	public static ChromeOptions chromeOption() {
		ChromeOptions options = new ChromeOptions();
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default.content_settings.popups", 0);
		System.out.println("downlaod path" + downloadFilepath);
		chromePrefs.put("download.default_directory", downloadFilepath);
		options.setExperimentalOption("prefs", chromePrefs);
		options.addArguments("--ignore-certificate-errors");
		options.addArguments("--disable-web-security");
		options.addArguments("--allow-running-insecure-content");
		options.addArguments("--allow-insecure-localhost");
		options.addArguments("--no-sandbox");
		
		options.addArguments("--disable-features=VizDisplayCompositor");
		options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		LoggingPreferences loggingprefs = new LoggingPreferences();
		loggingprefs.enable(LogType.BROWSER, Level.ALL);
		options.setCapability(CapabilityType.LOGGING_PREFS, loggingprefs);
		options.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);

		if (HeadlessState.equalsIgnoreCase("true")) {
			options.addArguments("--headless");

		} else if (HeadlessState.equalsIgnoreCase("false")) {

		}

		return options;

	}

	@AfterClass
	public void afterMethod() {

		
		
		  driver.close(); driver.quit();
		 
		 

	}

	@AfterSuite(alwaysRun = true)
	public void endSuite() {
		log.info("end report in after class");
		extent.flush();
	}

}
