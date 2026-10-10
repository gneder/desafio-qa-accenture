# language: pt
  @front @sortable
  Funcionalidade: Sortable
    Cenário: Colocar a lista em ordem crescente
      Dado que acesso o site demoqa
      E clico na opção "Interactions"
      E clico no submenu "Sortable"
      E inverto a ordem da lista
      Quando arrasto os itens para a ordem crescente
      Então a lista deve ficar na ordem One, Two, Three, Four, Five, Six