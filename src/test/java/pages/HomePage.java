package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;
    private By signupLoginLink = By.linkText("Signup / Login");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void abrir() {
        driver.get("https://automationexercise.com");
    }

    public void clicarSignupLogin() {
        driver.findElement(signupLoginLink).click();
    }
}
