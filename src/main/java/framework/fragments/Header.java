package framework.fragments;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

import framework.selenium.BasePageFragmentClass;

public class Header extends BasePageFragmentClass {

	@FindBy(className = "logo-txt-pic")
	private WebElement meetTheMastersLogoText;

	@FindBy(css = ".navbar-menu-desktop [href^='/login']")
	private WebElement signInBtn;
	
	@FindBy (css = ".btn-nav[href*='/archive']")
	private WebElement archiveBtn;

	public boolean isLogoTextVisible() {
		if (isElementVisible(meetTheMastersLogoText)) {
			Reporter.log("'MEET THE MASTERS' logo text is visible", true);
			return true;
		} else {
			Reporter.log("'MEET THE MASTERS' logo text is NOT!!! visible", true);
			return false;
		}
	}

	public void clickSingIn() {
		clickElement(signInBtn);
		waitForDocumentReadyState();
		Reporter.log("Click on 'Sign In' button in the header", true);
	}

	public void clickArchiveBtn() {
		clickElement(archiveBtn);
		waitForDocumentReadyState();
		Reporter.log("Click on 'Archive' button", true);
	}

}
