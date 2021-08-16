package Actions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * SelectActions class in Actions package contains methods to select by index
 * and select by value and clear selection and select by visible text and check
 * if element is checked or not
 * 
 * @author Toka.Ashraf
 *
 */

public class SelectActions {
	Logger log = LogManager.getLogger("SelectActions");

	public void selectOptionByIndex(int index, WebElement element) {
		log.info("get list of elements");
		Select list = new Select(element);
		log.info("select by index" + index);
		list.selectByIndex(index);

	}

	public void selectOptionByVisibleText(String value, WebElement element) {
		log.info("select by element");
		Select list = new Select(element);
		list.selectByVisibleText(value);

	}

}
