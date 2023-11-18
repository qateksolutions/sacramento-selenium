package automation_test.php_travel;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.ScreenCapture;

public class ValidatePageTitle {
    WebDriver driver;

    @BeforeMethod
    public void browserInitialization() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.get("https://phptravels.com/demo");

    }

    @Test
    public void verifyHomePageTitle() {
        String expectedTitle = "Book Your Free Demo Now - Phptravels";
        String actualTile = driver.getTitle();
        Assert.assertEquals(actualTile, expectedTitle);
    }

    @AfterMethod
    public void browserCleanup(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            ScreenCapture.getScreenShot(driver);
        }
        driver.quit();
    }
}
