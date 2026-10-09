package steps;

import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import pages.WebTablesPage;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WebTablesSteps {

    WebTablesPage tabela = new WebTablesPage();
    String email;

    @Quando("crio um novo registro")
    public void crioUmNovoRegistro() {
        email = tabela.criarRegistro();
        assertTrue(tabela.registroExiste(email));
    }

    @E("edito o registro criado")
    public void editarRegistro() {
        tabela.editarIdade(email, "30");
        assertTrue(tabela.linhaDoRegistro(email).contains("30"));
    }

    @E("deleto o registro criado")
    public void deletarRegistro() {
        tabela.deletar(email);
    }

    @Então("o registro não deve aparecer na tabela")
    public void validarRegistroDeletado(){
        assertFalse(tabela.registroExiste(email));
    }
}
