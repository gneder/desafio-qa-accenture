package steps;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utils.Driver;

public class Hooks {

    @After("@front")
    public void depois(Scenario cenario) {
        if (cenario.isFailed()) {
            byte[] print = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            cenario.attach(print, "image/png", "screenshot");
        }
        Driver.fecharDriver();
    }

}
