import Utilities.ConfigReader;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase {
    private static AppiumDriverLocalService service;
    public static AndroidDriver<AndroidElement> driver;

    public static AndroidDriver<AndroidElement> capabilities() throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        ConfigReader configReader = new ConfigReader();
        Properties prop = configReader.initProp();
        File app = new File((String) prop.get("apkPath"));

        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, prop.getProperty("emulatorName"));
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, prop.getProperty("automationName"));
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 20);
        capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }
    @BeforeTest
    public void killNode() throws IOException {
        //Runtime.getRuntime().exec("killall node");
    }

    @BeforeSuite
    public void startAppiumServer() throws IOException {

        //Start emulator
        Runtime.getRuntime().exec("src/main/resources/StartEmulator.sh");

        //Start Appium
        final AppiumServiceBuilder builder = new AppiumServiceBuilder();
        builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
        service = AppiumDriverLocalService.buildService(builder);

        if (appiumServerStatus(4723)) {
            service.stop();
        }

        service.start();
    }

    @AfterSuite
    public void stopAppiumServer() {
        service.stop();
    }


    public static boolean appiumServerStatus(int port) {
        boolean serverStatus = false;
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port);
            serverSocket.close();
        } catch (IOException e) {
            serverStatus = true;
        } finally {
            serverSocket = null;
        }
        return serverStatus;
    }

    public void takeScreenShot(String testName) throws IOException {

        File FailureScreenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(FailureScreenshot, new File("src/test/resources/Screenshot/" + testName + ".png"));

    }
}
