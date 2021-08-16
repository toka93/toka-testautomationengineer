package UIPages;


import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CustomerPage extends PageBase {

	
	String CID = null;

	public CustomerPage(WebDriver driver) {
		super(driver);

	}

	@FindBy(xpath = "//*[@ng-model='fName']")
	WebElement FirstName;
	@FindBy(xpath = "//*[@ng-model='lName']")
	WebElement LastName;
	@FindBy(xpath = "//*[@ng-model='postCd']")
	WebElement PostalCode;
	@FindBy(xpath = "//*[@type='submit' and text()='Add Customer']")
	WebElement AddCustomerButton;

	@FindBy(xpath = "//*[contains(text(),'Customers')]")
	WebElement CustomersButton;

	By tablerows=By.xpath("//*/table/tbody/tr") ;

	public int getNumberOFRows(WebDriver driver)
	{
		int count = elActions.getCountOfElements(tablerows, driver);
		
		return count;
	}
	public void addCustomerFirstName(String fName) {
		textActions.enterTextinField(FirstName, fName);

	}

	public void addCustomerLastName(String lName) {
		textActions.enterTextinField(LastName, lName);

	}

	public void addCustomerPostalCode(String code) {
		textActions.enterTextinField(PostalCode, code);

	}

	public void clickOnAddCustomer(WebDriver driver) {
		clickActions.clickOnElement(AddCustomerButton);
		waitJS.waitAllRequest(driver);

	}

	public void addCustomer(String fname, String lname, String pcode, WebDriver driver) {

		addCustomerFirstName(fname);
		addCustomerLastName(lname);
		addCustomerPostalCode(pcode);

	}

	public String getCustomerIDandAcceptAlert(WebDriver driver) throws InterruptedException {
		String custID = null;

		try {
			clickOnAddCustomer(driver);
		} catch (UnhandledAlertException ex) {
			try {
				String alertMSG = AuthActions.getAlertText(driver);
				log.info("Alert Message is : " + alertMSG);

				String[] words = helper.splitString(alertMSG, ":");
				log.info("Alert Message split is : " + words[0] + " and " + words[1]);
				log.info("Customer ID is : " + words[1]);
				AuthActions.acceptAlert(driver);
				custID = words[1];
			} catch (NoAlertPresentException ex2) {

				log.info("No Alert!!");

			}
		}
		return custID;
	}

	public void clickOnCustomersTab() {
		clickActions.clickOnElement(CustomersButton);

	}

	public Boolean GetCustomerValues(String customerID, WebDriver driver, String FName, String LName, String Code) {
		Boolean flag = false;
		String CellsInEachRow = "//*/table/tbody/tr[" + customerID + "]/td";
		WebElement elm = driver.findElement(By.xpath(CellsInEachRow));

		String Fname = actionsElement.GetTextOfElementfromListOFElementsByIndex(elm, "xpath", driver, 0);
		log.info("first name : " + Fname);

		String Lname = actionsElement.GetTextOfElementfromListOFElementsByIndex(elm, "xpath", driver, 1);
		log.info("last name is : " + Lname);

		String code = actionsElement.GetTextOfElementfromListOFElementsByIndex(elm, "xpath", driver, 2);
		log.info("Code is : " + code);

		if (Fname.equals(FName) && Lname.equals(LName) && Code.equals(Code))
			flag = true;
		else
			flag = false;

		return flag = true;
	}

	public boolean checkAccountNumber(String cusID, String AccID, WebDriver driver) {
		Boolean flag = false;

		String AccountNumberCell = "//*/table/tbody/tr[" + cusID + "]/td[4]";
		WebElement elm = driver.findElement(By.xpath(AccountNumberCell));
		String acc = textActions.getElementText(elm);
		if (acc.equals(AccID))
			flag = true;
		else
			flag = false;
		return flag;

	}

	public void DeleteCustomer(String cusID, WebDriver driver) {

		String DeleteButton = "//*/table/tbody/tr[" + cusID + "]/td[5]/button";
		WebElement elm = driver.findElement(By.xpath(DeleteButton));

		clickActions.clickOnElement(elm);

		waitJS.waitAllRequest(driver);

	}

}