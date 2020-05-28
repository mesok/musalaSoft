package framework.selenium;

import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.Graphene;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;

import framework.datamodel.Device;
import framework.datamodel.SiteDetails;

public abstract class BasePageFragmentClass {
    
	@Drone
    private static WebDriver browser;
	
    protected static Device device;

    protected static SiteDetails site;

    private static Logger logger = Logger.getLogger(BasePageFragmentClass.class.getName());

    public static void setSite(SiteDetails usedSited) {
        site = usedSited;
        Reporter.log(String.format("Current site is '%s'", site.name), true);
    }

    public static SiteDetails getCurrentSite() {
        return site;
    }
    
    public static void setDevice(Device usedDevice) {
        device = usedDevice;
        Reporter.log(String.format("Device name is '%s'", device.name), true);
    }

    public boolean isDesktopDevice() {
        return device.type.equalsIgnoreCase("desktop");
    }

    /*
     * Actions with browser
     */

    public void setBrowserDimensions() {
        if (device.isFullscreen) {
            browser.manage().window().maximize();
            Reporter.log("Browser is set to fullscreen size", true);
        } else {
            browser.manage().window().setSize(new Dimension(device.width, device.height));
            Reporter.log(String.format("Browser's dimensions are set to width=%d and height=%d", device.width,
                    device.height), true);
        }
    }

    /**
     * This method gets current url from the browser.
     * 
     * @return current browser url as String value.
     */
    public String getCurrentUrl() {
        String currentUrl = browser.getCurrentUrl();
        Reporter.log(String.format("Current browser url is '%s'", currentUrl), true);
        return currentUrl;
    }

    public void goToBasicSiteUrl() {
        browser.get(site.url);
        Reporter.log(String.format("Open base site url: %s", site.url), true);
    }


    public void switchToNextWindow() {
        String win = browser.getWindowHandle();
        Set<String> winHandles = browser.getWindowHandles();
        for (String winHandle : winHandles) {
            if (!winHandle.equals(win)) {
                browser.switchTo().window(winHandle);
            }
        }
        Reporter.log(String.format("Title of the new window is %s", browser.getTitle()), true);
    }

    /*
     * Use only when we have one window
     */
    public void returnToParentWindow() {
        Set<String> winHandles = browser.getWindowHandles();
        browser.switchTo().window(winHandles.iterator().next());
        Reporter.log(String.format("Return to parent window %s", browser.getTitle()), true);
    }
    /*
     * Wait for elements
     */

    public void waitForElementToBeClickable(WebElement element) {
        Graphene.waitModel(browser).until().element(element).is().clickable();
    }

    public void waitForElementToBeVisible(WebElement element) {
        Graphene.waitModel(browser).until().element(element).is().visible();
    }


    public void waitForAjaxToComplete() {
        Graphene.waitModel(browser)
                .until(wd -> (Boolean) ((JavascriptExecutor) wd).executeScript("return jQuery.active == 0"));
    }

    public void waitForDocumentReadyState() {
        Graphene.waitModel(browser)
                .until(wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }

    /*
     * Actions with elements
     */

    // Click element
    /**
     * Wait for element to be clickable and then click
     */
    public void clickElement(WebElement element) {
        waitForElementToBeClickable(element);
        element.click();
    }

    /**
     * Wait for element to be visible and get text from it
     */
    public String getTextFromVisibleElement(WebElement element) {
        waitForElementToBeVisible(element);
        return element.getText().trim();
    }
    
    /**
     * Wait for element to be visible, then write text into it
     */
    public void writeIntoElement(WebElement element, String text) {
        if (text == null || text.isEmpty())
            return;
        waitForElementToBeClickable(element);
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Wait for element to be visible, then write text into it and wait for ajax
     * request to complete
     */



    /**
     * Check is element visible, or timeout after 1 second
     * 
     * @param element
     * @return
     */
    public boolean isElementVisible(WebElement element) {
        if (isElementExist(element)) {
            try {
                Graphene.waitModel(browser).withTimeout(1, TimeUnit.SECONDS).pollingEvery(100, TimeUnit.MICROSECONDS)
                        .until().element(element).is().visible();
                return true;
            } catch (TimeoutException e) {
                Reporter.log("Element is not visible", false);
                logger.log(Level.INFO, e.getMessage());
            }
        }
        return false;
    }

    public boolean isElementExist(WebElement element) {
        try {
            Graphene.waitModel(browser).withTimeout(1, TimeUnit.SECONDS).pollingEvery(100, TimeUnit.MICROSECONDS)
                    .until().element(element).is().present();
            return true;
        } catch (NoSuchElementException e) {
            Reporter.log("Element not exists", false);
            logger.log(Level.INFO, e.getMessage());
        }
        return false;
    }
   
    public void hoverWebElement(WebElement we) {
        waitForElementToBeVisible(we);
        new Actions(browser).moveToElement(we).build().perform();
    }

    public void scrollToElement(WebElement we) {
        Reporter.log("Scroll to element", true);
        ((JavascriptExecutor) browser).executeScript("arguments[0].scrollIntoView({block: 'center'});", we);
    }

    /**
     * Check if element is visible and generate report in the log file.
     * 
     * @param element     -> WebElement that we want to check
     * @param elementName -> Element name to be reported in the log
     * @return
     */
    public boolean isElementVisibleWithLogging(WebElement element, String elementName) {
        if (isElementVisible(element)) {
            Reporter.log(elementName + " is vissible", true);
            return true;
        }
        Reporter.log(elementName + " is not vissible", true);
        return false;
    } 
}
