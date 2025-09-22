package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.NoSuchElementException;

public class HomePage {
    private WebDriver driver;

    private By signupLoginLink = By.linkText("Signup / Login");
    private By logoutLink = By.linkText("Logout");
    private By usuarioLogadoLabel = By.cssSelector("li > a[href='/account']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void abrir() {
        driver.get("https://automationexercise.com");
    }

    public void clicarSignupLogin() {
        driver.findElement(signupLoginLink).click();
    }

    public boolean isUsuarioLogado() {
        try {
            return driver.findElement(logoutLink).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void logout() {
        if (isUsuarioLogado()) {
            driver.findElement(logoutLink).click();
        }
    }
}
