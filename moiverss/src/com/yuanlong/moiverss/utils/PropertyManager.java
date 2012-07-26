package com.yuanlong.moiverss.utils;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class PropertyManager {
	private static final String CONFIG = "config";
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
			.getBundle("config");

	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
		}
		throw new RuntimeException("! config : " + key + '!');
	}

	public static String getRB(String rb, String key) throws Exception {
		try {
			return ResourceBundle.getBundle(rb).getString(key);
		} catch (MissingResourceException e) {
		}
		throw new RuntimeException('!' + rb + ":" + key + '!');
	}

	public static Map<String, String> getStringMap(String baseName) {
		Map m = new HashMap();
		ResourceBundle rb = ResourceBundle.getBundle(baseName);
		Enumeration keys = rb.getKeys();

		while (keys.hasMoreElements()) {
			String temp = (String) keys.nextElement();
			m.put(temp, rb.getString(temp));
		}
		ResourceBundle.clearCache();
		return m;
	}
}
