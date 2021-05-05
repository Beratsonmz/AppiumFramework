package Utilities;

import org.testng.annotations.DataProvider;

public class TestData {

    @DataProvider(name = "NameFieldData")
    public static Object[][] getDataForEditField() {
        Object[][] testData = new Object[][]{{"hello"}};
        return testData;
    }


}
