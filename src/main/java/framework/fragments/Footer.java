package framework.fragments;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

import framework.selenium.BasePageFragmentClass;

public class Footer extends BasePageFragmentClass {

	@FindBy (css = ".leftPartFooter a")
	private WebElement musalaSoftLogo;
	
	@FindBy (css = "[src*='facebook.png']")
	private WebElement facebookImage;
	
	public void clickLogo() {
		scrollToElement(musalaSoftLogo);
		Reporter.log("Scroll to footer logo",true);
		clickElement(musalaSoftLogo);
		Reporter.log("CLick on 'Musala Soft' logo",true);
		waitForDocumentReadyState();
	}

	public void clickFacebook() {
		clickElement(facebookImage);
		waitForDocumentReadyState();
		Reporter.log("Click on 'Facebook' image", true);

	}

}
