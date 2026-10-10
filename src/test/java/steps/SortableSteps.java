package steps;

import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import pages.SortablePage;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class SortableSteps {

    SortablePage sortable = new SortablePage();
    List<String> crescente = List.of("One", "Two", "Three", "Four", "Five", "Six");

    @E("inverto a ordem da lista")
    public void inverterLista() {
        sortable.ordenar(List.of("Six", "Five", "Four", "Three", "Two", "One"));
        assertNotEquals(crescente, sortable.ordemAtual());
    }

    @Quando("arrasto os itens para a ordem crescente")
    public void ordenarCrescente() {
        sortable.ordenar(crescente);
    }

    @Então("a lista deve ficar na ordem One, Two, Three, Four, Five, Six")
    public void validarOrdem(){
        assertEquals(crescente, sortable.ordemAtual());
    }

}
