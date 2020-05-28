package framework.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

import framework.datamodel.Url;

public class MusalaSoftOfficialPage extends BasePageWithNavigation {

	@FindBy(css = ".brand .logo")
	private WebElement siteLogo;
	
	
	public boolean isAt(Url siteUrlDetails) {
		switchToNextWindow();
		if (getCurrentUrl().equalsIgnoreCase(siteUrlDetails.url)) {
			return true;
		} else {
			Reporter.log(String.format("Current site url is '%s' and this is not execpted", getCurrentUrl()));
			return false;
		}
	}
	
	public boolean isSiteLogoDisplayed() {
		return isElementVisibleWithLogging(siteLogo, "Site logo");
	}
}
