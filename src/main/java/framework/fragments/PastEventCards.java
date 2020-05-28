package framework.fragments;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

import framework.selenium.BasePageFragmentClass;

public class PastEventCards extends BasePageFragmentClass {

	@FindBy(className = "event-body-home")
	private WebElement eventDescription;

	@FindBy(className = "event-header-home")
	private WebElement eventTitle;
	
	@FindBy (className = "event-magnifier")
	private WebElement eventMagnifer;

	public String getEventDescription() {
		return getTextFromVisibleElement(eventDescription);
	}

	public void openEvent() {
		hoverTitle();
		clickMagnifier();
		waitForDocumentReadyState();
	}
	
	private void hoverTitle() {
		hoverWebElement(eventTitle);
		Reporter.log("hoover on 'Event Title'",true);
	}
	
	private void clickMagnifier() {
		clickElement(eventMagnifer);
		Reporter.log("Click on 'Magnifier' icon",true);
		waitForDocumentReadyState();
	}
}
