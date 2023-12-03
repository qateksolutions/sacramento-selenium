package automation_test.mortgage_calculator;

import automation_test.BaseClass;
import org.testng.annotations.Test;
import page_objects.NavigationBar;

public class CalculateRealAprRate extends BaseClass {
    @Test
    public void calculateRealApr() throws Exception {
        new NavigationBar(driver)
                .mouseHoverToRate()
                .navigateToRealApr()
                .waitForPageToLoad()
                .typeHomePrice("200000")
                .clickDownPaymentInDollar()
                .typeDownPayment("15000")
                .typeInterestRate("3")
                .clickOnCalculateRateButton()
                .validateRealAprRate("3.130%");
    }
}
