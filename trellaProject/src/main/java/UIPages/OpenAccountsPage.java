package UIPages;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Actions.ClickActions;
import Actions.Helpers;
import Actions.WaitActions;
import Actions.WaitJS;

public class OpenAccountsPage extends PageBase {
	


	public OpenAccountsPage(WebDriver driver) {
		super(driver);

	}

	
	@FindBy(xpath = "//*[contains(text(),'Open Account')]")
	WebElement OpenAccountButon;

	@FindBy(xpath = "//*[@id='userSelect']")
	WebElement UsersDDL;
	@FindBy(xpath = "//*[@id='currency']")
	WebElement CurrencyDDL;
	@FindBy(xpath = "//*[@type='submit' and text()='Process']")
	WebElement ProcessButton;
	
	
public void chooseUser(String user)
{
selectActions.selectOptionByVisibleText(user, UsersDDL);	

}

public void chooseCurrency(int index,WebDriver driver)
{
	waitJS.waitAllRequest(driver);

selectActions.selectOptionByIndex(index, CurrencyDDL);	

}

public void ClickOnProcess()
{
clickActions.clickOnElement(ProcessButton);	
}
	
public void OpenOpenAccount(WebDriver driver) {
		
		waitActions.waitForElementToBeClickable(driver, OpenAccountButon, 5);
		clickActions.clickOnElement(OpenAccountButon);
		waitJS.waitAllRequest(driver);
	}
	


public String getAccountIDandAcceptAlert(WebDriver driver) throws InterruptedException
{
	String custID=null;
	
	try {
		ClickOnProcess();
	}
	catch(UnhandledAlertException ex)
	{
		try {
			String alertMSG=AuthActions.getAlertText(driver);
			log.info("Alert Message is : "+ alertMSG);

			String[] words=helper.splitString(alertMSG, ":");
			log.info("Alert Message split is : "+ words[0] +" and "+words[1]);
			log.info("Customer ID is : "+ words[1]);
		AuthActions.acceptAlert(driver);
		custID= words[1];
		}
		catch (NoAlertPresentException ex2) {
			
			log.info("No Alert!!");

		}
	}
return custID;
}









}