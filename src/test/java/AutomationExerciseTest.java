
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.HomePage;
import pages.LoginPage;
import pages.SignupPage;

import java.time.Duration;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AutomationExerciseTest {
    private static WebDriver driver;
    private static HomePage homePage;
    private static SignupPage signupPage;
    private static LoginPage loginPage;

    @BeforeAll
    static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        homePage = new HomePage(driver);
        signupPage = new SignupPage(driver);
        loginPage = new LoginPage(driver);
    }

    @AfterAll
    static void tearDown() {
        driver.quit();
    }
    @BeforeEach
    void ensureLoggedOut() {
        homePage.abrir();
        if (homePage.isUsuarioLogado()) {
            homePage.logout();
        }
    }
    @Test
    @Order(1)
    void testCadastroUsuario() {
        homePage.abrir();
        homePage.clicarSignupLogin();
        String emailUnico = "lucas" + System.currentTimeMillis() + "@gmail.com";
        signupPage.preencherFormulario("Lucas Teste", emailUnico, "123456");
        signupPage.criarConta();
        Assertions.assertTrue(signupPage.getMsgContaCriada().isDisplayed(), "Conta deveria ter sido criada!");
    }

    @Test
    @Order(2)
    void testLoginUsuario() {
        homePage.abrir();
        homePage.clicarSignupLogin();
        loginPage.login("lucasinfsasanet@gmail.com", "123456");
        Assertions.assertTrue(loginPage.getUsuarioLogado().isDisplayed(), "Usuário deveria estar logado!");

    }

    @Test
    @Order(3)
    void testFalseLoginUsuario() {
        homePage.abrir();
        homePage.clicarSignupLogin();
        loginPage.login("errortestoloko@gmail.com", "errortestoloko");
        Assertions.assertEquals("Your email or password is incorrect!", loginPage.getMsgErro().getText());
    }
}
