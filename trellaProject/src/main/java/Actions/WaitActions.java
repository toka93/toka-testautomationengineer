package Actions;

import java.util.List;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * WaitActions class in Actions package contains all wait metods ; wait for
 * element to be visible , to be clickable, wait for frame and switch to it ,
 * wait for alert
 * 
 * @author Toka.Ashraf
 *
 */

public class WaitActions {

	Logger log = LogManager.getLogger("WaitAcions");

	private static WebDriver jsWaitDriver;
	private static WebDriverWait jsWait;
	private static JavascriptExecutor jsExec;

	public void waitForElementToBeClickable(WebDriver driver, WebElement element, int timeoutInSeconds) {

		log.info("wait for element to be clickable");
		WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);

		wait.until(ExpectedConditions.elementToBeClickable(element));

	}

}
