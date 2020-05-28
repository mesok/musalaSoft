package framework.commons;

import org.jboss.arquillian.graphene.page.Page;
import org.testng.annotations.BeforeMethod;

import framework.pages.ArchivePage;
import framework.pages.EventDetailsPage;
import framework.pages.FacebookPage;
import framework.pages.HomePage;
import framework.pages.LoginRegisterPage;
import framework.pages.MusalaSoftOfficialPage;
import framework.testng.BaseTestClass;

public class BaseTestClassWithPages extends BaseTestClass {

	@Page
	protected HomePage homePage;
	
	@Page
	protected LoginRegisterPage loginRegisterPage;
	
	@Page
	protected MusalaSoftOfficialPage musalaSoftOfficialHomePage;
	
	@Page
	protected FacebookPage facebookPage;
	
	@Page
	protected ArchivePage archivePage;
	
	@Page
	protected EventDetailsPage eventDetailsPage;
	
	@BeforeMethod
	public void openHomePage() {
		homePage.setBrowserDimensions();
		homePage.goToBasicSiteUrl();		
	}
}
