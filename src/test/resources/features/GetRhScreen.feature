#language: pt

@regressivo
Funcionalidade: Consulta dos dados por mês e ano pra tela de RH
  Como usuário da API
  Quero consultar os dados das emoções existentes
  Para que possa exibir os dados corretamente

  Cenário: Consulta dos dados bem-sucedido
    Dado que eu tenha os seguintes dados:
      | yearMonth | 2025-09 |
    Quando eu enviar a requisição para o endpoint "/emotions/2025-09" com o método GET
    Entao o status code da resposta deve ser 200
