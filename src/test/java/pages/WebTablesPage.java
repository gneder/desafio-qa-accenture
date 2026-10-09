package pages;

import net.datafaker.Faker;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Driver;

import java.time.Duration;

public class WebTablesPage {

    WebDriver driver = Driver.getDriver();
    Faker faker = new Faker();
    Keys ctrl = Platform.getCurrent().is(Platform.MAC) ? Keys.COMMAND : Keys.CONTROL;

    // cria um registro e devolve o registro criaro
    public String criarRegistro() {
       String email = faker.internet().emailAddress();

       driver.findElement(By.id("addNewRecordButton")).click();
       driver.findElement(By.id("firstName")).sendKeys(faker.name().firstName());
       driver.findElement(By.id("lastName")).sendKeys(faker.name().lastName());
       driver.findElement(By.id("userEmail")).sendKeys(email);
       driver.findElement(By.id("age")).sendKeys(String.valueOf(faker.number().numberBetween(18, 60)));
       driver.findElement(By.id("salary")).sendKeys(String.valueOf(faker.number().numberBetween(1000, 9000 )));
       driver.findElement(By.id("department")).sendKeys("QA");
       driver.findElement(By.id("submit")).click();

       return email;
    }

    public void editarIdade(String email, String notaIdade) {
        String xpath = String.format("//tr[td[text()='%s']]//span[starts-with(@id,'edit-record')]", email);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement editar = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        editar.click();

        driver.findElement(By.id("age")).sendKeys(Keys.chord(ctrl, "a"));
        driver.findElement(By.id("age")).sendKeys(notaIdade);
        driver.findElement(By.id("submit")).click();
    }

    public void deletar(String email) {
        driver.findElement(By.xpath("//td[text()='" + email + "']/..//span[@title='Delete']")).click();
    }

    public boolean registroExiste(String email) {
        return driver.getPageSource().contains(email);
    }

    public String linhaDoRegistro(String email) {
        return driver.findElement(By.xpath("//td[text()='" + email + "']/..")).getText();
    }

    public void mostrarMaisLinhas() {
        new Select(driver.findElement(By.cssSelector("select[aria-label='rows per page']"))).selectByValue("50");
    }

}
