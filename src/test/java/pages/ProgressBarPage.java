package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Driver;

import java.time.Duration;

public class ProgressBarPage {

    WebDriver driver = Driver.getDriver();
    By botaoStartStop = By.id("startStopButton");

    public int valorDaBarra() {
        String valor = driver.findElement(By.xpath("//div[@role='progressbar']")).getDomAttribute("aria-valuenow");
        return Integer.parseInt(valor);
    }

    public void iniciarPararAntesDe25() {
        driver.findElement(botaoStartStop).click();
        new WebDriverWait(driver, Duration.ofSeconds(10)).pollingEvery(Duration.ofMillis(50)).until(d -> valorDaBarra() >= 18);
        driver.findElement(botaoStartStop).click();
    }

    public void iniciarAte100() {
        driver.findElement(botaoStartStop).click();
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOfElementLocated(By.id("resetButton")));
    }

    public void resetar() {
        driver.findElement(By.id("resetButton")).click();
    }

}
