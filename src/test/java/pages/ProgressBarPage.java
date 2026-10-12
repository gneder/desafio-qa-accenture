package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProgressBarPage extends BasePage {

    By botaoStartStop = By.id("startStopButton");

    public int valorDaBarra() {
        String valor = elemento(By.xpath("//div[@role='progressbar']")).getDomAttribute("aria-valuenow");
        return Integer.parseInt(valor);
    }

    public void iniciarPararAntesDe25() {
        elemento(botaoStartStop).click();
        new WebDriverWait(driver, Duration.ofSeconds(10)).pollingEvery(Duration.ofMillis(50)).until(d -> valorDaBarra() >= 18);
        elemento(botaoStartStop).click();
    }

    public void iniciarAte100() {
        elemento(botaoStartStop).click();
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOfElementLocated(By.id("resetButton")));
    }

    public void resetar() {
        elemento(By.id("resetButton")).click();
    }

}
