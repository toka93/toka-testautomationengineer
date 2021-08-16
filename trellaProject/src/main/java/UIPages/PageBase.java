package UIPages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Actions.AuthenticatingActions;
import Actions.ClickActions;
import Actions.ElementActions;
import Actions.Helpers;
import Actions.SelectActions;
import Actions.TextActions;
import Actions.WaitActions;
import Actions.WaitJS;
import Actions.WindowActions;
import configuration.Log4j2Configurations;

public class PageBase {
	protected WebDriver driver;
	Logger log = LogManager.getLogger(Log4j2Configurations.class);
	ElementActions actionsElement = new ElementActions();
	ClickActions clickActions = new ClickActions();
	TextActions textActions = new TextActions();
	ElementActions elActions= new ElementActions();
	AuthenticatingActions AuthActions= new AuthenticatingActions();
	WaitJS waitJS=new WaitJS();
	Helpers helper = new Helpers();
	WindowActions win = new WindowActions();
	WaitActions waitActions = new WaitActions();
	  SelectActions selectActions = new SelectActions();

	public PageBase(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}



}
