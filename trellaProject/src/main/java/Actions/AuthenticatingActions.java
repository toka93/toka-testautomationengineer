package Actions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * this is AuthenticatingActions class in Actions Package which contains all
 * methods needed to interact with alerts like (Accept Alert , Dismiss Alert ,
 * get text from alert , send text to alert , accept alert and click ok )
 * 
 * @author Toka.Ashraf
 *
 */
public class AuthenticatingActions {
	Logger log = LogManager.getLogger("AuthenticatingActions");

	ClickActions clickAC = new ClickActions();
	WaitActions waitAC = new WaitActions();

	public void acceptAlert(WebDriver driver) throws InterruptedException {
		log.info("Accept Alert");
		Alert Completealert = driver.switchTo().alert();
		String alertText = Completealert.getText();
		System.out.println("text in alert" + alertText);
		Completealert.accept();

	}

	
	public String getAlertText(WebDriver driver) {
		log.info("get Alert text ");
		String Text = driver.switchTo().alert().getText();
		log.info("text is : " + Text);
		return Text;
	}

	
}
