package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Driver;

import java.time.Duration;
import java.util.List;

public class BasePage {

    WebDriver driver = Driver.getDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    // espera o elemento aparecer na página antes de devolver
    WebElement elemento(By by) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    List<WebElement> elementos(By by) {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    WebElement clicavel(By by) {
        return wait.until(ExpectedConditions.elementToBeClickable(by));
    }
}
