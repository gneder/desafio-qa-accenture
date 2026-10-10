package steps;

import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import pages.ProgressBarPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProgressBarSteps {

    ProgressBarPage barra = new ProgressBarPage();

    @Quando("inicio a barra e paro antes de 25%")
    public void pararAntesDe25() throws InterruptedException {
        barra.iniciarPararAntesDe25();
    }

    @Então("o valor da barra deve ser menor ou igual a 25")
    public void validarMenorDe25() {
        int valor = barra.valorDaBarra();
        assertTrue(valor <= 25, "A barra parou em" + valor);

    }

    @Quando("inicio a barra de novo até chegar em 100%")
    public void irAte100() {
        barra.iniciarAte100();
        assertEquals(100, barra.valorDaBarra());
    }

    @E("reseto a barra")
    public void resetarBarra() {
        barra.resetar();
    }

    @Então("o valor da barra deve ser 0")
    public void validarZerada() {
        assertEquals(0, barra.valorDaBarra());
    }
}
