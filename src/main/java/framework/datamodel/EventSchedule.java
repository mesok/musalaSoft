package framework.datamodel;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

import framework.selenium.BasePageFragmentClass;

public class EventSchedule extends BasePageFragmentClass {

	@FindBy (className = "header-timeslot")
	private WebElement headerTimeslot;
	
	@FindBy (tagName = "h4")
	private WebElement speakerName;
	
	@FindBy (css = ".speaker-info:not([span])")
	private WebElement spearkInfo;

	public String getHeaderTimeslot() {
		String headerTitle = getTextFromVisibleElement(headerTimeslot);
		Reporter.log(String.format("Event header is '%s'", headerTitle),true);
		return headerTitle;
	}

	public String getSpeakerName() {
		return speakerName.getText().trim();
	}

	public String getSpearkInfo() {
		String speakerInfo = getTextFromVisibleElement(spearkInfo).replace(getSpeakerName(),"");
		Reporter.log(speakerInfo,true);
		return speakerInfo;
	}
	
	public String getEventDay() {
		String eventDay = getTextFromVisibleElement(headerTimeslot);
		Reporter.log(String.format("Event day is '%s'", eventDay),true);
		return eventDay;
	}
	
	
}
