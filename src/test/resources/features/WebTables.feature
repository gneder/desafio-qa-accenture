# language: pt
  @front @webtables
  Funcionalidade: Web Tables
    Contexto:
      Dado que acesso o site demoqa
      E clico na opção "Elements"
      E clico no submenu "Web Tables"

    Cenário: Adicionar um novo registro na tabela
      Quando crio um novo registro
      E edito o registro criado
      E deleto o registro criado
      Então o registro não deve aparecer na tabela