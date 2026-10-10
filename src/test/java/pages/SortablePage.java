package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utils.Driver;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class SortablePage {

    WebDriver driver = Driver.getDriver();
    By itens = By.cssSelector("#demo-tabpane-list .list-group-item");

    public List<String> ordemAtual() {
        List<String> textos = new ArrayList<>();
        for (WebElement item : driver.findElements(itens)) {
            textos.add(item.getText());
        }
        return textos;
    }

    public void ordenar(List<String> ordemDesejada) {
        for (int i = 0; i < ordemDesejada.size(); i++) {
            List<WebElement> lista = driver.findElements(itens);

            // acha o item que deveria estar na posição i
            WebElement item = null;
            for (WebElement el : lista) {
                if (el.getText().equals(ordemDesejada.get(i))) {
                    item = el;
                }
            }

            // se já está no lugar, não precisa arrastar
            if (!lista.get(i).getText().equals(ordemDesejada.get(i))) {
                arrastar(item, lista.get(i));
            }
        }
    }

    private void arrastar(WebElement origem, WebElement destino) {
        new Actions(driver).clickAndHold(origem).moveByOffset(0, 5).moveToElement(destino).pause(Duration.ofMillis(300)).release().perform();
    }
}
