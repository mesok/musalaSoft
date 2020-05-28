package framework.selenium;

import org.jboss.arquillian.graphene.context.GrapheneContext;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Utils {
	protected Utils() {
		throw new IllegalStateException("This utility class is not meant to be instantiated.");
	}

	public static boolean isDesktop() {
		Platform currentPlatform = ((RemoteWebDriver) GrapheneContext.lastContext().getWebDriver()).getCapabilities()
				.getPlatform();
		return (currentPlatform.is(Platform.WINDOWS) || currentPlatform.is(Platform.MAC));
	}
}
