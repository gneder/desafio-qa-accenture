package pages;

import net.datafaker.Faker;
import org.openqa.selenium.*;
import utils.Driver;

import java.util.Random;

public class FormularioPage {

    WebDriver driver = Driver.getDriver();
    Faker faker = new Faker();
    Random random = new Random();
    Keys ctrl = Platform.getCurrent().is(Platform.MAC) ? Keys.COMMAND : Keys.CONTROL;

    public void preencherFormulario() {
        driver.findElement(By.id("firstName")).sendKeys(faker.name().firstName());
        driver.findElement(By.id("lastName")).sendKeys(faker.name().lastName());
        driver.findElement(By.id("userEmail")).sendKeys(faker.internet().emailAddress());
        String[] generos = {"Male", "Female", "Other"};
        driver.findElement(By.xpath("//label[text()='" + generos[random.nextInt(3)] + "']")).click();
        driver.findElement(By.id("userNumber")).sendKeys(faker.number().digits(10));

        WebElement data = driver.findElement(By.id("dateOfBirthInput"));
        data.sendKeys(Keys.chord(ctrl, "a"));
        data.sendKeys("11 Fev 1988");
        data.sendKeys(Keys.ENTER);

//        driver.findElement(By.id("subjectsInput")).sendKeys("Maths", Keys.ENTER);
        String [] hobbies = {"1", "2", "3"};
        driver.findElement(By.id("hobbies-checkbox-" + hobbies[random.nextInt(3)] + "")).click();

        String arquivo = System.getProperty("user.dir") + "/src/test/resources/arquivos/teste_qa.txt";
        driver.findElement(By.id("uploadPicture")).sendKeys(arquivo);
        driver.findElement(By.id("currentAddress")).sendKeys(faker.address().fullAddress());
        driver.findElement(By.id("react-select-3-input")).sendKeys("NCR", Keys.ENTER);
        driver.findElement(By.id("react-select-4-input")).sendKeys("Delhi", Keys.ENTER);

    }

    public void enviarFormulario() {
        WebElement botao = driver.findElement(By.id("submit"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", botao);
    }

    public String tituloDoPopip() {
        return driver.findElement(By.id("example-modal-sizes-title-lg")).getText();
    }

    public void fecharPopip() {
        WebElement fechar = driver.findElement(By.id("closeLargeModal"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", fechar);
    }
}


