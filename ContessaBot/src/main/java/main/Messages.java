package main;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Used to store and retrieve messages sent by the bot in an external file.
 * Automatically generated by the Eclipse "Externalize Strings" tool.
 */
public class Messages {
	private static final String BUNDLE_NAME = "main.messages"; //$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

	private Messages() {
	}

	/**
	 * Retrieves the string matching the given key from the resource bundle.
	 * @param key 
	 * @return String value corresponding to key.
	 */
	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return "Message retrieval error, please notify an administrator.";
		}
	}
}
