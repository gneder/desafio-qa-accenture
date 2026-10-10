package steps;

import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import pages.FormularioPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FormularioSteps {

    FormularioPage formulario = new FormularioPage();
    String email;

    @Quando("preencho o formulario com dados aleatorios")
    public void preencherFormulario() {
        email = formulario.preencherFormulario();
    }

    @E("envio o formulario")
    public void enviarFormulario() {
        formulario.enviarFormulario();
    }

    @Entao("deve aparecer o poopup de confirmacao")
    public void validarPopupConfirmacao() {
        assertEquals("Thanks for submitting the form", formulario.tituloDoPopip());
    }

    @E("o popup deve mostrar os dados preenchidos")
    public void validarDadosPopup() {
        assertTrue(formulario.textoDoPopup().contains(email));
        assertTrue(formulario.textoDoPopup().contains("11 February,1988"));
    }

    @E("fecho o popup")
    public void fecharPopup() {
        formulario.fecharPopip();
    }
}
