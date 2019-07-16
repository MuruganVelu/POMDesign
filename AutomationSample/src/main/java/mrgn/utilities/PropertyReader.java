package mrgn.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class PropertyReader implements ConfigReader {

	private static FileInputStream file;
	public static Properties OR;

	public PropertyReader() {
		try {
			String filePath = ResourceHelper.getResourcePath("src/main/resources/configfile/config.properties");
			file = new FileInputStream(new File(filePath));
			OR = new Properties();
			OR.load(file);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int getImpliciteWait() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getExplicitWait() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPageLoadTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BrowserType getBrowserType() {
		// TODO Auto-generated method stub
		return BrowserType.valueOf(OR.getProperty("browserType"));
	}

	@Override
	public String getUrl() {
		if (System.getProperty("url") != null) {
			return System.getProperty("url");
		}
		return OR.getProperty("applicationUrl");

	}

	@Override
	public String getUserName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

}
