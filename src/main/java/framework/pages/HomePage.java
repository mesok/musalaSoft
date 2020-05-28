package framework.pages;

public class HomePage extends BasePageWithNavigation {

	public boolean isAt() {
		return isLogoTextDisplayed();
	}
}
