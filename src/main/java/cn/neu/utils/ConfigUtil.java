package cn.neu.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class ConfigUtil extends PropertyPlaceholderConfigurer{
	private static final Map<String, Object> propertiesMap = new HashMap<String, Object>();

	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props)
			throws BeansException {
		super.processProperties(beanFactoryToProcess, props);
		for (Object key : props.keySet()) {
			String keyStr = key.toString().trim();
			propertiesMap.put(keyStr, props.getProperty(keyStr).trim());
		}
	}

	public static String getString(String key) {
		return propertiesMap.get(key).toString();
	}

	public static Integer getInt(String key) {
		return Integer.valueOf(propertiesMap.get(key).toString());
	}

	public static Long getLong(String key) {
		return Long.valueOf(propertiesMap.get(key).toString());
	}
}
