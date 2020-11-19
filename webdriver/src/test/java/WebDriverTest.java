import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class WebDriverTest {
    private WebDriver driver;

    @BeforeMethod (alwaysRun = true)
    public void browserSetup() {
        driver = new ChromeDriver();
    }

    @Test
    public void commonSearchResultsNotEmpty() {
        String expected = "К сожалению, Ваш адрес электронной почты не зарегистрирован. " +
                "Убедитесь, что вы вводите правильный адрес.Если у вас нету аккаунта, вы можете легко зарегистрироваться сейчас!";
        String message = "Messages differ";
        String email = "someemail@gmail.com";
        String password = "testpassword123";

        driver.get("https://www.lcwaikiki.by/ru-RU/BY/login");
        new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(CustomConditions.jQueryAJAXCompleted());

        driver.findElement(By.id("LoginEmail")).sendKeys(email);
        driver.findElement(By.id("Password")).sendKeys(password);
        driver.findElement(By.id("loginLink")).click();

        WebElement alertError = new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(ExpectedConditions.presenceOfElementLocated(By.className("alert-error")));
        Assert.assertEquals(message, alertError.getText(), expected);
    }

    @AfterMethod (alwaysRun = true)
    public void quitBrowser() {
        driver.quit();
        driver = null;
    }
}
