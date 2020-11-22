import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPage {
    protected static WebDriver driver;

    private By usernameBy = By.id("RegisterFormView_FullName");
    private By emailBy = By.id("RegisterFormView_RegisterEmail");
    private By passwordBy = By.id("RegisterFormView_Password");
    private By passwordAgainBy = By.id("RegisterFormView_PasswordAgain");

    private By registerBy = By.className("register-button-link");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement registerUserWithRegisteredEmail(String userName, String email, String password, String passwordAgain) {
        driver.findElement(usernameBy).sendKeys(userName);
        driver.findElement(emailBy).sendKeys(email);
        driver.findElement(passwordBy).sendKeys(password);
        driver.findElement(passwordAgainBy).sendKeys(passwordAgain);
        driver.findElement(registerBy).click();
        return new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("[for='RegisterFormView_RegisterEmail']"))));
    }
}