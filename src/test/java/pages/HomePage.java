package pages;

import org.openqa.selenium.By;

public class HomePage extends BasePage {


    public void abrir() {
        driver.get("https://demoqa.com/");
    }

    public void clicarNoCard(String nome) {
        elemento(By.xpath("//h5[text()='" + nome + "']")).click();
    }

    public void clicarNoMenu(String nome) {
        elemento(By.xpath("//span[text()='" + nome + "']")).click();
    }
}
