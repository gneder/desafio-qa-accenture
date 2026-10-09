package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.Driver;

public class HomePage {

    WebDriver driver = Driver.getDriver();

    public void abrir() {
        driver.get("https://demoqa.com/");
    }

    public void clicarNoCard(String nome) {
        driver.findElement(By.xpath("//h5[text()='" + nome + "']")).click();
    }

    public void clicarNoMenu(String nome) {
        driver.findElement(By.xpath("//span[text()='" + nome + "']")).click();
    }
}
