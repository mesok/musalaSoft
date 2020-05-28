package framework.fragments;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

import framework.datamodel.User;
import framework.selenium.BasePageFragmentClass;

public class LoginRegisterForm extends BasePageFragmentClass {
	
	@FindBy (css = "[id$='username' i]")
	private WebElement userName;
	
	@FindBy (css = "[id*='pass' i]:not([id*='password-error'])")
	private WebElement passwordField;
	
	@FindBy (id = "btn-sign-in")
	private WebElement signInBtn;
	
	@FindBy (css = ".bs-callout-error:not([style]) p")
	private WebElement wrongUserErrorVldMsg;
	
	private void enterUserName(String userName) {
		writeIntoElement(this.userName, userName);
		Reporter.log(String.format("Write '%s' into the 'Username' field", userName),true);
	}
	
	private void enterPassword(String password) {
		writeIntoElement(passwordField, password);
		Reporter.log(String.format("Write '%s' into the 'Password' field", password),true);
	}
	
	private void clickSignInBtn() {
		clickElement(signInBtn);
		waitForDocumentReadyState();
		Reporter.log("Click on 'Sign In' button inside the login form",true);
		
	}
	public void enterUserDetailsAndSignIn(User userData) {
		enterUserName(userData.userName);
		enterPassword(userData.password);
		clickSignInBtn();	
	}
	
	public boolean isWrongUserErrorValidationMessageDisplayed() {
		String vldMsg = "Wrong user or password.";
		if (getTextFromVisibleElement(wrongUserErrorVldMsg).contains(vldMsg)) {
			Reporter.log(String.format("Error validation message with text '%s' is displayed", vldMsg));
			return true;
		} else {
			Reporter.log(String.format("Error validation message with text '%s' is NOT!!!!! displayed", vldMsg));
			return false;
		}
	}

}
