package automation_test.mortgage_calculator_parameterized;

import automation_test.data.DataStore;
import command_providers.ActOn;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page_objects.NavigationBar;
import utilities.ReadConfigFiles;

public class CalculateRealAprRateParameterized {
    private static final Logger LOGGER = LogManager.getLogger(CalculateRealAprRateParameterized.class);
    WebDriver driver;

    @BeforeMethod
    public void browserInitialization() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);

        LOGGER.info("----------Test Name: Calculate Real Apr-------------");
        String browserUrl = ReadConfigFiles.getPropertyValues("Url");
        ActOn.browser(driver).openBrowser(browserUrl);
    }

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

    @AfterMethod
    public void browserCleanup() {
        ActOn.browser(driver).closeBrowser();
        LOGGER.info("----------End Test: Calculate Real Apr-------------");
    }
}
