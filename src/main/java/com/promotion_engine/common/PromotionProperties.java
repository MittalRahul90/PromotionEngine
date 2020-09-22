package com.promotion_engine.common;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PromotionProperties {

	Properties prop = new Properties();
	
	public String getPropValues(String fileName, String skuId) throws IOException {
		
		InputStream inputStream = null;
		try {
			String propFileName = fileName;

			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("Property file '" + propFileName + "' not found in the classpath");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			inputStream.close();
		}
		return prop.getProperty(skuId);
	}

}
