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

    # bug ao excluir registros, ver o cenário @bug_encontrado
    Cenário: Criar e deletar 12 registros
      Quando crio 12 registros
      Então os 12 registros devem aparecer na tabela
      Quando deleto todos os registros criados
      Então nenhum registro criado deve aparecer na tabela

    @bug @bug_encontrado
    Cenário: Excluir um registro não deve afetar os demais
      Quando crio 3 registros
      E deleto apenas o primeiro registro criado
      Então os outros 2 registros devem continuar na tabela