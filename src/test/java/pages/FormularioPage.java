package pages;

import net.datafaker.Faker;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Driver;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class FormularioPage {

    WebDriver driver = Driver.getDriver();
    Faker faker = new Faker();
    Random random = new Random();
    Keys ctrl = Platform.getCurrent().is(Platform.MAC) ? Keys.COMMAND : Keys.CONTROL;
    LocalDate nascimento;

    public String preencherFormulario() {
        String email = faker.internet().emailAddress();
        driver.findElement(By.id("firstName")).sendKeys(faker.name().firstName());
        driver.findElement(By.id("lastName")).sendKeys(faker.name().lastName());
        driver.findElement(By.id("userEmail")).sendKeys(email);
        String[] generos = {"Male", "Female", "Other"};
        driver.findElement(By.xpath("//label[text()='" + generos[random.nextInt(3)] + "']")).click();
        driver.findElement(By.id("userNumber")).sendKeys(faker.number().digits(10));

        String[] materias = {"Maths", "English", "Physics", "Chemistry", "Biology"};
        driver.findElement(By.id("subjectsInput")).sendKeys(materias[random.nextInt(5)]);
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.cssSelector(".subjects-auto-complete__option"))).click();

        nascimento = faker.timeAndDate().birthday(18, 60);
        WebElement data = driver.findElement(By.id("dateOfBirthInput"));
        data.sendKeys(Keys.chord(ctrl, "a"));
        data.sendKeys(nascimento.format(DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH)));
        data.sendKeys(Keys.ENTER);

        String [] hobbies = {"1", "2", "3"};
        driver.findElement(By.id("hobbies-checkbox-" + hobbies[random.nextInt(3)] + "")).click();

        String arquivo = System.getProperty("user.dir") + "/src/test/resources/arquivos/teste_qa.txt";
        driver.findElement(By.id("uploadPicture")).sendKeys(arquivo);
        driver.findElement(By.id("currentAddress")).sendKeys(faker.address().fullAddress());
        driver.findElement(By.id("react-select-3-input")).sendKeys("NCR", Keys.ENTER);
        driver.findElement(By.id("react-select-4-input")).sendKeys("Delhi", Keys.ENTER);

        return email;
    }

    public void enviarFormulario() {
        WebElement botao = driver.findElement(By.id("submit"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", botao);
    }

    public String tituloDoPopip() {
        return driver.findElement(By.id("example-modal-sizes-title-lg")).getText();
    }

    public String textoDoPopup() {
        return driver.findElement(By.className("modal-body")).getText();
    }

    public String dataNoPopup() {
        return nascimento.format(DateTimeFormatter.ofPattern("dd MMMM,yyyy", Locale.ENGLISH));
    }

    public void fecharPopip() {
        WebElement fechar = driver.findElement(By.id("closeLargeModal"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", fechar);
    }
}


