package utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonDataReader {

    private static final JSONObject testData;

    static {
        try {
            // Get Base Dir
            String baseDir = System.getProperty("user.dir");

            /* Equivalent path: <project-root>/src/test/resources/testfiles/TestData.json */
            String path = Paths.get(baseDir, "src/test/resources/testfiles", "TestData.json").toString();

            var path1 = Paths.get(path);
            if (!Files.exists(path1)) {
                throw new RuntimeException("JSON data file not found at: " + path);
            }

            String content = new String(Files.readAllBytes(path1));
            testData = new JSONObject(content);

        } catch (IOException e) {
            throw new RuntimeException("Failed to load JSON data: " + e.getMessage(), e);
        }
    }

    public static String get(int index, String key) {

        if (testData == null) {
            throw new IllegalStateException("JSON data not loaded.");
        }

        JSONArray dataArray = testData.optJSONArray("data");
        if (dataArray == null || dataArray.isEmpty()) {
            throw new IllegalStateException("No data entries found in JSON file.");
        }

        if (index < 0 || index >= dataArray.length()) {
            throw new IndexOutOfBoundsException(
                    "Index " + index + " is out of range. Valid range: 0 to " + (dataArray.length() - 1)
            );
        }

        JSONObject obj = dataArray.optJSONObject(index);
        if (obj == null) {
            throw new IllegalStateException("JSON object at index " + index + " is invalid.");
        }

        if (!obj.has(key)) {
            throw new RuntimeException("Key '" + key + "' not found in JSON object at index " + index);
        }

        return obj.optString(key);
    }
}
