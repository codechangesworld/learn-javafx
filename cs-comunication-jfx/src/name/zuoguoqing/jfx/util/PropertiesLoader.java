/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.jfx.util
 * @file LoadProperties.java
 * @author zuoguoqing
 * @date 2017年4月10日
 * @version 
 */
package name.zuoguoqing.jfx.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

/**
 * @author zuoguoqing
 *
 */
public class PropertiesLoader {
	private final static Map<String, Properties> PROPERTIES_MAP = new HashMap<>();
	private static Properties properties;

	/**
	 * load specified property file
	 * 
	 * @param propertiesFile
	 * @return
	 */
	public static boolean load(String propertiesFile) {
		try {
			properties = new Properties();
			properties.load(new FileInputStream(propertiesFile));
			PROPERTIES_MAP.put(propertiesFile, properties);
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * get property by key from last loaded properties file
	 * 
	 * @param key
	 * @return
	 */
	public static String getValue(String key) {
		return properties.getProperty(key);
	}
	
	/**
	 * get property by key from specified properties
	 * @param key
	 * @param propertiesFile specified properties file
	 * @return
	 */
	public static String getValue(String key, String propertiesFile) {
		if (PROPERTIES_MAP.containsKey(propertiesFile)
				|| load(propertiesFile)) {
			return PROPERTIES_MAP.get(propertiesFile).getProperty(key);
		} else {
			return null;
		}
	}

	/**
	 * store key=value to properties file
	 * @param key
	 * @param value
	 * @param propertiesFile
	 * @return
	 */
	public static boolean storeValue(String key, String value, 
			String propertiesFile) {
		try {
			Properties prop = PROPERTIES_MAP.get(propertiesFile);
			if (prop == null) {
				prop = new Properties();
			}
			prop.setProperty(key, value);
			prop.store(new FileOutputStream(propertiesFile), "some comment");
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * store key-value map to properties file 
	 * @param items
	 * @param propertiesFile
	 * @return
	 */
	public static boolean storeValues(Map<String, String> items,
			String propertiesFile) {
		try {
			Properties prop = PROPERTIES_MAP.get(propertiesFile);
			if (prop == null) {
				prop = new Properties();
			}
			Set<Entry<String, String>> set = items.entrySet();
			Iterator<Entry<String, String>> iterator = set.iterator();
			while (iterator.hasNext()) {
				Entry<String, String> entry = iterator.next();
				prop.setProperty(entry.getKey(), entry.getValue());
			}
			prop.store(new FileOutputStream(propertiesFile), "some comment");
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}//end try
		
	}
	
	
}
