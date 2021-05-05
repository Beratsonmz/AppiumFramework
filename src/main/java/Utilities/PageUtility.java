package Utilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class PageUtility {
    AndroidDriver<AndroidElement> driver;

    public PageUtility(AndroidDriver<AndroidElement> driver) {
        this.driver = driver;
    }

    public void scrollIntoText(String text) {
        System.out.println("Scrolling into text: " + text);
        driver.findElementByAndroidUIAutomator
                ("new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + text + "\"))").click();
    }

    public void scrollIntoView(String text) {
        System.out.println("Scrolling into view: " + text);
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()" +
                ".resourceId(\"com.androidsample.generalstore:id/rvProductList\")).scrollIntoView(" +
                "new UiSelector().text(\"" + text + "\").instance(0));").click();
    }
}
