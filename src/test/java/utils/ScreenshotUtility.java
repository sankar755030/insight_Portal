package utils;

import org.openqa.selenium.*;
import java.io.File;
import java.nio.file.Files;

public class ScreenshotUtility {

    public static String takeScreenshotAsBase64(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
    }


    public static String takeScreenshotAsPNG(WebDriver driver, String status, String methodName) {
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String path = System.getProperty("user.dir") + "/screenshots/" + status + "/" + methodName + "_" + System.currentTimeMillis() + ".png";

            File dest = new File(path);
            dest.getParentFile().mkdirs(); // Ensures the folder exists
            Files.copy(src.toPath(), dest.toPath());

            return path;
        } catch (Exception e) {
            e.printStackTrace(); // <-- Add this for debug visibility
            return null;
        }
    }


    public static String takeFullPageScreenshotAsBase64(WebDriver driver) {
        // You can integrate AShot or other tools for full-page capture later
        return takeScreenshotAsBase64(driver);
    }

    public static String takeFullPageScreenshotAsPNG(WebDriver driver, String status, String methodName) {
        return takeScreenshotAsPNG(driver, status, methodName);
    }

    public static String takeElementScreenshotAsBase64(WebElement element) {
        return element.getScreenshotAs(OutputType.BASE64);
    }

    public static String takeElementScreenshotAsPNG(WebElement element, String status, String methodName) {
        try {
            File src = element.getScreenshotAs(OutputType.FILE);
            String path = System.getProperty("user.dir") + "/screenshots/" + status + "_element_" + methodName + ".png";
            File dest = new File(path);
            dest.getParentFile().mkdirs();
            Files.copy(src.toPath(), dest.toPath());
            return path;
        } catch (Exception e) {
            return null;
        }
    }

    public static String takeFullElementScreenshotAsBase64(WebDriver driver, WebElement element) {
        // Full element screenshot using base64 (same as normal element)
        return element.getScreenshotAs(OutputType.BASE64);
    }

    public static String takeFullElementScreenshotAsPNG(WebDriver driver, WebElement element, String status, String methodName) {
        return takeElementScreenshotAsPNG(element, status, methodName);
    }
}
