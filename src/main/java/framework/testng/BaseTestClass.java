package framework.testng;

import java.io.IOException;
import java.lang.reflect.Method;

import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.testng.Arquillian;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import framework.datamodel.DataManagement;
import framework.selenium.BasePageFragmentClass;

@RunAsClient
public abstract class BaseTestClass extends Arquillian {
    
    @Drone
    private WebDriver browser;
    
	protected static final String STOREFRONT_NAME = System.getProperty("storefront.name", "MusalaSoft");
    protected static final String DEVICE_NAME = System.getProperty("device.name", "desktopFullscreen");

    @BeforeClass
    public void setup() throws IOException {
        BasePageFragmentClass.setSite(DataManagement.getSiteDetails(STOREFRONT_NAME));
        BasePageFragmentClass.setDevice(DataManagement.getDeviceByName(DEVICE_NAME));
    }

    @BeforeMethod
    public void printTestName(Method method) {
        Reporter.log(String.format("Started test '%s'", method.getName()), true);
    }

    @AfterMethod
    public void printTestEnd(Method method) {
        Reporter.log(String.format("Completed test '%s'", method.getName()), true);
    }
}
