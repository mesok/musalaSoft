
package framework.datamodel;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import framework.selenium.BasePageFragmentClass;

public abstract class DataManagement {
    

    protected static final String USERS_DATA_FILE = "users.json";
    protected static final String SITES_DATA_FILE = "sites.json";
    protected static final String URL_DETAILS_DATA_FILE = "url.json";
    protected static final String DEVICE_TYPE_FILE = "devices.json";
    protected static final String EVENTS_TYPE_FILE = "events.json";
    
    protected DataManagement() {
        throw new IllegalStateException("This utility class is not meant to be instantiated.");
    }

    private static InputStream getFolderAndFileForResource(String fileName) throws IOException {
        InputStream resouceStream;
        if (SITES_DATA_FILE.equals(fileName) || BasePageFragmentClass.getCurrentSite() == null
                || BasePageFragmentClass.getCurrentSite().resourceFolder == null) {
            resouceStream = ClassLoader.getSystemClassLoader().getResourceAsStream(fileName);
        } else {
            resouceStream = ClassLoader.getSystemClassLoader().getResourceAsStream(
                    String.format("%s/%s", BasePageFragmentClass.getCurrentSite().resourceFolder, fileName));
            if (resouceStream == null) {
                resouceStream = ClassLoader.getSystemClassLoader().getResourceAsStream(fileName);
            }
        }
        if (resouceStream == null) {
            throw new IOException(String.format("Resource file '%s' doesn't exist", fileName));
        } else {
            return resouceStream;
        }
    }

    public static <T> T getSingleObjectFromArrayJsonFile(String resourceFileName, Class<T> tClass, int index)
            throws IOException {
        List<T> jsonList = getListObjectFromArrayJsonFile(resourceFileName, tClass);
        if (index >= jsonList.size())
            index = 0;
        return jsonList.get(index);
    }

    public static <T> List<T> getListObjectFromArrayJsonFile(String resourceFileName, Class<T> tClass)
            throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        CollectionType listType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, tClass);
        return mapper.readValue(getFolderAndFileForResource(resourceFileName), listType);
    }

    public static <T> T getFirstMatchingElementFromListOrThrowAnException(String resourceFileName, Class<T> tClass,
            Predicate<T> predicate, String expectedValue) throws IOException {
        List<T> elementLists = getListObjectFromArrayJsonFile(resourceFileName, tClass);
        return elementLists.stream().filter(predicate).findFirst().orElseThrow(() -> new NoSuchElementException(
                String.format("Element '%s' does not exist in the list of the '%s' file , plaese check your data file", expectedValue , resourceFileName)));
    }


	public static Url getUrlByLabel(String urlLabel) throws IOException {
		return getListObjectFromArrayJsonFile(URL_DETAILS_DATA_FILE, Url.class).stream()
				.filter(url -> urlLabel.equalsIgnoreCase(url.label))
				.findFirst().orElseThrow(() -> new NoSuchElementException(String.format(
						"Cant find URL with '%s' label in the JSON file , please check your input data", urlLabel)));
	}
		
	
    // Site Methods
    public static SiteDetails getSiteDetails(String site) throws IOException {
        return getFirstMatchingElementFromListOrThrowAnException(SITES_DATA_FILE, SiteDetails.class,
                (expectedSite -> site.equalsIgnoreCase(expectedSite.name)), site);
    }
    
    // Device
    public static Device getDeviceByName(String type) throws IOException {
        return getFirstMatchingElementFromListOrThrowAnException(DEVICE_TYPE_FILE, Device.class,
                (device -> type.equalsIgnoreCase(device.name)), type);
    }
    
    public static User getUserByLabel(String label) throws IOException {
        return getFirstMatchingElementFromListOrThrowAnException(USERS_DATA_FILE, User.class,
                (user -> label.equalsIgnoreCase(user.label)), label);
    }
    
	public static Events getEventById(String eventId) throws IOException {
		return getFirstMatchingElementFromListOrThrowAnException(EVENTS_TYPE_FILE, Events.class,
				(event -> eventId.equalsIgnoreCase(event.id)), eventId);
	}
}
