package UIPages;

import java.awt.AWTException;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends PageBase {
	
	

	public HomePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//*/a[contains(@href,'angularjs-protractor/banking')]/h2")
	WebElement BankingAngularButton;

	public void OpenBanking(WebDriver driver) {
		waitJS.waitAllRequest(driver);
		win.scrolltoElement(driver, BankingAngularButton);
		waitActions.waitForElementToBeClickable(driver, BankingAngularButton, 3);
		clickActions.clickOnElement(BankingAngularButton);
		waitJS.waitAllRequest(driver);

	}

	public void switchToNewTab(WebDriver driver) throws AWTException {
		String currentHandle = driver.getWindowHandle();
		Set<String> handles = driver.getWindowHandles();
		for (String actual : handles) {

			if (!actual.equalsIgnoreCase(currentHandle)) {
				driver.switchTo().window(actual);

			}
		}
	}

}