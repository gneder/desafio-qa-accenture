package pages;

import net.datafaker.Faker;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class WebTablesPage extends BasePage {

    Faker faker = new Faker();
    Keys ctrl = Platform.getCurrent().is(Platform.MAC) ? Keys.COMMAND : Keys.CONTROL;

    // cria um registro e devolve o registro criado
    public String criarRegistro() {
       String email = faker.internet().emailAddress();

       elemento(By.id("addNewRecordButton")).click();
       elemento(By.id("firstName")).sendKeys(faker.name().firstName());
       elemento(By.id("lastName")).sendKeys(faker.name().lastName());
       elemento(By.id("userEmail")).sendKeys(email);
       elemento(By.id("age")).sendKeys(String.valueOf(faker.number().numberBetween(18, 30)));
       elemento(By.id("salary")).sendKeys(String.valueOf(faker.number().numberBetween(1000, 9000 )));
       elemento(By.id("department")).sendKeys("QA");
       elemento(By.id("submit")).click();

       return email;
    }

    public void editarIdade(String email, String novaIdade) {
        String xpath = String.format("//tr[td[text()='%s']]//span[starts-with(@id,'edit-record')]", email);

        clicavel(By.xpath(xpath)).click();

        elemento(By.id("age")).sendKeys(Keys.chord(ctrl, "a"));
        elemento(By.id("age")).sendKeys(novaIdade);
        elemento(By.id("submit")).click();
    }

    public boolean registroExiste(String email) {
        return driver.getPageSource().contains(email);
    }

    public String idadeDoRegistro(String email) {
        return elemento(By.xpath("//td[text()='" + email + "']/../td[3]")).getText();
    }

    public void mostrarMaisLinhas() {
        new Select(elemento(By.cssSelector(".pagination select"))).selectByValue("50");
    }

    // excluir um registro adicionado remove todos os outros adicionados(bug) e por isso só clica se a linha existir
    public void deletar(String email) {
        List<WebElement> botoes = driver.findElements(By.xpath("//td[text()='" + email + "']/..//span[@title='Delete']"));
        if (!botoes.isEmpty()) {
            botoes.get(0).click();
        }
    }

}
