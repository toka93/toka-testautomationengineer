package Actions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
/**
 * TextActions class in Actions package 
 * contains 2 method to enter text in field and to get text of element
 * @author Toka.Ashraf
 *
 */
	
public class TextActions  {
	Logger log = LogManager.getLogger("TextActions");

	public void enterTextinField(WebElement element, String Text)

	{
		log.info("send text");
		element.sendKeys(Text);

	}

	public String getElementText(WebElement element)

	{
 
		log.info("get text of element");
		String Text = element.getText();

		return Text;
	}



}
