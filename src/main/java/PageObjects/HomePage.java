package PageObjects;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    public HomePage(AndroidDriver<AndroidElement> driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id = "android:id/text1")
    private WebElement countryDropDown;

    @AndroidFindBy(uiAutomator = "new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"))")
    private WebElement countryDropDownItem;

    @AndroidFindBy(className = "android.widget.EditText")
    private WebElement nameField;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/radioMale")
    private WebElement maleButton;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/radioFemale")
    private WebElement femaleButton;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
    private WebElement button;

    @AndroidFindBy(xpath = "//android.widget.Toast[1]")
    private WebElement toastMessage;

    public WebElement getCountryDropDown() {
        System.out.println("Finding Country Drop Down ...");
        return countryDropDown;
    }

    public WebElement getCountryDropDownItem() {
        System.out.println("Finding Country Drop Down Item ...");
        return countryDropDownItem;
    }

    public WebElement getNameField() {
        System.out.println("Finding Name Field ...");
        return nameField;
    }

    public WebElement getMaleButton() {
        System.out.println("Finding Male Button ...");
        return maleButton;
    }

    public WebElement getFemaleButton() {
        System.out.println("Finding Female Button ...");
        return femaleButton;
    }

    public WebElement getButton() {
        System.out.println("Finding Let's Shopping Button ...");
        return button;
    }

    public WebElement getToastMessage() {
        System.out.println("Finding Toast Message ...");
        return toastMessage;
    }
}
