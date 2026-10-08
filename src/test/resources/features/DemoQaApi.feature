# language: pt
  @api
  Funcionalidade: API da Bookstore
    Cenário: Criar usuário e fazer o aluguel de dois livros
      Dado que eu crio um usuário
      E gero o token de autenticação
      E o usuário está autenticado
      Quando listo os livros disponíveis
      E faço o aluguel dos livros através do isbn
        | isbn           |
        | 9781449325862  |
        | 9781449331818  |
      Então os detalhes do aluguel são exibidos