package automation_test.mortgage_calculator_parameterized;

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
import page_objects.Home;
import utilities.DateUtils;
import utilities.ReadConfigFiles;
import utilities.SqlConnector;

import java.sql.ResultSet;

public class CalculateMonthlyPaymentParameterized {
    private static final Logger LOGGER = LogManager.getLogger(CalculateMonthlyPaymentParameterized.class);
    WebDriver driver;

    @BeforeMethod
    public void browserInitialization() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);

        LOGGER.info("----------Test Name: Calculate Monthly payment-------------");
        String browserUrl = ReadConfigFiles.getPropertyValues("Url");
        ActOn.browser(driver).openBrowser(browserUrl);
    }

    @Test
    public void calculateMonthlyPayment() throws Exception {
        String[] date = DateUtils.returnNextMonth();
        String month = date[0];
        String year = date[1];

        ResultSet rs = SqlConnector.readDate("select * from monthly_mortgage");
        while (rs.next()) {
            new Home(driver)
                    .typeHomePrice(rs.getString("homevalue"))
                    .typeDownPayment(rs.getString("downpayment"))
                    .clickDownPaymentInDollar()
                    .typeLoanAmount(rs.getString("loanamount"))
                    .typeInterestRate(rs.getString("interestrate"))
                    .typeLoanTermYears(rs.getString("loanterm"))
                    .selectMonth(month).typeYear(year)
                    .typePropertyTax(rs.getString("propertytax"))
                    .typePMI(rs.getString("pmi"))
                    .typeHomeOwnerInsurance(rs.getString("homeownerinsurance"))
                    .typeMonthlyHOA(rs.getString("monthlyhoa"))
                    .selectLoanType(rs.getString("loantype"))
                    .selectBuyOrRefinance(rs.getString("buyorrefi"))
                    .clickOnCalculateButton()
                    .validateTotalMonthlyPayment(rs.getString("totalmonthlypayment"));
        }
    }

    @AfterMethod
    public void browserCleanup() {
        ActOn.browser(driver).closeBrowser();
        LOGGER.info("----------End Test: Calculate Monthly payment-------------");
    }
}
