package framework.pages;

import org.openqa.selenium.support.FindBy;

import framework.datamodel.User;
import framework.fragments.LoginRegisterForm;

public class LoginRegisterPage extends BasePageWithNavigation {

	@FindBy (name = "login-form")
	private LoginRegisterForm loginForm;
	
	public void logWithUser(User userData) {
		loginForm.enterUserDetailsAndSignIn(userData);
	}
	
	public boolean isErrorValidationMsgAsExpected() {
		return loginForm.isWrongUserErrorValidationMessageDisplayed();
	}
	
}
