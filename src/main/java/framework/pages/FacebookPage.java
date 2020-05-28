package framework.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

import framework.selenium.BasePageFragmentClass;

public class FacebookPage extends BasePageFragmentClass {
	
	@FindBy(css = "[aria-label*='profile' i]")
	private WebElement fbProfilePicture;
	
	public boolean isUrlAndProfilePicAsExpected() {
		switchToNextWindow();
		String fbUrl = "https://www.facebook.com/MUFFINconference/";
		if(getCurrentUrl().equalsIgnoreCase(fbUrl) && isProfilePictureVisible()) {
			Reporter.log("Url is as expected",true);
			return true;
		} else {
			Reporter.log(String.format("Current url is NOT!!! '%s'", fbUrl));
			return false;
		}	
	}
	
	private boolean isProfilePictureVisible() {
		return isElementVisibleWithLogging(fbProfilePicture, "Profile Picture");
	}
}
