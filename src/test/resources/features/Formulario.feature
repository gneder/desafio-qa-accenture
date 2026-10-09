# language: pt
  @front @formulario
  Funcionalidade: Practice Form
  Cenário: Preencher e enviar o formulario
    Dado que acesso o site demoqa
    E clico na opção "Forms"
    E clico no submenu "Practice Form"
    Quando preencho o formulario com dados aleatorios
    E envio o formulario
    Entao deve aparecer o poopup de confirmacao
    E fecho o popup