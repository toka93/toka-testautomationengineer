package UIPages;

import java.awt.AWTException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Actions.ClickActions;
import Actions.ElementActions;
import Actions.TextActions;
import Actions.WaitActions;
import Actions.WindowActions;

public class BankingPage extends PageBase {

	WaitActions waitActions = new WaitActions();
	ClickActions clickActions = new ClickActions();

	public BankingPage(WebDriver driver) {
		super(driver);

	}

	@FindBy(xpath = "//*[text()='Bank Manager Login']")
	WebElement BankManagerLoginButton;
	@FindBy(xpath = "//*[contains(text(),'Add Customer')]")
	WebElement AddCustomerButton;

	

	public void OpenBankManagerLogin(WebDriver driver) {
		
		waitJS.waitAllRequest(driver);
		clickActions.clickOnElement(BankManagerLoginButton);
		waitJS.waitAllRequest(driver);
	}

public void OpenAddCustomer(WebDriver driver) {
		
		waitActions.waitForElementToBeClickable(driver, AddCustomerButton, 5);
		clickActions.clickOnElement(AddCustomerButton);
		waitJS.waitAllRequest(driver);
	}
	
}