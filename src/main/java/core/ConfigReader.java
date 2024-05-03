package core;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import utils.LogUtils;

public class ConfigReader {
    private static Properties properties = new Properties();

    static {
        loadConfig();
    }

    private static void loadConfig() {
        String filePath = "src/main/resources/configurations.properties";
        try (FileInputStream fis = new FileInputStream(filePath)) {
            properties.load(fis);
            LogUtils.logInfo("Configuration loaded successfully from file: " + filePath);
        } catch (IOException e) {
            LogUtils.logError("Error loading configuration from file: " + filePath);
        }
    }

    public static String getValue(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            LogUtils.logError("Value for key '" + key + "' not found in configuration.");
        }
        return value;
    }
}