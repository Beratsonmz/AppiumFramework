import PageObjects.CartPage;
import PageObjects.HomePage;
import PageObjects.ProductListPage;
import Utilities.PageUtility;
import Utilities.TestData;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.testng.Assert;
import org.testng.annotations.*;


import java.net.MalformedURLException;

public class TestEx extends TestBase {
    public AndroidDriver<AndroidElement> driver;

    @BeforeMethod
    public void setUp() throws MalformedURLException {
        driver = capabilities();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test(dataProvider = "NameFieldData", dataProviderClass = TestData.class)
    public void Test1(String input) {

        HomePage homePage = new HomePage(driver);
        PageUtility u = new PageUtility(driver);
        homePage.getNameField().sendKeys(input);
        homePage.getFemaleButton().click();
        homePage.getCountryDropDown().click();
        u.scrollIntoText("Argentina");
        homePage.getButton().click();

    }

    @Test
    public void Test2() {

        HomePage homePage = new HomePage(driver);
        PageUtility u = new PageUtility(driver);
        homePage.getButton().click();

        String toastMessage = homePage.getToastMessage().getAttribute("name");

        Assert.assertEquals(toastMessage, "Please enter your namee");

    }

    @Test
    public void Test3() throws InterruptedException {

        HomePage homePage = new HomePage(driver);
        ProductListPage productListPage = new ProductListPage(driver);
        CartPage cartPage = new CartPage(driver);
        PageUtility u = new PageUtility(driver);

        homePage.getNameField().sendKeys("bero");
        homePage.getButton().click();
        u.scrollIntoView("Jordan 6 Rings");
        String productName = "";
        int count = driver.findElements(productListPage.getProducts()).size();
        for (int i = 0; i < count; i++) {
            productName = driver.findElements(productListPage.getProducts()).get(i).getText();

            if (productName.equals("Jordan 6 Rings")) {
                driver.findElements(productListPage.getAddToCartButtons()).get(i).click();
                break;
            }
        }
        productListPage.getCartButton().click();
        Thread.sleep(3000);
        String cartItemName = cartPage.getProductName().getText();

        System.out.println(productName);
        System.out.println(cartItemName);

        Assert.assertEquals(productName, cartItemName);

    }

    @Test
    public void Test4() throws InterruptedException {

        HomePage homePage = new HomePage(driver);
        ProductListPage productListPage = new ProductListPage(driver);
        CartPage cartPage = new CartPage(driver);


        homePage.getNameField().sendKeys("bero");
        homePage.getButton().click();

        int count = driver.findElements(productListPage.getProducts()).size();

        String productName = "";
        String productPrice = "";

        for (int i = 0; i < count; i++) {
            productName = driver.findElements(productListPage.getProducts()).get(i).getText();
            productPrice = driver.findElements(productListPage.getPrices()).get(i).getText();
            if (productName.equals("Air Jordan 1 Mid SE")) {
                driver.findElements(productListPage.getAddToCartButtons()).get(i).click();
                break;
            }

        }

        productListPage.getCartButton().click();
        Thread.sleep(3000);
        String cartItemName = cartPage.getProductName().getText();
        String cartItemPrice = cartPage.getProductPrice().getText();
        System.out.println(productName + "-" + productPrice);
        System.out.println(cartItemName + "-" + cartItemPrice);
        Assert.assertEquals(productName, cartItemName);
        Assert.assertEquals(productPrice, cartItemPrice);

    }

    @Test
    public void Test5() throws InterruptedException {

        HomePage homePage = new HomePage(driver);
        ProductListPage productListPage = new ProductListPage(driver);
        CartPage cartPage = new CartPage(driver);

        homePage.getNameField().sendKeys("bero");
        homePage.getButton().click();

        String productPrice = driver.findElements(productListPage.getPrices()).get(0).getText();
        driver.findElements(productListPage.getAddToCartButtons()).get(0).click();

        String productPrice2 = driver.findElements(productListPage.getPrices()).get(1).getText();
        driver.findElements(productListPage.getAddToCartButtons()).get(1).click();

        productListPage.getCartButton().click();
        Thread.sleep(3000);

        float first = Float.parseFloat(productPrice.replace("$", ""));
        float second = Float.parseFloat(productPrice2.replace("$", ""));
        float total = Float.parseFloat(cartPage.getTotalPrice().getText().replace("$ ", ""));
        System.out.println("First Item's Price:" + first + '\n' + "Second Item's Price:" + second + '\n' + "Total Price:" + total);
        Assert.assertEquals(first + second, total);

    }


}
