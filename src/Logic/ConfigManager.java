package Logic;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
    private static final Properties properties;

    static {
        properties = new Properties();
        try (InputStream input = ConfigManager.class.getClassLoader().getResourceAsStream("config.properties.txt")) {
            if (input != null) {
                properties.load(input);
            } else {
                System.err.println("Unable to find or load config.properties.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Getters for file paths
    public static String getRequestFilePath() {
        return properties.getProperty("requestfile");
    }

    public static String getTeacherFilePath() {
        return properties.getProperty("teacherfile");
    }

    public static String getAssignedFilePath() {
        return properties.getProperty("assignedfile");
    }
}
