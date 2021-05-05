package PageObjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class ProductListPage {
    public ProductListPage(AndroidDriver<AndroidElement> driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);

    }

    private final By products = By.id("com.androidsample.generalstore:id/productName");
    private final By addToCartButtons = By.id("com.androidsample.generalstore:id/productAddCart");
    private final By prices = By.id("com.androidsample.generalstore:id/productPrice");

    @AndroidFindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
    private WebElement cartButton;

    public WebElement getCartButton(){
        System.out.println("Finding Cart Button ...");
        return cartButton;
    }
    public By getProducts() {
        return products;
    }
    public By getPrices(){
        return prices;
    }

    public By getAddToCartButtons() {
        return addToCartButtons;
    }
}
