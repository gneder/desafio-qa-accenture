package pages;

import net.datafaker.Faker;
import org.openqa.selenium.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class FormularioPage extends BasePage {

    Faker faker = new Faker();
    Random random = new Random();
    Keys ctrl = Platform.getCurrent().is(Platform.MAC) ? Keys.COMMAND : Keys.CONTROL;
    LocalDate nascimento;

    public String preencherFormulario() {
        String email = faker.internet().emailAddress();
        elemento(By.id("firstName")).sendKeys(faker.name().firstName());
        elemento(By.id("lastName")).sendKeys(faker.name().lastName());
        elemento(By.id("userEmail")).sendKeys(email);
        String[] generos = {"Male", "Female", "Other"};
        elemento(By.xpath("//label[text()='" + generos[random.nextInt(3)] + "']")).click();
        elemento(By.id("userNumber")).sendKeys(faker.number().digits(10));

        String[] materias = {"Maths", "English", "Physics", "Chemistry", "Biology"};
        elemento(By.id("subjectsInput")).sendKeys(materias[random.nextInt(5)]);
        clicavel(By.cssSelector(".subjects-auto-complete__option")).click();

        nascimento = faker.timeAndDate().birthday(18, 60);
        WebElement data = elemento(By.id("dateOfBirthInput"));
        data.sendKeys(Keys.chord(ctrl, "a"));
        data.sendKeys(nascimento.format(DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH)));
        data.sendKeys(Keys.ENTER);

        String [] hobbies = {"1", "2", "3"};
        elemento(By.id("hobbies-checkbox-" + hobbies[random.nextInt(3)] + "")).click();

        String arquivo = System.getProperty("user.dir") + "/src/test/resources/arquivos/teste_qa.txt";
        elemento(By.id("uploadPicture")).sendKeys(arquivo);
        elemento(By.id("currentAddress")).sendKeys(faker.address().fullAddress());
        elemento(By.id("react-select-3-input")).sendKeys("NCR", Keys.ENTER);
        elemento(By.id("react-select-4-input")).sendKeys("Delhi", Keys.ENTER);

        return email;
    }

    public void enviarFormulario() {
        WebElement botao = elemento(By.id("submit"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", botao);
    }

    public String tituloDoPopup() {
        return elemento(By.id("example-modal-sizes-title-lg")).getText();
    }

    public String textoDoPopup() {
        return elemento(By.className("modal-body")).getText();
    }

    public String dataNoPopup() {
        return nascimento.format(DateTimeFormatter.ofPattern("dd MMMM,yyyy", Locale.ENGLISH));
    }

    public void fecharPopup() {
        WebElement fechar = elemento(By.id("closeLargeModal"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", fechar);
    }
}


