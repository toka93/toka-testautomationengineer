package Actions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 * ClickActions class in Actions Package contains method such as (click on
 * element , click on elment using enter , click on elements using actions ,
 * double click on elment right click on element , drag and drop element , hover
 * and click on element , Click on Esc using actions ,
 * 
 * @author Toka.Ashraf
 *
 */

public class ClickActions {
	Logger log = LogManager.getLogger("ClickActions");

	public void clickOnElement(WebElement element) {

		log.info("click on element");
		element.click();

	}

	

}
