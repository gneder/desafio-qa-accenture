package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Driver;

import java.time.Duration;

public class JanelasPage {

    WebDriver driver = Driver.getDriver();
    String janelaPrincipal;

    public void abrirNovaJanela() {
        janelaPrincipal = driver.getWindowHandle();
        driver.findElement(By.id("windowButton")).click();

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.numberOfWindowsToBe(2));

        for (String janela : driver.getWindowHandles()) {
            if (!janela.equals(janelaPrincipal)) {
                driver.switchTo().window(janela);
                break;
            }
        }
    }

    public String textoNovaJanela() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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
