package steps;

import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import pages.JanelasPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JanelasSteps {

    JanelasPage janelas = new JanelasPage();

    @Quando("clico no botão New Window")
    public void clicarBotaoNewWindow() {
        janelas.abrirNovaJanela();
    }

    @Entao("a nova janela deve mostrar {string}")
    public void validarTextoNovaJanela(String texto) {
        assertEquals(2, janelas.quantidadeDeJanelas());
        assertEquals(texto, janelas.textoNovaJanela());
    }

    @E("fecho a nova janela")
    public void fecharNovaJanela() {
        janelas.fecharNovaJanela();
    }
}
