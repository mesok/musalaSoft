package framework.pages;

import java.util.List;

import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

import framework.datamodel.EventSchedule;

public class EventDetailsPage extends BasePageWithNavigation {
	
	@FindBy(className = "checked-list-box")
	private List<EventSchedule> eventSchedule;
		
	private void printDetails(int speaker) {		
	
		eventSchedule.get(speaker).getHeaderTimeslot();
		eventSchedule.get(speaker).getSpeakerName();
		Reporter.log(String.format("Speaker name is '%s'", eventSchedule.get(speaker).getSpeakerName()),true);
		eventSchedule.get(speaker).getSpearkInfo();
	}
	
	public void printFirstDay() {
		int day = 0;
		eventSchedule.get(day).getEventDay();
		for (int speaker = 1; speaker < 5; speaker++) {
			printDetails(speaker);
		}
	}


	public void printSecondDay() {
		int day = 5;
		eventSchedule.get(day).getEventDay();
		for (int speaker = 6; speaker < 10 ; speaker++) {
			printDetails(speaker);
		}
		
	}
	
	public void printThirdDay() {
		int day = 11;
		eventSchedule.get(day).getEventDay();
		for (int speaker = 12; speaker < 15; speaker++) {
			printDetails(speaker);
		}
	}

	
}	
