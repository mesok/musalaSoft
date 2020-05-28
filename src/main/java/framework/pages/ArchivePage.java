package framework.pages;

import java.util.List;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.FindBy;

import framework.datamodel.Events;
import framework.fragments.PastEventCards;

public class ArchivePage extends BasePageWithNavigation {
	
	@FindBy (className = "event-box-mobile")
	private List<PastEventCards> pastEventOptions;
	
	
	public void selectEvent(Events lastEvent) {
		waitForAjaxToComplete();
		PastEventCards filteredEvent = getEventByMatchingText(lastEvent);
		filteredEvent.openEvent();
		waitForAjaxToComplete();
	}
	
	private PastEventCards getEventByMatchingText(Events expectedText) {		
		return pastEventOptions.stream().filter(cards -> expectedText.description.contains(cards.getEventDescription()))
				.findFirst()
				.orElseThrow(() -> new NoSuchElementException(String.format("Event with '%s' description does not exist", expectedText.description)));
	}
}
