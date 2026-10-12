package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class JanelasPage extends BasePage {

    String janelaPrincipal;

    public void abrirNovaJanela() {
        janelaPrincipal = driver.getWindowHandle();
        elemento(By.id("windowButton")).click();

        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        for (String janela : driver.getWindowHandles()) {
            if (!janela.equals(janelaPrincipal)) {
                driver.switchTo().window(janela);
                break;
            }
        }
    }

    public String textoNovaJanela() {
        wait.until(d -> !d.getWindowHandle().equals(janelaPrincipal));
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("sampleHeading"))).get(0).getText();
    }

    public void fecharNovaJanela() {
        driver.close();
        driver.switchTo().window(janelaPrincipal);
    }

    public int quantidadeDeJanelas() {
        return driver.getWindowHandles().size();
    }

}
