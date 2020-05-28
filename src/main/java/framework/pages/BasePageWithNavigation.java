package framework.pages;

import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

import framework.fragments.Footer;
import framework.fragments.Header;
import framework.selenium.BasePageFragmentClass;

public class BasePageWithNavigation extends BasePageFragmentClass {

	@FindBy(className = "navbar")
	private Header headerDesktop;
	
	@FindBy(tagName = "footer")
	private Footer footer;
	
	public void navigateToLoginRegisterPage() {
		if (!isDesktopDevice()) {
			Reporter.log("Mobile flow is not implemented yet", true);
			return;
		}
		headerDesktop.clickSingIn();
	}
	
	public boolean isLogoTextDisplayed() {
		if (isDesktopDevice()) {
			return headerDesktop.isLogoTextVisible();
		} else {
			Reporter.log("Mobile flow is not implemented yet", true);
			return false;
		}
	}
	
	public void clickMusalaFooterLogo() {
		footer.clickLogo();
	}
	
	public void clickFacebookIcon() {
		returnToParentWindow();
		footer.clickFacebook();	
	}
	
	public void navigateToArchive() {
		headerDesktop.clickArchiveBtn();	
	}
}
