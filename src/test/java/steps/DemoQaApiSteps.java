package steps;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DemoQaApiSteps {

    String url = "https://demoqa.com";
    String usuario;
    String senha;
    String userId;
    String token;
    List<String> livros;
    List<String> livrosAlugados = new ArrayList<>();
    Response response;

    @Dado("que eu crio um usuário")
    public void criarUsuario() {
        usuario = "neder" + System.currentTimeMillis();
        senha = "@Gns" + System.currentTimeMillis();

        response = given().contentType("application/json").body(credenciais()).when().post(url + "/Account/v1/User");

        assertEquals(201, response.getStatusCode(), "Erro ao criar usuário:" + response.asString());
        userId = response.jsonPath().getString("userID");

    }

    @Dado("gero o token de autenticação")
    public void gerarToken() {
        response = given().contentType("application/json").body(credenciais()).post(url + "/Account/v1/GenerateToken");

        assertEquals("Success", response.jsonPath().getString("status"), response.asString());
        token = response.jsonPath().getString("token");

    }

    @Dado("o usuário está autenticado")
    public void validarAutenticado() {
        response = given().contentType("application/json").body(credenciais()).post(url + "/Account/v1/Authorized");
        assertEquals("true", response.getBody().asString());

    }

    @Quando("listo os livros disponíveis")
    public void listarLivros() {
        response = given().get(url + "/BookStore/v1/Books");

        assertEquals(200, response.getStatusCode());
        livros = response.jsonPath().getList("books.isbn");

    }

    @Quando("faço o aluguel dos livros através do isbn")
    public void alugarLivros() {
        assertTrue(livros != null && livros.size() >= 2, "Não existem pelo menos dois livros disponíveis.");

        List<String> itens = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            String isbn = livros.get(i);

            itens.add("{\"isbn\": \"" + isbn + "\"}");
            livrosAlugados.add(isbn);
        }

        String body = "{\"userId\": \"" + userId + "\", \"collectionOfIsbns\": [" + String.join(",", itens) + "]}";

        response = given().contentType("application/json").header("Authorization", "Bearer " + token).body(body).post(url + "/BookStore/v1/Books");

        assertEquals(201, response.getStatusCode(), "Erro ao alugar livros: " + response.asString());

    }

    @Então("os detalhes do aluguel são exibidos")
    public void validarAluguel() {
        response = given().contentType("application/json").header("Authorization", "Bearer " + token).get(url + "/Account/v1/User/" + userId);

        assertEquals(200, response.getStatusCode());
        List<String> livrosDoUsuario = response.jsonPath().getList("books.isbn");
        assertEquals(livrosAlugados.size(), livrosDoUsuario.size());
        assertTrue(livrosDoUsuario.containsAll(livrosAlugados));

        response.prettyPrint();

    }

    private String credenciais() {
        return "{\"userName\": \"" + usuario + "\", \"password\": \"" + senha + "\"}";
    }

}
