package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DataLoader {
    private DataLoader() {}
    private static final String TEST_DATA_FILE = "src/test/resources/testdata.properties";
    public static String loadProperty(String key) {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream(TEST_DATA_FILE)) {
            properties.load(input);
            String value = properties.getProperty(key);
            if (value == null) {
                Log.getInstance().warn("Property '{}' not found in the test data file", key);
                return null;
            }
            return value;
        } catch (IOException e) {
            Log.getInstance().error("Error loading test data from properties file", e);
            return null;
        }
    }
}
