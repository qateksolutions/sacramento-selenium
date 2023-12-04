package automation_test.mortgage_calculator_parameterized;

import automation_test.BaseClass;
import automation_test.data.DataStore;
import org.testng.annotations.Test;
import page_objects.NavigationBar;

public class CalculateRealAprRateParameterized extends BaseClass {
    @Test(dataProvider = "RealAprRates", dataProviderClass = DataStore.class)
    public void calculateRealApr(String homePrice, String downPayment, String interestRate, String expectedApr) throws Exception {
        new NavigationBar(driver)
                .mouseHoverToRate()
                .navigateToRealApr()
                .waitForPageToLoad()
                .typeHomePrice(homePrice)
                .clickDownPaymentInDollar()
                .typeDownPayment(downPayment)
                .typeInterestRate(interestRate)
                .clickOnCalculateRateButton()
                .validateRealAprRate(expectedApr);
    }
}
