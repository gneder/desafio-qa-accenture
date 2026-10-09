# language: pt
  @front @janelas
  Funcionalidade: Abrir Janelas
  Cenário: Abrir uma nova janela
    Dado que acesso o site demoqa
    E clico na opção "Alerts, Frame & Windows"
    E clico no submenu "Browser Windows"
    Quando clico no botão New Window
    Então a nova janela deve mostrar "This is a sample page"
    E fecho a nova janela