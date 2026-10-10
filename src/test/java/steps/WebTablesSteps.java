package steps;

import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import pages.WebTablesPage;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WebTablesSteps {

    WebTablesPage tabela = new WebTablesPage();
    String email;
    List<String> emails = new ArrayList<>();

    @Quando("crio um novo registro")
    public void crioUmNovoRegistro() {
        email = tabela.criarRegistro();
        assertTrue(tabela.registroExiste(email));
    }

    @E("edito o registro criado")
    public void editarRegistro() {
        tabela.editarIdade(email, "30");
        assertEquals("30", tabela.idadeDoRegistro(email));
    }

    @E("deleto o registro criado")
    public void deletarRegistro() {
        tabela.deletar(email);
    }

    @Então("o registro não deve aparecer na tabela")
    public void validarRegistroDeletado(){
        assertFalse(tabela.registroExiste(email));
    }

    @Quando("crio {int} registros")
    public void criarRegistros(int quantidade){
        tabela.mostrarMaisLinhas();
        for (int i = 0; i < quantidade; i++) {
         emails.add(tabela.criarRegistro());
        }
    }

    @Então("os {int} registros devem aparecer na tabela")
    public void validarRegistros(int quantidade) {
        assertEquals(quantidade, emails.size());
        for (String e : emails) {
            assertTrue(tabela.registroExiste(e), "Não achei o registro:" + e);
        }
    }

    @Quando("deleto todos os registros criados")
    public void deletarRegistros() {
        for (String e : emails) {
            tabela.deletar(e);
        }
    }

    @Então("nenhum registro criado deve aparecer na tabela")
    public void validarDeletados() {
        for (String e : emails) {
            assertFalse(tabela.registroExiste(e));
        }

    }

    @E("deleto apenas o primeiro registro criado")
    public void deletarPrimeiroRegistro() {
        tabela.deletar(emails.get(0));
    }

    @Então("os outros {int} registros devem continuar na tabela")
    public void validarOutrosRegistros(int quantidade) {
        List<String> outros = emails.subList(1, emails.size());
        assertEquals(quantidade, outros.size());
        for (String e : outros) {
            assertTrue(tabela.registroExiste(e), "Registro sumiu indevidamente:" + e);
        }
    }
}
