package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SignupPage {
    private WebDriver driver;

    // Locators
    private By nameField = By.name("name");
    private By emailField = By.xpath("//input[@data-qa='signup-email']");
    private By signupButton = By.xpath("//button[@data-qa='signup-button']");
    private By genderMr = By.id("id_gender1");
    private By passwordField = By.id("password");
    private By daysField = By.id("days");
    private By monthsField = By.id("months");
    private By yearsField = By.id("years");
    private By firstName = By.id("first_name");
    private By lastName = By.id("last_name");
    private By company = By.id("company");
    private By address1 = By.id("address1");
    private By state = By.id("state");
    private By city = By.id("city");
    private By zipcode = By.id("zipcode");
    private By mobile = By.id("mobile_number");
    private By createAccountButton = By.xpath("//button[@data-qa='create-account']");
    private By msgContaCriada = By.xpath("//h2[@data-qa='account-created']");

    public SignupPage(WebDriver driver) {
        this.driver = driver;
    }

    public void preencherFormulario(String nome, String email, String senha) {
        driver.findElement(nameField).sendKeys(nome);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(signupButton).click();
        driver.findElement(genderMr).click();
        driver.findElement(passwordField).sendKeys(senha);
        driver.findElement(daysField).sendKeys("10");
        driver.findElement(monthsField).sendKeys("May");
        driver.findElement(yearsField).sendKeys("1990");
        driver.findElement(firstName).sendKeys("Lucas");
        driver.findElement(lastName).sendKeys("Gomes");
        driver.findElement(company).sendKeys("OpenAI");
        driver.findElement(address1).sendKeys("Rua Teste 123");
        driver.findElement(state).sendKeys("SP");
        driver.findElement(city).sendKeys("São Paulo");
        driver.findElement(zipcode).sendKeys("01000-000");
        driver.findElement(mobile).sendKeys("11999999999");
    }

    public void criarConta() {
        driver.findElement(createAccountButton).click();
    }

    public WebElement getMsgContaCriada() {
        return driver.findElement(msgContaCriada);
    }
}