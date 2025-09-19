package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private WebDriver driver;

    private By loginEmail = By.xpath("//input[@data-qa='login-email']");
    private By loginPassword = By.xpath("//input[@data-qa='login-password']");
    private By loginButton = By.xpath("//button[@data-qa='login-button']");
    private By usuarioLogado = By.xpath("//li//a[contains(text(), 'Logged in as')]");
    private By msgErro = By.xpath("//p[contains(text(),'Your email or password is incorrect!')]");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String email, String senha) {
        driver.findElement(loginEmail).sendKeys(email);
        driver.findElement(loginPassword).sendKeys(senha);
        driver.findElement(loginButton).click();
    }

    public WebElement getUsuarioLogado() {
        return driver.findElement(usuarioLogado);
    }

    public WebElement getMsgErro() {
        return driver.findElement(msgErro);
    }
}