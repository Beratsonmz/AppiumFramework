package PageObjects;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class CartPage {
    public CartPage(AndroidDriver<AndroidElement> driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
    @AndroidFindBy(id = "com.androidsample.generalstore:id/productName")
    private WebElement productName;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/totalAmountLbl")
    private WebElement totalPrice;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/productPrice")
    private WebElement productPrice;

    public WebElement getProductName(){
        System.out.println("Finding Product Name ...");
        return productName;
    }
    public WebElement getProductPrice(){
        System.out.println("Finding Total Price ...");

        return productPrice;
    }
    public WebElement getTotalPrice(){
        System.out.println("Finding Total Price ...");

        return totalPrice;
    }
}
