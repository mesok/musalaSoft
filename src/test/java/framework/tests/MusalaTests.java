package framework.tests;
import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.testng.annotations.Test;

import framework.commons.BaseTestClassWithPages;
import framework.datamodel.DataManagement;
import framework.datamodel.Events;
import framework.datamodel.Url;
import framework.datamodel.User;

public class MusalaTests extends BaseTestClassWithPages {
	
	@Test(description = "Test Case Scenario 1" , invocationCount = 5)
	public void verifyInvalidLoggin() throws IOException {
		
		User userData = DataManagement.getUserByLabel("TestUser");
		assertTrue(homePage.isAt(), "'Musala Soft' home page is not displayed , please check whats wrong");
		homePage.navigateToLoginRegisterPage();
		userData.userName = userData.userName + System.currentTimeMillis();
		loginRegisterPage.logWithUser(userData);
		assertTrue(loginRegisterPage.isErrorValidationMsgAsExpected(),"Error validation message is not displayed");

		
	}
	
	@Test(description = "Test Case Scenario 2")
	public void verifyLogoAndFacebookRedirects() throws IOException {
		
		Url retailSite = DataManagement.getUrlByLabel("MusalaSoftRetailSite");

		assertTrue(homePage.isAt(), "'Musala Soft' home page is not displayed , please check whats wrong");
		homePage.clickMusalaFooterLogo();
		assertTrue(musalaSoftOfficialHomePage.isAt(retailSite),"Redirect to 'Musala Soft' offical page does not happen");
		assertTrue(musalaSoftOfficialHomePage.isSiteLogoDisplayed(),"Logo is not displayed");		
		homePage.clickFacebookIcon();
		assertTrue(facebookPage.isUrlAndProfilePicAsExpected(),"Facebook Url or Profile picture is not as expected");
		
	}
	
	@Test(description = "Test Case Scenario 3")
	public void verifyLastEventInTheSchedule() throws IOException {
		
		Events lastEvent = DataManagement.getEventById("last");
		
		assertTrue(homePage.isAt(), "'Musala Soft' home page is not displayed , please check whats wrong");
		homePage.navigateToArchive();
		archivePage.selectEvent(lastEvent);
		eventDetailsPage.printFirstDay();
		eventDetailsPage.printSecondDay();
		eventDetailsPage.printThirdDay();
	}	
}
