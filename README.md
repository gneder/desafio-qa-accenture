# Desafio QA Automation Accenture

O frontend roda no [demoqa](https://demoqa.com) com Java, Selenium e Cucumber, e a parte de API (Bookstore) usa RestAssured.

## Pré-requisitos

Java 25 (versão do `pom.xml`), Maven e Chrome.

## Como rodar

No terminal, na raiz do projeto:

```bash
mvn clean install -DskipTests # compila

mvn test # roda todos os testes
mvn test -Dcucumber.filter.tags="@api" # roda apenas api
mvn test -Dcucumber.filter.tags="@webtables and not @bug_encontrado" # roda o cenário webtables e não roda o bug encontrado
mvn test -Dcucumber.filter.tags="@bug_encontrado" # falha intencional
```

Atenção com o `-Dcucumber.filter.tags`: ele não soma com o filtro do `junit-platform.properties`, ele substitui. Então se for filtrar por tag, coloca o `and not @bug_encontrado` junto, senão o cenário do bug entra na execução.

O relatório sai em `target/cucumber-reports.html`. Quando um teste de front falha, o print da tela vai junto.

## Estrutura

```
src/test/java
├── pages     # page objects
├── steps     # steps do cucumber e hooks
└── utils     # driver
src/test/resources
├── features  # cenários em português
└── arquivos  # arquivo usado no upload do formulário
```

Segui Page Object: cada página tem sua classe em `pages` e os steps só chamam as ações dela. O `Driver` é um singleton, abre um Chrome por cenário e o hook fecha no final.

## Cenários

- `@api` – cria usuário, gera token, lista os livros e aluga dois
- `@formulario` – Practice Form com dados aleatórios (datafaker), confere o popup
- `@janelas` – abre nova janela e valida o texto
- `@webtables` – cria, edita e exclui registro. Tem também o bônus dos 12 registros
- `@progressbar` – para antes de 25%, depois deixa chegar em 100% e reseta
- `@sortable` – ordena a lista com drag and drop

## Bug encontrado

No bônus dos 12 registros o teste sempre quebrava na exclusão. Primeiro achei que era a paginação (a tabela mostra 10 por página), corrigi isso e continuou falhando. Fui no print do relatório e a tabela estava só com os 3 registros originais. Os 12 tinham sumido depois do primeiro delete.

Reproduzi na mão: adicionei 3 registros e excluí um. Sumiram os 3.

**Esperado:** sair só o registro excluído.  
**Obtido:** todos os registros adicionados somem.

Pra não deixar o teste quebrando por causa do site, o `deletar` só clica se a linha ainda existir, e no final continuo validando que nenhum dos 12 ficou na tabela. O comportamento certo ficou documentado no cenário `@bug_encontrado`, que está fora da execução padrão.

## O que dá pra melhorar

Vi essas coisas mas preferi não mexer agora:

- seletores declarados no topo das pages, e não dentro dos métodos
- o `registroExiste` da Web Tables procura no `getPageSource()` inteiro, dava pra buscar direto na linha da tabela
- headless + CI no GitHub Actions
- outros navegadores
