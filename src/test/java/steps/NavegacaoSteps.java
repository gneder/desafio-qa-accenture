package steps;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import pages.HomePage;

public class NavegacaoSteps {

    HomePage home = new HomePage();

    @Dado("que acesso o site demoqa")
    public void acessarSiteDemoQa() {
        home.abrir();
    }

    @E("clico na opção {string}")
    public void clicarOpcao(String opcao) {
        home.clicarNoCard(opcao);
    }

    @E("clico no submenu {string}")
    public void clicarSubmenu(String submenu) {
        home.clicarNoMenu(submenu);
    }
}
