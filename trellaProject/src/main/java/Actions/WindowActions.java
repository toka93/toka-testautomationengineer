package Actions;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.StringTokenizer;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.assertthat.selenium_shutterbug.utils.web.ScrollStrategy;

/**
 * windowsActions class in Actions package contains methods to refresh window ,
 * to maximize window , to naviage back or forward ,
 * 
 * @author Toka.Ashraf
 *
 */
public class WindowActions {
	Logger log = LogManager.getLogger("AuthenticatingActions");
	String ScreenShotBasePath = System.getProperty("user.dir") + File.separator + "screenshots";

	public void scrolltoElement(WebDriver driver, WebElement element) {

		log.info("scroll horizontal to element");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", element);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(element));

	}

	// File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

	public String takeScreenshot(WebDriver driver, String name) {
		log.info("take  screenshot of page ");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH-mm");
		String filepath = null;
		File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		System.out.println(file.getName());
		try {
			File screenshotname = new File(ScreenShotBasePath + file.separator + "screenshots" + file.separator
					+ dtf.format(localDate) + file.separator + name + ".png");
			FileUtils.copyFile(file, screenshotname);

			filepath = screenshotname.toString();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return filepath;

	}

}
