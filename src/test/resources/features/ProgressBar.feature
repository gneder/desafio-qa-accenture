# language: pt
  @front @progressbar
  Funcionalidade: Progress Bar
    Cenário: Parar antes de 25% e resetar no final
      Dado que acesso o site demoqa
      E clico na opção "Widgets"
      E clico no submenu "Progress Bar"
      Quando inicio a barra e paro antes de 25%
      Então o valor da barra deve ser menor ou igual a 25
      Quando inicio a barra de novo até chegar em 100%
      E reseto a barra
      Então o valor da barra deve ser 0