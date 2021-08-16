package Actions;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * ElementActions class in Actions package contains methods to check if element
 * is displayed , check if element is enabled , get count of list of elements ,
 * get element attribute , get element color
 * 
 * @author Toka.Ashraf
 *
 */

public class ElementActions {
	ClickActions clickAc = new ClickActions();
	TextActions textAc = new TextActions();

	Logger log = LogManager.getLogger("ElementsActions");

	// only method needs by
	public int getCountOfElements(By element, WebDriver driver) {
		int x = 0;
		List<WebElement> elements = driver.findElements(element);
		log.info(elements.toString());
		System.out.println(elements.toString());
		x = elements.size();
		log.info("the size of list of elements : " + x);

		return x;
	}

	public List<WebElement> getListOFWebElements(WebElement el, String locator, WebDriver driver) {
		String str = el.toString();
		String[] listString = null;
		if (str.contains(locator))
			listString = str.split(locator + ":");
		String last = listString[1].trim();
		String LocatorValue = last.substring(0, last.length() - 1);
		System.out.println(LocatorValue);

		By by = null;

		by = By.xpath(LocatorValue);

		List<WebElement> elementsList = driver.findElements(by);

		return elementsList;

	}

	public String GetTextOfElementfromListOFElementsByIndex(WebElement el, String locator, WebDriver driver, int index)

	{

		List<WebElement> elements = getListOFWebElements(el, locator, driver);

		WebElement elementToBeClicked = elements.get(index);

		String txt = textAc.getElementText(elementToBeClicked);
		return txt;

	}

}
