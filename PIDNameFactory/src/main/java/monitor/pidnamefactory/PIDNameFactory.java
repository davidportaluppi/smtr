package monitor.pidnamefactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import monitor.pidnamefactory.exceptions.UnsupportedLanguage;

public class PIDNameFactory {
	private Properties prop;
	private static final String MESSAGE_FILE_NAME = "messages";
	private static final String MESSAGE_FILE_EXTENSION = ".properties";
	private static final String MESSAGE_PREFIX = "smtr.pidname.";
	private static final String MESSAGE_FILE_SEPARATOR = "_";
	private String language = "";
	
	/**
	 * @return Map<metricID, metricName>
	 * @throws IOException
	 */
	public PIDNameFactory() throws IOException {		
		
		this.prop = buildPropertyFile(MESSAGE_FILE_NAME + MESSAGE_FILE_EXTENSION);
	}

	public Map<String, String> getMetricsNames(String language,
			List<String> metricIDList) throws IOException {
		Map<String, String> metricsNames = new HashMap<String, String>();

		try {
			changeLanguage(language);
		} catch (UnsupportedLanguage e) {
			// Log Error!!!
			// e.printStackTrace();
		}

		for (String metricID : metricIDList) {
			metricsNames.put(metricID,
					prop.getProperty(MESSAGE_PREFIX + metricID));
		}
		return metricsNames;
	}

	private Properties buildPropertyFile(String path) throws IOException {
		Properties p = new Properties();
		p.load(getClass().getClassLoader().getResourceAsStream(path));
		return p;
	}

	public void changeLanguage(String language) throws UnsupportedLanguage, IOException {
		if (language == null) {
			return;
		}
		
		
		language = language.trim().toLowerCase();
		if (!this.language.equalsIgnoreCase(language)) {
			try {
				this.prop = buildPropertyFile(MESSAGE_FILE_NAME + MESSAGE_FILE_SEPARATOR + language
						+ MESSAGE_FILE_EXTENSION);
				this.language = language;
			} catch (NullPointerException e) {
				throw new UnsupportedLanguage("Invalid language");
			}
		}
	}
}
