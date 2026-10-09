package steps;

import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import pages.FormularioPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FormularioSteps {

    FormularioPage formulario = new FormularioPage();

    @Quando("preencho o formulario com dados aleatorios")
    public void preencherFormulario() {
        formulario.preencherFormulario();
    }

    @E("envio o formulario")
    public void enviarFormulario() {
        formulario.enviarFormulario();
    }

    @Entao("deve aparecer o poopup de confirmacao")
    public void validarPopupConfirmacao() {
        assertEquals("Thanks for submitting the form", formulario.tituloDoPopip());
    }

    @E("fecho o popup")
    public void fecharPopup() {
        formulario.fecharPopip();
    }
}
